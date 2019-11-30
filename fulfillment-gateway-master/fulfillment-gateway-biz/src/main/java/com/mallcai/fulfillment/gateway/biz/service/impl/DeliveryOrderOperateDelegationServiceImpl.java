package com.mallcai.fulfillment.gateway.biz.service.impl;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderOperateService;
import com.mallcai.fulfillment.dp.api.request.MultiPkgModifyExpressDTO;
import com.mallcai.fulfillment.gateway.api.service.DeliveryOrderOperateDelegationService;
import com.mallcai.router.api.RouterRequest;
import com.mallcai.router.api.RouterService;
import com.mallcai.router.api.RouterType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gaoguoming
 * @date 2019-11-12 21:55:16
 */
@Service("deliveryOrderOperateDelegationService")
public class DeliveryOrderOperateDelegationServiceImpl implements DeliveryOrderOperateDelegationService {

    @Resource
    private RouterService routerService;

    @Resource
    private RouterCityService routerCityService;

    @Override
    public PlainResult<Boolean> modifyMultiPkgExpress(Set<Integer> cityIds, MultiPkgModifyExpressDTO multiPkgModifyExpressDTO) {
        if (CollectionUtils.isEmpty(cityIds)) {
            cityIds = routerCityService.getAllCity();
        }

        if (CollectionUtils.isEmpty(cityIds)) {
            return PlainResultBuilder.fail(0, "修改物流信息调用失败");
        }
        Map<Integer, MultiPkgModifyExpressDTO> param = new HashMap<>();
        if (cityIds.size() > 1) {
            cityIds.forEach(cityId -> {
                param.put(cityId, multiPkgModifyExpressDTO);
            });
            return modifyMultiPkgExpressMulti(param);
        } else {
            Integer cityId = new ArrayList<>(cityIds).get(0);
            param.put(cityId, multiPkgModifyExpressDTO);
            return modifyMultiPkgExpress(param);
        }
    }

    private PlainResult<Boolean> modifyMultiPkgExpress(Map<Integer, MultiPkgModifyExpressDTO> param) {
        RouterRequest request = createRouterRequest(param, "modifyMultiPkgExpress", RouterType.SINGLE);
        PlainResult<Boolean> result = (PlainResult<Boolean>) routerService.invoke(request);

        return result != null ? result : PlainResultBuilder.fail(0, "单城修改物流信息调用失败");
    }

    private PlainResult<Boolean> modifyMultiPkgExpressMulti(Map<Integer, MultiPkgModifyExpressDTO> param) {
        RouterRequest request = createRouterRequest(param, "modifyMultiPkgExpress", RouterType.MULTI);
        Map<Integer, PlainResult<Boolean>> resultMap = (Map<Integer, PlainResult<Boolean>>) routerService.invoke(request);

        if (MapUtils.isNotEmpty(resultMap)) {
            for (Map.Entry<Integer, PlainResult<Boolean>> entry : resultMap.entrySet()) {
                if (entry.getValue().isSuccess() && entry.getValue().getData()) {
                    return entry.getValue();
                }
            }

            new ArrayList<>(resultMap.values()).get(0);
        }

        return PlainResultBuilder.fail(0, "多城修改物流信息调用失败");
    }

    private RouterRequest createRouterRequest(Map<Integer, ?> param, String methodName, RouterType routerType) {
        RouterRequest routerRequest = new RouterRequest();
        routerRequest.setRouterType(routerType);
        routerRequest.setParamMap(param);
        routerRequest.setServiceClass(DeliveryOrderOperateService.class);
        routerRequest.setMethodName(methodName);
        return routerRequest;
    }
}
