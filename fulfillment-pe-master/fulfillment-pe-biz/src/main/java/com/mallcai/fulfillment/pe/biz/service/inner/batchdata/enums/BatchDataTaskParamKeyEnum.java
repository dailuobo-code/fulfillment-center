package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums;

import lombok.Getter;

/**
 * @description: 批次任务参数key枚举
 * @author: chentao
 * @create: 2019-10-19 21:39:23
 */
@Getter
public enum BatchDataTaskParamKeyEnum {

    WARE_HOUSE_ID("batch_data_warehouseId", "仓库id"),
    GROUP_VALUE("batch_data_groupValue", "groupValue"),
    ALL_PRODUCE_ORDER_KEY("batch_data_allProduceOrderMapKey", "所有生产单集合redis key");

    private String key;

    private String desc;

    BatchDataTaskParamKeyEnum(String key, String desc){

        this.key = key;
        this.desc = desc;
    }
}
