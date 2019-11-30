package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * @description: 订单聚合器接口
 * @author: chentao
 * @create: 2019-08-22 20:44:11
 */
public interface OrderAggregationActuatorService {

    /**
     *
     * @param oriOrders
     * @return 聚合维度与（具体的维度值与生产单的映射）的映射
     */
    Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregateOrder(GroupValueEnum groupValueEnum, List<OrderDO> oriOrders);

}
