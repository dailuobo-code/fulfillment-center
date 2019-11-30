package com.mallcai.fulfillment.pe.api.enums;

import lombok.Getter;

/**
 * @description: 仓库顺序枚举
 * @author: chentao
 * @create: 2019-09-17 00:18:15
 */
@Getter
public enum WarehouseOrderEnum {

    ORDER_1(1, "第一位"),
    ORDER_2(2, "第二位");

    private Integer order;

    private String desc;

    WarehouseOrderEnum(Integer order, String desc){

        this.order = order;
        this.desc = desc;
    }
}
