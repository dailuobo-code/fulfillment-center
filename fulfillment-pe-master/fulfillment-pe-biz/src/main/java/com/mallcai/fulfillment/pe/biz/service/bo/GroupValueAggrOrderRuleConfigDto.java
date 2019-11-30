package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @description: 品类集单规则配置
 * @author: chentao
 * @create: 2019-09-19 00:56:47
 */
@Data
public class GroupValueAggrOrderRuleConfigDto {

    /**
     * 见GroupValueEnum的key
     */
    private String groupValueKey;

    private List<AggregateOrderRuleConfig> rules;
}
