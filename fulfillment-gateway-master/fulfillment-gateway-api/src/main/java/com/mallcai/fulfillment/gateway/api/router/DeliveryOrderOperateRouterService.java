package com.mallcai.fulfillment.gateway.api.router;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.DeliveryOrderOperateService;
import com.mallcai.fulfillment.dp.api.request.ModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.MultiPkgModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.OrderConfirmReceiveDTO;
import com.mallcai.router.api.Router;
import com.mallcai.router.api.RouterType;

import java.util.Map;

/**
 * @author gaoguoming
 * @date 2019-11-11 11:23:22
 */
public interface DeliveryOrderOperateRouterService {
    @Router(group = "fulfillment", serviceClass = DeliveryOrderOperateService.class, methodName = "modifyMultiPkgExpress", routeType = RouterType.SINGLE)
    PlainResult<Boolean> modifyMultiPkgExpress(Map<Integer, MultiPkgModifyExpressDTO> param);

    @Router(group = "fulfillment", serviceClass = DeliveryOrderOperateService.class, methodName = "modifyMultiPkgExpress", routeType = RouterType.MULTI)
    Map<Integer, PlainResult<Boolean>> modifyMultiPkgExpressMulti(Map<Integer, MultiPkgModifyExpressDTO> param);
}
