package com.mallcai.fulfillment.pe.biz.service.inner.actuator;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateOrderRuleConfig;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.ListUtils;
import com.mallcai.fulfillment.pe.common.utils.ObjectCopyUtil;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-24 14:43:26
 */
@Slf4j
@Service
public class OrderAggregationActuatorServiceImpl implements OrderAggregationActuatorService {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private ConfigurationProcessor configurationProcessor;

    @Override
    public Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregateOrder(GroupValueEnum groupValueEnum, List<OrderDO> oriOrders) {

        Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> result = new HashMap<>();

        Map<String, OrderAggregationRuleService> beansMap = listableBeanFactory.getBeansOfType(OrderAggregationRuleService.class);

//        List<OrderAggregationRuleService> beansList = new ArrayList<>(beansMap.values());
        Map<GroupValueEnum, List<AggregateOrderRuleConfig>> aggregateOrderRuleMap = configurationProcessor.getAggregateOrderRule();

        List<AggregateOrderRuleConfig> aggregateOrderRuleConfigs = aggregateOrderRuleMap.get(groupValueEnum);
        // 根据优先级对规则排序，优先级高的规则先执行
        Collections.sort(aggregateOrderRuleConfigs, new Comparator<AggregateOrderRuleConfig>() {
            @Override
            public int compare(AggregateOrderRuleConfig o1, AggregateOrderRuleConfig o2) {

                return o1.getOrder() - o2.getOrder();
            }
        });

        // deep copy
        List<OrderDO> needAggregateOrders = oriOrders.stream().map(order -> (OrderDO)ObjectCopyUtil.deeplyCopy(order)).collect(Collectors.toList());
        List<Long> needAggregateIds = needAggregateOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        for (AggregateOrderRuleConfig aggregateOrderRuleConfig : aggregateOrderRuleConfigs){

            log.info("进行积单处理，数量:{},ids:{}",needAggregateIds.size(), StringUtils.join(needAggregateIds, Constants.SYMBOL_COMMA));
            // step1 分组
//            Map<String, List<OrderDO>> storeId2OrdersMap = orderDivideRuleService.execute(needAggregateOrders);

//            List<List<OrderDO>> divideOrdersByStoreId = new ArrayList<>(storeId2OrdersMap.values());

            OrderAggregationRuleService aggregationRuleService = beansMap.get(aggregateOrderRuleConfig.getProcessorBeanName());

            // step2 聚合
            Map<String, List<OrderDO>> map = aggregationRuleService.execute(needAggregateOrders);

            result.put(aggregationRuleService.getOrderAggregationType(), map);

            List<OrderDO> convertList = ListUtils.convertList(new ArrayList<>(map.values()));

            // 将已经聚合完的订单剔除掉
            removeAggregatedOrders(needAggregateOrders, convertList);
        }

        return result;
    }

    /**
     * 剔除已经聚合过的订单，不要用removeall，对象不一样了
     * @param needAggregateOrders
     * @param aggregatedOrders
     */
    public void removeAggregatedOrders(List<OrderDO> needAggregateOrders, List<OrderDO> aggregatedOrders){

        List<Long> aggregatedOrderIds = aggregatedOrders.stream().map(OrderDO::getId).collect(Collectors.toList());

        Iterator<OrderDO> iterator = needAggregateOrders.iterator();

        while (iterator.hasNext()){

            if (aggregatedOrderIds.contains(iterator.next().getId())){

                iterator.remove();
            }
        }
    }
}
