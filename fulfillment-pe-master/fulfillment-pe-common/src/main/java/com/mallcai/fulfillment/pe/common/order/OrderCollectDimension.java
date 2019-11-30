package com.mallcai.fulfillment.pe.common.order;

/**
 * @author 王晶城
 * 订单收集维度
 */
public enum OrderCollectDimension {

    PERSONAL(1, "个人"),
    SHOP(2, "门店"),

    ;

    private Integer type;
    private String desc;

    OrderCollectDimension(Integer type, String desc) {
        this.desc = desc;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
