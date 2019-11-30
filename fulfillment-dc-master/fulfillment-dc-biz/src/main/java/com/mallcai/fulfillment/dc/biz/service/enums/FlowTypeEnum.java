package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 16:58:57
 */
@Getter
public enum FlowTypeEnum {

    NON_CHILD((byte)1, "非子流程"),
    CHILD((byte)2, "子流程");

    private Byte type;

    private String desc;

    FlowTypeEnum (Byte type, String desc){

        this.type = type;
        this.desc = desc;
    }
}
