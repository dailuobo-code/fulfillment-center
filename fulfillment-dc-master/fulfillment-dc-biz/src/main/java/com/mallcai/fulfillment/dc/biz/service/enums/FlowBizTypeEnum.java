package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 14:35:52
 */
@Getter
public enum FlowBizTypeEnum {

    ORDER((byte)1,"order", "订单"),
    PRODUCE_ORDER((byte)2,"produceOrder", "生产单"),
    SKU((byte)3,"sku", "订单"),
    CAR_PLAIN((byte)4,"carPlain", "排车计划");

    FlowBizTypeEnum(Byte type, String name, String desc){

        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public static FlowBizTypeEnum fromName(String name){

        for (FlowBizTypeEnum flowBizTypeEnum : FlowBizTypeEnum.values()){

            if (flowBizTypeEnum.name.equals(name)){

                return flowBizTypeEnum;
            }
        }

        return null;
    }

    public static FlowBizTypeEnum fromType(Byte type){

        for (FlowBizTypeEnum flowBizTypeEnum : FlowBizTypeEnum.values()){

            if (flowBizTypeEnum.type.equals(type)){

                return flowBizTypeEnum;
            }
        }

        return null;
    }

    private Byte type;

    private String name;

    private String desc;
}
