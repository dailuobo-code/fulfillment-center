package com.mallcai.fulfillment.pe.biz.service.inner.configuration;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.ConfigurationCondition;
import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.bo.OrderFilterDto;
import com.mallcai.fulfillment.pe.biz.service.bo.TaskConfiguration;
import com.mallcai.fulfillment.pe.biz.service.enums.ConditionEffectiveStageEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.ConfigurationConditionEnum;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 配置条件处理器
 * @author: chentao
 * @create: 2019-09-18 00:30:23
 */
@Slf4j
@Component
public class ConfigurationConditionProcessor {

    @ConfigValue(key = "/app_fulfillment/common/aggregate.order.task.config")
    private JSONObject taskConfig;

    private final String TASK_CONFIG_KEY = "taskConfig";

    public Map<String, DataBo> buildQueryAndFilterCondition() {

        log.info("获取任务配置，content:{}", taskConfig.toJSONString());
        JSONArray jsonArray = taskConfig.getJSONArray(TASK_CONFIG_KEY);

        Map<String, DataBo> task2DataBoMap = new HashMap<>();

        for (int i = 0; i < jsonArray.size(); i++) {

            TaskConfiguration taskConfiguration = JSONObject.toJavaObject(jsonArray.getJSONObject(i), TaskConfiguration.class);

            DataBo dataBo = convertFromConfigurationCondition(taskConfiguration.getConditions());

            task2DataBoMap.put(taskConfiguration.getTaskName() + taskConfiguration.getCron(), dataBo);
        }

        return task2DataBoMap;
    }

    /**
     * 获取配置中的查询、过滤条件
     *
     * @param key
     * @return
     */
    public DataBo getQueryAndFilterCondition(String key) {

        return buildQueryAndFilterCondition().get(key);
    }

    /**
     * 将配置中的条件转为查询条件、过滤条件实体
     * @param conditions
     * @return
     */
    private DataBo convertFromConfigurationCondition(List<ConfigurationCondition> conditions) {

        DataBo dataBo = new DataBo();

        // 保证这两个对象不为null
        OrderQueryDto orderQueryDto = new OrderQueryDto();
        OrderFilterDto orderFilterDto = new OrderFilterDto();

        dataBo.setOrderQueryDto(orderQueryDto);
        dataBo.setOrderFilterDto(orderFilterDto);

        if (conditions == null){

            return dataBo;
        }

        for (ConfigurationCondition configurationCondition : conditions) {

            switch (ConfigurationConditionEnum.fromCondition(configurationCondition.getCondition())) {

                case GROUP_VALUE:

                    if (ConditionEffectiveStageEnum.QUERY.getStage().equals(configurationCondition.getEffectiveStage())) {

                        validateQueryStageConditionContent(configurationCondition.getConditionContent());

                        orderQueryDto.setGroupValue(configurationCondition.getConditionContent());
                    } else {

                        AssertUtils.assertNotNull(configurationCondition.getConditionContent());

                        orderFilterDto.setGroupValue(Arrays.asList(configurationCondition.getConditionContent().trim().split(Constants.SYMBOL_COMMA)));
                    }
                    break;

                case PRODUCT_NO:
                    if (ConditionEffectiveStageEnum.QUERY.getStage().equals(configurationCondition.getEffectiveStage())) {

                        validateQueryStageConditionContent(configurationCondition.getConditionContent());

                        orderQueryDto.setProductNo(configurationCondition.getConditionContent());
                    } else {

                        AssertUtils.assertNotNull(configurationCondition.getConditionContent());

                        orderFilterDto.setProductNo(Arrays.asList(configurationCondition.getConditionContent().trim().split(Constants.SYMBOL_COMMA)));
                    }
                    break;

                case PRODUCE_WAREHOUSE_ID:
                    if (ConditionEffectiveStageEnum.QUERY.getStage().equals(configurationCondition.getEffectiveStage())) {

                        validateQueryStageConditionContent(configurationCondition.getConditionContent());

                        orderQueryDto.setProduceWarehouseId(Integer.valueOf(configurationCondition.getConditionContent().trim()));
                    } else {

                        AssertUtils.assertNotNull(configurationCondition.getConditionContent());

                        List<String> produceWarehouseIdStrs = Arrays.asList(configurationCondition.getConditionContent().trim().split(Constants.SYMBOL_COMMA));
                        orderFilterDto.setProduceWarehouseId(produceWarehouseIdStrs.stream().map(produceWarehouseIdStr -> Integer.valueOf(produceWarehouseIdStr)).collect(Collectors.toList()));
                    }
                    break;

                case CITY_ID:
                    if (ConditionEffectiveStageEnum.QUERY.getStage().equals(configurationCondition.getEffectiveStage())) {

                        validateQueryStageConditionContent(configurationCondition.getConditionContent());

                        orderQueryDto.setCityId(Integer.valueOf(configurationCondition.getConditionContent().trim()));
                    } else {

                        AssertUtils.assertNotNull(configurationCondition.getConditionContent());

                        List<String> cityIdStrs = Arrays.asList(configurationCondition.getConditionContent().trim().split(Constants.SYMBOL_COMMA));
                        orderFilterDto.setCityId(cityIdStrs.stream().map(cityIdStr -> Integer.valueOf(cityIdStr)).collect(Collectors.toList()));
                    }
                    break;

                default:
                    throw new BizException(Errors.CONFIG_CONTENT_ERROR);
            }
        }

        return dataBo;
    }

    private void validateQueryStageConditionContent(String conditionContent) {

        if (conditionContent == null) {

            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }
        if (conditionContent.split(Constants.SYMBOL_COMMA).length > 1) {

            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }
    }
}
