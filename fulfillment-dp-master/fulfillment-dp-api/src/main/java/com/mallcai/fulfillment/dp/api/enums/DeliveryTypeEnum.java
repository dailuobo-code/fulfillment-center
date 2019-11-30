package com.mallcai.fulfillment.dp.api.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配送方式
 * @author bh.zhong
 * @date 2019/9/6 1:38 PM
 */
public enum DeliveryTypeEnum {

    /**
     * 同城配送
     */

    /**
     * 快递配送
     */
    EXPRESS_MERCHANT_CALL(1, "快递-商家呼叫快递");
    @Getter
    private int code;

    @Getter
    private String desc;

    DeliveryTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    /**
     * 支持的发货方式
     */
    public static List<Integer> ALLOWED_DELIVERYTYPE = new ArrayList<>();
    static{
        for (DeliveryTypeEnum item : values()) {
            ALLOWED_DELIVERYTYPE.add(item.getCode());
        }
    }

    public static DeliveryTypeEnum findByCode(int code) {
        for (DeliveryTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

}
