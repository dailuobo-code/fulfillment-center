package com.mallcai.fulfillment.pe.biz.service.inner.produceorder;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.bo.OrderFilterDto;
import com.mallcai.fulfillment.pe.biz.service.bo.ProduceOrderAndDetailBO;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderGroupActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.ProduceOrderAndDetailDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.ProduceOrderDetailDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.ProduceOrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 订单聚合实现类
 * @author: chentao
 * @create: 2019-08-24 10:44:45
 */
@Slf4j
@Service
public class ProduceOrderServiceImpl implements ProduceOrderService {

    @Autowired
    private OrderAggregationActuatorService orderAggregationActuatorService;

    @Autowired
    private ProduceOrderAndDetailDao produceOrderAndDetailDao;

    @Autowired
    private ProduceOrderDetailDao produceOrderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderFilterActuatorService orderFilterActuatorService;

    @Autowired
    private OrderGroupActuatorService orderGroupActuatorService;

    @Autowired
    private ConfigurationProcessor configurationPorcessor;

//    @ConfigValue(key = "/app_fulfillment/common/order.cancel.expire.time")
//    private String ORDER_CANCEL_EXPIRE_TIME;

//    @Override
//    public void createProduceOrder(Date startTime, Date endTime, Integer orderHoldMins) {
//
//        List<OrderDO> orders = getNeedHandleOrder(startTime, endTime, orderHoldMins, null);
//
//        createProduceOrders(orders);
//    }

    @Override
    public void createProduceOrder(DataBo dataBo) {

        List<OrderDO> orders = getNeedHandleOrder(dataBo.getOrderQueryDto());

        List<OrderDO> dataFilteredOrders = null;

        if (dataBo.getOrderFilterDto() != null) {
            dataFilteredOrders = queryFilterData(dataBo.getOrderFilterDto(), orders);
            createProduceOrders(dataFilteredOrders);
        }else {

            createProduceOrders(orders);
        }


    }

    private List<OrderDO> getNeedHandleOrder(OrderQueryDto orderQueryDto){

        String orderCancelExpireTime = configurationPorcessor.getOrderCancelExpireTime(orderQueryDto);

        Date nowDate = new Date();
        Date orderCancelExpireDate = null;
        try {
            orderCancelExpireDate = DateUtil.parseDateTime(DateUtil.formatDate(nowDate) + " " + orderCancelExpireTime.trim());
        } catch (Exception e) {
            log.error("订单取消截止时间格式有误，{}", orderCancelExpireTime);
            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }

        //  订单取消截止时间内，将订单hold指定时间再推.到了订单取消截止时间就不hold订单了。
        List<OrderDO> orders = null;
        // 字段比较多，用set比较不容易出错
//        OrderQueryDto orderQueryDto = new OrderQueryDto();
//
//        orderQueryDto.setStartTime(startTime);
//        orderQueryDto.setEndTime(endTime);
//        orderQueryDto.setStoreId(storeId);

        if (nowDate.compareTo(orderCancelExpireDate) <= 0) {

//            orderQueryDto.setOrderHoldMins(orderHoldMins);
            orders = orderDao.selectNeedAggregate(orderQueryDto);
        } else {

            orderQueryDto.setOrderHoldMins(null);
            orders = orderDao.selectNeedAggregate(orderQueryDto);
        }

        List<Long> ids = orders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("创建生产单，数量:{}, 订单ids:{}",ids.size(), StringUtils.join(ids, Constants.SYMBOL_COMMA));

        return orders;
    }

    private void createProduceOrders(List<OrderDO> orders) {

        if (orders.isEmpty()){
            log.info("没有需要处理的订单");
            return;
        }

        // step1 过滤订单
        List<OrderDO> filterOrders = orderFilterActuatorService.filter(orders);

        if (filterOrders.isEmpty()) {
            log.info("没有需要聚合的订单");
            return;
        }

        // step 分组
        Map<GroupValueEnum, List<List<OrderDO>>> group2GroupOrdersMap = orderGroupActuatorService.groupOrders(filterOrders);

        for (Map.Entry<GroupValueEnum, List<List<OrderDO>>> group2GroupOrdersEntry : group2GroupOrdersMap.entrySet()){

            for (List<OrderDO> groupOrders : group2GroupOrdersEntry.getValue()){

                // step 按维度聚合订单
                Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregationType2OrdersMap = orderAggregationActuatorService.aggregateOrder(group2GroupOrdersEntry.getKey(), groupOrders);

                List<ProduceOrderAndDetailBO> produceOrderAndDetailBos = new ArrayList<>();

                for (Map.Entry<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregateType2ProduceOrderEntry : aggregationType2OrdersMap.entrySet()) {

                    produceOrderAndDetailBos.addAll(build(aggregateType2ProduceOrderEntry.getKey(), group2GroupOrdersEntry.getKey(), aggregateType2ProduceOrderEntry.getValue()));
                }

                // step 持久化 判断数据是否存在
                for (ProduceOrderAndDetailBO produceOrderAndDetailBo : produceOrderAndDetailBos) {

                    try {

                        createProduceOrder(produceOrderAndDetailBo);
                    } catch (Exception e) {
                        log.error("创建生产单异常,produceOrderNo:{}", produceOrderAndDetailBo.getProduceOrder().getProduceOrderNo(), e);
                        continue;
                    }
                }
            }
        }
    }

    private void createProduceOrder(ProduceOrderAndDetailBO produceOrderAndDetailBO) {

        List<Long> orderIds = produceOrderAndDetailBO.getProduceOrderDetails().stream().map(ProduceOrderDetail::getOrderId).collect(Collectors.toList());
        String orderIdsStr = StringUtils.join(orderIds, Constants.SYMBOL_COMMA);

        // 更新失败可能是订单已经在处理中，则此次不需要处理
        try {
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_PROCESS.getStatus(), OrderStatusEnum.INIT.getStatus());
        } catch (Exception e) {

            log.error("积单置处理中状态异常,idorderIds:{}", orderIdsStr, e);
            throw e;
        }

        List<ProduceOrderDetail> produceOrderDetails = produceOrderDetailDao.selectByOrderIds(orderIds);

        // 判断数据是否存在
        if (!produceOrderDetails.isEmpty()) {

            // 如果积单明细表中已存在订单，但数量不一致则表明数据不完整，因为积单明细表是事务批量插入，数据应该完整才对
            if (orderIds.size() != produceOrderDetails.size()) {

                log.error("积单明细表数据不完整,orderIds:{}", orderIdsStr);
                orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.INIT.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
                throw new BizException(Errors.DATA_ERROR);
            }

            // 已存在则将状态更新积单完成
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_COMPLETE.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());

            return;
        }

        try {
            produceOrderAndDetailDao.insertProduceOrderAndDetailAndUpdateOrder(produceOrderAndDetailBO.getProduceOrder(), produceOrderAndDetailBO.getProduceOrderDetails(), orderIds);
        } catch (Exception e) {
            log.error("创建生产单异常,produceOrderNo:{}", produceOrderAndDetailBO.getProduceOrder().getProduceOrderNo(), e);

            try {
                orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.INIT.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
            } catch (Exception ex) {

                log.error("订单状态回置为初始状态失败,orderIds:{}", orderIdsStr);
            }

            throw e;
        }
    }

    private List<ProduceOrderAndDetailBO> build(OrderAggregationTypeEnum orderAggregationType, GroupValueEnum groupValueEnum, Map<String, List<OrderDO>> aggregateValue2OrdersMap) {

        List<ProduceOrderAndDetailBO> produceOrderAndDetails = new ArrayList<>();
        for (Map.Entry<String, List<OrderDO>> entry : aggregateValue2OrdersMap.entrySet()) {

            List<OrderDO> ordersOri = entry.getValue();
            ProduceOrderAndDetailBO produceOrderAndDetailBo = new ProduceOrderAndDetailBO();
            ProduceOrder produceOrder = new ProduceOrder();


            // 如果是按照交易订单维度集单的生产单号就填写交易单号
            String produceOrderNo = null;

            // 标品按交易订单维度集单时生产单号以交易单号
            if (GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue().equals(ordersOri.get(0).getGroupValue()) &&
                    OrderAggregationTypeEnum.TRANS_ORDER.equals(orderAggregationType)) {

                produceOrderNo = ordersOri.get(0).getTransOrderId();
            } else {
                // produceOrderNo 生成固定长度32位
                produceOrderNo = DateUtil.formatLDate(new Date()) + String.format("%024d", ordersOri.get(0).getId());
            }

            produceOrder.setProduceOrderNo(produceOrderNo);
            produceOrder.setExpectPushTime(ordersOri.get(0).getExpectPushTime());
            produceOrder.setOrderCount(ordersOri.size());
            produceOrder.setStoreId(ordersOri.get(0).getStoreId());
            produceOrder.setCityId(ordersOri.get(0).getCityId());
            produceOrder.setWarehouseId(ordersOri.get(0).getWarehouseId());
            produceOrder.setStatus(ProduceOrderStatusEnum.INIT.getStatus());
            produceOrder.setAggregateType(orderAggregationType.getValue());
            produceOrder.setAggregateValue(entry.getKey());
            produceOrder.setGroupType(groupValueEnum.getGroup());
            produceOrder.setGroupValue(groupValueEnum.getGroupValue());

            List<ProduceOrderDetail> produceOrderDetails = new ArrayList<>();

            for (OrderDO orderDO : ordersOri) {

                produceOrderDetails.add(buildProduceOrderDetail(orderDO, produceOrderNo));
            }

            produceOrderAndDetailBo.setProduceOrder(produceOrder);
            produceOrderAndDetailBo.setProduceOrderDetails(produceOrderDetails);

            produceOrderAndDetails.add(produceOrderAndDetailBo);
        }
        return produceOrderAndDetails;
    }

    private ProduceOrderDetail buildProduceOrderDetail(OrderDO orderDO, String produceOrderNo) {

        ProduceOrderDetail produceOrderDetail = new ProduceOrderDetail();

        produceOrderDetail.setOrderId(orderDO.getId());
        produceOrderDetail.setProduceOrderNo(produceOrderNo);

        return produceOrderDetail;
    }

    private List<OrderDO> queryFilterData(OrderFilterDto orderFilterDto, List<OrderDO> orders){

        List<OrderDO> filteredOrders = new ArrayList<>();

        for (OrderDO orderDO : orders){

            if (isNeedFilterData(orderFilterDto, orderDO)){

                continue;
            }

            filteredOrders.add(orderDO);
        }

        return filteredOrders;
    }

    private boolean isNeedFilterData(OrderFilterDto orderFilterDto, OrderDO order){

        if (CollectionUtils.isNotEmpty(orderFilterDto.getCityId()) && !orderFilterDto.getCityId().contains(order.getCityId())){

            return true;
        }

        if (CollectionUtils.isNotEmpty(orderFilterDto.getGroupValue()) && !orderFilterDto.getCityId().contains(order.getGroupValue())){

            return true;
        }

        if (CollectionUtils.isNotEmpty(orderFilterDto.getProduceWarehouseId()) && !orderFilterDto.getCityId().contains(order.getWarehouseId())){

            return true;
        }

        if (CollectionUtils.isNotEmpty(orderFilterDto.getProductNo()) && !orderFilterDto.getCityId().contains(order.getProductNo())){

            return true;
        }

        if (CollectionUtils.isNotEmpty(orderFilterDto.getStoreId()) && !orderFilterDto.getCityId().contains(order.getStoreId())){

            return true;
        }

        return false;
    }
}
