package com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 配送商品明细
 * @author bh.zhong
 * @date 2019/9/8 1:15 PM
 */
@Data
public class DistOrderItemDO {

  /**
   * 主键id
   */
  private String id;
  /**
   * 配送单号
   */
  private String distOrderNo;

  /**
   * 订单号
   */
  private String orderNo;

  /**
   * 店铺Id
   */
  private Long storeId;

  /**
   * 总部商品编号
   */
  private String productNo;

  /**
   * 城市商品ID
   */
  private Long cityProductId;

  /**
   * 商品数量
   */
  private Integer num;
  /**
   * 扩展信息
   */
  private String extend;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;
}
