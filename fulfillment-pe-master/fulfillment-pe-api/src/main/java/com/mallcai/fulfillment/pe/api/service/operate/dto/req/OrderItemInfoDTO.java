package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-10 21:41
 */
@Data
public class OrderItemInfoDTO implements Serializable {

  private static final long serialVersionUID = 7514275894842085638L;
  private Long id;

  private String orderNo;

  private Byte orderType;

  private Byte orderSource;

  private Integer cityProductId;

  private Integer specId;

  private Integer productNum;

  private Integer storeId;

  private Integer userId;

  private String pickupTime;

  private Integer cityId;

  private Integer warehouseId;

  private String productNo;

  private String itemExt;

  private Date createTime;

  private Date updateTime;
}
