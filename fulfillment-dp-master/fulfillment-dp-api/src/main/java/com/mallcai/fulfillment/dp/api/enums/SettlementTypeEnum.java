package com.mallcai.fulfillment.dp.api.enums;

/**
 * @author yl
 * @description Settlement_log类型
 * @date 2019-06-25
 */
public enum SettlementTypeEnum {

    /**
     * PICK_UP_LOG
     */
    PICK_UP_LOG(1, "pick_up_log"),
    /**
     * SORTING_LOG
     */
    SORTING_LOG(2, "sorting_log"),

    /**
     * ORDER_DETAIL_JS
     */
    ORDER_DETAIL_JS(3, "order_detail_js"),

    /**
     * CHANGE_UPDATE
     */
    CHANGE_UPDATE(4, "change_update"),

    /**
     * SUCCESS_END
     */
    SUCCESS_END(100, "SUCCESS_END");

    private Integer code;

    private String describe;

    SettlementTypeEnum(Integer code, String describe) {
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
