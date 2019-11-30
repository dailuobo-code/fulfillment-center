package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 快递发货->物流信息
 * 包括物流基本信息 和物流详
 * @author mengru
 * @date 17/12/26
 */
@Data
public class ExpressInfo implements Serializable{

  private static final long serialVersionUID = -6175699673180268532L;
  /**
   * 物流单号
   */
  private String expressNo;

  /**
   * 物流公司id
   */
  private String expressName;

}

