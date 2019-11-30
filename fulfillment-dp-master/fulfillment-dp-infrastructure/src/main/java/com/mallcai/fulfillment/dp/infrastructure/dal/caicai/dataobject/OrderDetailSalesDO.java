package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

/**
 * 订单详情 商品销量
 *
 * @author: wangtao
 * @date:2019-07-22
 */
@Data
public class OrderDetailSalesDO {

  /**
   * 门店id
   */
  private Long storeId;

  /**
   * 商品id
   */
  private Long cityProductId;

  /**
   * 销量 （已付款）
   */
  private Integer salesNum;

  /**
   * 销售额 （已结算）
   */
  private Long salesAmount;

}
