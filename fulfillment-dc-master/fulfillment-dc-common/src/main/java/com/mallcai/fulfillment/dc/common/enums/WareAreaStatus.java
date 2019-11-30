package com.mallcai.fulfillment.dc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WareAreaStatus {
    CANCEL((byte)-1,"已取消"),
    VALID((byte)1,"生效");

    private byte code;
    private String desc;
}
