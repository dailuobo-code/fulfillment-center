package com.mallcai.fulfillment.dc.biz.service.enums;

import lombok.Getter;

/**
 * @description: 发货仓类型枚举
 * @author: chentao
 * @create: 2019-08-26 18:57:10
 */
@Getter
public enum WareHouseTypeEnum {

    WARE_HOUSE_FRESH(1, "生鲜仓"),
    WARE_HOUSE_STANDARD(2, "标仓"),
    WARE_HOUSE_VIRTUAL(3, "虚拟");

    private Integer type;

    private String desc;

    WareHouseTypeEnum(Integer type, String desc){

        this.type = type;
        this.desc = desc;
    }
}
