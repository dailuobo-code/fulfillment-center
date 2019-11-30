package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 16:15:08
 */
public enum StepResultStatusEnum {

    EXECUTE_SUCCESS(1),
    EXECUTE_FAIL(-1);

    @Getter
    int code;

    StepResultStatusEnum(int code) {
        this.code = code;
    }
}
