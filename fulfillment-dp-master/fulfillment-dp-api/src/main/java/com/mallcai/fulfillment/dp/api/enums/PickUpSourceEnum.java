package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description 交付类型枚举
 * @date 2019-06-25
 */
public enum PickUpSourceEnum {
    /**
     * PDA
     */
    PDA(1, "PDA"),

    /**
     * PC
     */
    PC(2, "PC"),
    ;

    private Integer code;

    private String describe;

    PickUpSourceEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getdescribe() {
        return this.describe;
    }

    public Integer getCode() {
        return this.code;
    }

    public static boolean isContainType(Integer type) {
        for (PickUpSourceEnum pickUpSourceEnum : PickUpSourceEnum.values()) {
            if (pickUpSourceEnum.getCode().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
