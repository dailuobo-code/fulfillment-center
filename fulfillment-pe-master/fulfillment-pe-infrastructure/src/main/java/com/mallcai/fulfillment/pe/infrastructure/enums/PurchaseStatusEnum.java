package com.mallcai.fulfillment.pe.infrastructure.enums;

import lombok.Getter;

/**
 * 采购枚举
 * @author bh.zhong
 * @date 2019/10/13 11:48 PM
 */
@Getter
public enum PurchaseStatusEnum {

    INIT((byte)0, "初始化"),
    PURCHASE_SUCCESS((byte)10, "推送完成处理中");

    private Byte status;

    private String desc;

    PurchaseStatusEnum(Byte status, String desc){

        this.status = status;
        this.desc = desc;
    }

}
