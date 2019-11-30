package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:回调步骤名枚举，唯一
 * @author: chentao
 * @create: 2019-11-17 22:59:55
 */
@Getter
public enum CallbackStepNameEnum {

    PRODUCE_ORDER_CREATE("produceOrder", "common", "produceOrderCreate", "produceOrderCreate");

    private String bizTypeName;

    private String group;

    private String node;

    private String callbackStepName;

    CallbackStepNameEnum(String bizTypeName, String group, String node, String callbackStepName){

        this.bizTypeName = bizTypeName;
        this.group = group;
        this.node = node;
        this.callbackStepName = callbackStepName;
    }

    public static CallbackStepNameEnum fromCallbackStepName(String callbackStepName){

        for (CallbackStepNameEnum callbackStepNameEnum : CallbackStepNameEnum.values()){

            if (callbackStepNameEnum.getCallbackStepName().equals(callbackStepName)){

                return callbackStepNameEnum;
            }
        }

        return null;
    }
}
