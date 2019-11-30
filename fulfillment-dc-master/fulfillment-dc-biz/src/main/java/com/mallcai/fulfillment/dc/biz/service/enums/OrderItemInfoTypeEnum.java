package com.mallcai.fulfillment.dc.biz.service.enums;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-09-28 17:23
 */
public enum OrderItemInfoTypeEnum {
  FRESH((byte)1, "生鲜"),
  NORMAL((byte)2, "预售"),
  STANDARD((byte)10, "标品");
  private byte value;
  private String desc;

  private OrderItemInfoTypeEnum(Byte value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public Byte getValue() {
    return this.value;
  }

  public String getDesc() {
    return this.desc;
  }
}
