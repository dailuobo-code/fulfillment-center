package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Liu Yang
 * @description 大数据的消息，分拣预测
 * @date: 2019-10-02 10:57
 */
@Data
public class CancleProduceRequestDTO implements Serializable {

  private static final long serialVersionUID = 3045346547895281221L;
  /**
   * 城市id
   */
  private Integer cityId;
  /**￿
   * 仓库id
   */
  private Integer warehouseId;
  /**
   * 预测日期 yyyy-mm-dd
   */
  private String forecastDate;

}
