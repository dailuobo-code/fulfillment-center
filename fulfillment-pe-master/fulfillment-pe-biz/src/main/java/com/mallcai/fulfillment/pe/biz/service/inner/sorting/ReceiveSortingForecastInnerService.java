package com.mallcai.fulfillment.pe.biz.service.inner.sorting;

import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoListMsg;

/**
 * @author: Liu Yang
 * @description 接收DC系统发来的分拣预测消息
 * @date: 2019-10-09 21:55
 */
public interface ReceiveSortingForecastInnerService {

  /**
   *
   * 消息处理
   * @param orderItemInfoListMsg
   * @return
   */
  boolean receiveMsg(OrderItemInfoListMsg orderItemInfoListMsg);

}
