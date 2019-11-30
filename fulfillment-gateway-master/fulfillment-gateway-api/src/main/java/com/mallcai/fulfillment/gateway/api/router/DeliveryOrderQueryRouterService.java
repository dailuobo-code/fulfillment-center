package com.mallcai.fulfillment.gateway.api.router;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.DeliveryOrderQueryService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.ExpressCompanyListDTO;
import com.mallcai.router.api.Router;
import com.mallcai.router.api.RouterType;

import java.util.Map;

/**
 * @author gaoguoming
 * @date 2019-11-11 11:33:14
 */
public interface DeliveryOrderQueryRouterService {
    @Router(group = "fulfillment", serviceClass = DeliveryOrderQueryService.class, methodName = "queryMultiPkgByOrderNo", routeType = RouterType.SINGLE)
    PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(Map<Integer, DeliveryOrderQueryDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderQueryService.class, methodName = "queryMultiPkgByOrderNo", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<DistOrderMultiPkgDTO>> queryMultiPkgByOrderNoMulti(Map<Integer, DeliveryOrderQueryDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderQueryService.class, methodName = "batchQueryMultiPkgByOrderNos", routeType = RouterType.SINGLE)
    PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(Map<Integer, BatchDeliveryOrderQueryDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderQueryService.class, methodName = "batchQueryMultiPkgByOrderNos", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<BatchDistOrderMultiPkgDTO>> batchQueryMultiPkgByOrderNosMulti(Map<Integer, BatchDeliveryOrderQueryDTO> param);
}
