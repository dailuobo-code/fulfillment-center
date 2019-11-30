package com.mallcai.fulfillment.biz.object.enums;

import com.mallcai.manager.common.exception.BizException;
import lombok.Getter;

@Getter
public enum OrderTypeEnum {
    STANDARD(1, "标品"),
    FROZEN(2, "冻品"),
    FRESH(3, "生鲜");

    private final int value;

    private final String desc;

    private OrderTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static OrderTypeEnum getEnuFromKey(int key){

        for (OrderTypeEnum enu : OrderTypeEnum.values()){

            if (enu.getValue() == key){

                return enu;
            }
        }

        throw new BizException();
    }
}
