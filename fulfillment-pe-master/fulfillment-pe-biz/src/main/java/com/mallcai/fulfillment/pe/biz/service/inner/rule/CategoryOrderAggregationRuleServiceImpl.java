package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.AggregateRulePriorityEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.biz.service.inner.PickingRuleInnerService;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 品类积单规则
 * @author: chentao
 * @create: 2019-08-22 20:58:57
 */
@Slf4j
@Service("categoryOrderDivideRuleService")
public class CategoryOrderAggregationRuleServiceImpl implements OrderAggregationRuleService {

    @Autowired
    private PickingRuleInnerService pickingRuleInnerService;

    private final OrderAggregationTypeEnum orderAggregationType = OrderAggregationTypeEnum.PRODUCT;

    @Override
    public Map<String, List<OrderDO>> execute(List<OrderDO> orderList) {

        Map<String, List<List<OrderDO>>> productNo2OrdersMapResult = new HashMap<>();

        // 所有需要聚合分拣的商品编码
        List<String> allNeedAggregateProductNos = pickingRuleInnerService.getAllProductNos();

        log.info("所有需要聚单商品编码:{}", StringUtils.join(allNeedAggregateProductNos, Constants.SYMBOL_COMMA));

        if (allNeedAggregateProductNos.isEmpty()){

            log.warn("需要聚合分拣品类产品编码为空");
        }

//        // 在事先分好组的列表中按照总部产品编码做聚合
//
//            //  不要用map.putAll方法，容易发生key相同造成的覆盖
//            for (Map.Entry<String, List<OrderDO>> entry : .entrySet()){
//
//                if (productNo2OrdersMapResult.containsKey(entry.getKey())){
//
//                    productNo2OrdersMapResult.get(entry.getKey()).add(entry.getValue());
//                }else {
//
//                    // 这样做是为了防止之前按门店维度的划分又被覆盖了
//                    List<List<OrderDO>> elementsList = new ArrayList<>();
//                    elementsList.add(entry.getValue());
//
//                    productNo2OrdersMapResult.put(entry.getKey(), elementsList);
//                }
//            }

        return aggregateByProductNo(orderList, allNeedAggregateProductNos);
    }

    @Override
    public String aggregateOrder(OrderDO order) {
        List<String> allNeedAggregateProductNos = pickingRuleInnerService.getAllProductNos();
        if(allNeedAggregateProductNos.contains(order.getProductNo())){
            return order.getProductNo();
        }
        return null;
    }

    @Override
    public OrderAggregationTypeEnum getOrderAggregationType() {

        return orderAggregationType;
    }

    private Map<String, List<OrderDO>> aggregateByProductNo(List<OrderDO> orders, List<String> allNeedAggregateProductNos){

        Map<String, List<OrderDO>> productNo2OrdersMap = new HashMap<>();
        List<Long> ids = orders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("按商品维度聚单，所有原订单数量:{}, ids:{}", ids.size(), StringUtils.join(ids, Constants.SYMBOL_COMMA));
        for (OrderDO orderDO : orders){

            String productNo = orderDO.getProductNo();

            // 不需要聚合的商品订单过滤掉
            if (!allNeedAggregateProductNos.contains(productNo)){

                log.info("过滤不需要聚合的商品订单，id:{},productNo:{}", orderDO.getId(), productNo);
                continue;
            }

            if (productNo2OrdersMap.containsKey(productNo)){

                log.info("按商品品类聚单结果productNo:{}, orderId:{}", productNo, orderDO.getId());
                productNo2OrdersMap.get(productNo).add(orderDO);
            }else {

                log.info("按商品品类聚单结果productNo:{}, orderId:{}", productNo, orderDO.getId());
                productNo2OrdersMap.put(productNo, Lists.newArrayList(orderDO));
            }
        }

        return productNo2OrdersMap;
    }
}
