package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * UserPickupChangeErrorLogDO
 * @author zhanghao
 * @date 2019-08-14 00:21:58
 */
@Data
public class UserPickupChangeErrorLogDO implements Serializable {

  private static final long serialVersionUID = -7948622270963424446L;

  private Integer id;

  private Integer userId;

  private String orderIds;

  private Integer totalChange;

  private String msg;

  private Date createTime;

}