package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description: 配置条件生效阶段枚举
 * @author: chentao
 * @create: 2019-09-18 00:16:22
 */
@Getter
public enum ConditionEffectiveStageEnum {

    QUERY(1, "数据查询阶段"),
    FILTER(2, "数据过滤阶段");

    private Integer stage;

    private String desc;

    ConditionEffectiveStageEnum(Integer stage, String desc){

        this.stage = stage;
        this.desc = desc;
    }
}
