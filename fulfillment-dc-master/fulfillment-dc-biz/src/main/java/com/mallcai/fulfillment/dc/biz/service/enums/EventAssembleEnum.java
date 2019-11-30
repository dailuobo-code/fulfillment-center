package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * 事件集合
 *
 * @author bh.zhong
 * @date 2019/8/15 2:04 PM
 */
public enum EventAssembleEnum {

    /**
     * 已推送给WMS
     */
    SEND_WMS("已发送给库房", "FPE", "sendWmsActuator"),
    /**
     * 门店已经接受
     */
    SHOP_ALREADY_RECEIVE("货物已到门店", "MGR", "shopReceiveActuator");
    /**
     * 事件名称
     */
    @Getter
    private String eventName;

    /**
     * 回调来源
     */
    @Getter
    private String fromApp;

    /**
     * 执行器名称，spring bean name
     */
    @Getter
    private String actuatorName;

    EventAssembleEnum(String eventName, String fromApp, String actuatorName) {
        this.eventName = eventName;
        this.fromApp = fromApp;
        this.actuatorName = actuatorName;
    }

    /**
     * 获取值
     * @param eventName
     * @param fromApp
     * @return
     */
    public static EventAssembleEnum getEvent(String eventName, String fromApp) {
        for (EventAssembleEnum eventEnum : values()) {
            if (eventEnum.getEventName().equals(eventName) && eventEnum.getFromApp().equals(fromApp)) {
                return eventEnum;
            }
        }
        return null;
    }


}
