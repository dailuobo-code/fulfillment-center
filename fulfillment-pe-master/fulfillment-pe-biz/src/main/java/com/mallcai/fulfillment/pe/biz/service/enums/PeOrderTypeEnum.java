package com.mallcai.fulfillment.pe.biz.service.enums;

/**
 * @author 刘洋
 * 订单类型
 * 生鲜 、标品 / 有其它类型再进添加
 */
public enum PeOrderTypeEnum {
    FRESH(1, "生鲜"),
    BOOKING(2, "预售"),
    STANDARD(12, "标品")
    ;


    PeOrderTypeEnum(Integer type, String desc) {
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
