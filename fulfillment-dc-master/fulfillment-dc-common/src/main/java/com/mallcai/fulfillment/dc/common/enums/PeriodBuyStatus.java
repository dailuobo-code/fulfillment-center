package com.mallcai.fulfillment.dc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeriodBuyStatus {
    BOOKING((byte)10,"预定中"),
    BOOKING_SUCC((byte)11,"订购成功"),
    BOOKING_FAIL((byte)12,"订购失败"),
    CANCEL_SUCC((byte)21,"退订成功"),
    CANCEL_FAIL((byte)22,"退订失败");

    private byte code;
    private String desc;
}
