package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * @description: 配置的生效条件
 * @author: chentao
 * @create: 2019-09-17 23:28:16
 */
@Data
public class ConfigurationCondition {

    /**
     *  条件 见ConfigurationConditionEnum
     */
    private String condition;

    /**
     * 条件内容,多项用逗号分割。目前暂不支持数据查询阶段有多项，只支持数据过滤阶段有多项
     */
    private String conditionContent;

    /**
     * 条件生效阶段，见ConditionEffectiveStageEnum
     */
    private Integer effectiveStage;
}
