package com.mallcai.fulfillment.pe.infrastructure.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-24 17:56:16
 */
@Getter
public enum ProduceOrderStatusEnum {

    CANCEL((byte)-1, "作废"),
    INIT((byte)0, "初始化"),
    PROCESS((byte)1, "处理中"),
    COMPLETE((byte)2, "推单完成");

    /**
     * 状态
     */
    private Byte status;

    private String desc;

    ProduceOrderStatusEnum(Byte status, String desc){

        this.status = status;
        this.desc = desc;
    }
}
