package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 10:25
 */
@Data
public class PurchaseDTO implements Serializable {

  private static final long serialVersionUID = 3886826530358008060L;

  /**
   * 主键
   */
  private Long id;

  /**
   * 城市ID
   */
  private Integer cityId;

  /**
   * 仓库ID
   */
  private Integer warehouseId;

  /**
   * 门店ID
   */
  private Integer storeId;

  /**
   * 城市商品ID
   */
  private Integer cityProductId;

  /**
   * 预测数量
   */
  private Integer forecastNum;
  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;
  /**
   * 预测时间
   */
  private Date forecastDate;
  /**
   * 版本号
   */
  private Integer version;
  /**
   * 预测类型 1-当日预测(补采) 2-提前预测
   */
  private Byte forecastType;
  /**
   * 品类类型 类型待定
   */
  private Byte categoryType;
}
