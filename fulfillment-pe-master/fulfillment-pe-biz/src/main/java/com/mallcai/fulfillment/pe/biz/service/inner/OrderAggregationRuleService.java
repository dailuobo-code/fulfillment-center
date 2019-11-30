package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.biz.service.bo.AggregateInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.AggregateRulePriorityEnum;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * @description: 生产单积单规则规则
 * @author: chentao
 * @create: 2019-08-22 20:50:52
 */
public interface OrderAggregationRuleService {

    /**
     * @param
     * @return
     */
    Map<String, List<OrderDO>> execute(List<OrderDO> orderList);

    /**
     * @param
     * @return
     */
    String aggregateOrder(OrderDO order);

    /**
     * 获取订单聚合维度
     * @return
     */
    OrderAggregationTypeEnum getOrderAggregationType();
}
