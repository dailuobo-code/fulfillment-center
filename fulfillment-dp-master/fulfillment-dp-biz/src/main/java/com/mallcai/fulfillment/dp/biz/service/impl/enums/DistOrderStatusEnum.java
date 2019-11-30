package com.mallcai.fulfillment.dp.biz.service.impl.enums;

import lombok.Getter;

/**
 * 配送单状态枚举
 * @author bh.zhong
 * @date 2019/9/8 9:02 PM
 */
@Getter
public enum DistOrderStatusEnum {

    NON_DELIVERY("NonDeliver", 0, "未发货"),
    DELIVERING("delivering", 1, "发货中"),
    DELIVERED("delivered", 2, "已发货");

    private String name;

    private int code;

    private String desc;

    DistOrderStatusEnum(String name, int code, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 是否已经发货
     * @param code
     * @return
     */
    public static boolean isDelivered(int code) {
        return DELIVERED.getCode() == code;
    }

    /**
     * 是否允许发货
     * @param code
     * @return
     */
    public static boolean needDeliver(int code) {
        return NON_DELIVERY.getCode() == code
                || DELIVERING.getCode() == code;
    }

}
