package com.mallcai.fulfillment.pe.biz.service.inner.actuator;

import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderDivideRuleService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderGroupActuatorService;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单分组器实现
 * @author: chentao
 * @create: 2019-09-16 21:18:51
 */
@Service
public class OrderGroupActuatorServiceImpl implements OrderGroupActuatorService {

    @Autowired
    @Qualifier("storeOrderDivideRuleService")
    private OrderDivideRuleService orderDivideRuleService;

    @Override
    public Map<GroupValueEnum, List<List<OrderDO>>> groupOrders(List<OrderDO> orders) {

        Map<GroupValueEnum, List<OrderDO>> groupValue2OrdersMap = firstGroup(orders);

        return secondGroup(groupValue2OrdersMap);
    }

    /**
     *
     * @param orders
     * @return
     */
    private Map<GroupValueEnum, List<OrderDO>> firstGroup(List<OrderDO> orders){

        Map<GroupValueEnum, List<OrderDO>> groupValue2OrdersMap = new HashMap<>();

        for (OrderDO order : orders){

            GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(order.getGroupValue());
            if (groupValue2OrdersMap.containsKey(groupValueEnum)){

                groupValue2OrdersMap.get(groupValueEnum).add(order);
            }else {

                groupValue2OrdersMap.put(groupValueEnum, Lists.newArrayList(order));
            }
        }

        return groupValue2OrdersMap;
    }

    private Map<GroupValueEnum, List<List<OrderDO>>> secondGroup(Map<GroupValueEnum, List<OrderDO>> groupValue2OrdersMap){

        Map<GroupValueEnum, List<List<OrderDO>>> groupValue2GroupOrdersMap = new HashMap<>();

        for (Map.Entry<GroupValueEnum, List<OrderDO>> entry : groupValue2OrdersMap.entrySet()){


            if (!entry.getKey().isNeedSecondGroup()){

                // 转成同样的数据结构
                List<List<OrderDO>> groupOrders = new ArrayList<>();
                groupOrders.add(entry.getValue());

                groupValue2GroupOrdersMap.put(entry.getKey(), groupOrders);
            }else {

                Map<String, List<OrderDO>> storeId2OrdersMap = orderDivideRuleService.execute(entry.getValue());

                groupValue2GroupOrdersMap.put(entry.getKey(), new ArrayList<>(storeId2OrdersMap.values()));

            }
        }

        return groupValue2GroupOrdersMap;
    }
}
