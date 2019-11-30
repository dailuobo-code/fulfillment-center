package com.mallcai.fulfillment.pe.biz.service.enums;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-09-28 17:23
 */
public enum PeOrderSourceEnum {
  CUSTOMER((byte)1, "消费者订单"),
  BIG((byte)2, "大数据"),
  MGR((byte)3, "MGR");
  private byte value;
  private String desc;

  private PeOrderSourceEnum(Byte value, String desc) {
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
