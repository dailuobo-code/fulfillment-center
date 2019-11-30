package com.mallcai.fulfillment.biz.object.enums;

import lombok.Getter;

/**
 * @description: 订单状态枚举
 * @author: chentao
 * @create: 2019-08-25 13:29:26
 */
@Getter
public enum PeOrderStatusEnum {

    DELETE((byte)-1, "已删除"),
    INIT((byte)0, "初始化"),
    AGGREGATE_PROCESS((byte)1, "积单处理中"),
    AGGREGATE_COMPLETE((byte)2, "积单完成"),
    PUSH_COMPLETE((byte)3, "推单完成"),
    FILTERED((byte)4, "被过滤的"),
    CANCEL((byte)5, "取消");

    private Byte status;

    private String desc;

    PeOrderStatusEnum(Byte status, String desc){

        this.status = status;
        this.desc = desc;
    }


}
