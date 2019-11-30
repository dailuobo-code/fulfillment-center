package com.mallcai.fulfillment.pe.api.enums;

import lombok.Getter;

/**
 * @description: 订单聚合划分维度
 * @author: chentao
 * @create: 2019-08-23 14:49:58
 */
@Getter
public enum OrderAggregationTypeEnum {

    PRODUCT("product", (byte)1, "categoryOrderDivideRuleService", "产品品类维度"),
    USER("user", (byte)2, "userOrderAggregationRuleService", "用户维度"),
    STORE("store", (byte)3, "storeOrderAggregationRuleService", "门店维度"),
    TRANS_ORDER("transOrder", (byte)4, "transOrderOrderAggregationRuleService", "交易订单维度");

    /**
     * 维度名
     */
    private String name;

    /**
     * 维度值
     */
    private Byte value;

    /**
     * 对应的处理器bean name
     */
    private String processorBeanName;

    /**
     * 描述
     */
    private String desc;

    OrderAggregationTypeEnum(String name, Byte value, String processorBeanName, String desc){

        this.name = name;
        this.value = value;
        this.processorBeanName = processorBeanName;
        this.desc = desc;
    }

    public static OrderAggregationTypeEnum fromValue(Byte value){

        for (OrderAggregationTypeEnum enu : OrderAggregationTypeEnum.values()){

            if (enu.getValue().equals(value)){

                return enu;
            }
        }
        return null;
    }

    public static OrderAggregationTypeEnum fromName(String name){

        for (OrderAggregationTypeEnum enu : OrderAggregationTypeEnum.values()){

            if (enu.getName().equals(name)){

                return enu;
            }
        }
        return null;
    }
}
