package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-19 19:24:26
 */
@Getter
public enum DataReloadAndAggregateOrderLockRequesterEnum {

    DATA_RELOAD("data_reload", "数据重入"),
    AGGREGATE_ORDER("aggregate_order", "集单");

    private String requester;

    private String desc;

    DataReloadAndAggregateOrderLockRequesterEnum(String requester, String desc){

        this.requester = requester;
        this.desc = desc;
    }
}
