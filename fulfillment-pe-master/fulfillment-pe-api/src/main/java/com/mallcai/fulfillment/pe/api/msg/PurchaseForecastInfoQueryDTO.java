package com.mallcai.fulfillment.pe.api.msg;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 10:03
 */
@Data
public class PurchaseForecastInfoQueryDTO implements Serializable {

  private static final long serialVersionUID = 2846134254498811668L;
  /**
   * 城市id
   */
  private Integer cityId;
  /**￿
   * 仓库id
   */
  private Integer warehouseId;
  /**
   * 操作的版本号 0:首次插入 大于1：修改
   */
  private Integer version;
  /**
   * 预测类型 1当日预测，2提前预测
   */
  private Byte forecastType;
  /**
   * 预测日期 yyyy-mm-dd
   */
  private String forecastDate;
  /**
   * 品类类型
   */
  private Byte categoryType;
}
