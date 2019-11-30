package com.mallcai.fulfillment.biz.enums;

import com.google.common.base.Objects;
import com.mallcai.wms.oub.client.enums.OutboundBillTypeEnum;
import com.mallcai.wms.oub.client.enums.PerformDeliveryOrderDetailTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分组值枚举，所有值不重复(方便后期扩展，只使用一个字段也可以
 *  定位属于哪个分组)
 * @author: chentao
 * @create: 2019-09-16 17:00:44
 */
@Getter
@AllArgsConstructor
public enum GroupValueEnum {

    STANDARD("1", OutboundBillTypeEnum.STAND_PRODUCT, PerformDeliveryOrderDetailTypeEnum.STAND_PRODUCT,"订单类型-标品"),
    FROZEN("2", OutboundBillTypeEnum.FINISHED_PRODUCT, PerformDeliveryOrderDetailTypeEnum.FINISHED_PRODUCT,"存储类型-冻品"),
    FRESH("3", OutboundBillTypeEnum.RAW_PRODUCT,  PerformDeliveryOrderDetailTypeEnum.RAW_PRODUCT,"品类-生鲜");


    private String key;

    private OutboundBillTypeEnum bondtype;

    private PerformDeliveryOrderDetailTypeEnum deliveryType;

    private String desc;

    public static GroupValueEnum fromKey(String key){

        for (GroupValueEnum groupValueEnum : GroupValueEnum.values()){

            if (groupValueEnum.getKey().equals(key)){

                return groupValueEnum;
            }
        }

        return null;
    }
}
