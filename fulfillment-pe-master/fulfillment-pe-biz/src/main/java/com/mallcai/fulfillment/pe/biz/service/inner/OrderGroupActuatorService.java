package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * @description: 订单分组器
 * @author: chentao
 * @create: 2019-09-16 21:17:44
 */
public interface OrderGroupActuatorService {

    Map<GroupValueEnum, List<List<OrderDO>>> groupOrders(List<OrderDO> orders);
}
