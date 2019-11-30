package com.mallcai.fulfillment.pe.biz.service.bo;

import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import lombok.Data;

/**
 * @description: 集单规则配置dto
 * @author: chentao
 * @create: 2019-09-19 00:47:35
 */
@Data
public class AggregateOrderRuleConfig {

    /**
     * 规则名称，见OrderAggregationTypeEnum的name
     */
    private String ruleName;

    /**
     * 规则优先级，1-n， 1优先级最高
     */
    private Integer order;

    public String getProcessorBeanName(){

        OrderAggregationTypeEnum orderAggregationTypeEnum = OrderAggregationTypeEnum.fromName(ruleName);

        if (orderAggregationTypeEnum ==  null){

            throw new BizException(Errors.CONFIG_CONTENT_ERROR);
        }

        return orderAggregationTypeEnum.getProcessorBeanName();
    }
}
