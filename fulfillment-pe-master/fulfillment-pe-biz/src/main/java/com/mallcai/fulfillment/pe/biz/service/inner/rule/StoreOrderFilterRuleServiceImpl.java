package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.WhiteTypeBO;
import com.mallcai.fulfillment.pe.biz.service.handle.StoreWhiteHandle;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterRuleService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 门店过滤规则
 * @author: chentao
 * @create: 2019-08-29 00:57:57
 */
@Slf4j
@Service("storeOrderFilterRuleService")
public class StoreOrderFilterRuleServiceImpl implements OrderFilterRuleService {

    @Autowired
    private StoreWhiteHandle storeWhiteHandle;

    @Override
    public List<OrderDO> executeRule(List<OrderDO> oriOrders) {

        List<OrderDO> filterOrders = new ArrayList<>();

        Map<String, List<OrderDO>> groupValue2OrderMap = oriOrders.stream().collect(Collectors.groupingBy(OrderDO::getGroupValue));

        Set<String> groupValueSet = groupValue2OrderMap.keySet();
        Set<GroupValueEnum> groupValueEnumSet = groupValueSet.stream().map(groupValue -> GroupValueEnum.fromValue(groupValue)).collect(Collectors.toSet());

        Map<String, WhiteTypeBO> groupValue2StoreWhiteMap = storeWhiteHandle.getWhiteByType(groupValueEnumSet);

        log.info("门店白名单，{}", JSON.toJSONString(groupValue2StoreWhiteMap));


        List<Long> oriIds = oriOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("门店维度订单过滤，过滤前ids:{}", StringUtils.join(oriIds, Constants.SYMBOL_COMMA));

        for (OrderDO orderDO : oriOrders) {

            WhiteTypeBO whiteTypeBO = groupValue2StoreWhiteMap.get(orderDO.getGroupValue());

            // 生鲜不走门店白名单 冻品不走门店白名单
            if ((whiteTypeBO.getCityWhites() != null && whiteTypeBO.getCityWhites().contains(orderDO.getCityId())) ||
                    (whiteTypeBO.getStoreWhites() != null && whiteTypeBO.getStoreWhites().contains(orderDO.getStoreId())) ||
                    GroupValueEnum.FRESH_PRODUCT.equals(GroupValueEnum.fromValue(orderDO.getGroupValue())) ||
                    GroupValueEnum.ORDER_TYPE_STANDARD.equals(GroupValueEnum.fromValue(orderDO.getGroupValue()))) {

                filterOrders.add(orderDO);
            }
        }

        List<Long> filteredIds = filterOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        log.info("门店维度订单过滤，过滤后ids:{}", StringUtils.join(filteredIds, Constants.SYMBOL_COMMA));

        return filterOrders;
    }
}
