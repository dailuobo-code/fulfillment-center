package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description:配置的生效条件枚举
 * @author: chentao
 * @create: 2019-09-18 00:09:57
 */
@Getter
public enum ConfigurationConditionEnum {

    GROUP_VALUE("groupValue", "groupValue"),
    PRODUCT_NO("productNo", "商品编号"),
    CITY_ID("cityId", "城市id"),
    PRODUCE_WAREHOUSE_ID("produceWarehouseId", "生产仓ID");

    private String condition;

    private Integer effectiveStage;

    private String desc;

    ConfigurationConditionEnum(String condition, String desc){

        this.condition = condition;
        this.desc = desc;
    }

    public static ConfigurationConditionEnum fromCondition(String condition){

        for (ConfigurationConditionEnum configurationConditionEnum : ConfigurationConditionEnum.values()){

            if (configurationConditionEnum.getCondition().equals(condition)){

                return configurationConditionEnum;
            }
        }

        return null;
    }
}
