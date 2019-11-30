package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 14:35:52
 */
@Getter
public enum FlowStepTypeEnum {

    INVOKE((byte)1,"invoke", "invoke"),
    CALLBACK((byte)2,"callback", "callback");

    FlowStepTypeEnum(Byte type, String name, String desc){

        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public static FlowStepTypeEnum fromName(String name){

        for (FlowStepTypeEnum flowStepTypeEnum : FlowStepTypeEnum.values()){

            if (flowStepTypeEnum.name.equals(name)){

                return flowStepTypeEnum;
            }
        }

        return null;
    }
    private Byte type;

    private String name;

    private String desc;
}
