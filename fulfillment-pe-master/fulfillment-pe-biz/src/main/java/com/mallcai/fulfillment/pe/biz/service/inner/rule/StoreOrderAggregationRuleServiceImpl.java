package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.AggregateRulePriorityEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 门店维度集单规则
 * @author: chentao
 * @create: 2019-09-19 00:00:14
 */
@Service("storeOrderAggregationRuleService")
public class StoreOrderAggregationRuleServiceImpl implements OrderAggregationRuleService {

    @Override
    public Map<String, List<OrderDO>> execute(List<OrderDO> orderList) {

        return orderList.stream().collect(Collectors.groupingBy(order -> order.getStoreId().toString()));
    }

    @Override
    public String aggregateOrder(OrderDO order) {
        return String.format("%s_%s",order.getGroupValue(),order.getStoreId());
    }

    @Override
    public OrderAggregationTypeEnum getOrderAggregationType() {
        return OrderAggregationTypeEnum.STORE;
    }
}
