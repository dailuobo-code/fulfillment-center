package com.mallcai.fulfillment.pe.biz.service.inner.configuration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateOrderRuleConfig;
import com.mallcai.fulfillment.pe.biz.service.bo.ConfigurationDto;
import com.mallcai.fulfillment.pe.biz.service.bo.GroupValueAggrOrderRuleConfigDto;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description: 配置处理器
 * @author: chentao
 * @create: 2019-09-17 14:35:11
 */
@Slf4j
@Component
public class ConfigurationProcessor {

    @ConfigValue(key = "/app_fulfillment/common/order.cancel.expire.time")
    private JSONObject orderCancelExpireTime;

    /**
     * 订单暂存时间，订单在生产保留多久后再积单、推单。
     * 根据order表的createTime计算，单位分钟
     */
    @ConfigValue(key = "/app_fulfillment/common/order.hold.mins")
    private JSONObject orderHoldMins;

    @ConfigValue(key = "/app_fulfillment/common/aggregate.order.rule")
    private JSONObject aggregateOrderRule;

    private final String ORDER_HOLD_MINS_KEY = "orderHoldMins";

    private final String ORDER_CANCEL_EXPIRE_TIME_KEY = "orderCancelExpireTime";

    private final String AGGREGATE_ORDER_RULE = "aggregateOrderRule";

    /**
     * 根据条件获取订单取消截止时间（orderQueryDto：查询条件一定要先赋值）
     * @param orderQueryDto
     * @return
     */
    public String getOrderCancelExpireTime(OrderQueryDto orderQueryDto) {

        try {

            log.info("获取订单取消截止时间配置，content:{},condition:{}", orderCancelExpireTime.toJSONString(), orderQueryDto.toString());
            JSONArray jsonArray = orderCancelExpireTime.getJSONArray(ORDER_CANCEL_EXPIRE_TIME_KEY);

            for (int i = 0; i < jsonArray.size(); i++) {

                ConfigurationDto configurationDto = JSONObject.toJavaObject(jsonArray.getJSONObject(i), ConfigurationDto.class);

                if (isConditionMatched(configurationDto, orderQueryDto)) {

                    return configurationDto.getOrderCancelExpireTime();
                }
            }

            return null;
        } catch (Exception e) {

            log.error("取消截止时间配置解析异常，content:{}", orderCancelExpireTime.toJSONString());
            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }
    }

    /**
     * 根据条件获取相应的hold单时间(orderQueryDto：查询条件一定要先赋值)
     * @param orderQueryDto
     * @return
     */
    public Integer getOrderHoldMins(OrderQueryDto orderQueryDto){

        try {

            log.info("获取订单取消截止时间配置，content:{},condition:{}", orderHoldMins.toJSONString(), orderQueryDto.toString());

            JSONArray jsonArray = orderHoldMins.getJSONArray(ORDER_HOLD_MINS_KEY);

            for (int i = 0; i < jsonArray.size(); i++) {

                ConfigurationDto configurationDto = JSONObject.toJavaObject(jsonArray.getJSONObject(i), ConfigurationDto.class);

                if (isConditionMatched(configurationDto, orderQueryDto)) {

                    return configurationDto.getOrderHoldMins();
                }
            }

            return null;
        } catch (Exception e) {

            log.error("订单暂存时间配置解析异常，content:{}", orderHoldMins.toJSONString());
            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }
    }

    public Integer getOrderHoldMins(String groupValue){
            List<ConfigurationDto> config = orderHoldMins.getObject(ORDER_HOLD_MINS_KEY, new TypeReference<List<ConfigurationDto>>() {});
        Optional<ConfigurationDto> optional = config.stream().filter(p -> p.getGroupValue().equals(groupValue)).findFirst();
        return optional.isPresent()?optional.get().getOrderHoldMins():0;
    }

    public String getOrderCancelExpireTime(String groupValue) {

        List<ConfigurationDto> config = orderCancelExpireTime.getObject(ORDER_CANCEL_EXPIRE_TIME_KEY, new TypeReference<List<ConfigurationDto>>() {});
        return config.stream().filter(p->p.getGroupValue().equals(groupValue))
                .findFirst().orElse(new ConfigurationDto()).getOrderCancelExpireTime();
    }

    /**
     * 获取集单规则配置
     * @return
     */
    public Map<GroupValueEnum, List<AggregateOrderRuleConfig>> getAggregateOrderRule(){

        Map<GroupValueEnum, List<AggregateOrderRuleConfig>> map = null;
        try {
            log.info("获取集单规则配置，content:{}", aggregateOrderRule.toJSONString());
            JSONArray jsonArray = aggregateOrderRule.getJSONArray(AGGREGATE_ORDER_RULE);

            map = new HashMap<>();

            for (int i = 0; i < jsonArray.size(); i++){

                GroupValueAggrOrderRuleConfigDto groupValueAggrOrderRuleConfigDto = JSONObject.toJavaObject(jsonArray.getJSONObject(i), GroupValueAggrOrderRuleConfigDto.class);
                GroupValueEnum groupValueEnum = GroupValueEnum.fromKey(groupValueAggrOrderRuleConfigDto.getGroupValueKey());

                if (groupValueEnum == null){

                    throw new BizException(Errors.CONFIG_CONTENT_ERROR);
                }

                map.put(groupValueEnum, groupValueAggrOrderRuleConfigDto.getRules());
            }
        } catch (Exception e) {

            log.error("解析集单规则配置异常", e);
            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }

        return map;
    }


    public AggregateOrderRuleConfig getAggregateOrderRule(String groupValue){
        List<GroupValueAggrOrderRuleConfigDto> config = aggregateOrderRule.getObject(AGGREGATE_ORDER_RULE, new TypeReference<List<GroupValueAggrOrderRuleConfigDto>>() {});
        GroupValueEnum valueEnum = GroupValueEnum.fromValue(groupValue);
        List<AggregateOrderRuleConfig> rules = config.stream().filter(p -> p.getGroupValueKey().equals(valueEnum.name())).findFirst().orElse(new GroupValueAggrOrderRuleConfigDto()).getRules();
        if(CollectionUtils.isEmpty(rules)){
            return null;
        }
        rules.sort((p1,p2)->p2.getOrder().compareTo(p1.getOrder()));
        return rules.get(0);
    }

    /**
     *  判断配置条件是否匹配
     * @param configurationDto
     * @param orderQueryDto
     * @return
     */
    private boolean isConditionMatched(ConfigurationDto configurationDto, OrderQueryDto orderQueryDto) {

        if (configurationDto.getCityId() != null && !configurationDto.getCityId().equals(orderQueryDto.getCityId())) {

            return false;
        }

        if (configurationDto.getGroupType() != null && !configurationDto.getGroupType().equals(orderQueryDto.getGroupType())) {

            return false;
        }

        if (configurationDto.getGroupValue() != null && !configurationDto.getGroupValue().equals(orderQueryDto.getGroupValue())) {

            return false;
        }

        if (configurationDto.getProduceWarehouseId() != null && !configurationDto.getProduceWarehouseId().equals(orderQueryDto.getProduceWarehouseId())) {

            return false;
        }

        if (configurationDto.getProductNo() != null && !configurationDto.getProductNo().equals(orderQueryDto.getProductNo())) {

            return false;
        }

        if (configurationDto.getStoreId() != null && !configurationDto.getStoreId().equals(orderQueryDto.getStoreId())) {

            return false;
        }

        return true;
    }
}
