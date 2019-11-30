package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description Settlement_log状态
 * @date 2019-06-25
 */
public enum SettlementStatusEnum {
    /**
     * success
     */
    SUCCESS(10, "success"),

    /**
     * fail
     */
    FAIL(20, "fail"),

    /**
     * processing
     */
    PROCESSING(30, "processing"),
    ;

    private Integer code;

    private String describe;

    SettlementStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getdescribe() {
        return this.describe;
    }

    public Integer getCode() {
        return this.code;
    }

}
