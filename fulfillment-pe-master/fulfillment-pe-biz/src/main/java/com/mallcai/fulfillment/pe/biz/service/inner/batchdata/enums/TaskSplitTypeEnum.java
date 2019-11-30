package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums;


/**
 * @description: 任务分割类型枚举，唯一id或游标分割
 * @author: chentao
 * @create: 2019-10-21 22:28:54
 */
public enum TaskSplitTypeEnum {

    UNIQUE_ID(1, "唯一ID"),
    CURSOR(2, "游标");

    private Integer type;

    private String desc;

    TaskSplitTypeEnum(Integer type, String desc){

        this.type = type;
        this.desc = desc;
    }
}
