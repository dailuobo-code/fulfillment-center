package com.mallcai.fulfillment.dc.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.utils.CollectionUtils;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.dc.biz.service.bo.PeriodBuyConfig;
import com.mallcai.fulfillment.dc.biz.service.utils.BaseBeanUtil;
import com.mallcai.fulfillment.dc.common.enums.PeriodBuyStatus;
import com.mallcai.fulfillment.dc.mapper.dc.PeriodBuyRecordMapperExtend;
import com.mallcai.fulfillment.dc.mapper.dc.PeriodDeliveryMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.PeriodBuyRecord;
import com.mallcai.fulfillment.dc.valueobject.dc.PeriodDelivery;
import com.mallcai.open.api.model.gm.*;
import com.mallcai.open.api.service.gm.IGuangmingService;
import com.mallcai.trade.business.api.aftersales.msg.AfterSalesStatusChangingDTO;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PeriodBuyService {

    @Resource
    private IGuangmingService iGuangmingService;

    @Autowired
    private RedisIdFactory redisIdFactory;

    @ConfigValue(key = "/fulfillment-rule/fulfillment-rule/fulfillment.period.buy")
    private JSONObject periodBuyInfo;

    @Autowired
    PeriodBuyRecordMapperExtend periodBuyRecordMapperExtend;

    @Autowired
    PeriodDeliveryMapper periodDeliveryMapper;




    // 订购周期购产品
    public boolean orderPeriodBuy(SOAOrderDetailVO orderVO){
        CreateOrderInfo orderInfo=new CreateOrderInfo();
        orderInfo.setOrderId(orderVO.getOrderId());
        orderInfo.setCustId(String.valueOf(orderVO.getUserId()));


        JSONObject addressInfo = JSON.parseObject(orderVO.getRecipientsAddr());
        String address = String.format("%s|%s|%s|%s", addressInfo.getString("provinceName"), addressInfo.getString("cityName"), addressInfo.getString("countyName"), addressInfo.getString("address"));
        orderInfo.setAddress(address);

        orderInfo.setName(orderVO.getRecipients());
        orderInfo.setPhone1(orderVO.getRecipientsPhone());
        orderInfo.setPhone2("");
        orderInfo.setCityId(orderVO.getCityId());
        orderInfo.setRemark(orderVO.getMemo());
        String serialNo=String.format("%s%04d", DateUtils.format(new Date(),DateUtils.YEAR_MONTH_DATE_PATTEN),redisIdFactory.getIdByDay("OPBZQG"));
        orderInfo.setSerialNo(serialNo);

        //生成产品列表
        AtomicInteger index=new AtomicInteger(1);
        List<ProductInfo> products = orderVO.getOrderDetailLst().stream().map(p -> {
            ProductInfo product = new ProductInfo();
            product.setItem(index.getAndIncrement());
            if(p.getPrice()!=null && p.getMaxNum()!=null && p.getProductNum()!=null){
                product.setPayMoney(new BigDecimal(p.getPrice()*p.getProductNum()).divide(new BigDecimal(100)));   // todo  总价格
                BigDecimal prodPrice = product.getPayMoney().divide(new BigDecimal(p.getMaxNum() * p.getProductNum()), 2, BigDecimal.ROUND_HALF_DOWN);
                product.setPrice(prodPrice);
            }else{
                product.setPayMoney(BigDecimal.ZERO);
                product.setPrice(BigDecimal.ZERO);
            }
            product.setRuleId(RuleEnum.DAILY);
            product.setAmount(p.getProductNum());  //

            //获取配置
            String mechantStr = periodBuyInfo.getString(String.valueOf(p.getMerchantId()));
            PeriodBuyConfig mechantInfo = JSON.parseObject(mechantStr, PeriodBuyConfig.class);
            Date start = DateUtils.addDay(DateUtils.startDate(new Date()), mechantInfo.getPeroid());
            Date end = DateUtils.addDay(start, p.getMaxNum());
            product.setPdtId(mechantInfo.getProdMap().get(p.getCityProductId()+""));  // todo 光明产品ID  productCode 自己转换
            product.setStartDate(start);
            product.setEndDate(end);
            return product;
        }).collect(Collectors.toList());

        orderInfo.setProductInfo(products);

        //沉淀数据库
        saveBookRecord(orderInfo,orderVO);
        log.info("周期购订购信息沉淀数据库完成.orderId:{}",orderInfo.getOrderId());

        log.info("调用三方平台[光明鲜奶订购接口],入参:{}", JSON.toJSONString(orderInfo));
        PlainResult<Boolean> result =null;
        try {
            result = iGuangmingService.createOrder(orderInfo);
        }catch (Exception e){
            log.error("调用三方平台[光明鲜奶订购接口]错误,",e);
            result=PlainResult.fail("接口调用错误");
        }
        log.info("调用三方平台[光明鲜奶订购接口],返回:{}",JSON.toJSONString(result));


        //更新数据库状态
        updateRecodcreate(orderInfo.getOrderId(),result);
        log.info("更新数据库调用结果.orderId:{}",orderInfo.getOrderId());

        return result.isSuccess();
    }


    // 退订周期购产品
    public boolean CancelPeroidBuy(AfterSalesStatusChangingDTO order){

        String orderNo=order.getOrderId();
        PeriodBuyRecord record = periodBuyRecordMapperExtend.selectByOrderNo(orderNo);
        if(record ==null ){
            log.info("订单[{}]无法查询到周期购信息,直接返回!!",orderNo);
            return true;
        }

        List<ProductInfo> productInfos = JSON.parseArray(record.getProductInfo(), ProductInfo.class);
        if(CollectionUtils.isEmpty(productInfos)){
            log.error("没有查询到周期购[{}]订单的产品信息,无法退订!!",orderNo);
            return false;
        }
        ProductInfo product = productInfos.get(0);

        CancelOrderInfo orderInfo=new CancelOrderInfo();

        orderInfo.setOrderId(record.getOrderNo());
        orderInfo.setSerialNo("9"+record.getSerialNo());  // 光明要求 serialno 全部为数字
        orderInfo.setPdtId(product.getPdtId()); //
        orderInfo.setAmount(product.getAmount());
        orderInfo.setRuleId(product.getRuleId());
        orderInfo.setOldEndDate(product.getEndDate());

        String mechantStr = periodBuyInfo.getString(String.valueOf(record.getMerchantId()));
        PeriodBuyConfig mechantInfo = JSON.parseObject(mechantStr, PeriodBuyConfig.class);
        Date end = DateUtils.addDay(DateUtils.startDate(order.getOccurTime()), mechantInfo.getPeroid());
        orderInfo.setEndDate(end);
        orderInfo.setItem(1);
        orderInfo.setPayDate(record.getPayTime());
        orderInfo.setMoney(product.getPayMoney());
        orderInfo.setPrice(product.getPrice());

        log.info("调用三方平台[光明鲜奶退订接口],入参:{}", JSON.toJSONString(orderInfo));
        PlainResult<Boolean> result =null;
        try {
            result = iGuangmingService.cancelOrder(orderInfo);
        }catch (Exception e){
            log.error("调用三方平台[光明鲜奶退订接口]错误,",e);
            result=PlainResult.fail("接口调用错误");
        }
        log.info("调用三方平台[光明鲜奶退订接口],返回:{}",JSON.toJSONString(result));

        //沉淀数据库
        updateBookRecordcancel(order,orderInfo,result);
        log.info("周期购退订信息沉淀数据库完成.orderId:{}",orderInfo.getOrderId());

        return result.isSuccess();
    }


    private void saveBookRecord(CreateOrderInfo order,SOAOrderDetailVO detail){
        PeriodBuyRecord record=new PeriodBuyRecord();
        record.setOrderNo(order.getOrderId());
        record.setSerialNo(order.getSerialNo());
        record.setStatus(PeriodBuyStatus.BOOKING.getCode());
        record.setUserName(order.getName());
        record.setUserPhone1(order.getPhone1());
        record.setUserPhone2(order.getPhone2());
        record.setCityId(order.getCityId());
        record.setAddress(order.getAddress());
        record.setProductInfo(JSON.toJSONString(order.getProductInfo()));
        record.setPayTime(DateUtils.parseDate(detail.getPayTime(),DateUtils.DEFAULT_DATE_TIME_PATTERN));
        if(!CollectionUtils.isEmpty(detail.getOrderDetailLst())) {
            record.setMerchantId(detail.getOrderDetailLst().get(0).getMerchantId());
        }
        if(!CollectionUtils.isEmpty(order.getProductInfo())){
            ProductInfo product = order.getProductInfo().get(0);
            record.setExternProdId(product.getPdtId());
            record.setStartTime(product.getStartDate());
            record.setEndTime(product.getEndDate());
            record.setProdAmount(product.getAmount());
            record.setBookRule(product.getRuleId().name());
        }
        periodBuyRecordMapperExtend.insertSelective(record);
    }

    private void updateRecodcreate(String orderId,PlainResult result){
        PeriodBuyRecord record=periodBuyRecordMapperExtend.selectByOrderNo(orderId);
        if(result.isSuccess()) {
            record.setStatus(PeriodBuyStatus.BOOKING_SUCC.getCode());
        }else{
            record.setStatus(PeriodBuyStatus.BOOKING_FAIL.getCode());
            record.setStatusMsg(result.getError());
        }
        record.setUpdateTime(new Date());

        periodBuyRecordMapperExtend.updateByPrimaryKeySelective(record);
    }


    private void updateBookRecordcancel(AfterSalesStatusChangingDTO dto,CancelOrderInfo order,PlainResult result){
        PeriodBuyRecord record=periodBuyRecordMapperExtend.selectByOrderNo(order.getOrderId());
        record.setCancelTime(dto.getOccurTime());
        record.setRealEndDate(order.getEndDate());
        if(result.isSuccess()) {
            record.setStatus(PeriodBuyStatus.CANCEL_SUCC.getCode());
        }else{
            record.setStatus(PeriodBuyStatus.CANCEL_FAIL.getCode());
            record.setStatusMsg(result.getError());
        }
        record.setUpdateTime(new Date());


        periodBuyRecordMapperExtend.updateByPrimaryKeySelective(record);
    }


    public void deliveryPeriodBuy(DeliveryOrderInfo info){
        PeriodDelivery delivery =BaseBeanUtil.convertObject(info,PeriodDelivery.class);
        delivery.setOrderNo(info.getOrderId());
        delivery.setProdAmount(info.getAmount());
        periodDeliveryMapper.insertSelective(delivery);
        log.info("沉淀周期购配送信息成功!,info:{}",JSON.toJSONString(delivery));
    }


}
