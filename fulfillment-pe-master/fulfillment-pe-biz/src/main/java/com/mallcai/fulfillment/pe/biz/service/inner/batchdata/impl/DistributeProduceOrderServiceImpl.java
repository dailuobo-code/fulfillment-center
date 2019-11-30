package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.ProduceOrderAndDetailBO;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataHandleService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 分布式生产单处理实现
 * @author: chentao
 * @create: 2019-10-12 02:16:04
 */
@Service
@Slf4j
public class DistributeProduceOrderServiceImpl implements SplitDataHandleService {

    @Autowired
    private ProduceOrderDetailDao produceOrderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProduceOrderAndDetailDao produceOrderAndDetailDao;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {

        return BatchDataTypeEnum.CREATE_PRODUCE_ORDER;
    }

    @Override
    public void handle(SplitDataMessage splitDataMessage) {

        log.info("开始执行分布式创建生产单子任务,message:{}", JSON.toJSONString(splitDataMessage));
        if (splitDataMessage.getUniqueIds().size() == 0){

            log.error("创建订单子任务数为0，batchId:{},pageId:{}", splitDataMessage.getBatchId(), splitDataMessage.getPageId());
            return;
        }

        boolean isAllSuc = true;
        for (Object entryJson : splitDataMessage.getUniqueIds()) {

            for (Object entryObject : (JSONObject.toJavaObject((JSONObject) entryJson, Map.class)).entrySet()) {

                Map.Entry<String, String> entry = (Map.Entry<String, String>) entryObject;
                String produceOrderKey = entry.getKey();

                // 生产单的key中包含了AggregationTypeEnum、aggregateValue信息
                String[] values = produceOrderKey.split(Constants.SYMBOL_UNDERLINE);

                OrderAggregationTypeEnum orderAggregationTypeEnum = OrderAggregationTypeEnum.fromValue(Byte.valueOf(values[2]));
                String aggregateValue = values[3];

                Set<String> idStrs = null;
                String allIdStrs = null;
                try {
                    // 获取该生产单中所有的ids
                    idStrs = jedisClientUtil.smembers(entry.getKey());

                    if (CollectionUtils.isEmpty(idStrs)){

                        log.error("生产单中的ids为空,可能消息重复消费导致,key:{}", entry.getKey());
                        return;
                    }

                    allIdStrs = StringUtils.join(idStrs, Constants.SYMBOL_COMMA);
                    log.info("获取生产单成功,key:{}, ids:{}", entry.getKey(), allIdStrs);

                    if (idStrs.size() != Integer.valueOf(entry.getValue())) {

                        log.error("生产单中实际订单数量与记录不符，produceOrderKey:{},实际数量:{},记录数量:{}", entry.getKey(), idStrs.size(), entry.getValue());
                        continue;
                    }

                    List<Long> ids = idStrs.stream().map(idStr -> Long.valueOf(idStr)).collect(Collectors.toList());

                    // 创建生产单,防止消息重复消费要做幂等
                    createProduceOrder(orderAggregationTypeEnum, aggregateValue, ids);

                    // 创建生产单成功后删除redis生产单信息
                    delRedisProduceOrder(entry.getKey());

                } catch (Exception e) {

                    log.error("创建生产单异常,produceOrderKey:{},ids:{}", entry.getKey(), allIdStrs, e);
                    isAllSuc = false;
                }
            }
        }

        if (!isAllSuc) {

            log.error("批量创建生产单失败");
            throw new BizException();
        }
    }

    /**
     * 创建生产单
     * @param orderAggregationTypeEnum
     * @param aggregateValue
     * @param orderIds
     */
    private void createProduceOrder(OrderAggregationTypeEnum orderAggregationTypeEnum, String aggregateValue, List<Long> orderIds) {

        if (orderIds.size() == 0){

            log.error("生产单中没有需要处理的订单");
            return;
        }

        String idsStr = StringUtils.join(orderIds, Constants.SYMBOL_COMMA);

        log.info("创建生产单,ids:{}", idsStr);

        List<OrderDO> orders = orderDao.selectByIdsAndStatus(orderIds, OrderStatusEnum.AGGREGATE_PROCESS.getStatus());

        // 可能由于消息重复消费导致
        if (orders.size() != orderIds.size()){

            log.error("生产单信息异常,redis中生产单中订单数据与实际不相等,orderAggregationType:{},aggregateValue:{},ids:{}", orderAggregationTypeEnum.getValue(), aggregateValue, idsStr);

            return;
        }

        ProduceOrderAndDetailBO produceOrderAndDetailBO = build(GroupValueEnum.fromValue(orders.get(0).getGroupValue()), orderAggregationTypeEnum, aggregateValue, orders);

        String orderIdsStr = StringUtils.join(orderIds, Constants.SYMBOL_COMMA);

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

    private ProduceOrderAndDetailBO build(GroupValueEnum groupValueEnum, OrderAggregationTypeEnum orderAggregationType, String aggregateValue, List<OrderDO> ordersOri) {

            ProduceOrderAndDetailBO produceOrderAndDetailBo = new ProduceOrderAndDetailBO();
            ProduceOrder produceOrder = new ProduceOrder();

            // 如果是按照交易订单维度集单的生产单号就填写交易单号
            String produceOrderNo = null;
            if (OrderAggregationTypeEnum.TRANS_ORDER.equals(orderAggregationType)) {

                produceOrderNo = ordersOri.get(0).getTransOrderId();
            } else {
                // produceOrderNo 生成固定长度32位
                produceOrderNo = DateUtil.formatLDateTime(new Date()) + String.format("%018d", ordersOri.get(0).getId());
            }

            produceOrder.setProduceOrderNo(produceOrderNo);
            produceOrder.setExpectPushTime(ordersOri.get(0).getExpectPushTime());
            produceOrder.setOrderCount(ordersOri.size());
            produceOrder.setStoreId(ordersOri.get(0).getStoreId());
            produceOrder.setStatus(ProduceOrderStatusEnum.INIT.getStatus());
            produceOrder.setAggregateType(orderAggregationType.getValue());
            produceOrder.setAggregateValue(aggregateValue);
            produceOrder.setCityId(ordersOri.get(0).getCityId());
            produceOrder.setWarehouseId(ordersOri.get(0).getWarehouseId());
            produceOrder.setGroupType(groupValueEnum.getGroup());
            produceOrder.setGroupValue(groupValueEnum.getGroupValue());

            List<ProduceOrderDetail> produceOrderDetails = new ArrayList<>();

            for (OrderDO orderDO : ordersOri) {

                produceOrderDetails.add(buildProduceOrderDetail(orderDO, produceOrderNo));
            }

            produceOrderAndDetailBo.setProduceOrder(produceOrder);
            produceOrderAndDetailBo.setProduceOrderDetails(produceOrderDetails);

            return produceOrderAndDetailBo;
    }

    private ProduceOrderDetail buildProduceOrderDetail(OrderDO orderDO, String produceOrderNo) {

        ProduceOrderDetail produceOrderDetail = new ProduceOrderDetail();

        produceOrderDetail.setOrderId(orderDO.getId());
        produceOrderDetail.setProduceOrderNo(produceOrderNo);

        return produceOrderDetail;
    }

    private void delRedisProduceOrder(String key){

        Long result = null;
        try {
            result = jedisClientUtil.del(key);
        } catch (Exception e) {
            log.error("删除redis中的生产单信息异常,key:{}", key, e);
        }
        log.info("删除redis中的生产单信息结束,result:{}", result);
    }
}
