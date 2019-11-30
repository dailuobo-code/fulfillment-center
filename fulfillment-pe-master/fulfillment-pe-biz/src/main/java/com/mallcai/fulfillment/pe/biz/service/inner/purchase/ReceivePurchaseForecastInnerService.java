package com.mallcai.fulfillment.pe.biz.service.inner.purchase;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;

/**
 * @author: Liu Yang
 * @description 接收DC系统发来的采购预测消息（包括采购预测和补采预测）
 * @date: 2019-10-09 21:55
 */
public interface ReceivePurchaseForecastInnerService {

  /**
   *
   * 消息处理
   * @param purchaseForecastInfoListDTO
   * @return
   */
  boolean receiveMsg(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO);

  /**
   * 数据修复，补全货品信息
   * @param purchaseForecastInfoListDTO
   * @return
   */
  PlainResult executeDateRepair(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO);

  /**
   * 执行数据重新推送
   * @param purchaseForecastInfoListDTO
   * @return
   */
  PlainResult executeDateRePush(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO);
}
