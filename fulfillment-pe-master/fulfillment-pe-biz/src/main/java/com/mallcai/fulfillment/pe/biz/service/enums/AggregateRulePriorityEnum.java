package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description: 聚合规则优先级
 * @author: chentao
 * @create: 2019-08-24 16:53:42
 */
@Getter
public enum AggregateRulePriorityEnum {

    PRIORITY_1(1, "第一优先级"),
    PRIORITY_2(2, "第二优先级");

    /**
     * 优先级，值越小优先级越高
     */
    private Integer priority;

    private String desc;

    AggregateRulePriorityEnum(Integer priority, String desc){

        this.priority = priority;
        this.desc = desc;
    }
}
