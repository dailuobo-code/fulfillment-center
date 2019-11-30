package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 发货明细
 * @author bh.zhong
 * @date 2019/9/6 10:27 AM
 */
@Data
public class DeliveryItemDTO implements Serializable{

  private static final long serialVersionUID = -8854276427333721601L;

  /**
   * 总部订单商品Id
   */
  private String productNo;
  /**
   * 城市订单商品Id
   */
  private Long cityProductId;

  /**
   * 需要发货的商品数量
   */
  private Integer num;

  /**
   * 需要发货的商品重量（可不传）
   */
  private Long weight;
  /**
   * 商品发货补重量差价扩展字段:做快照用
   */
  private String extend;
}
