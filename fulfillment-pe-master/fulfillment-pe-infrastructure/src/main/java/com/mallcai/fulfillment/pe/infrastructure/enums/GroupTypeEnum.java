package com.mallcai.fulfillment.pe.infrastructure.enums;

import lombok.Getter;

/**
 * @description: 分组维度枚举
 * @author: chentao
 * @create: 2019-09-16 16:43:06
 */
@Getter
public enum GroupTypeEnum {

    ORDER_TYPE((byte)1, "订单类型"),
    SKU((byte)2, "sku维度"),
    STORE((byte)3, "存储类型"),
    CATEGORY((byte)4,"品类");

    private Byte groupType;

    private String desc;

    GroupTypeEnum(Byte groupType, String desc){

        this.groupType = groupType;
        this.desc = desc;
    }
}
