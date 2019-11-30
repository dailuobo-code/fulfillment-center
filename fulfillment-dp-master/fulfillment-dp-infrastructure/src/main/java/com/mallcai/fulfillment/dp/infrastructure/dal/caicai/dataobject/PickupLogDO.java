package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * PickupLogDO
 * @author zhanghao
 * @date 2019-08-13 23:54:47
 */
@Data
public class PickupLogDO implements Serializable {

  private static final long serialVersionUID = 370170479997730386L;
  private Integer id;

  private Integer cityId;

  private Long storeId;

  private Date pickupDate;

  private Integer operatorId;

  private Integer appUserId;

  private String pickupJson;

  private Date createTime;

    
}