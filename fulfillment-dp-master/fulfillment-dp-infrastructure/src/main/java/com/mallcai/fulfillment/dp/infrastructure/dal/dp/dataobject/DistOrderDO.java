package com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 配送单信息
 * @author bh.zhong
 * @date 2019/9/8 1:15 PM
 */
@Data
public class DistOrderDO {

  /**
   * 主键id
   */
  private String id;
  /**
   * 配送单号
   */
  private String distOrderNo;
  /**
   * 订单号
   */
  private String orderNo;
  /**
   * 门店Id
   */
  private Long storeId;
  /**
   * 门店Id
   */
  private Integer userId;
  /**
   * 配送的具体状态 发货成功:0 发货失败:1
   */
  private Integer status;
  /**
   * 确认收货状态
   */
  private Integer confirmStatus;
  /**
   * 配送方式
   */
  private Integer distType;
  /**
   * 城市id
   */
  private Integer cityId;
  /**
   * 快递信息修改次数
   */
  private Integer modifyTimes;
  /**
   * 物流编号
   */
  private String expressNo;
  /**
   * 承运公司
   */
  private String expressName;
  /**
   * 收货人人省
   */
  private String receiverProvince;
  /**Name
   * 收货人城市
   */
  private String receiverCity;
  /**
   * 收货人区县
   */
  private String receiverCounty;
  /**
   * 收货人地址
   */
  private String receiverAddress;
  /**
   * 收货人姓名
   */
  private String receiverName;
  /**
   * 收货人联系方式
   */
  private String receiverPhone;
  /**
   * 收货人编号
   */
  private Integer receiverUserId;
  /**
   * 扩展信息
   */
  private String extend;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 更新时间
   */
  private Date updateTime;

  /**
   * 是否置为删除 0-未删除，1-删除
   */
  private Byte isDeleted;
}
