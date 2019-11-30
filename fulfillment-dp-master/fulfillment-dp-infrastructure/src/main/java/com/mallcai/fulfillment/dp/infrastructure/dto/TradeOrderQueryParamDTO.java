package com.mallcai.fulfillment.dp.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单查询参数
 *
 * @author lidege
 * @date 2019/07/19
 */
@Getter
@Setter
@ToString
public class TradeOrderQueryParamDTO implements Serializable {

  private static final long serialVersionUID = -3813567951177048304L;

  /**
   * 用户ID，必填
   */
  private Long userId;

  /**
   * 城市ID，必填
   */
  private Integer cityId;

  /**
   * 订单ID
   */
  private String orderId;

  /**
   * 订单创建时间开始
   */
  private Date createTimeStart;

  /**
   * 订单创建时间截止
   */
  private Date createTimeEnd;

  /**
   * 配送方式：1 自提，2 配送
   */
  private Integer deliveryMode;

  /**
   * 门店ID列表，用于in查询
   */
  private String storeIdsStr;

  /**
   * 状态列表，用于in查询
   */
  private String statusesStr;
}
