package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderGroupActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.ProduceOrderRedisKeyService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataHandleService;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 集单批分页数据处理类
 * @author: chentao
 * @create: 2019-10-10 00:51:45
 */
@Slf4j
@Service
public class AggregateOrderSplitDataHandleServiceImpl implements SplitDataHandleService {

    @Autowired
    private OrderFilterActuatorService orderFilterActuatorService;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProduceOrderRedisKeyService produceOrderRedisKeyService;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {
        return BatchDataTypeEnum.AGGREGATE_ORDER;
    }

    @Override
    public void handle(SplitDataMessage splitDataMessage) {

        if(splitDataMessage.getUniqueIds().size() == 0){

            log.info("批处理子任务数为0，batchId:{},pageId:{}", splitDataMessage.getBatchId(), splitDataMessage.getPageId());
            return;
        }

        List<Long> ids = splitDataMessage.getUniqueIds().stream().map(id -> ((Number)id).longValue()).collect(Collectors.toList());

        log.info("分页处理ids:{}", StringUtils.join(ids, Constants.SYMBOL_COMMA));
        List<OrderDO> orders = orderDao.selectByIdsAndStatus(ids, OrderStatusEnum.INIT.getStatus());

        // 幂等
        if (orders.size() == 0){

            log.info("符合条件的订单数为0，ids:{}", StringUtils.join(ids, Constants.SYMBOL_COMMA));
            return;
        }

        Map<String, List<String>> key2IdsMap = createProduceOrdersRedisContent(splitDataMessage, orders);

        if (key2IdsMap == null){

            log.info("构建生产单为空,ids:{}", StringUtils.join(ids, Constants.SYMBOL_COMMA));
            return;
        }

        temporaryStorageAggregateOrder(splitDataMessage.getBatchId(), key2IdsMap);
    }

    private Map<String, List<String>> createProduceOrdersRedisContent(SplitDataMessage splitDataMessage, List<OrderDO> orders) {

        if (orders.isEmpty()) {
            log.info("没有需要处理的订单");
            return null;
        }

        List<OrderDO> filterOrders = orderFilterActuatorService.filter(orders);

        if (orders.isEmpty()) {
            log.info("没有需要聚合的订单");
            return null;
        }

        Map<String, List<String>> redisKey2ProduceOrderIdsMap = new HashMap<>();

        Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregationType2OrdersMap = aggreOrderQueryService.aggreSubOrder(filterOrders);

        for (Map.Entry<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregateType2ProduceOrderEntry : aggregationType2OrdersMap.entrySet()) {

            redisKey2ProduceOrderIdsMap.putAll(buildRedisKeyValue(splitDataMessage.getBatchId(), aggregateType2ProduceOrderEntry.getKey(), aggregateType2ProduceOrderEntry.getValue()));
        }

        return redisKey2ProduceOrderIdsMap;
    }

    /**
     * 将聚合的订单id集合临时存入缓存中
     *
     * @param redisKey2ProduceOrderIdsMap
     */
    private void temporaryStorageAggregateOrder(String batchId, Map<String, List<String>> redisKey2ProduceOrderIdsMap) {

        boolean isAllSuc = true;

        for (Map.Entry<String, List<String>> entry : redisKey2ProduceOrderIdsMap.entrySet()) {

            try {
                temporaryStorageAggregateOrder(batchId, entry.getKey(), entry.getValue());
            } catch (Exception e) {

                isAllSuc = false;
            }
        }

        // 只要有一个生产单异常就认为这批次处理失败，进行重试
        if (!isAllSuc){

            log.info("批次处理子任务集单失败，batchId:{}", batchId);
            throw new BizException(Errors.BATCH_AGGREGATE_ORDER_ERROR);
        }
    }

    /**
     * 将生产单中包含的orderids临时存储到redis中
     * @param batchId
     * @param key
     * @param idStrList
     */
    private void temporaryStorageAggregateOrder(String batchId, String key, List<String> idStrList) {

        List<Long> orderIds = idStrList.stream().map(idStr -> Long.valueOf(idStr)).collect(Collectors.toList());

        if (idStrList.size() == 0){
            log.error("生产单中的订单为空");
            return;
        }

        String orderIdsStr = StringUtils.join(idStrList, Constants.SYMBOL_COMMA);

        // 更新失败可能是订单已经在处理中，则此次不需要处理
        try {
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_PROCESS.getStatus(), OrderStatusEnum.INIT.getStatus());
        } catch (Exception e) {

            log.error("积单置处理中状态异常,idorderIds:{}", orderIdsStr, e);
            throw e;
        }
        log.info("生产单对应的orderId存储至redis开始，key:{},ids:{}", key, orderIdsStr);

        Long result = null;
        try {

            String[] idStrArray = new String[idStrList.size()];
            result = jedisClientUtil.sadd(key, idStrList.toArray(idStrArray));

            log.info("往生产单中添加ids成功,key:{},ids:{},添加成功数量:{}", key, orderIdsStr, result);
            String aggregateOrderBatchAllKey = produceOrderRedisKeyService.buildAggregateOrderBatchAllKey(batchId);
            Long curProduceOrderSize = jedisClientUtil.hincrBy(aggregateOrderBatchAllKey, key, result);
            log.info("往记录所有生产单key集合中添加生产单计数成功,aggregateOrderBatchAllKey:{},key:{},增加值:{},当前生产单大小:{}", aggregateOrderBatchAllKey, key
                    , result, curProduceOrderSize);

        } catch (Exception e) {

            log.error("生产单对应的orderId存储至redis异常key:{},ids:{},result:{}", key, orderIdsStr, result, e);
            try {
                orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.INIT.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
            } catch (Exception ex) {

                log.error("订单状态回置为初始状态失败,orderIds:{}", orderIdsStr);
            }

            throw e;
        }

        Set<String> curAllIds = new HashSet<>();
        try {
            curAllIds = jedisClientUtil.smembers(key);
        } catch (Exception e) {
            log.error("获取当前生产单的id集合异常,key:{}", key, e);
        }

        log.info("生产单对应的orderId存储至redis结束，key:{}", key, StringUtils.join(curAllIds, Constants.SYMBOL_COMMA));
    }

    /**
     * 构建redis key 、value。key能够确定为一个生产单，value是该生产单对应的ID的集合
     *
     * @param orderAggregationType
     * @param aggregateValue2OrdersMap
     * @return
     */
    private Map<String, List<String>> buildRedisKeyValue(String batchId, OrderAggregationTypeEnum orderAggregationType, Map<String, List<OrderDO>> aggregateValue2OrdersMap) {

        Map<String, List<String>> redisKey2ProduceOrderIdsMap = new HashMap<>();
        for (Map.Entry<String, List<OrderDO>> entry : aggregateValue2OrdersMap.entrySet()) {
            List<OrderDO> ordersOri = entry.getValue();
            String redisKey = buildAggregateOrderRedisKey(batchId, orderAggregationType, entry.getKey());

            List<String> oriIds = ordersOri.stream().map(orderDO -> orderDO.getId().toString()).collect(Collectors.toList());
            redisKey2ProduceOrderIdsMap.put(redisKey, oriIds);
        }
        return redisKey2ProduceOrderIdsMap;
    }

    /**
     * 构建订单集合redis key
     *
     * @param batchId
     * @param orderAggregationTypeEnum
     * @param aggregateValue
     * @return
     */
    private String buildAggregateOrderRedisKey(String batchId, OrderAggregationTypeEnum orderAggregationTypeEnum, String aggregateValue) {

        return String.format(BizConstant.AGGREGATE_ORDER_SPIT_DATA_KEY_FORMAT, batchId, orderAggregationTypeEnum.getValue().toString(), aggregateValue);
    }
}
