package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;

import java.util.List;
import java.util.Map;

/**
 * @description: 分布式生产单业务接口
 * @author: chentao
 * @create: 2019-10-12 02:15:22
 */
public interface DistributeProduceOrderService {

    void createProduceOrders(Map<String, String> produceOrder2SizeMap);

    void createProduceOrder(GroupValueEnum groupValueEnum, OrderAggregationTypeEnum orderAggregationTypeEnum, String aggregateValue, List<Long> orderIds);
}
