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
public class PurchaseForecastInfoListDTO implements Serializable {

  private static final long serialVersionUID = 2846134254498811668L;
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
   * 预测类型 1当日预测，2提前预测
   */
  private Byte forecastType;
  /**
   * 预测日期 yyyy-mm-dd
   */
  private String forecastDate;
  /**
   * 品类类型
   */
  private Byte categoryType;
  /**
   * 本次预测的数据总量
   */
  private Integer totalCount;
  /**
   * key:cityId_warehouseId_forecastType_categoryType_forecastDate,value:已经成功的版本号
   */
  private String redisKey;
  /**
   * 采购对象列表
   */
  List<PurchaseDTO> purchaseDOList;
}
