package com.mallcai.fulfillment.dc.common.enums;

import lombok.Getter;

/**
 * @description: 调度事件枚举
 * @author: chentao
 * @create: 2019-11-16 00:46:51
 */
@Getter
public enum DispatchEventEnum {

    PRODUCE_ORDER_CREATE("produceOrderCreate", "生产单创建"),
    TMS_LINE_PLAIN("tmsLinePlain", "排线计划事件"),
    TMS_CAR_PLAIN("tmsCarPlain", "排车计划事件");

    private String eventName;

    private String desc;

    DispatchEventEnum(String eventName, String desc){

        this.eventName = eventName;
        this.desc = desc;
    }
}
