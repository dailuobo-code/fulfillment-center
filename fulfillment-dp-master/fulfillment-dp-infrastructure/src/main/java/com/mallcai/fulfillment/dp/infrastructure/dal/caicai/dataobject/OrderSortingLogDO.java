package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderSortingLogDO
 * @author zhanghao
 * @date 2019-08-13 23:52:28
 */
@Data
public class OrderSortingLogDO implements Serializable {

  private static final long serialVersionUID = 7482979792002166646L;

  private Integer id;

  private Integer cityId;

  private Integer sortingUserId;

  private Integer cityProductId;

  private String orderId;

  private String scaleType;

  private Integer scaleNo;

  private Date sortingDate;

  private Date createTime;

  private String sortingStr;

}