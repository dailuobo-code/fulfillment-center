package com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SettlementWarnLogDO
 * @author zhanghao
 * @date 2019-08-14 00:08:10
 */
@Data
public class SettlementWarnLogDO implements Serializable {

  private static final long serialVersionUID = -3561519239418718897L;
  private Long id;

  private String orderId;

  private Long userId;

  private Date createTime;

  private Date updateTime;

  private Integer type;

  private Long version;

  private String deviceId;

  private Long operateId;

  private Integer cityId;

  private Integer storeId;

}