package com.mallcai.fulfillment.pe.biz.service.inner.rule;

import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderDivideRuleService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:门店维度分组规则实现类
 * @author: chentao
 * @create: 2019-08-24 15:44:31
 */
@Slf4j
@Service("storeOrderDivideRuleService")
public class StoreOrderDivideRuleServiceImpl implements OrderDivideRuleService {

    @Override
    public Map<String, List<OrderDO>> execute(List<OrderDO> orders) {

        // key转为String类型，注意
        Map<String, List<OrderDO>> storeId2OrdersMap = new HashMap<>();

        for (OrderDO orderDO : orders){

            if (storeId2OrdersMap.containsKey(orderDO.getStoreId().toString())){

                List<Long> ids = storeId2OrdersMap.get(orderDO.getStoreId().toString()).stream().map(OrderDO::getId).collect(Collectors.toList());
                log.info("按门店分组结果，storeId:{},orderId:{},当前门店ids:{}", orderDO.getStoreId(), orderDO.getId(), StringUtils.join(ids, Constants.SYMBOL_COMMA));
                storeId2OrdersMap.get(orderDO.getStoreId().toString()).add(orderDO);
            }else {

                log.info("按门店分组结果，storeId:{},orderId:{}", orderDO.getStoreId(), orderDO.getId());
                storeId2OrdersMap.put(orderDO.getStoreId().toString(), Lists.newArrayList(orderDO));
            }
        }

        return storeId2OrdersMap;
    }
}
