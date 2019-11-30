package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * detaiDO
 * @author zhanghao
 * @date 2019-08-13 23:50:44
 */
@Data
public class OrderPackageDetailDO implements Serializable {

  private static final long serialVersionUID = -2611558914799992297L;
  private Integer id;

  private String packageNo;

  private Integer productNo;

  private Integer productNum;

  private Integer createUser;

  private Date createTime;

  private String orderId;

    
}