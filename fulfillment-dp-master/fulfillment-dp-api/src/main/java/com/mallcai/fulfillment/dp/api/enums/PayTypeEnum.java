package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description 订单支付类型
 * @date 2019-05-09 15:17
 */
public enum PayTypeEnum {

    /**
     * 积分订单
     */
    BALANCE(0, "balance"),

    /**
     * 支付宝订单
     */
    ALIPAY(1, "alipay"),

    /**
     * 微信订单
     */
    WECHART(2, "wechart"),

    /**
     * 积分订单
     */
    POINT(3, "point"),

    ;
    private Integer type;
    private String desc;

    PayTypeEnum(Integer type, String desc) {
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
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (payTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
