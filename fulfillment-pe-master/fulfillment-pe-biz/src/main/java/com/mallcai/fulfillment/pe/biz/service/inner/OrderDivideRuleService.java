package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-24 15:42:17
 */
public interface OrderDivideRuleService {

    /**
     *  订单分组规则
     * @param orders
     * @return key都转为String类型
     */
    Map<String, List<OrderDO>> execute(List<OrderDO> orders);
}
