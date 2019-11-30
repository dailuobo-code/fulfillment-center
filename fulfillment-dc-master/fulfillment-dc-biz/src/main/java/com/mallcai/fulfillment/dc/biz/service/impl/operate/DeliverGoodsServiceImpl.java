package com.mallcai.fulfillment.dc.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.util.UuidUtils;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.dc.api.service.dc.operate.DeliverGoodsService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DeliveryGoodsDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DeliveryGoodsItemRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoSourceEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.dc.biz.service.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dc.biz.service.verify.DeliverGoodsServiceParamChecker;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.utils.BeanCopierUtils;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.dao.dc.OrderItemInfoDAO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import com.mallcai.fulfillment.pe.api.service.operate.PeOrderOperateService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderItemInfoDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderItemInfoListDTO;
import com.mallcai.scm.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-10 20:09
 */
@Service("deliverGoodsService")
@Slf4j
public class DeliverGoodsServiceImpl implements DeliverGoodsService {

  @Resource
  private DeliverGoodsServiceParamChecker deliverGoodsServiceParamChecker;
  @Autowired
  @Qualifier("fdcTransactionTemplate")
  private TransactionTemplate fdcTransactionTemplate;
  @Resource
  private OrderItemInfoDAO orderItemInfoDAO;
  @Resource
  private PeOrderOperateService peOrderOperateService;
  @Resource
  private GoodsGrayRouterService goodsGrayRouterService;

  @Autowired
  RedisIdFactory redisIdFactory;

  @Override
  public PlainResult<Boolean> importGoodsCsv(DeliveryGoodsDTO deliveryGoodsDTO) {
    log.info("即将开始处理来自MGR的数据导入");
    //参数校验
    PlainResult<?> result = deliverGoodsServiceParamChecker.checkDeliveryGoodsItem(deliveryGoodsDTO);
    if(!result.isSuccess()){
      return PlainResult.fail(result.getMessage());
    }
    List<OrderItemInfoDO> orderItemInfoDOList=new ArrayList<>();
    //进行格式转换
    convertGoodsItemDTO(deliveryGoodsDTO.getDeliveryGoodsItemRequestDTOList(),orderItemInfoDOList);
    // 插入数据
    if(!executeInsertOrderItemInfoList(orderItemInfoDOList)){
      log.error("插入运营导入数据失败,orderItemInfoDOList:{}", JSON.toJSONString(orderItemInfoDOList));
      return PlainResult.fail("插入运营导入数据失败!");
    }
    //通知PE
    if(!sendPe(orderItemInfoDOList)){
      return PlainResult.fail("调用PE系统接口插入来自MGR的数据失败!");
    }
    return PlainResultBuilder.success(true);
  }

  public boolean executeInsertOrderItemInfoList(List<OrderItemInfoDO> orderItemInfoDoList) {
    boolean flag = true;
    for (OrderItemInfoDO orderItemInfoDO : orderItemInfoDoList) {
      if (checkIfNotExistOrderItemInfoDO(orderItemInfoDO)) {
        try {
          orderItemInfoDAO.insert(orderItemInfoDO);
        } catch (Exception e) {
          log.error("插入MGR数据时发生错误,orderItemInfoDO:{}", JSON.toJSONString(orderItemInfoDO), e);
          flag = false;
        }
      }
    }
    return flag;
  }

  public boolean checkIfNotExistOrderItemInfoDO(OrderItemInfoDO orderItemInfoDO) {
    OrderItemInfoCriteria orderItemInfoCriteria = new OrderItemInfoCriteria();
    orderItemInfoCriteria.createCriteria().andCityIdEqualTo(orderItemInfoDO.getCityId())
        .andWarehouseIdEqualTo(orderItemInfoDO.getWarehouseId()).
        andStoreIdEqualTo(orderItemInfoDO.getStoreId())
        .andCityProductIdEqualTo(orderItemInfoDO.getCityProductId())
        .andCreateTimeGreaterThanOrEqualTo(DateUtil.dayStart(new Date())).
        andCreateTimeLessThan(DateUtil.addDays(DateUtil.dayStart(new Date()), 1)).
        andOrderTypeEqualTo(orderItemInfoDO.getOrderType())
        .andOrderSourceEqualTo(orderItemInfoDO.getOrderSource()).andIsDeletedEqualTo(
        Constants.NOT_DELETED);
    int count = orderItemInfoDAO.countSpecialByExample(orderItemInfoCriteria);
    if (count == 0) {
      return true;
    }
    return false;
  }

  @Override
  public PlainResult<Boolean> reInvokePeImportMgrService(String importTime) {
    String pickupTimeStr="";
    Date pickupTime=null;
    try {
      pickupTime=DateUtil.parseDate(importTime);
      pickupTime=DateUtil.addDays(pickupTime,1);
      pickupTimeStr= DateUtil.formatDate(pickupTime);
    }catch (Exception e){
      log.error("时间转换失败,importTime:{}",importTime);
      PlainResult.fail("时间转换失败");
    }
    List<OrderItemInfoDO> orderItemInfoDOList=getMgrOrderItemInfoListByExample(pickupTimeStr);
    //通知PE
    if(!sendPe(orderItemInfoDOList)){
      return PlainResult.fail("调用PE系统接口插入来自MGR的数据失败!");
    }
    return PlainResultBuilder.success(true);
  }
  public List<OrderItemInfoDO> getMgrOrderItemInfoListByExample(String pickupTimeStr){
    OrderItemInfoCriteria orderItemInfoCriteria=new OrderItemInfoCriteria();
    orderItemInfoCriteria.createCriteria().andOrderSourceEqualTo(OrderItemInfoSourceEnum.MGR.getValue()).andPickupTimeEqualTo(pickupTimeStr).andIsDeletedEqualTo(Constants.NOT_DELETED);
    return orderItemInfoDAO.selectOrderItemInfoListByExample(orderItemInfoCriteria);
  }


  private boolean sendPe(List<OrderItemInfoDO> orderItemInfoDOList) {
    OrderItemInfoListDTO orderItemInfoListDTO=new OrderItemInfoListDTO();
    assembleOrderItemInfoListDTO(orderItemInfoListDTO,orderItemInfoDOList);
    try {
      PlainResult plainResult=peOrderOperateService.acceptOrderItem(orderItemInfoListDTO);
      if(!plainResult.isSuccess()){
        log.error("调用PE系统接口插入来自MGR的数据失败,orderItemInfoListDTO:{}",JSON.toJSONString(orderItemInfoListDTO));
        return false;
      }
    }catch (Exception e){
      log.error("调用PE系统插入MGR数据接口的网络调用超时，需要人工介入，导入日期为:{}",e);
      return false;
    }
    return true;
  }
  public void assembleOrderItemInfoListDTO(OrderItemInfoListDTO orderItemInfoListDTO,List<OrderItemInfoDO> orderItemInfoDOList){
    List<OrderItemInfoDTO> orderItemInfoDTOList=new ArrayList<>();
    for(OrderItemInfoDO orderItemInfoDO:orderItemInfoDOList){
      OrderItemInfoDTO orderItemInfoDTO=new OrderItemInfoDTO();
      BeanCopierUtils.copyProperties(orderItemInfoDO, orderItemInfoDTO);
      orderItemInfoDTOList.add(orderItemInfoDTO);
    }
    orderItemInfoListDTO.setOrderItemInfoDOList(orderItemInfoDTOList);
  }

  public void convertGoodsItemDTO(List<DeliveryGoodsItemRequestDTO> deliveryGoodsItemRequestDTOList,List<OrderItemInfoDO> orderItemInfoDOList){
    for(DeliveryGoodsItemRequestDTO deliveryGoodsItemRequestDTO:deliveryGoodsItemRequestDTOList){
      OrderItemInfoDO orderItemInfoDO=new OrderItemInfoDO();
      orderItemInfoDO.setOrderNo(Constants.MGR_PREFIX+ DateUtils.dateFormat(new Date(),"yyyyMMdd")+String.format("%018d",redisIdFactory.getIdByDay(Constants.MGR_PREFIX)));
      orderItemInfoDO.setOrderType(OrderItemInfoTypeEnum.FRESH.getValue());
      orderItemInfoDO.setOrderSource(OrderItemInfoSourceEnum.MGR.getValue());
      orderItemInfoDO.setCityProductId(deliveryGoodsItemRequestDTO.getCityProductId());
      orderItemInfoDO.setSpecId(0);
      orderItemInfoDO.setProductNum(new BigDecimal(deliveryGoodsItemRequestDTO.getProductNum().toString()));
      orderItemInfoDO.setStoreId(deliveryGoodsItemRequestDTO.getStoreId());
      orderItemInfoDO.setUserId(0);
      Date pickupTime= DateUtil.addDays(DateUtil.parseDate(deliveryGoodsItemRequestDTO.getImportTime()),1);
      orderItemInfoDO.setPickupTime(DateUtils.dateFormat(pickupTime));
      orderItemInfoDO.setCityId(deliveryGoodsItemRequestDTO.getCityId());
      orderItemInfoDO.setWarehouseId(deliveryGoodsItemRequestDTO.getWarehouseId());
      orderItemInfoDO.setProductNo(deliveryGoodsItemRequestDTO.getProductNo());
      orderItemInfoDO.setItemExt("");
      orderItemInfoDO.setVersion(0);
      orderItemInfoDO.setIsDeleted(Constants.NOT_DELETED);
      orderItemInfoDO.setCreateTime(new Date());
      orderItemInfoDO.setUpdateTime(new Date());
      orderItemInfoDOList.add(orderItemInfoDO);
    }
  }
}
