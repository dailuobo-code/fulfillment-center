package com.mallcai.fulfillment.dp.infrastructure.dal.tradehis.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderDetailHisDO
 *
 * @author lidege
 * @date 2019/06/11
 */
@Getter
@Setter
@ToString
public class OrderDetailHisDO implements Serializable {

  private static final long serialVersionUID = -8836270048771155612L;

  private Integer id;

  private String orderId;

  private Integer specId;

  private Integer productNum;

  private BigDecimal price;

  private Date createTime;

  private Date updateTime;

  private Integer userId;

  private Integer cityId;

  private Integer storeId;

  /**
   * 商家id
   */
  private Long merchantId;

  private String isPay;

  private Date payTime;

  private Integer cityProductId;

  private String prodShowName;

  private String isLock;

  private String prodIcon;

  private String specName;

  private Integer starNum;

  private Integer changeFlag;

  private BigDecimal minWeight;

  private BigDecimal maxWeight;

  private Integer minNum;

  private Integer maxNum;

  private String numUnit;

  private String weightUnit;

  private Integer unitType;

  private Integer packageMaxWeight;

  private Integer isGift;

  private BigDecimal originalPrice;

  private BigDecimal couponPrice;

  private BigDecimal vipPrice;

  private String remark1;

  private String remark2;

  private Integer fullPrice;

  private Integer fullId;

  private Integer pointPrice;

  private BigDecimal remark6;

  private BigDecimal totalDiscount;
}