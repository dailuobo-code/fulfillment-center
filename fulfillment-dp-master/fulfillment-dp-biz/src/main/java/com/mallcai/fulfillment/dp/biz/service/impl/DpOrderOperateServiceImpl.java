package com.mallcai.fulfillment.dp.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.utils.Arith;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.biz.service.impl.assembly.OrderAssembly;
import com.mallcai.fulfillment.dp.biz.service.impl.dto.ActualOrderDetailDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.execute.*;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.OrderParamChecker;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailJsDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailJsDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderSortingLogDO;
import com.mallcai.fulfillment.dp.api.DpOrderOperateService;
import com.mallcai.fulfillment.dp.api.common.dto.DeliveredOrder;
import com.mallcai.fulfillment.dp.api.common.dto.SettlementLogDTO;
import com.mallcai.fulfillment.dp.api.enums.OrderStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.PayTypeEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementStatusEnum;
import com.mallcai.fulfillment.dp.api.enums.SettlementTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOperateDTO;
import com.mallcai.service.user.api.IUserService;
import com.mallcai.service.user.vo.user.AppUserInfo;
import com.mallcai.trade.business.api.common.dto.OrderQuerySelector;
import com.mallcai.trade.business.api.common.dto.TradeOrderDetailDTO;
import com.mallcai.trade.business.api.order.OrderQueryService;
import com.mallcai.trade.business.api.order.request.OrderQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yl
 * @description 订单操作服务实现
 * @date 2019-07-15
 */
@Service
@Slf4j
public class DpOrderOperateServiceImpl implements DpOrderOperateService {

    @Resource
    private OrderParamChecker orderParamChecker;
    @Resource
    private PickUpService pickUpService;
    @Resource
    private ChangeExecuteService changeExecuteService;
    @Resource
    private OrderAssembly orderAssembly;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private OrderDetailJsDAO orderDetailJsDAO;
    @Resource
    private OrderMsgService orderMsgService;
    @Resource
    private OrderDetailDAO orderDetailDAO;
    @Resource
    private IUserService iUserService;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private OrderExecuteService orderExecuteService;
    @Resource
    private SettlementService settlementService;
    @Resource
    private OrderQueryService orderQueryService;


    private static final List<Integer> ORDER_STATUS_LIST = Arrays
            .asList(OrderStatusEnum.WAIT_EVALUATE.getType(), OrderStatusEnum.REFUND.getType(),
                    OrderStatusEnum.FINISH.getType(),
                    OrderStatusEnum.AFTER_SALE_PROCESSING.getType(),
                    OrderStatusEnum.AFTER_SALE_DONE.getType());

    @Override
    public PlainResult<TradeOperateDTO> syncDeliveredOrder(DeliveredDetailDTO deliveredDetailDTO) {
        //校验
        PlainResult<TradeOperateDTO> plainResult = orderParamChecker.checkDeliverOrder(deliveredDetailDTO);
        if(!plainResult.isSuccess()){
            return plainResult;
        }
        try {
            taskExecutor.execute(() -> handleDeliveredOrder(deliveredDetailDTO));
        } catch (Exception e) {
            log.error("[同步取货订单]异常,request:" + deliveredDetailDTO, e);
        }
        return plainResult;
    }

    /**
     * 同步pda已完成取货的订单,单个多个订单
     * 一个订单校验失败，其他订单都不执行
     *
     * @param deliveredDetailDTO 交付详情DTO
     * @return PlainResult
     */
    @Override
    public PlainResult<TradeOperateDTO> syncDeliveredOrderOneFailAllFail(DeliveredDetailDTO deliveredDetailDTO){
        //校验
        PlainResult<TradeOperateDTO> plainResult = orderParamChecker.checkDeliverOrderOneFailAllFail(deliveredDetailDTO);
        if(!plainResult.isSuccess()){
            return plainResult;
        }
        try {
            taskExecutor.execute(() -> handleDeliveredOrder(deliveredDetailDTO));
        } catch (Exception e) {
            log.error("[同步取货订单]异常,request:" + deliveredDetailDTO, e);
        }
        return plainResult;
    }

    /**
     * 批量交付
     *
     * @param deliveredDetailDtoList 交付详情DTO
     */
    @Override
    public PlainResult<TradeOperateDTO> batchSyncDeliveredOrder(
            List<DeliveredDetailDTO> deliveredDetailDtoList) {

        List<String> errorOrderIdList = Lists.newArrayList();
        List<String> successOrderIdList = Lists.newArrayList();
        TradeOperateDTO tradeResponse = new TradeOperateDTO();
        tradeResponse.setErrorOrderIdList(errorOrderIdList);
        tradeResponse.setSuccessOrderIdList(successOrderIdList);

        for (DeliveredDetailDTO detailDTO : deliveredDetailDtoList) {
            PlainResult<TradeOperateDTO> plainResult = syncDeliveredOrder(detailDTO);

            if (!plainResult.isSuccess()) {
                continue;
            }
            TradeOperateDTO tradeOperateDTO = plainResult.getData();

            if (Objects.nonNull(tradeOperateDTO)) {
                if (CollectionUtils.isNotEmpty(tradeOperateDTO.getErrorOrderIdList())) {
                    errorOrderIdList.addAll(tradeOperateDTO.getErrorOrderIdList());
                }
                if (CollectionUtils.isNotEmpty(tradeOperateDTO.getSuccessOrderIdList())) {
                    successOrderIdList.addAll(tradeOperateDTO.getSuccessOrderIdList());
                }
            }
        }
        HashSet<String> errorSet = Sets.newHashSet(errorOrderIdList);
        HashSet<String> successSet = Sets.newHashSet(successOrderIdList);
        errorOrderIdList.clear();
        errorOrderIdList.addAll(errorSet);
        successOrderIdList.clear();
        successOrderIdList.addAll(successSet);
        return PlainResultBuilder.success(tradeResponse);
    }

    /**
     * 交付订单处理
     */
    private void handleDeliveredOrder(DeliveredDetailDTO deliveredDetailDTO) {
        int succCount = 0;
        for (DeliveredOrder deliveredOrder : deliveredDetailDTO.getDeliveredOrderList()) {
            try {
                DeliveredDetailDTO deliveredDetail = new DeliveredDetailDTO();
                BeanUtils.copyProperties(deliveredDetailDTO, deliveredDetail);
                deliveredDetail.setSettlementLog(null);
                deliveredDetail.setDeliveredOrderList(null);
                deliveredDetail.setOperateSettlement(null);
                deliveredDetail.setDeliveredOrderList(Collections.singletonList(deliveredOrder));

                List<ActualOrderDetailDTO> actualDetailList = Lists.newArrayList();
                List<OrderSortingLogDO> sortingList = Lists.newArrayList();
                orderAssembly.handleDeliveredAssembly(actualDetailList, sortingList, deliveredDetail);
                SettlementLogDTO settlementLog = deliveredDetail.getSettlementLog();
                OrderDO orderDO = orderDAO.selectByOrderId(deliveredOrder.getOrderId());

                if (SettlementTypeEnum.SUCCESS_END.getCode().equals(settlementLog.getType())) {
                    settlementService.insertWarnLog(deliveredDetail, SettlementTypeEnum.SUCCESS_END.getCode());
                    log.warn("settlementlog记录已经为成功，orderId:{}", deliveredOrder.getOrderId());
                    continue;
                }

                if (ORDER_STATUS_LIST.contains(orderDO.getStatus()) && !SettlementTypeEnum.CHANGE_UPDATE.getCode().equals(settlementLog.getType())) {
                    log.warn("订单已结算过,orderId:{}", deliveredOrder.getOrderId());
                    settlementService.updateSettlement(deliveredOrder.getOrderId(),
                            SettlementTypeEnum.SUCCESS_END.getCode(),
                            SettlementStatusEnum.SUCCESS.getCode(), deliveredDetail);
                    settlementService.insertWarnLog(deliveredDetail, 99);
                    continue;
                }
                boolean pickupResult = pickUpService.savePickupLog(deliveredDetail, sortingList);
                if (!pickupResult) {
                    log.warn("savePickupLog操作失败，orderId:{}", deliveredOrder.getOrderId());
                    continue;
                }
                boolean sortingResult = pickUpService.saveOrderSortingLogDAO(sortingList, deliveredDetail);
                if (!sortingResult) {
                    log.warn("OrderSortingLog操作失败，orderId:{}", deliveredOrder.getOrderId());
                    continue;
                }
                PlainResult result = completeOrder(actualDetailList, deliveredDetail);
                if (!result.isSuccess()) {
                    log.warn("completeOrder失败：orderId{}，result：{}", deliveredOrder.getOrderId(),
                            JSONObject.toJSONString(result));
                    List<OrderDetailJsDO> orderDetailJsDoList = orderDetailJsDAO
                            .selectByOrderId(deliveredOrder.getOrderId());

                    if (CollectionUtils.isNotEmpty(orderDetailJsDoList)) {
                        boolean handleResult = orderExecuteService.orderPostProcessor(deliveredDetail);
                        if (!handleResult) {
                            log.warn("订单已经存在结算数据更新订单失败：orderId{}", deliveredOrder.getOrderId());
                            continue;
                        }
                    }
                }
                succCount++;
            } catch (Exception e) {
                log.error("处理失败:request:" + JSONObject.toJSONString(deliveredDetailDTO), e);
            }
        }
        if (succCount > 0) {
            //发鸡蛋券
            pickUpService.sendEggCouponForInviteUser(deliveredDetailDTO.getUserId());
        }
    }


    /**
     * 订单结算
     */
    private PlainResult completeOrder(List<ActualOrderDetailDTO> detailList,
                                      DeliveredDetailDTO deliveredDetail) {

        log.info(">>>称重结算参数:{}", JSONObject.toJSONString(detailList));
        OrderDO orderDO = orderDAO.selectByOrderId(detailList.get(0).getOrderId());
        AppUserInfo appUserInfo = iUserService.getUserInfoById(deliveredDetail.getUserId());

        //积分订单
        if (PayTypeEnum.POINT.getType().equals(orderDO.getPayType())) {
            Map<String, Integer> pointMap = Maps.newHashMap();
            List<OrderDetailJsDO> pointList = Lists.newArrayList();
            getPointOrderDetailJs(detailList, pointList, pointMap, appUserInfo);
            boolean detailJsFlag = orderExecuteService.savePointOrderDetailJs(pointList, deliveredDetail);
            if (!detailJsFlag) {
                log.warn("更新结算数据出错");
                return PlainResultBuilder.fail(0, "更新结算数据出错");
            }

            Integer point = Objects.isNull(pointMap.get(orderDO.getOrderId())) ? 0
                    : pointMap.get(orderDO.getOrderId());
            boolean pointFlag = changeExecuteService.handleReturnPoint(point, orderDO, deliveredDetail);

            if (!pointFlag) {
                log.warn("handlePoint失败，orderId:{}", orderDO.getOrderId());
                return PlainResultBuilder.fail(0, "handlePoint失败");
            }
            //消息推送
            orderMsgService.handleMsgPush(0, appUserInfo.getUserId());
            return PlainResultBuilder.success(null);
        }

        //常规订单
        List<OrderDetailJsDO> orderDetailJsDoList = Lists.newArrayList();
        Map<String, Integer> changeMap = Maps.newHashMap();
        getGeneralOrderDetailJs(detailList, orderDetailJsDoList, changeMap, appUserInfo,deliveredDetail);
        Integer change = Objects.isNull(changeMap.get(orderDO.getOrderId())) ? 0
                : changeMap.get(orderDO.getOrderId());
        deliveredDetail.setForcedChangeSum(Objects.isNull(deliveredDetail.getForcedChangeSum()) ? 0
                : deliveredDetail.getForcedChangeSum());
        boolean detailJsFlag = orderExecuteService.saveGenerateOrderDetailJs(orderDetailJsDoList, deliveredDetail, change);
        if (!detailJsFlag) {
            log.warn("更新结算数据出错");
            return PlainResultBuilder.fail(0, "更新结算数据出错");
        }
        OrderDO order = new OrderDO();
        BeanUtils.copyProperties(orderDO, order);
        PlainResult plainResult = changeExecuteService.handleChange(change, deliveredDetail);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        order.setChange(change);
        //发送消息
        orderMsgService.sendUpdateMsg(order, deliveredDetail);
        return PlainResultBuilder.success(null);
    }


    /**
     * 常规订单detail_js
     */
    private void getGeneralOrderDetailJs(List<ActualOrderDetailDTO> detailList,
                                         List<OrderDetailJsDO> orderDetailJsDoList, Map<String, Integer> changeMap,
                                         AppUserInfo appUserInfo, DeliveredDetailDTO deliveredDetail) {

        log.info(">>>结算参数:{}", detailList);
        String orderId = detailList.get(0).getOrderId();
        int jsCnt = orderDetailJsDAO.countByOrderId(orderId);
        if (jsCnt > 0) {
            log.warn(">>订单已结算过,订单id={},忽略本次处理....", orderId);
            return;
        }
        Integer totalChange = 0;
        Integer forcedChang = 0;
        Map<String, List<TradeOrderDetailDTO>> priceMap = findOrderProductPrice(orderId);
        for (ActualOrderDetailDTO detail : detailList) {
            Integer cityProductId = detail.getCityProductId();
            OrderDetailDO orderDetailDO = orderDetailDAO.selectOrderDetail(orderId, cityProductId);
            List<Integer> weightList = detail.getActualWeight();
            List<String> sortingStr = detail.getSortingStr();
            List<Integer> isOutStock = detail.getIsOutStock();
            List<TradeOrderDetailDTO> tradeOrderDetailDtos = priceMap.get(orderId);
            List<TradeOrderDetailDTO> productWeights = tradeOrderDetailDtos.stream().filter(d -> d.getCityProductId().equals(cityProductId)).collect(Collectors.toList());
            int j = 0;
            for (int i = 0; i < weightList.size(); i++) {
                OrderDetailJsDO orderDetailJsDO = new OrderDetailJsDO();
                orderDetailJsDO.setIsOutStock(isOutStock.get(i));
                Integer outStock = isOutStock.get(i);

                //防止数组越界
                j = (productWeights.size()-1) > j ? j : (productWeights.size()-1) ;
                BigDecimal price = new BigDecimal(productWeights.get(j).getUnitActualPrice());
                //缺货
                if (outStock == 0) {
                    orderDetailJsDO.setActualPrice(0);
                    orderDetailJsDO.setActualWeight(0);
                    Integer change = price.intValue();
                    totalChange += change;
                    forcedChang += change;
                    log.info("常规结算-缺货>>orderId:{},OutStock:{},change:{}", orderId, outStock, change);
                    changeMap.put(orderId, totalChange);
                    deliveredDetail.setForcedChangeSum(forcedChang);
                    j++;
                    //退货 不找零(前端会自动补状态为0的记录)
                } else if (outStock == 2) {
                    orderDetailJsDO.setActualPrice(0);
                    orderDetailJsDO.setActualWeight(0);
                    log.info("常规结算订单-退货>>>>>orderId={},isOutStock={},oldChange={},change={}", orderId,
                            outStock, changeMap.get(orderId), 0);
                } else {
                    // 找零  按重量收费
                    if (orderDetailDO.getChangeFlag() == 1 && orderDetailDO.getUnitType() == 1) {
                        orderDetailJsDO.setActualPrice(new BigDecimal(
                                Arith.div(Arith.mul(weightList.get(i), price.doubleValue()),
                                        orderDetailDO.getMaxWeight().doubleValue(), 0)).intValue());
                        Integer change = new BigDecimal(
                                Arith.sub(price.doubleValue(), orderDetailJsDO.getActualPrice()))
                                .intValue();
                        if (0 > change) {
                            change = 0;
                            orderDetailJsDO.setActualPrice(price.intValue());
                        }
                        totalChange += change;
                        log.info("常规结算结算订单-实际称重找零>>>>>orderId={},isOutStock={},oldChange={},change={}",
                                orderId, outStock, changeMap.get(orderId), change);
                        changeMap.put(orderId, totalChange);
                    } else {
                        // 不找零的商品 找零值为0
                        log.info("常规结算订单-实际称重不找零/按数量收费>>>>>orderId={},isOutStock={},oldChange={},change={}",
                                orderId, outStock, changeMap.get(orderId), 0);
                        orderDetailJsDO.setActualPrice(price.intValue());
                    }
                    orderDetailJsDO.setActualWeight(weightList.get(i));
                    orderDetailJsDO.setSortingStr(sortingStr.get(i));
                    j++;
                }
                //基础数据detailJs
                baseHandleOrderDetailJs(orderDetailDO, orderDetailJsDO, detail, appUserInfo,
                        orderDetailJsDoList,price);
            }
        }
    }

    private Map<String, List<TradeOrderDetailDTO>> findOrderProductPrice(String orderId) {
        OrderQueryDTO orderQueryDTO = new OrderQueryDTO();
        List<String> orderIdList = new ArrayList<>();
        orderIdList.add(orderId);
        OrderQuerySelector selector = new OrderQuerySelector();
        selector.setOrderIdList(orderIdList);
        orderQueryDTO.setOrderQuerySelector(selector);
        PlainResult<Map<String, List<TradeOrderDetailDTO>>> plainResult = orderQueryService.queryOrderDetails4Pickup(orderQueryDTO);
        if(plainResult != null){
            return plainResult.getData();
        }
        return Maps.newHashMap();
    }

    /**
     * 积分订单结算数据
     */
    private void getPointOrderDetailJs(List<ActualOrderDetailDTO> detailList,
                                       List<OrderDetailJsDO> orderDetailJsDoList, Map<String, Integer> pointMap,
                                       AppUserInfo appUserInfo) {

        String orderId = detailList.get(0).getOrderId();
        int jsCnt = orderDetailJsDAO.countByOrderId(orderId);
        if (jsCnt > 0) {
            log.warn(">>积分订单已结算过,订单id={},忽略本次处理....", orderId);
            return;
        }
        Integer totalPoint = 0;
        for (ActualOrderDetailDTO detail : detailList) {
            Integer cityProductId = detail.getCityProductId();
            OrderDetailDO orderDetailDO = orderDetailDAO.selectOrderDetail(orderId, cityProductId);
            List<Integer> weightList = detail.getActualWeight();
            List<Integer> isOutStockList = detail.getIsOutStock();
            List<String> sortingStrList = detail.getSortingStr();

            BigDecimal price = orderDetailDO.getPrice();
            for (int i = 0; i < weightList.size(); i++) {
                OrderDetailJsDO orderDetailJs = new OrderDetailJsDO();
                orderDetailJs.setIsOutStock(isOutStockList.get(i));
                // 缺货
                if (isOutStockList.get(i) == 0) {
                    orderDetailJs.setActualPointPrice(0);
                    orderDetailJs.setActualPrice(0);
                    orderDetailJs.setActualWeight(0);
                    Integer change = orderDetailDO.getPointPrice();
                    totalPoint += change;
                    //处理积分
                    log.info("积分-结算订单-缺货>>>>>orderId={},isOutStock={},change={}", detail.getOrderId(),
                            isOutStockList.get(i), change);
                    pointMap.put(orderId, totalPoint);
                } else {
                    orderDetailJs.setActualPrice(orderDetailDO.getPrice().intValue());
                    orderDetailJs.setActualPointPrice(orderDetailDO.getPointPrice());
                    orderDetailJs.setActualWeight(weightList.get(i));
                    orderDetailJs.setSortingStr(sortingStrList.get(i));
                }
                orderDetailJs.setPointPrice(orderDetailDO.getPointPrice());
                //基础数据detailJs
                baseHandleOrderDetailJs(orderDetailDO, orderDetailJs, detail, appUserInfo,
                        orderDetailJsDoList,price);
            }
        }

    }

    /**
     * 基础公用orderDetailJs
     */
    private void baseHandleOrderDetailJs(OrderDetailDO orderDetailDO,
                                         OrderDetailJsDO orderDetailJsDO, ActualOrderDetailDTO actualOrderDetailDTO,
                                         AppUserInfo appUserInfo, List<OrderDetailJsDO> list,BigDecimal price) {
        orderDetailJsDO.setCityId(orderDetailDO.getCityId());
        orderDetailJsDO.setCityProductId(orderDetailDO.getCityProductId());
        orderDetailJsDO.setCreateTime(new Date());
        orderDetailJsDO.setOrderId(actualOrderDetailDTO.getOrderId());
        orderDetailJsDO.setPrice(price.intValue());
        orderDetailJsDO.setProdIcon(orderDetailDO.getProdIcon());
        orderDetailJsDO.setProdShowName(orderDetailDO.getProdShowName());
        orderDetailJsDO.setSpecId(orderDetailDO.getSpecId());
        orderDetailJsDO.setSpecName(orderDetailDO.getSpecName());
        orderDetailJsDO.setStoreId(orderDetailDO.getStoreId());
        orderDetailJsDO.setUserId(orderDetailDO.getUserId());
        orderDetailJsDO.setWeight(orderDetailDO.getMaxWeight().intValue());
        orderDetailJsDO.setChangeFlag(orderDetailDO.getChangeFlag());
        orderDetailJsDO.setCreateUserId(actualOrderDetailDTO.getOperUserId());
        orderDetailJsDO.setRefundType(appUserInfo.getRefundType());
        list.add(orderDetailJsDO);
    }

}
