package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description 大数据的消息，采购预测
 * @date: 2019-10-02 10:57
 */
@Data
public class PurchasePredictRequestDTO implements Serializable {

  private static final long serialVersionUID = -5753371897818742742L;
  /**
   * 城市id
   */
  private Integer cityId;
  /**￿
   * 仓库id
   */
  private Integer warehouseId;
  /**
   * 一级分类 1：水果 2：蔬菜
   */
  private Integer categoryType;
  /**
   * 预测日期 yyyy-mm-dd
   */
  private String forecastDate;
  /**
   * 操作的版本号 0:首次插入 大于1：修改
   */
  private Integer version;

}
