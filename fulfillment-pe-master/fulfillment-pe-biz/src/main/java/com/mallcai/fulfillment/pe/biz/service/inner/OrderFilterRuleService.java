package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;

/**
 * @description: 订单过滤规则
 * @author: chentao
 * @create: 2019-08-28 23:23:26
 */
public interface OrderFilterRuleService {

    List<OrderDO> executeRule(List<OrderDO> oriOrders);
}
