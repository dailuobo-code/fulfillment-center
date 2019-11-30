package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description 编号类型
 * @date 2019-05-09 15:17
 */
public enum NumberTypeEnum {

    /**
     * 手机号
     */
    PHONE(1, "phone"),

    /**
     * 订单号
     */
    ORDER_ID(2, "orderId"),

    /**
     * 取货卡号
     */
    CARD_ID(3, "cardId"),
    ;
    private Integer type;
    private String desc;

    NumberTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isContainType(Integer type) {
        for (NumberTypeEnum numberTypeEnum : NumberTypeEnum.values()) {
            if (numberTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
