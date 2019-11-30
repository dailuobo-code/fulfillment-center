package com.mallcai.fulfillment.dc.biz.service.inner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dc.biz.service.bo.OrderControllerBo;
import com.mallcai.fulfillment.dc.biz.service.build.OrderItemInfoBuild;
import com.mallcai.fulfillment.dc.biz.service.build.RealTimeOrderInfoMsgBuild;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoSourceEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.TransOrderExtEnum;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.PeriodBuyService;
import com.mallcai.fulfillment.dc.biz.service.mq.MqEventMsg;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.constants.RpcErrorConstants;
import com.mallcai.fulfillment.dc.common.utils.BeanCopierUtils;
import com.mallcai.fulfillment.dc.dao.dc.OrderItemInfoDAO;
import com.mallcai.fulfillment.dc.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.dc.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.dc.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.dc.mapper.dc.OrderInfoMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderInfoDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import com.mallcai.fulfillment.pe.api.service.operate.OrderAcceptService;
import com.mallcai.fulfillment.pe.api.service.operate.OrderService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderDetailDTO;
import com.mallcai.service.order.constants.DeliveryModeEnum;
import com.mallcai.service.order.constants.OrderExtraKeys;
import com.mallcai.service.order.constants.OrderItemProductInfoKeys;
import com.mallcai.service.order.constants.OrderTypeEnum;
import com.mallcai.service.order.vo.OrderStatusEnum;
import com.mallcai.trade.common.model.SOAOrderDetail;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 接收订单
 *
 * @author bh.zhong
 * @date 2019/8/17 4:30 PM
 */
@Service
@Slf4j
public class ReceiveOrderInnerService {
    @Resource
    private OrderItemInfoDAO orderItemInfoDAO;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    @Qualifier("fdcTransactionTemplate")
    private TransactionTemplate fdcTransactionTemplate;
    @Resource
    private OrderAcceptService orderAcceptService;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsGrayRouterService goodsGrayRouterService;

    @Autowired
    private PeriodBuyService periodBuyService;
    @Resource
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;
    @Resource
    private MqEventMsg mqEventMsg;

    /**
     * 交易订单中冻品标识
     */
    private final String IS_FROZEN = "1";

    public Boolean receiveOrder(SOAOrderDetailVO orderDetailVO) {

        OrderControllerBo orderControllerBo = buildOrderControllerBo(orderDetailVO);

        //周期购订购业务
        if(orderControllerBo.isPeriodBuy()){
            //周期购不沉淀order_info 数据
            try {
                return periodBuyService.orderPeriodBuy(orderDetailVO);
            }catch (Exception e){
                log.error("周期购订单[{}] 处理失败.",orderDetailVO.getOrderId(),e);
            }
            return true;
        }
        //过滤到家商品
        if (DeliveryModeEnum.HOME.getValue() == orderDetailVO.getDeliveryMode()) {
            return true;
        }
        //过滤非标品非冻品订单
        if(!orderControllerBo.isGoodsGrayOpen()){
            if (!orderControllerBo.isStandard() && !orderControllerBo.isFrozen()) {
                return true;
            }
        }

        //step1:如果是支付完成
        if (OrderStatusEnum.PAID.getValue().equals(orderDetailVO.getStatus())) {
            if (executeOrder(orderDetailVO,orderControllerBo)) {
                return sendPe(orderDetailVO);
            }
        } else
            //如果是已取消
            if (OrderStatusEnum.CANCEL.getValue().equals(orderDetailVO.getStatus()) || OrderStatusEnum.REFUNDED.getValue().equals(orderDetailVO.getStatus())) {
                OrderInfoDO orderInfoDO = orderInfoMapper.selectByOrderNo(orderDetailVO.getOrderId());
                if (orderInfoDO == null) {
                    //插入取消信息成功
                    return executeOrder(orderDetailVO,orderControllerBo);
                } else {
                    //已经取消过了
                    if (OrderStatusEnum.CANCEL.getValue().equals(orderInfoDO.getStatus()) || OrderStatusEnum.REFUNDED.getValue().equals(orderInfoDO.getStatus())) {
                        //可能调用生产rpc异常，依然调用生产取消
                        return !rpcResultCheckNeedRetry(orderService.cancelOrder(orderInfoDO.getOrderNo()));
                    } else {
                        orderInfoMapper.updateOrderStatusCancle(orderDetailVO.getStatus(), orderInfoDO.getOrderNo());
                        return !rpcResultCheckNeedRetry(orderService.cancelOrder(orderInfoDO.getOrderNo()));
                    }
                }
            } else {
                return true;
            }
        return true;
    }

    private OrderControllerBo buildOrderControllerBo(SOAOrderDetailVO orderDetailVO) {
        JSONObject extraJson = JSON.parseObject(orderDetailVO.getExtras());
        String isFrozen = extraJson.getString(OrderExtraKeys.IS_FROZEN_ORDER);
        OrderControllerBo orderControllerBo = new OrderControllerBo();
        orderControllerBo.setBooking(OrderTypeEnum.PRESELL.getValue() == orderDetailVO.getOrderType());
        orderControllerBo.setGoodsGrayOpen(goodsGrayRouterService.isNeedRoute(orderDetailVO.getCityId()));
        orderControllerBo.setFrozen(StringUtils.equals(isFrozen,IS_FROZEN));
        orderControllerBo.setPeriodBuy(StringUtils.equals(extraJson.getString("is_period_buy"),"1"));
        orderControllerBo.setStandard(OrderTypeEnum.STANDARD_PRODUCT.getValue() == orderDetailVO.getOrderType());
        return orderControllerBo;

    }

    public Boolean executeOrder(SOAOrderDetailVO orderDetailVO,OrderControllerBo orderControllerBo) {
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        List<OrderItemInfoDO> orderItemInfoDOList = new ArrayList<>();
        orderInfoDO.setOrderNo(orderDetailVO.getOrderId());
        orderInfoDO.setCityId(orderDetailVO.getCityId());
        orderInfoDO.setStoreId(orderDetailVO.getStoreId());
        orderInfoDO.setPickupTime(orderDetailVO.getPickupTime());
        orderInfoDO.setUserId(orderDetailVO.getUserId());
        orderInfoDO.setStatus(orderDetailVO.getStatus());
        orderInfoDO.setOrderType(orderDetailVO.getOrderType());
        JSONObject transOrderExt = new JSONObject();
        transOrderExt.put(TransOrderExtEnum.USER_NAME.getKey(), orderDetailVO.getRecipients());
        transOrderExt.put(TransOrderExtEnum.TEL.getKey(), orderDetailVO.getRecipientsPhone());
        orderInfoDO.setOrderInfoExt(transOrderExt.toJSONString());
        List<SOAOrderDetail> soaOrderDetails = new ArrayList<>();
        for (SOAOrderDetail soaOrderDetail : orderDetailVO.getOrderDetailLst()) {
            //过滤购物袋
            if (soaOrderDetail.getProductInfoAsMap().get(OrderItemProductInfoKeys.IS_SHOP_BAG) != null) {
                continue;
            }
            if (soaOrderDetail.getMerchantId() != null && soaOrderDetail.getMerchantId() > 0 ) {
                continue;
            }
            OrderItemInfoDO orderItemInfoDO = new OrderItemInfoDO();
            orderItemInfoDO.setCityId(soaOrderDetail.getCityId());
            //目前只存生鲜仓id
            String freshWarehouseId = soaOrderDetail.getProductInfoAsMap().get(OrderItemProductInfoKeys.FRESH_WID);
            orderItemInfoDO.setWarehouseId(StringUtils.isNotEmpty(freshWarehouseId) ? Integer.valueOf(freshWarehouseId) : 0);
            orderItemInfoDO.setOrderSource(OrderItemInfoSourceEnum.CUSTOMER.getValue());
            orderItemInfoDO.setCityProductId(soaOrderDetail.getCityProductId());
            orderItemInfoDO.setOrderNo(soaOrderDetail.getOrderId());
            orderItemInfoDO.setUserId(soaOrderDetail.getUserId());
            orderItemInfoDO.setStoreId(soaOrderDetail.getStoreId());
            orderItemInfoDO.setSpecId(soaOrderDetail.getSpecId());
            orderItemInfoDO.setProductNum(new BigDecimal(soaOrderDetail.getProductNum().toString()));
            orderItemInfoDO.setPickupTime(orderDetailVO.getPickupTime());
            orderItemInfoDO.setOrderType(orderDetailVO.getOrderType().byteValue());
            orderItemInfoDO.setIsDeleted(Constants.NOT_DELETED);
            orderItemInfoDO.setProductNo(soaOrderDetail.getProductInfoAsMap().get(OrderItemProductInfoKeys.PRODUCT_NO));
            orderItemInfoDO.setVersion(0);
            orderItemInfoDOList.add(orderItemInfoDO);
            soaOrderDetails.add(soaOrderDetail);
        }
        orderDetailVO.setOrderDetailLst(soaOrderDetails);
        //1.过滤三方订单和购物袋
        if (CollectionUtils.isEmpty(orderItemInfoDOList)) {
            return false;
        }
        //货品开关内,非标品，发送货品消息给erp,非预售不走pe、dc
        if (orderControllerBo.isGoodsGrayOpen() && !orderControllerBo.isStandard()) {
            OrderItemInfoBuild.buildOrderItemInfoExt(getGoodsList(orderItemInfoDOList),orderItemInfoDOList);
            mqEventMsg.sendRealTimeOrderInfoMsg(RealTimeOrderInfoMsgBuild.buildRealTimeOrderInfoMsg(orderItemInfoDOList,orderInfoDO));
            if (!orderControllerBo.isBooking() && !orderControllerBo.isFrozen()) {
                return false;
            }
        }


        boolean flag = fdcTransactionTemplate.execute(status -> {
            try {
                orderInfoMapper.insertOrderInfo(orderInfoDO);
                orderItemInfoDAO.batchItemOrderInfo(orderItemInfoDOList);
            } catch (DuplicateKeyException e) {
                return true;
            }
            return true;
        });
        return flag;
    }


    /**
     * 发送数据给履约生产
     *
     * @param orderDetailVO
     * @return
     */
    private boolean sendPe(SOAOrderDetailVO orderDetailVO) {
        OrderAcceptRequestDTO requestDTO = new OrderAcceptRequestDTO();
        List<OrderDetailDTO> orderDetailLst = new ArrayList<>();
        for (SOAOrderDetail soaOrderDetail : orderDetailVO.getOrderDetailLst()) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            BeanCopierUtils.copyProperties(soaOrderDetail, orderDetailDTO);
            orderDetailLst.add(orderDetailDTO);
        }
        BeanCopierUtils.copyProperties(orderDetailVO, requestDTO);
        requestDTO.setOrderDetailLst(orderDetailLst);
        try {
            return !rpcResultCheckNeedRetry(orderAcceptService.acceptOrder(requestDTO));
        } catch (Exception e) {
            log.error("ReceiveOrderInnerService.receiveOrder orderAcceptService.acceptOrder rpc exception,requestDTO:{}", requestDTO, e);
            return false;
        }
    }

    /**
     * rpc是否需要重试 true 需要 false 不需要
     * @param result
     * @return
     */
    private boolean rpcResultCheckNeedRetry(PlainResult result) {
        if (!result.isSuccess() && RpcErrorConstants.FPE_SYSTEM_ERROR.equals(result.getCode())) {
            return true;
        } else {
            return false;
        }
    }

    public List<ItemGoodsInfoBO> getGoodsList(List<OrderItemInfoDO> orderItemInfoDOS) {
        List<ItemGoodsInfoBO> result = new ArrayList<>();
        if (!goodsGrayRouterService.isNeedRoute(orderItemInfoDOS.get(0).getCityId())) {
            return result;
        }
        List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
        orderItemInfoDOS.forEach(orderItemInfoDO -> {
            QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
            queryGoodsRelBO.setCityId(orderItemInfoDO.getCityId());
            queryGoodsRelBO.setCityProductId(orderItemInfoDO.getCityProductId());
            queryGoodsRelBOList.add(queryGoodsRelBO);
        });
        if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
            result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
        }
        return result;
    }


}
