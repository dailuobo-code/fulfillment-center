package com.mallcai.fulfillment.dc.biz.service.inner;

import com.alibaba.fastjson.JSON;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderItemInfoDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderItemInfoListMsg;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.SortingRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoSourceEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.dc.biz.service.producer.SendPeSortingForecastMsg;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.utils.BeanCopierUtils;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.dc.dao.bigData.StoreSkuForecastDAO;
import com.mallcai.fulfillment.dc.dao.bigData.WarehouseSalesForecastDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderItemInfoDAO;
import com.mallcai.fulfillment.dc.dao.dc.PurchaseDAO;
import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import com.mallcai.fulfillment.pe.api.service.operate.PeOrderOperateService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CancleProduceRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.SortingOrderRequestDTO;
import com.mallcai.scm.utils.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 接收订单
 *
 * @author Liu Yang
 * @description 大数据采购预测订单处理
 * @date 2019/9/28 4:30 PM
 */
@Service
@Slf4j
public class ReceiveSortingForecastInnerService {

  @Autowired
  @Qualifier("fdcTransactionTemplate")
  private TransactionTemplate fdcTransactionTemplate;
  @Resource
  private PurchaseDAO purchaseDAO;
  @Resource
  private WarehouseSalesForecastDAO warehouseSalesForecastDAO;
  @Resource
  private RedisService redisService;
  @Resource
  private OrderItemInfoDAO orderItemInfoDAO;
  @Resource
  private StoreSkuForecastDAO storeSkuForecastDAO;
  @Autowired
  private SendPeSortingForecastMsg sendPeSortingForecastMsg;
  @Resource
  private PeOrderOperateService peOrderOperateService;
  @Resource
  private GoodsGrayRouterService goodsGrayRouterService;
  @Autowired
  RedisIdFactory redisIdFactory;
  @Autowired
  private RedisDistributeLockUtil redisDistributeLockUtil;

  /**
   * 一次最大传送消息数量
   */
  private final Integer MAX_PROCESS_NUM = 100;
  /**
   * 第一次插入
   */
  private final Integer IS_FIRST_TIME = 0;

  public Boolean receiveMsg(SortingRequestDTO sortingRequestDTO, String lockKey, String requestId) {
    if (!goodsGrayRouterService.isNeedRoute(sortingRequestDTO.getCityId())) {
      log.warn("本城市没有开通货品开关，无法进行数据导入,sortingRequestDTO:{}", JSON.toJSONString(sortingRequestDTO));
      releaseLock(lockKey,requestId,sortingRequestDTO,"本城市没有开通货品开关，无法进行数据导入");
      return true;
    }
    boolean checkResult = bigDataSortingPredictParamChecker(sortingRequestDTO);
    if (!checkResult) {
      log.error("参数校验失败！");
      releaseLock(lockKey,requestId,sortingRequestDTO,"参数校验失败");
      return true;
    }
    String redisKey = sortingRequestDTO.getCityId() + "_" + sortingRequestDTO.getWarehouseId() + "_"
        + sortingRequestDTO.getForecastDate();
    String value = redisService.get(redisKey);
    if (null != value) {
      Integer existVersion = Integer.valueOf(value);
      if (existVersion < sortingRequestDTO.getVersion()) {
        log.info("当前系统中已存在版本低于消息中数据版本，将进行数据作废后更新操作！sortingRequestDTO:{}",
            JSON.toJSONString(sortingRequestDTO));
        //当前消息中的version大于value中的version，则作废之前的数据，重新插入
        if (!invalidationOrders(sortingRequestDTO)) {
          releaseLock(lockKey,requestId,sortingRequestDTO,"大数据发送更新数据时，接收前调用ERP接口失败或者获取锁失败");
          return false;
        }
        orderItemInfoDAO
            .deleteByCondition(sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
                sortingRequestDTO.getForecastDate());
        //PE数据库中的数据也要作废
        if(!cancelOrder(sortingRequestDTO)){
          releaseLock(lockKey,requestId,sortingRequestDTO,"删除PE系统的本日该仓已存预测数据失败");
          return false;
        }
        //将现在的数据插入
        if (!executeBatchInsertSortingList(sortingRequestDTO, redisKey)) {
          releaseLock(lockKey,requestId,sortingRequestDTO,"执行数据插入失败");
          return false;
        }
        log.info("数据更新成功！sortingRequestDTO:{}", JSON.toJSONString(sortingRequestDTO));
      } else {
        //当前消息中的version小于等于value中的version，则忽略掉该消息直接返回true
        log.info("当前消费成功的版本为：{},收到消息的版本低于当前库中的版本，消息将被忽略！sortingRequestDTO:{}",
            redisService.get(redisKey), JSON.toJSONString(sortingRequestDTO));
        releaseLock(lockKey,requestId,sortingRequestDTO,"收到消息的版本低于当前库中的版本，消息将被忽略");
        return true;
      }
    } else {
      //如果redis没这个key，视为没有成功消费过该天该仓的数据。则查询履约库中有没有该天该仓的数据，如果有，则比较后插入或者更新，如果没有该天的数据，则直接插入。成功消费时记录或者更新redis
      log.info("数据即将进行首次插入！sortingRequestDTO:{}", JSON.toJSONString(sortingRequestDTO));
      if (!executeBatchInsertSortingList(sortingRequestDTO, redisKey)) {
        releaseLock(lockKey,requestId,sortingRequestDTO,"数据执行插入失败");
        return false;
      }
      log.info("数据插入成功！sortingRequestDTO:{}", JSON.toJSONString(sortingRequestDTO));
    }

    return true;
  }

  public boolean cancelOrder(SortingRequestDTO sortingRequestDTO){
    SortingOrderRequestDTO sortingOrderRequestDTO = new SortingOrderRequestDTO();
    sortingOrderRequestDTO.setCityId(sortingRequestDTO.getCityId());
    sortingOrderRequestDTO.setWarehouseId(sortingRequestDTO.getWarehouseId());
    sortingOrderRequestDTO.setForecastDate(sortingRequestDTO.getForecastDate());
    try {
      PlainResult plainResult = peOrderOperateService.cancelOrder(sortingOrderRequestDTO);
      if (!plainResult.isSuccess()) {
        log.error("删除PE系统的本日该仓已存预测数据失败,cityId:{},warehouseId:{},forecastDate:{}",
            sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
            sortingRequestDTO.getForecastDate());
        return false;
      }
    }catch (Exception e){
      log.error("删除PE系统的本日该仓已存预测数据超时,cityId:{},warehouseId:{},forecastDate:{}",
          sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
          sortingRequestDTO.getForecastDate());
      return false;
    }
    return true;
  }

  public void releaseLock(String lockKey,String requestId,SortingRequestDTO sortingRequestDTO,String message){
    redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
    log.info("自动解锁成功,解锁原因:{},sortingRequestDTO:{}", message,JSON.toJSONString(sortingRequestDTO));
  }

  public boolean executeBatchInsertSortingList(SortingRequestDTO sortingRequestDTO,
      String redisKey) {
    int forecastResultNum = storeSkuForecastDAO
        .countByExample(sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
            DateUtil.parseDate(sortingRequestDTO.getForecastDate()));
    int executeNum = forecastResultNum / MAX_PROCESS_NUM + 1;
    log.info("本次预测插入总量为:{},预测指令为：{}", forecastResultNum, JSON.toJSONString(sortingRequestDTO));
    if (executeNum == 0) {
      log.error("查询分拣预测数据失败,cityId:{},warehouseId:{},forecastDate:{}",
          sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
          sortingRequestDTO.getForecastDate());
      return false;
    } else {
      for (int i = 0; i < executeNum; i++) {
        //查询大数据线上库并赋值到OrderItemInfo表
        List<OrderItemInfoDO> orderItemInfoDoList = getStoreSkuForecastByLimit(sortingRequestDTO,
            i);
        if(CollectionUtils.isEmpty(orderItemInfoDoList)){
          break;
        }
        if (executeInsertOrderItemInfoList(orderItemInfoDoList)) {
          //发送消息给PE
          SendPe(sortingRequestDTO, orderItemInfoDoList, i, forecastResultNum, redisKey);
        } else {
          log.error("插入OrderItemInfo数据异常，orderItemInfoDoList:{}",
              JSON.toJSONString(orderItemInfoDoList));
          return false;
        }
      }
    }
    return true;
  }

  public boolean invalidationOrders(SortingRequestDTO sortingRequestDTO) {
    CancleProduceRequestDTO dto = new CancleProduceRequestDTO();
    dto.setCityId(sortingRequestDTO.getCityId());
    dto.setWarehouseId(sortingRequestDTO.getWarehouseId());
    dto.setForecastDate(sortingRequestDTO.getForecastDate());
    try {
      PlainResult result = peOrderOperateService.cancelProduceOrder(dto);
      if (!result.isSuccess()) {
        log.error("大数据发送更新数据时，接收前调用ERP接口失败或者获取锁失败,sortingRequestDTO:{}",
            JSON.toJSONString(sortingRequestDTO));
        return false;
      }
    }catch (Exception e){
      return false;
    }
    return true;
  }

  public boolean bigDataSortingPredictParamChecker(SortingRequestDTO sortingRequestDTO) {
    if (Objects.isNull(sortingRequestDTO.getCityId()) || Objects
        .isNull(sortingRequestDTO.getWarehouseId()) ||
        Objects.isNull(sortingRequestDTO.getForecastDate()) || Objects
        .isNull(sortingRequestDTO.getVersion())) {
      log.error("大数据分拣预测消息数据参数为空，purchasePredictRequestDTO:{}",
          JSON.toJSONString(sortingRequestDTO));
      return false;
    }
    Date currentDate = DateUtil.todayStart();
    Date forecastDate = DateUtil.parseDate(sortingRequestDTO.getForecastDate());
    if (DateUtil.isBefore(forecastDate, currentDate)) {
      log.error("大数据分拣预测消息数据有误，预测时间小于当天时间！purchasePredictRequestDTO:{}",
          JSON.toJSONString(sortingRequestDTO));
    }
    return true;
  }


  /**
   * 发送分拣预测消息给PE
   *
   * @param sortingRequestDTO
   * @param orderItemInfoDoList
   * @param i
   */
  public void SendPe(SortingRequestDTO sortingRequestDTO, List<OrderItemInfoDO> orderItemInfoDoList,
      int i, int totalCount, String redisKey) {
    try {
      //组装PE消息数据
      OrderItemInfoListMsg assembleOrderItemInfoListMsg = assembleOrderItemInfoListMsg(
          sortingRequestDTO, orderItemInfoDoList, i, totalCount, redisKey);
      //调用PE，进行PE层面的数据插入
      log.info("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}",
          sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
          sortingRequestDTO.getVersion(), i);
      sendPeSortingForecastMsg.sendBatchMsg(assembleOrderItemInfoListMsg);
      log.info("发送大数据采购预测消息结束,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，品类:{},预测类型:{}",
          sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
          sortingRequestDTO.getVersion(), i);
    } catch (Exception e) {
      log.error("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，品类:{},预测类型:{}",
          sortingRequestDTO.getCityId(), sortingRequestDTO.getWarehouseId(),
          sortingRequestDTO.getVersion(), i);
      //DC层插入数据回滚，当前处理逻辑为删除本次插入的数据
      List<Long> orderItemInfoIdList = orderItemInfoDoList.stream().map(OrderItemInfoDO::getId)
          .collect(Collectors.toList());
      try {
        orderItemInfoDAO.cancelOrderItemInfoListById(orderItemInfoIdList);
      } catch (Exception ex) {
        log.error("删除已有分拣单异常,purchaseIdList:{}", JSON.toJSONString(orderItemInfoIdList), ex);
      }
    }
  }

  public boolean executeInsertOrderItemInfoList(List<OrderItemInfoDO> orderItemInfoDoList) {
    boolean flag = true;
    for (OrderItemInfoDO orderItemInfoDO : orderItemInfoDoList) {
      if (checkIfNotExistOrderItemInfoDO(orderItemInfoDO)) {
        try {
          orderItemInfoDAO.insert(orderItemInfoDO);
        } catch (Exception e) {
          log.error("插入分拣数据时发生错误,orderItemInfoDO:{}", JSON.toJSONString(orderItemInfoDO), e);
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
        .andOrderSourceEqualTo(orderItemInfoDO.getOrderSource())
        .andVersionEqualTo(orderItemInfoDO.getVersion()).andIsDeletedEqualTo(
        Constants.NOT_DELETED);
    int count = orderItemInfoDAO.countSpecialByExample(orderItemInfoCriteria);
    if (count == 0) {
      return true;
    }
    return false;
  }

  public List<OrderItemInfoDO> getStoreSkuForecastByLimit(SortingRequestDTO sortingRequestDTO,
      int i) {
    List<StoreSkuForecastCorrectionDO> storeSkuForecastCorrectionDoList = storeSkuForecastDAO
        .selectByLimit(sortingRequestDTO.getCityId(),
            sortingRequestDTO.getWarehouseId(),
            DateUtil.parseDate(sortingRequestDTO.getForecastDate()),
            i * MAX_PROCESS_NUM,
            MAX_PROCESS_NUM);
    return convertStoreSkuToOrderItemInfo(storeSkuForecastCorrectionDoList, sortingRequestDTO);
  }

  public List<OrderItemInfoDO> convertStoreSkuToOrderItemInfo(
      List<StoreSkuForecastCorrectionDO> storeSkuForecastCorrectionDOList,
      SortingRequestDTO sortingRequestDTO) {
    List<OrderItemInfoDO> result = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(storeSkuForecastCorrectionDOList)) {
      for (StoreSkuForecastCorrectionDO storeSkuForecastCorrectionDO : storeSkuForecastCorrectionDOList) {
        OrderItemInfoDO orderItemInfoDO = new OrderItemInfoDO();
        orderItemInfoDO.setOrderNo(
            Constants.BIG_PREFIX + DateUtils.dateFormat(new Date(), "yyyyMMdd") + String
                .format("%018d", redisIdFactory.getIdByDay(Constants.BIG_PREFIX)));
        orderItemInfoDO.setOrderType(OrderItemInfoTypeEnum.FRESH.getValue());
        orderItemInfoDO.setOrderSource(OrderItemInfoSourceEnum.BIG.getValue());
        orderItemInfoDO.setCityProductId(storeSkuForecastCorrectionDO.getCityProductId());
        orderItemInfoDO.setSpecId(0);
        orderItemInfoDO.setProductNum(storeSkuForecastCorrectionDO.getProductForecastNum());
        orderItemInfoDO.setStoreId(storeSkuForecastCorrectionDO.getStoreId());
        orderItemInfoDO.setUserId(0);
        if (storeSkuForecastCorrectionDO.getCaluDate() != null) {
          Date pickupTime = DateUtil.addDays(storeSkuForecastCorrectionDO.getCaluDate(), 1);
          orderItemInfoDO.setPickupTime(DateUtil.formatDate(pickupTime));
        }
        orderItemInfoDO.setCityId(storeSkuForecastCorrectionDO.getCityId());
        orderItemInfoDO.setWarehouseId(storeSkuForecastCorrectionDO.getWarehouseId());
        //大数据没有总部商品ID，这里赋值为""
        orderItemInfoDO.setProductNo("");
        orderItemInfoDO.setItemExt("");
        orderItemInfoDO.setCreateTime(new Date());
        orderItemInfoDO.setUpdateTime(new Date());
        orderItemInfoDO.setIsDeleted(Constants.NOT_DELETED);
        orderItemInfoDO.setVersion(sortingRequestDTO.getVersion());
        result.add(orderItemInfoDO);
      }
    }
    return result;
  }

  public OrderItemInfoListMsg assembleOrderItemInfoListMsg(
      SortingRequestDTO sortingRequestDTO, List<OrderItemInfoDO> orderItemInfoDoList,
      int count, int totalCount, String redisKey) {
    OrderItemInfoListMsg orderItemInfoListMsg = new OrderItemInfoListMsg();
    orderItemInfoListMsg.setCityId(sortingRequestDTO.getCityId());
    orderItemInfoListMsg.setWarehouseId(sortingRequestDTO.getWarehouseId());
    orderItemInfoListMsg.setForecastDate(sortingRequestDTO.getForecastDate());
    orderItemInfoListMsg.setVersion(sortingRequestDTO.getVersion());
    orderItemInfoListMsg.setCount(count);
    orderItemInfoListMsg.setTotalCount(totalCount);
    orderItemInfoListMsg.setRedisKey(redisKey);
    List<OrderItemInfoDTO> orderItemInfoDTOList = new ArrayList<>();
    for (OrderItemInfoDO orderItemInfoDO : orderItemInfoDoList) {
      OrderItemInfoDTO orderItemInfoDTO = new OrderItemInfoDTO();
      BeanCopierUtils.copyProperties(orderItemInfoDO, orderItemInfoDTO);
      orderItemInfoDTOList.add(orderItemInfoDTO);
    }
    orderItemInfoListMsg.setOrderItemInfoDTOList(orderItemInfoDTOList);
    return orderItemInfoListMsg;
  }


}
