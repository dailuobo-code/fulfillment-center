package com.mallcai.fulfillment.dc.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-14 11:17:22
 */
@Getter
public enum FlowStepStatusEnum {

    INIT((byte)0, "初始化"),
    PROCESS((byte)1,"处理中"),
    SUCCESS((byte)2, "成功"),
    FAIL((byte)3,"失败");

    /**
     * 状态
     */
    private Byte status;

    private String desc;

    FlowStepStatusEnum(Byte status, String desc){

        this.status = status;
        this.desc = desc;
    }
}
