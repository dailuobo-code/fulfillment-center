package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 10:03
 */
@Data
public class OrderItemInfoListMsg implements Serializable {

  private static final long serialVersionUID = -1842969742771679415L;

  /**
   * 城市id
   */
  private Integer cityId;
  /**￿
   * 仓库id
   */
  private Integer warehouseId;
  /**
   * 操作的版本号 0:首次插入 大于1：修改
   */
  private Integer version;
  /**
   * 该消息是该城市仓库下的批量发送过程中第几批，从0开始，一批最多发送100个采购对象
   */
  private Integer count;
  /**
   * 预测日期 yyyy-mm-dd
   */
  private String forecastDate;
  /**
   * 该仓本次预测总条数
   */
  private Integer totalCount;
  /**
   * key:cityId_warehouseId_forecastDate,value:已经成功的版本号
   */
  private String redisKey;
  /**
   * 采购对象列表
   */
  List<OrderItemInfoDTO> orderItemInfoDTOList;
}
