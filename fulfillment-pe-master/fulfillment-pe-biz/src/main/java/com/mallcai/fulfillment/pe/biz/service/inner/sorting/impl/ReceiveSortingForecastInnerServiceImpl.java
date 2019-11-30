package com.mallcai.fulfillment.pe.biz.service.inner.sorting.impl;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoDTO;
import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoListMsg;
import com.mallcai.fulfillment.pe.biz.service.enums.PeOrderSourceEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.RedisService;
import com.mallcai.fulfillment.pe.biz.service.inner.sorting.ReceiveSortingForecastInnerService;
import com.mallcai.fulfillment.pe.biz.service.mq.AggregateOrderMessage;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.AggregateOrderMessageProducer;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseDAO;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 21:57
 */
@Slf4j
@Service
public class ReceiveSortingForecastInnerServiceImpl implements ReceiveSortingForecastInnerService {

  @Autowired
  @Qualifier("fpeTransactionTemplate")
  private TransactionTemplate fpeTransactionTemplate;
  @Resource
  private PurchaseDAO purchaseDAO;
  @Resource
  private OrderDao orderDao;
  @Autowired
  private OrderInnerService orderInnerService;
  @Resource
  private IcProductGoodsServiceFacade icProductGoodsServiceFacade;
  @Resource
  private RedisService redisService;

  @Autowired
  private AggregateOrderMessageProducer aggregateOrderMessageProducer;

  /**
   * redis失效时间 默认一天 86400秒
   */
  private final Integer REDIS_EXPIRE_TIME = 86400;
  /**
   * 第一次插入
   */
  private final Integer IS_FIRST_TIME = 0;

  @Override
  public boolean receiveMsg(OrderItemInfoListMsg orderItemInfoListMsg) {
    List<OrderItemInfoDTO> orderItemInfoDTOList = orderItemInfoListMsg.getOrderItemInfoDTOList();
    if (CollectionUtils.isEmpty(orderItemInfoDTOList)) {
      log.warn("预测数量为空，无法进行PE层数据插入:{}", JSON.toJSONString(orderItemInfoDTOList));
      return true;
    }
    List<ItemGoodsInfoBO> itemGoodsInfoBOList = getGoodsList(orderItemInfoDTOList);
    int modifyCount = 0;
    StringBuffer modifyCountBuffer = new StringBuffer();
    List<OrderDO> peOrderList = convertOrderItemInfoDTO(orderItemInfoDTOList, itemGoodsInfoBOList);
    //执行插入或者更新
    boolean result = executeSavePeOrderList(peOrderList, modifyCountBuffer,itemGoodsInfoBOList);
    modifyCount = Integer.parseInt(modifyCountBuffer.toString());
    if (!result) {
      log.error("批量插入数据失败!orderItemInfoDTOList:{}", JSON.toJSONString(orderItemInfoDTOList));
      return false;
    } else {
      //查询当前PE库该仓的预测条数是否已经等于预测总数
      //本消息内的数量
      redisService
          .incrBy(orderItemInfoListMsg.getRedisKey() + "_" + orderItemInfoListMsg.getVersion(),
              modifyCount, REDIS_EXPIRE_TIME);
      String countValue = redisService
          .get(orderItemInfoListMsg.getRedisKey() + "_" + orderItemInfoListMsg.getVersion());
      int count = (countValue == null ? 0 : Integer.valueOf(countValue));
      if (count == orderItemInfoListMsg.getTotalCount()) {
        //插入完毕后检查，本库该仓数据已经和大数据预测总量一致，插入完成！
        //更新redis
        redisService.set(orderItemInfoListMsg.getRedisKey(),
            String.valueOf(orderItemInfoListMsg.getVersion()), REDIS_EXPIRE_TIME);
        // 调用实时集单推单接口
        sendAggregateOrderMsg(GroupValueEnum.FRESH_PRODUCT, orderItemInfoListMsg.getCityId(),
            orderItemInfoListMsg.getWarehouseId());
      }
      return true;
    }
  }

  public boolean executeSavePeOrderList(List<OrderDO> orderDOList, StringBuffer modifyCount,List<ItemGoodsInfoBO> itemGoodsInfoBOList) {
    boolean flag = true;
    int count = 0;
    for (OrderDO orderDO : orderDOList) {
      //非生鲜的来源于大数据的数据也插入，但是插入时就是被删除的状态，用于留存记录
      if(!checkIfIsFresh(orderDO,itemGoodsInfoBOList)){
        if (checkNotExistNotFreshOrderDo(orderDO)) {
          try {
            orderDO.setStatus(OrderStatusEnum.DELETE.getStatus());
            orderDao.insert(orderDO);
            count++;
          } catch (Exception e) {
            log.error("插入分拣预测数据失败,orderDO:{}", JSON.toJSONString(orderDO), e);
            flag = false;
          }
        }
        continue;
      }
      if (checkNotExistOrderDo(orderDO)) {
        try {
          orderDao.insert(orderDO);
          count++;
        } catch (Exception e) {
          log.error("插入分拣预测数据失败,orderDO:{}", JSON.toJSONString(orderDO), e);
          flag = false;
        }
      }
    }
    modifyCount.append(count);
    return flag;
  }


  public boolean checkNotExistNotFreshOrderDo(OrderDO orderDO) {
    OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
    orderDOCriteria.createCriteria().andCityIdEqualTo(orderDO.getCityId())
        .andWarehouseIdEqualTo(orderDO.getWarehouseId()).
        andStoreIdEqualTo(orderDO.getStoreId()).andCityProductIdEqualTo(orderDO.getCityProductId()).
        andExpectPushTimeGreaterThanOrEqualTo(orderDO.getExpectPushTime())
        .andExpectPushTimeLessThan(DateUtil.addDays(orderDO.getExpectPushTime(), 1))
        .andStatusEqualTo(OrderStatusEnum.DELETE.getStatus())
        .andOrderSourceEqualTo(PeOrderSourceEnum.BIG.getValue())
        .andVersionEqualTo(orderDO.getVersion());
    int count = orderDao.countSpecialByExample(orderDOCriteria);
    if (count == 0) {
      return true;
    }
    return false;
  }

  public boolean checkNotExistOrderDo(OrderDO orderDO) {
    OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
    orderDOCriteria.createCriteria().andCityIdEqualTo(orderDO.getCityId())
        .andWarehouseIdEqualTo(orderDO.getWarehouseId()).
        andStoreIdEqualTo(orderDO.getStoreId()).andCityProductIdEqualTo(orderDO.getCityProductId()).
        andExpectPushTimeGreaterThanOrEqualTo(orderDO.getExpectPushTime())
        .andExpectPushTimeLessThan(DateUtil.addDays(orderDO.getExpectPushTime(), 1))
        .andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus())
        .andOrderSourceEqualTo(PeOrderSourceEnum.BIG.getValue())
        .andVersionEqualTo(orderDO.getVersion());
    int count = orderDao.countSpecialByExample(orderDOCriteria);
    if (count == 0) {
      return true;
    }
    return false;
  }


  public List<OrderDO> convertOrderItemInfoDTO(List<OrderItemInfoDTO> orderItemInfoDTOList,
      List<ItemGoodsInfoBO> itemGoodsInfoBoList) {
    List<OrderDO> result = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(orderItemInfoDTOList)) {
      for (OrderItemInfoDTO orderItemInfoDTO : orderItemInfoDTOList) {
        OrderDO orderDO = new OrderDO();
        orderDO.setTransOrderId(orderItemInfoDTO.getOrderNo());
        orderDO.setStatus(OrderStatusEnum.INIT.getStatus());
        orderDO.setOrderType(orderItemInfoDTO.getOrderType().intValue());
        orderDO.setOrderSource(PeOrderSourceEnum.BIG.getValue());
        orderDO.setCityProductId(orderItemInfoDTO.getCityProductId());
        orderDO.setStoreId(orderItemInfoDTO.getStoreId());
        orderDO.setUserId(0);
        orderDO.setCityId(orderItemInfoDTO.getCityId());
        orderDO.setProductNo(orderItemInfoDTO.getProductNo());
        orderDO.setGroupType(GroupTypeEnum.CATEGORY.getGroupType());
        orderDO.setGroupValue(GroupValueEnum.FRESH_PRODUCT.getGroupValue());
        orderDO.setWarehouseId(orderItemInfoDTO.getWarehouseId());
        orderDO.setVersion(orderItemInfoDTO.getVersion());
        // 商品信息扩展字段
        orderInnerService
            .buildSortingOrderCommodityInfo(orderDO, itemGoodsInfoBoList, orderItemInfoDTO);
        orderDO.setExpectPushTime(DateUtil.dayStart(orderItemInfoDTO.getCreateTime()));
        //数据库字段对应的 timestem(0)  会做四舍五入存入数据库. 修改为应为直接截取毫秒数,
        Date now = new Date(new Date().getTime() / 1000 * 1000);
        orderDO.setCreateTime(now);
        orderDO.setUpdateTime(now);
        Date pickupTime = DateUtil.parseDate(orderItemInfoDTO.getPickupTime());
        orderDO.setPickupTime(pickupTime);
        result.add(orderDO);
      }
    }
    return result;
  }

  public boolean checkIfIsFresh(OrderDO order,List<ItemGoodsInfoBO> itemGoodsInfoBoList){
    Integer categoryType = 2;
    Integer attributeType = null;
    if(CollectionUtils.isNotEmpty(itemGoodsInfoBoList)){
      for (ItemGoodsInfoBO itemGoodsInfoBO : itemGoodsInfoBoList) {
        if (order.getCityId().equals(itemGoodsInfoBO.getCityId()) && order.getCityProductId().equals(itemGoodsInfoBO.getCityProductId())) {
          categoryType = itemGoodsInfoBO.getCategoryType();
          attributeType = itemGoodsInfoBO.getAttributeType();
        }
      }
      GroupValueEnum groupValueEnum = GroupValueEnum.fromCategoryTypeAndAttributeType(categoryType, attributeType);
      if(null!=groupValueEnum&&StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.FRESH_PRODUCT.getGroupValue())) {
        return true;
      }else{
        log.warn("生鲜分拣预测数据中包含非生鲜商品,本条数据将不会存入数据库,order:{}",JSON.toJSONString(order));
      }
    }else{
      log.error("货品中心列表为空，无法进行是否生鲜判断！");
      //找不到货品信息时先存进来，后面推单查不到货品还会报错的
      return true;
    }
    return false;
  }

  /**
   * 发送集单消息
   *
   * @param wareHouseId
   */
  private void sendAggregateOrderMsg(GroupValueEnum groupValueEnum, Integer cityId,
      Integer wareHouseId) {

    AggregateOrderMessage aggregateOrderMessage = new AggregateOrderMessage(
        groupValueEnum.getGroupValue(), cityId, wareHouseId);

    aggregateOrderMessageProducer.sendAggregateOrderMessage(aggregateOrderMessage);
  }

  public List<ItemGoodsInfoBO> getGoodsList(List<OrderItemInfoDTO> orderItemInfoDTOList) {
    List<ItemGoodsInfoBO> result = new ArrayList<>();
    List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
    for (OrderItemInfoDTO orderItemInfoDTO : orderItemInfoDTOList) {
      if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
        boolean flag = false;
        for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
          if (queryGoodsRelBO.getCityId().equals(orderItemInfoDTO.getCityId()) && queryGoodsRelBO
              .getCityProductId().equals(orderItemInfoDTO.getCityProductId())) {
            flag = true;
          }
        }
        if (!flag) {
          QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
          queryGoodsRelBO.setCityId(orderItemInfoDTO.getCityId());
          queryGoodsRelBO.setCityProductId(orderItemInfoDTO.getCityProductId());
          queryGoodsRelBOList.add(queryGoodsRelBO);
        }
      } else {
        QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
        queryGoodsRelBO.setCityId(orderItemInfoDTO.getCityId());
        queryGoodsRelBO.setCityProductId(orderItemInfoDTO.getCityProductId());
        queryGoodsRelBOList.add(queryGoodsRelBO);
      }
    }
    if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
      result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
    }
    return result;
  }


}
