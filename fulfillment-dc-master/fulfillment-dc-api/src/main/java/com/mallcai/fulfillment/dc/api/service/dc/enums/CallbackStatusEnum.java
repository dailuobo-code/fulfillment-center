package com.mallcai.fulfillment.dc.api.service.dc.enums;

import lombok.Getter;

/**
 * 履约链路状态枚举
 * @author bh.zhong
 * @date 2019/8/31 5:56 PM
 */
public enum CallbackStatusEnum {

    /**
     * 已推送给WMS
     */
    WMS_OUT_STOCK(10,"库房已出库"),
    /**
     * 门店已经接受
     */
    STORE_RECEIVE(50,"门店已收货");
    /**
     * 事件名称
     */
    @Getter
    private String receiveStatusMsg;

    @Getter
    private Integer status;


    CallbackStatusEnum(Integer status,String receiveStatusMsg) {
        this.receiveStatusMsg = receiveStatusMsg;
        this.status = status;
    }

    /**
     * 获取值
     * @param status
     * @return
     */
    public static CallbackStatusEnum getEvent(Integer status) {
        for (CallbackStatusEnum statusEnum : values()) {
            if (statusEnum.getStatus().equals(status)) {
                return statusEnum;
            }
        }
        return null;
    }

}
