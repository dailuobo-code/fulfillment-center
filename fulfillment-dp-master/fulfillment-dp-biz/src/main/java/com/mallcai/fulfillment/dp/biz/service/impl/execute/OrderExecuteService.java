package com.mallcai.fulfillment.dp.biz.service.impl.execute;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.utils.DatetimeUtil;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.common.constants.SymbolConstants;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.common.utils.RedisKeyGenerator;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailJsDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailJsDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao.SettlementLogDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dto.SettlementQueryDTO;
import com.mallcai.fulfillment.dp.infrastructure.mongo.OrderDetailJsMongoDAO;
import com.mallcai.fulfillment.dp.infrastructure.mongo.OrderMongoDAO;
import com.mallcai.fulfillment.dp.api.common.dto.*;
import com.mallcai.fulfillment.dp.api.enums.OrderStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOrderAssemblyDTO;
import com.mallcai.service.api.IGlobalService;
import com.mallcai.service.vo.ic.common.SOACityProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import com.mallcai.trade.common.mongo.vo.MongoOrder;
import com.mallcai.trade.common.mongo.vo.MongoOrderDetailjs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yl
 * @description 取货订单相关操作
 * @date 2019-07-18
 */
@Service
@Slf4j
public class OrderExecuteService {

    @Resource
    private OrderDetailJsDAO orderDetailJsDAO;
    @Resource
    private OrderDetailJsMongoDAO orderDetailJsMongoDAO;
    @Resource
    private OrderDAO orderDAO;

    @Autowired
    @Qualifier("caicaiTransactionTemplate")
    private TransactionTemplate caicaiTransactionTemplate;

    @Autowired
    @Qualifier("tradeTransactionTemplate")
    private TransactionTemplate tradeTransactionTemplate;

    @Resource
    private SettlementService settlementService;
    @Resource
    private OrderMongoDAO orderMongoDAO;
    @Resource
    private IGlobalService iGlobalService;
    @Resource
    private OrderDetailDAO orderDetailDAO;
    @Resource
    private SettlementLogDAO settlementLogDAO;
    @Resource
    private OrderMsgService orderMsgService;
    @Resource
    private JedisClientUtil jedisClientUtil;

    /**
     * 保存结算数据
     */
    public boolean saveGenerateOrderDetailJs(List<OrderDetailJsDO> orderDetailJsDoList,
                                             DeliveredDetailDTO deliveredDetail, Integer change) {

        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
        Integer operatorId = deliveredDetail.getOperatorId();
        boolean executeFlag = SettlementTypeEnum.ORDER_DETAIL_JS.getCode().equals(settlementLog.getType())
                        && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService.insertWarnLog(deliveredDetail, SettlementTypeEnum.ORDER_DETAIL_JS.getCode());
            }
            return true;
        }

        return tradeTransactionTemplate.execute(tradeStatus -> {
            Date now = new Date();
            SettlementLogDO settlementLogDO = new SettlementLogDO();
            settlementLogDO.setOrderId(orderId);
            settlementLogDO.setStatus(SettlementStatusEnum.PROCESSING.getCode());
            settlementLogDO.setType(SettlementTypeEnum.CHANGE_UPDATE.getCode());
            settlementLogDO.setCompleteTime(now);
            int count = settlementLogDAO.updateBySettlement(settlementLogDO);
            if (count <= 0) {
                throw new BizException("更新settlement 失败");
            }

            boolean caicaiFlag = caicaiTransactionTemplate.execute(caicaiStatus -> {
                OrderDO orderUpdate = new OrderDO();
                orderUpdate.setOrderId(orderId);
                orderUpdate.setUpdateTime(now);
                orderUpdate.setCompleteTime(now);
                orderUpdate.setCompleteUserId(operatorId);
                orderUpdate.setStatus(OrderStatusEnum.WAIT_EVALUATE.getType());
                int orderCount = orderDAO.updateByOrder(orderUpdate);
                if (orderCount <= 0) {
                    throw new BizException("更新订单数据失败");
                }
                int jsCount = orderDetailJsDAO.saveOrderDetailjs(orderDetailJsDoList);
                if (jsCount <= 0) {
                    throw new BizException("更新js数据失败");
                }
                handlePickupMongoOrder(now, orderId, change);
                handleMongoOrderDetailJs(orderDetailJsDoList);
                return true;
            });
            if (!caicaiFlag) {
                throw new BizException("更新订单or结算失败");
            }
            settlementLog.setType(SettlementTypeEnum.CHANGE_UPDATE.getCode());
            deliveredDetail.setSettlementLog(settlementLog);
            settlementLog.setStatus(SettlementStatusEnum.PROCESSING.getCode());
            deliveredDetail.setOperateSettlement(true);
            return true;
        });
    }


    /**
     * 积分订单结算数据保存
     */
    public boolean savePointOrderDetailJs(List<OrderDetailJsDO> pointList,
                                          DeliveredDetailDTO deliveredDetail) {

        SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        Integer operatorId = deliveredDetail.getOperatorId();
        boolean executeFlag =
                SettlementTypeEnum.ORDER_DETAIL_JS.getCode().equals(settlementLog.getType())
                        && SettlementStatusEnum.PROCESSING.getCode().equals(settlementLog.getStatus());
        if (!executeFlag) {
            if (!BooleanUtils.toBoolean(deliveredDetail.getOperateSettlement())
                    && !BooleanUtils.toBoolean(deliveredDetail.getIsTask())) {
                settlementService
                        .insertWarnLog(deliveredDetail, SettlementTypeEnum.ORDER_DETAIL_JS.getCode());
            }
            return true;
        }

        return tradeTransactionTemplate.execute(tradeStatus -> {

            SettlementLogDO settlementLogDO = new SettlementLogDO();
            settlementLogDO.setType(SettlementTypeEnum.CHANGE_UPDATE.getCode());
            settlementLogDO.setStatus(SettlementStatusEnum.PROCESSING.getCode());
            settlementLogDO.setOrderId(orderId);
            int count = settlementLogDAO.updateBySettlement(settlementLogDO);
            if (count <= 0) {
                throw new BizException("更新settlement 失败");
            }

            boolean caicaiFlag = caicaiTransactionTemplate.execute(caicaiStatus -> {
                Date now = new Date();
                OrderDO orderUpdate = new OrderDO();
                orderUpdate.setCompleteTime(now);
                orderUpdate.setCompleteUserId(operatorId);
                orderUpdate.setUpdateTime(now);
                orderUpdate.setStatus(OrderStatusEnum.WAIT_EVALUATE.getType());
                orderUpdate.setOrderId(orderId);
                int orderCount = orderDAO.updateByOrder(orderUpdate);
                if (orderCount <= 0) {
                    throw new BizException("更新订单数据失败");
                }
                int jsCount = orderDetailJsDAO.saveOrderDetailjs4Point(pointList);
                if (jsCount <= 0) {
                    throw new BizException("更新js数据失败");
                }
                handlePickupMongoOrder(now, orderId, null);
                handleMongoOrderDetailJs(pointList);
                return true;
            });
            if (!caicaiFlag) {
                throw new BizException("更新订单or结算失败");
            }
            settlementLog.setType(SettlementTypeEnum.CHANGE_UPDATE.getCode());
            settlementLog.setStatus(SettlementStatusEnum.PROCESSING.getCode());
            deliveredDetail.setSettlementLog(settlementLog);
            deliveredDetail.setOperateSettlement(true);
            return true;
        });
    }

    /**
     * 更新mongojs数据
     */
    private void handleMongoOrderDetailJs(List<OrderDetailJsDO> orderDetailJsDoList) {
        List<MongoOrderDetailjs> mongoOrderDetailJsList = Lists.newArrayList();

        orderDetailJsDoList.forEach(orderDetailJsDO -> {
            MongoOrderDetailjs mongoOrderDetailjs = new MongoOrderDetailjs();
            BeanUtils.copyProperties(orderDetailJsDO, mongoOrderDetailjs);
            mongoOrderDetailJsList.add(mongoOrderDetailjs);
        });
        orderDetailJsMongoDAO.batchInsert(mongoOrderDetailJsList);
    }


    /**
     * 更新订单为mongo数据
     */
    private void handlePickupMongoOrder(Date now, String orderId, Integer change) {
        MongoOrder set = new MongoOrder();
        set.setUpdateTime(DateFormatUtils.format(now, "yyyy-MM-dd"));
        set.setStatus(OrderStatusEnum.WAIT_EVALUATE.getType());
        set.setCompleteTime(DateFormatUtils.format(now, "yyyy-MM-dd"));
        if (Objects.nonNull(change)) {
            set.setChange(change);
        }
        MongoOrder where = new MongoOrder();
        where.setOrderId(orderId);
        orderMongoDAO.update(set, where, true);
    }


    /**
     * 订单状态修改为锁定
     */
    public void pickupLock(PlainResult<List<TradeOrderAssemblyDTO>> plainResult) {
        if (!plainResult.isSuccess()) {
            plainResult.setResult(null);
            return;
        }
        List<TradeOrderAssemblyDTO> orderAssemblyDtoList = plainResult.getData();
        if (CollectionUtils.isEmpty(orderAssemblyDtoList)) {
            log.info("获取锁定订单数据为空");
            plainResult.setResult(null);
            return;
        }
        try {

            List<String> ids = Lists.newArrayList();
            orderAssemblyDtoList.forEach(orderAssembly -> {
                TradeOrderDTO orderDO = orderAssembly.getOrderDO();
                if (OrderStatusEnum.LOCK_ORDER.getType().equals(orderDO.getStatus())) {
                    log.info("存在订单为99的状态>>{}", orderDO.getOrderId());
                    ids.add(orderDO.getOrderId());
                }
            });

            if (CollectionUtils.isNotEmpty(ids)) {
                return;
            }
            caicaiTransactionTemplate.execute(transactionStatus -> {
                for (TradeOrderAssemblyDTO orderAssembly : orderAssemblyDtoList) {
                    String orderId = orderAssembly.getOrderDO().getOrderId();
                    if (OrderStatusEnum.PAID.getType().equals(orderAssembly.getOrderDO().getStatus())
                            && isArrivePickUpTime(orderAssembly.getOrderDO().getPickupTime(),orderAssembly.getOrderDO().getPickupStartTime())) {
                        orderDAO.lockOrderStatus(orderId);
                    }
                }
                return true;
            });
        } catch (Exception e) {
            log.warn("锁定订单失败:plainResult:" + JSONObject.toJSONString(plainResult), e);
            plainResult.setCode(Errors.FULFILLMENT_SYSTEM_ERROR.getCode());
            plainResult.setError("锁定订单失败");
        }
    }

    /**
     * 判断取货时间是否大于当前时间
     * @param pickDate
     * @param pickupStartTime
     * @return
     */
    private boolean isArrivePickUpTime(Date pickDate,String pickupStartTime){
        String pickUpTime = DateTimeUtils.getDateStr(pickDate)+" " + pickupStartTime+":00";
        Date dPickUpTime = DateTimeUtils.strToDate(pickUpTime,DateTimeUtils.DATE_PATTERN_MM);
        return DateTimeUtils.compareTwoTime(new Date(),dPickUpTime) > 0;
    }

    /**
     * 后置处理
     */
    public boolean orderPostProcessor(DeliveredDetailDTO deliveredDetail) {

        String orderId = deliveredDetail.getDeliveredOrderList().get(0).getOrderId();
        String updateTime = DatetimeUtil.getNowStr();
        return tradeTransactionTemplate.execute(tradeStatus -> {
            settlementService.updateSettlement(orderId, SettlementTypeEnum.SUCCESS_END.getCode(),
                    SettlementStatusEnum.SUCCESS.getCode(), deliveredDetail);

            boolean caicaiFlag = caicaiTransactionTemplate.execute(caicaiStatus -> {
                OrderDO orderDO = new OrderDO();
                orderDO.setOrderId(orderId);
                orderDO.setStatus(OrderStatusEnum.WAIT_EVALUATE.getType());
                orderDO.setCompleteTime(new Date());
                orderDAO.updateByOrderId(orderDO);

                MongoOrder set = new MongoOrder();
                set.setStatus(OrderStatusEnum.WAIT_EVALUATE.getType());
                set.setCompleteTime(updateTime);
                MongoOrder where = new MongoOrder();
                where.setOrderId(orderId);
                orderMongoDAO.update(set, where, true);
                return true;
            });

            if (!caicaiFlag) {
                throw new BizException("订单后置处理失败");
            }
            return true;
        });

    }

    /**
     * 获取结算数量
     */
    public Integer getDetailJsNum(Integer cityProductId, OrderDO orderDO) {
        try {

            if (orderDO.getStatus() >= OrderStatusEnum.WAIT_EVALUATE.getType()) {
                return orderDetailJsDAO.countByOrderIdAndProductId(orderDO.getOrderId(), cityProductId);
            }
            return 0;
        } catch (Exception e) {
            log.error("查询detail_js 出错", e);
            return 0;
        }
    }

    public List<DeliveredDetailDTO> batchOrder(List<String> idList) {
        List<DeliveredDetailDTO> deliveredDetailDtoList = Lists.newArrayList();
        List<OrderDO> orderDoList = orderDAO.selectByOrderIdList(idList);

        orderDoList.forEach(orderDO -> {
            DeliveredDetailDTO deliveredDetailDTO = new DeliveredDetailDTO();
            deliveredDetailDTO.setUserId(orderDO.getUserId());
            deliveredDetailDTO.setCityId(orderDO.getCityId());
            deliveredDetailDTO.setStoreId(orderDO.getStoreId().longValue());
            deliveredDetailDTO.setOperateSource(1);
            deliveredDetailDTO.setOperatorId(111);
            List<DeliveredOrder> deliveredOrderList = Lists.newArrayList();

            //detail
            List<OrderDetailDO> orderDetailDoList = orderDetailDAO.selectByOrderId(orderDO.getOrderId());

            DeliveredOrder deliveredOrder = new DeliveredOrder();
            deliveredOrder.setOrderId(orderDO.getOrderId());
            List<Integer> cids = orderDetailDoList.stream().map(OrderDetailDO::getCityProductId).collect(
                    Collectors.toList());
            Response<Map<Integer, SOACityProduct>> cityProductsByIds = iGlobalService
                    .getCityProductsByIds(cids);

            Map<Integer, SOACityProduct> cityMap = cityProductsByIds.getData();
            List<ProductDTO> productDtoList = Lists.newArrayList();
            orderDetailDoList.forEach(orderDetailDO -> {
                ProductDTO productDTO = new ProductDTO();
                if (Objects.isNull(cityMap.get(orderDetailDO.getCityProductId()))) {
                    return;
                }
                productDTO.setNum(orderDetailDO.getProductNum());
                productDTO.setCityProductId(orderDetailDO.getCityProductId());
                productDTO.setProductNo(cityMap.get(orderDetailDO.getCityProductId()).getProductNo());

                List<BarCodeDTO> barCodeDtoList = Lists.newArrayList();

                for (int i = 0; i < orderDetailDO.getProductNum(); i++) {
                    BarCodeDTO barCodeDTO = new BarCodeDTO();
                    barCodeDTO.setBarCode(
                            "27" + cityMap.get(orderDetailDO.getCityProductId()).getProductNo() + "00500" + 1);
                    barCodeDTO.setSorting("2711216009650/ck/30/0153/181112173848/1001");
                    barCodeDTO.setStatus(1);
                    barCodeDtoList.add(barCodeDTO);
                }
                productDTO.setBarCodeDtoList(barCodeDtoList);
                productDtoList.add(productDTO);
            });
            deliveredOrder.setProductDtoList(productDtoList);
            deliveredOrderList.add(deliveredOrder);
            deliveredDetailDTO.setDeliveredOrderList(deliveredOrderList);
            deliveredDetailDtoList.add(deliveredDetailDTO);
        });
        return deliveredDetailDtoList;
    }
    /**
     * 消息补发，执行完可以去除
     */
    public PlainResult sendPartnerMsg(String orderIds, Long startDate,
                                      Long endDate) {
        log.info("消息补发开始>>>>>>>>>>>>>>");
        boolean paramFlag = StringUtils.isBlank(orderIds) &&
                (Objects.isNull(startDate) || Objects.isNull(endDate));
        if (paramFlag) {
            log.warn("参数错误");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR);
        }
        List<String> orderIdList = null;
        if (StringUtils.isNotBlank(orderIds)) {
            String[] orderIdArray = orderIds.split(SymbolConstants.COMMA);
            orderIdList = Arrays.asList(orderIdArray);
        }
        SettlementQueryDTO settlementQueryDTO = new SettlementQueryDTO();

        settlementQueryDTO.setOrderIdList(orderIdList);
        settlementQueryDTO.setEndTime(Objects.isNull(endDate) ? null : new Date(endDate));
        settlementQueryDTO.setStartTime(Objects.isNull(startDate) ? null : new Date(startDate));
        settlementQueryDTO.setLastId(0L);
        settlementQueryDTO.setLimit(1000);
        List<String> failOrderList = Lists.newArrayList();
        int allCount = 0;
        int successCount = 0;
        while (true) {

            List<SettlementLogDO> settlementLogDOList = settlementLogDAO
                    .querySettlementList(settlementQueryDTO);

            if (CollectionUtils.isEmpty(settlementLogDOList)) {
                break;
            }
            allCount += settlementLogDOList.size();
            for (SettlementLogDO settlementLogDO : settlementLogDOList) {
                try {
                    if (!getSendMsgLock()) {
                        log.info("消息补发 中止,key:{}", RedisKeyGenerator.sendOrderMsgSwitch());
                        return PlainResultBuilder.success(
                                "allCount:" + allCount + ",successCount:" + successCount + ",failOrderList:"
                                        + failOrderList);
                    }

                    OrderDO orderDO = orderDAO.selectByOrderId(settlementLogDO.getOrderId());
                    if (Objects.isNull(orderDO)) {
                        failOrderList.add(settlementLogDO.getOrderId());
                        continue;
                    }
                    DeliveredDetailDTO deliveredDetail = new DeliveredDetailDTO();
                    deliveredDetail.setOperatorId(orderDO.getCompleteUserId());
                    boolean sendFlag = orderMsgService.sendUpdateMsg(orderDO, deliveredDetail);
                    if (!sendFlag) {
                        throw new BizException("失败");
                    }
                    successCount += 1;
                } catch (Exception e) {
                    log.error("消息补发异常：", e);
                    failOrderList.add(settlementLogDO.getOrderId());
                }
            }
            settlementQueryDTO.setLastId(
                    settlementLogDOList.get(settlementLogDOList.size() - 1).getId());

        }
        log.info("消息补发结束");
        return PlainResultBuilder.success(
                "allCount:" + allCount + ",successCount:" + successCount + ",failOrderList:"
                        + failOrderList);
    }


    private boolean getSendMsgLock() {
        String switchValue = jedisClientUtil.get(RedisKeyGenerator.sendOrderMsgSwitch());
        if (StringUtils.isBlank(switchValue)) {
            return true;
        }
        return BooleanUtils.toBoolean(switchValue);
    }
}