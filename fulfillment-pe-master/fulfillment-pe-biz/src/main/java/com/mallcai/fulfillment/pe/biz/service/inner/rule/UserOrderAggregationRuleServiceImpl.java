package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.AggregateRulePriorityEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 按用户维度积单
 * @author: chentao
 * @create: 2019-08-22 20:54:09
 */
@Slf4j
@Service("userOrderAggregationRuleService")
public class UserOrderAggregationRuleServiceImpl implements OrderAggregationRuleService {

    private final OrderAggregationTypeEnum orderAggregationType = OrderAggregationTypeEnum.USER;

    @Override
    public Map<String, List<OrderDO>> execute(List<OrderDO> orderList) {

        log.info("按用户维度积单开始，orderId入参:{}", JSON.toJSONString(orderList.stream().map(OrderDO::getId).collect(Collectors.toList())));

//        Map<String, List<List<OrderDO>>> userId2OrderMapResult = new HashMap<>();
//
//        // 在事先分好组的列表中进行用户维度聚合
//
//            //  不要用map.putAll方法，容易发生key相同造成的覆盖
//            for (Map.Entry<String, List<OrderDO>> entry : .entrySet()){
//
//                if (userId2OrderMapResult.containsKey(entry.getKey())){
//
//                    userId2OrderMapResult.get(entry.getKey()).add(entry.getValue());
//                }else {
//
//                    // 这样做是为了防止之前按门店维度的划分又被覆盖了
//                    List<List<OrderDO>> elementsList = new ArrayList<>();
//                    elementsList.add(entry.getValue());
//
//                    userId2OrderMapResult.put(entry.getKey(), elementsList);
//                }
//            }

        return aggregateByUserId(orderList);
    }

    @Override
    public String aggregateOrder(OrderDO order) {
        return String.format("%s_%s",order.getGroupValue(),order.getStoreId());
    }

    /**
     * 按用户ID聚合订单
     * @param orders
     * @return
     */
    private Map<String, List<OrderDO>> aggregateByUserId(List<OrderDO> orders){

        List<Long> ids = orders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("按用户维度聚单开始，数量:{},ids:{}", ids.size(), StringUtils.join(ids, Constants.SYMBOL_COMMA));

        Map<String, List<OrderDO>> userId2OrdersMap = new HashMap<>();

        for (OrderDO orderDO : orders){

            // 一定要注意map中key的类型，Integer要转成String！！！
            if (userId2OrdersMap.containsKey(orderDO.getUserId().toString())){

                log.info("按用户维度聚单，userId:{},orderId:{}", orderDO.getUserId(), orderDO.getId());
                userId2OrdersMap.get(orderDO.getUserId().toString()).add(orderDO);
            }else {

                log.info("按用户维度聚单，userId:{},orderId:{}", orderDO.getUserId(), orderDO.getId());
                userId2OrdersMap.put(orderDO.getUserId().toString(), Lists.newArrayList(orderDO));
            }
        }


        return userId2OrdersMap;
    }

    @Override
    public OrderAggregationTypeEnum getOrderAggregationType() {
        return orderAggregationType;
    }

}
