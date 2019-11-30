package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.common.dto.ProductDTO;
import com.mallcai.fulfillment.dp.api.request.OrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.OrderRelationQueryDTO;
import com.mallcai.fulfillment.dp.api.response.PickUpResultDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOrderAssemblyDTO;

import java.util.List;

/**
 * @author yl
 * @description 订单查询服务
 * @date 2019-07-15
 */
public interface DpOrderQueryService {

    /**
     * 批量发货订单信息，支持分页查询
     *
     * @param orderQueryDTO 订单查询dto
     * @return PlainResult
     */
    PlainResult<PickUpResultDTO> queryBatchOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * 根据特点条件，查询关联的订单（此接口为限制异常情况，最多一次返回50条订单）
     *
     * @param orderRelationQuery 订单关联查询dto
     * @return PlainResult
     */
    PlainResult<List<TradeOrderAssemblyDTO>> queryRelationOrderList(OrderRelationQueryDTO orderRelationQuery);

    /**
     * 查询关联订单并锁定
     *
     * @param orderRelationQuery 订单关联查询dto
     * @return PlainResult
     */
    PlainResult<List<TradeOrderAssemblyDTO>> queryOrderAndLock(OrderRelationQueryDTO orderRelationQuery);

    /**
     * 查询强制结算订单列表.
     *
     * @param orderQueryDTO 订单查询dto
     * @return PlainResult
     */
    PlainResult<PickUpResultDTO> queryForcedSettlemenOrderList(OrderQueryDTO orderQueryDTO);

    /**
     * sku剩余.
     *
     * @param orderQueryDTO 订单查询dto
     * @return PlainResult
     */
    PlainResult<List<ProductDTO>> queryOrderProductNumList(OrderQueryDTO orderQueryDTO);
}
