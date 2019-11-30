package com.mallcai.fulfillment.dp.biz.service.impl.enums;

import lombok.Getter;

/**
 * 配送单删除状态
 * @author gaoguoming
 * @date 2019-10-30 17:06:19
 */
@Getter
public enum DeletedStatusEnum {
    NON_DELETED((byte)0, "未删除"),
    DELETED((byte)1, "已删除");


    private byte code;

    private String desc;

    DeletedStatusEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
