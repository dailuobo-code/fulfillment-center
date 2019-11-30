package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description: 批量数据类型枚举
 * @author: chentao
 * @create: 2019-10-08 21:40:00
 */
@Getter
public enum BatchDataTypeEnum {

    AGGREGATE_ORDER(1, "集单"),
    CREATE_PRODUCE_ORDER(2, "创建生产单");

    private Integer type;

    private String desc;

    BatchDataTypeEnum(Integer type, String desc){

        this.type = type;
        this.desc = desc;
    }
}
