package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateInfo;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 交易定案维度的集单
 * @author: chentao
 * @create: 2019-09-19 11:21:33
 */
@Service("transOrderOrderAggregationRuleService")
public class TransOrderOrderAggregationRuleServiceImpl implements OrderAggregationRuleService {

    @Override
    public Map<String, List<OrderDO>> execute(List<OrderDO> orderList) {
        return orderList.stream().collect(Collectors.groupingBy(OrderDO::getTransOrderId));
    }

    @Override
    public String aggregateOrder(OrderDO order) {
        return String.format("%s_%s",order.getGroupValue(),order.getTransOrderId());
    }

    @Override
    public OrderAggregationTypeEnum getOrderAggregationType() {

        return OrderAggregationTypeEnum.TRANS_ORDER;
    }
}
