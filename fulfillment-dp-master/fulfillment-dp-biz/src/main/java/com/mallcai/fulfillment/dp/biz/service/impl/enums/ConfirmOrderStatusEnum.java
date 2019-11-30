package com.mallcai.fulfillment.dp.biz.service.impl.enums;

import lombok.Getter;

/**
 * 确认收货
 * @author bh.zhong
 * @date 2019/9/8 9:02 PM
 */
@Getter
public enum ConfirmOrderStatusEnum {

    NO_CONFIRM_ORDER(0, "未确认收货"),
    CONFIRM_ORDER(1, "已确认收货");


    private int code;

    private String desc;

    ConfirmOrderStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 是否已经确认发货
     * @param code
     * @return
     */
    public static boolean isConfirm(int code) {
        return CONFIRM_ORDER.getCode() == code;
    }



}
