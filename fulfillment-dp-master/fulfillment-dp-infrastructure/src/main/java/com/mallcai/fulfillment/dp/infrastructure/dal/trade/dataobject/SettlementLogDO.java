package com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SettlementLogDO
 * @author zhanghao
 * @date 2019年08月14日00:05:49
 */
@Data
public class SettlementLogDO implements Serializable {

  private static final long serialVersionUID = 7030100080779146L;
  private Long id;

  private String orderId;

  private Long userId;

  private Integer status;

  private Integer type;

  private Date createTime;

  private Date updateTime;

  private Long version;

  private Integer source;

  private String requestData;

  private String deviceId;

  private Integer cityId;

  private Integer storeId;

  private Integer platformSource;

  private Date completeTime;

}