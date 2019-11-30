package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOperateDTO;

import java.util.List;

/**
 * @author yl
 * @description 订单操作服务
 * @date 2019-07-15
 */
public interface DpOrderOperateService {

    /**
     * 同步pda已完成取货的订单.
     *
     * @param deliveredDetailDTO 交付详情DTO
     * @return PlainResult
     */
    PlainResult<TradeOperateDTO> syncDeliveredOrder(DeliveredDetailDTO deliveredDetailDTO);

    /**
     * 同步pda已完成取货的订单,单个多个订单
     * 一个订单校验失败，其他订单都不执行
     *
     * @param deliveredDetailDTO 交付详情DTO
     * @return PlainResult
     */
    PlainResult<TradeOperateDTO> syncDeliveredOrderOneFailAllFail(DeliveredDetailDTO deliveredDetailDTO);


    /**
     * 批量结算,多人多订单.
     *
     * @param deliveredDetailDtoList 交付详情DTO
     * @return PlainResult
     */
    PlainResult<TradeOperateDTO> batchSyncDeliveredOrder(List<DeliveredDetailDTO> deliveredDetailDtoList);

}
