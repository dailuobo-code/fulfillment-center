package com.mallcai.fulfillment.dp.infrastructure.dal.tradehis.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderDetailJsHisDO
 *
 * @author lidege
 * @date 2019/06/11
 */
@Getter
@Setter
@ToString
public class OrderDetailJsHisDO implements Serializable {

  private static final long serialVersionUID = 5699510042158721541L;

  private Integer id;

  private String orderId;

  private Integer specId;

  private Integer userId;

  private Integer cityId;

  private Integer storeId;

  private Integer cityProductId;

  private String prodShowName;

  private String prodIcon;

  private String specName;

  private Integer weight;

  private Integer price;

  private Integer actualWeight;

  private Integer actualPrice;

  private Date createTime;

  private Date updateTime;

  private Integer createUserId;

  private Integer changeFlag;

  private Integer isOutStock;

  private String sortingStr;

  private String remark1;

  private String remark2;

  private Integer refundType;

  private Integer remark4;

  private BigDecimal remark5;

  private BigDecimal remark6;

  private Integer pointPrice;

  private Integer actualPointPrice;

}