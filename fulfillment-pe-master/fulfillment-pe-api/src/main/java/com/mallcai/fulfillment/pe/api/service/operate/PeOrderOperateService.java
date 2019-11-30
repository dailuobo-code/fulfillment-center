package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CancleProduceRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderItemInfoListDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.SortingOrderRequestDTO;

/**
 * @author: Liu Yang
 * @description 接单接口
 * @date: 2019-10-10 21:38
 */
public interface PeOrderOperateService {

  /**
   * 接收来自DC的MGR订单导入信息
   * @param orderItemInfoListDTO
   * @return
   */
  PlainResult<?> acceptOrderItem(OrderItemInfoListDTO orderItemInfoListDTO);

  /**
   * 作废订单
   * @param sortingOrderRequestDTO
   * @return
   */
  PlainResult<?> cancelOrder(SortingOrderRequestDTO sortingOrderRequestDTO);

  /**
   * 取消生产单
   * @param dto
   * @return
   */
  PlainResult<?> cancelProduceOrder(CancleProduceRequestDTO dto);

}
