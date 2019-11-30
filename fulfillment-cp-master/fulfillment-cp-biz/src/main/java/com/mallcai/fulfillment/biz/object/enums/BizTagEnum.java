package com.mallcai.fulfillment.biz.object.enums;

import com.mallcai.manager.common.exception.BizException;
import lombok.Getter;

/**
 * @author: Liu Yang
 * @description co_task_config的业务tag枚举
 * @date: 2019-11-12 12:04
 */
@Getter
public enum BizTagEnum {
    TRADE_COMPARE("TRADE_COMPARE", "交易单对账"),
    FRESH_SORTING_COMPARE("FRESH_SORTING_COMPARE", "履约_ERP生鲜分拣对账"),
    FROZEN_COMPARE("FROZEN_ERP_COMPARE", "履约_ERP冻品对账"),
    ERP_WMS_FROZEN_COMPARE("ERP_WMS_FROZEN_COMPARE", "ERP_WMS冻品数据对账"),
    ERP_WMS_FRESH_COMPARE("ERP_WMS_FRESH_COMPARE", "ERP_WMS生鲜数据对账");
    private final String value;
    private final String desc;
    private BizTagEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static BizTagEnum getEnuFromKey(String key){

        for (BizTagEnum enu : BizTagEnum.values()){

            if (enu.getValue().equals(key)){

                return enu;
            }
        }

        throw new BizException();
    }
}
