package com.mallcai.fulfillment.pe.common.order;

/**
 * @author wangjingcheng
 * 订单类型
 * 生鲜 、标品 / 有其它类型再进添加
 */
public enum OrderType {

    STANDARD(1, "标品"),
    FRESH(2, "生鲜"),
    ;


    OrderType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;
    private String desc;

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
    }
}
