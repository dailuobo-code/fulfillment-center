package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description 大数据的消息，分拣预测
 * @date: 2019-10-02 10:57
 */
@Data
public class SortingRequestDTO implements Serializable {

  private static final long serialVersionUID = -6733465199738976965L;
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
  /**
   * 操作的版本号 0:首次插入 大于1：修改
   */
  private Integer version;

}
