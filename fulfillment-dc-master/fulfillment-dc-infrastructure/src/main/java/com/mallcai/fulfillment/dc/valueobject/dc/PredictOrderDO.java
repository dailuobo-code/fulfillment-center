package com.mallcai.fulfillment.dc.valueobject.dc;

import lombok.Data;

/**
 * @author: Liu Yang
 * @description 预测数据
 * @date: 2019-09-28 16:28
 */
@Data
public class PredictOrderDO {

  /**
   *主键
   */
  private Integer id;
  /**
   *城市ID
   */
  private Integer cityId;
  /**
   *仓库ID
   */
  private Integer wareHouseId;
  /**
   *城市商品ID
   */
  private Integer cityProductId;
  /**
   *最终预测销量（已乘系数）
   */
  private Integer forecastSale;
  /**
   *被预测日
   */
  private String forecastDate;
  /**
   *预测类型 1：分拣预测 2：采购预测
   */
  private Integer forecastType;
}
