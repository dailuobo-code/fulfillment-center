package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterRuleService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.service.order.constants.OrderTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-29 00:58:58
 */
@Slf4j
public class OrderTypeOrderFilterRuleServiceImpl implements OrderFilterRuleService {

    private final static List<Integer> notNeedFilterType = Lists.newArrayList(OrderTypeEnum.STANDARD_PRODUCT.getValue());

    @Override
    public List<OrderDO> executeRule(List<OrderDO> oriOrders) {

        List<Long> ids = oriOrders.stream().map(OrderDO::getId).collect(Collectors.toList());
        log.info("ordertype过滤，过滤前,数量:{},ids:{}", ids.size(), StringUtils.join(ids, Constants.SYMBOL_COMMA));

        // 过滤
        List<OrderDO> filtedOrders = new ArrayList<>();

        for (OrderDO orderDO : oriOrders){

            boolean isFrozenProduct = OrderTypeEnum.NORMAL.getValue() == orderDO.getOrderType() && GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue().equals(orderDO.getGroupValue());

            if (notNeedFilterType.contains(orderDO.getOrderType()) || isFrozenProduct ){

                filtedOrders.add(orderDO);
            }
        }

        List<Long> filtedIds = filtedOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("ordertype过滤，过滤后,数量:{},ids:{}",filtedIds.size(), StringUtils.join(filtedIds, Constants.SYMBOL_COMMA));

        return filtedOrders;
    }
}
