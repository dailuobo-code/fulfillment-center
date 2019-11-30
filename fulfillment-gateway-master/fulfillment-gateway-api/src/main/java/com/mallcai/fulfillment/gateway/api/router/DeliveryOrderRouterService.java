package com.mallcai.fulfillment.gateway.api.router;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.DeliveryOrderService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDeliveryResultDTO;
import com.mallcai.fulfillment.dp.api.response.BathDeliveryOrderDTO;
import com.mallcai.router.api.Router;
import com.mallcai.router.api.RouterType;

import java.util.Map;

/**
 * @author gaoguoming
 * @date 2019-11-11 10:58:54
 */
public interface DeliveryOrderRouterService {
    @Router(group = "fulfillment", serviceClass = DeliveryOrderService.class, methodName = "orderMultiPkgSend", routeType = RouterType.SINGLE)
    PlainResult<Boolean> orderMultiPkgSend(Map<Integer, DeliveryOrderMultiPkgDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderService.class, methodName = "orderMultiPkgSend", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<Boolean>> orderMultiPkgSendMulti(Map<Integer, DeliveryOrderMultiPkgDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderService.class, methodName = "batchOrderMultiPkgSend", routeType = RouterType.SINGLE)
    PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(Map<Integer, BatchDeliveryOrderMultiPkgDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderService.class, methodName = "batchOrderMultiPkgSend", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<BatchDeliveryResultDTO>> batchOrderMultiPkgSendMulti(Map<Integer, BatchDeliveryOrderMultiPkgDTO> param);
}
