package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * PointRefundErrorLogDO
 * @author zhanghao
 * @date 2019-08-13 23:56:28
 */
@Data
public class PointRefundErrorLogDO implements Serializable {

  private static final long serialVersionUID = -2080061302957174505L;

  private Integer id;

  private String orderId;

  private Integer point;

  private String msg;

  private Date createTime;
  
}