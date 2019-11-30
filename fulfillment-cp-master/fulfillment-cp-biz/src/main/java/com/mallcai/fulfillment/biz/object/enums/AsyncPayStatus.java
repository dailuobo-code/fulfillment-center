package com.mallcai.fulfillment.biz.object.enums;

import lombok.Getter;

/**
 * @author: Liu Yang
 * @description tbl_order中的第三方支付异步通知状态
 * @date: 2019-11-12 12:04
 */
@Getter
public enum AsyncPayStatus {
    SUCCESS("2", "正常(第三方支付异步结果已返回且异步通知结果与同步结果一致)"),
    FAIL("1", "异常(未收到第三方异步通知或异步通知结果与同步结果不一致)");
    private final String value;
    private final String desc;
    private AsyncPayStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
