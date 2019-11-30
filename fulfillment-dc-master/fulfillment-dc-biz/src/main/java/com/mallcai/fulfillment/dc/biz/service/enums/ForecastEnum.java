package com.mallcai.fulfillment.dc.biz.service.enums;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-09-28 17:23
 */
public enum ForecastEnum {
  SORT(1, "分拣预测"),
  PURCHASE(2, "采购预测");
  private int value;
  private String desc;

  private ForecastEnum(int value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public int getValue() {
    return this.value;
  }

  public String getDesc() {
    return this.desc;
  }
}
