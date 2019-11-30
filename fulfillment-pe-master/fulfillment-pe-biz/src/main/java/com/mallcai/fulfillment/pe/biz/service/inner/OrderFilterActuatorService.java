package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;

/**
 * @description: 订单过滤器，有些订单可能由于门店规则、订单类型规则。是不需要推给
 * erp，将这部分订单过滤出来
 * @author: chentao
 * @create: 2019-08-28 23:14:35
 */
public interface OrderFilterActuatorService {

    List<OrderDO> filter(List<OrderDO> oriOrders);
}
