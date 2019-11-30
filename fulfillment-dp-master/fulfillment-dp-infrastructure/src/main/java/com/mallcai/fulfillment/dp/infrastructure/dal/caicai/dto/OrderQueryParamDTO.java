package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @description 订单查询dto
 * @date 2019-07-16
 */
@Data
public class OrderQueryParamDTO {

  /**
   * 用户Id
   */
  private Long userId;

  /**
   * 支付类型
   */
  private Integer payType;

  /**
   * 时间类型
   */
  private Integer timeType;
  
  /**
   * 查询开始日期
   */
  private Date queryStartDate;

  /**
   * 查询开始日期
   */
  private Date queryEndDate;

  /**
   * 订单状态
   */
  private List<Integer> statusList;

  /**
   * 偏移量
   */
  private Integer offset;

  /**
   * 限制数量
   */
  private Integer limit;

  /**
   * 城市Id
   */
  private Integer cityId;

  /**
   * 门店id
   */
  private Long storeId;
  

}
