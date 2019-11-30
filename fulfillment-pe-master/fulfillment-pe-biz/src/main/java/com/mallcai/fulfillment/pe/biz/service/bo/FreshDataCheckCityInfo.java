package com.mallcai.fulfillment.pe.biz.service.bo;

import java.util.List;
import lombok.Data;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-28 17:54
 */
@Data
public class FreshDataCheckCityInfo {
  /**
   * 检测时间 20:40:00
   */
  private String checkTime;
  /**￿
   * 预测类型 1:分拣 2：采购
   */
  private Integer checkType;
  /**
   * (分拣不需要这个字段)
   * 一级分类 1：水果 2：蔬菜
   */
  private Integer categoryType;
  /**
   * 适用于此条规则的城市ID列表
   */
  private List<Integer> cityIdList;


}
