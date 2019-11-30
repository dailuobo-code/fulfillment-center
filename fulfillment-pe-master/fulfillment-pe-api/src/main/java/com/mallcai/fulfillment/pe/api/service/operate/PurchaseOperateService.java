package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoQueryDTO;

/**
 * @author: Liu Yang
 * @description 预测订单处理
 * @date: 2019-10-11 23:06
 */
public interface PurchaseOperateService {

  /**
   * 取消本日该仓的数据
   * @param purchaseForecastInfoListDTO
   * @return
   */
  PlainResult<?> cancelPurchase(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO);

  /**
   * 查询该仓数据是否全部被处理完
   * @param purchaseForecastInfoQueryDTO
   * @return
   */
  PlainResult<?> getPurchaseStatusByCondition(
      PurchaseForecastInfoQueryDTO purchaseForecastInfoQueryDTO);
}
