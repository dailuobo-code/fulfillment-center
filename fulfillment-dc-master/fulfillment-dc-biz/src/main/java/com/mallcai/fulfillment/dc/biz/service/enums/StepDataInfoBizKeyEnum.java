package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 01:03:21
 */
@Getter
public enum StepDataInfoBizKeyEnum {

    PRODUCE_ORDER("produceOrder", "生产单");

    private String key;

    private String desc;

    StepDataInfoBizKeyEnum(String key, String desc){

        this.key = key;
        this.desc = desc;
    }
}
