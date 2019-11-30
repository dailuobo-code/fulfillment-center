package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description 时间类型
 * @date 2019-05-09 15:17
 */
public enum TimeTypeEnum {

    /**
     * 创建时间
     */
    CREATE_TIME(1, "create_time"),

    /**
     * 更新时间
     */
    UPDATE_TIME(2, "update_time"),

    /**
     * 取货时间
     */
    PICKUP_TIME(3, "pickup_time"),
    ;

    private Integer type;
    private String desc;

    TimeTypeEnum(Integer type, String desc) {
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
        for (TimeTypeEnum timeTypeEnum : TimeTypeEnum.values()) {
            if (timeTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
