package com.mallcai.fulfillment.gateway.biz.service.impl;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDeliveryResultDTO;
import com.mallcai.fulfillment.dp.api.response.ErrorOrderInfo;
import com.mallcai.fulfillment.gateway.api.service.DeliveryOrderDelegationService;
import com.mallcai.router.api.RouterRequest;
import com.mallcai.router.api.RouterService;
import com.mallcai.router.api.RouterType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author gaoguoming
 * @date 2019-11-12 20:05:33
 */
@Service("deliveryOrderDelegationService")
public class DeliveryOrderDelegationServiceImpl implements DeliveryOrderDelegationService {

    @Resource
    private RouterService routerService;

    @Resource
    private RouterCityService routerCityService;

    private static final String ORDERID_NOT_EXIST = "180100004";

    @Override
    public PlainResult<Boolean> orderMultiPkgSend(Set<Integer> cityIds, DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO) {

        if (CollectionUtils.isEmpty(cityIds)) {
            cityIds = routerCityService.getAllCity();
        }

        if (CollectionUtils.isEmpty(cityIds)) {
            return PlainResultBuilder.fail(0, "发货调用失败");
        }
        Map<Integer, DeliveryOrderMultiPkgDTO> param = new HashMap<>();
        if (cityIds.size() > 1) {
            cityIds.forEach(cityId -> {
                param.put(cityId, deliveryOrderMultiPkgDTO);
            });
            return orderMultiPkgSendMulti(param);
        } else {
            Integer cityId = new ArrayList<>(cityIds).get(0);
            param.put(cityId, deliveryOrderMultiPkgDTO);
            return orderMultiPkgSend(param);
        }
    }

    private PlainResult<Boolean> orderMultiPkgSend(Map<Integer, DeliveryOrderMultiPkgDTO> param) {
        RouterRequest request = createRouterRequest(param, "orderMultiPkgSend", RouterType.SINGLE);
        PlainResult<Boolean> result = (PlainResult<Boolean>) routerService.invoke(request);

        return result != null ? result : PlainResultBuilder.fail(0, "单城发货调用失败");
    }

    private PlainResult<Boolean> orderMultiPkgSendMulti(Map<Integer, DeliveryOrderMultiPkgDTO> param) {
        RouterRequest request = createRouterRequest(param, "orderMultiPkgSend", RouterType.MULTI);
        Map<Integer, PlainResult<Boolean>> resultMap = (Map<Integer, PlainResult<Boolean>>) routerService.invoke(request);

        if (MapUtils.isNotEmpty(resultMap)) {
            for (Map.Entry<Integer, PlainResult<Boolean>> entry : resultMap.entrySet()) {
                if (entry.getValue().isSuccess() && entry.getValue().getData()) {
                    return entry.getValue();
                }
            }

            new ArrayList<>(resultMap.values()).get(0);
        }

        PlainResult<Boolean> result = PlainResultBuilder.fail(0, "多城发货失败");

        return result;
    }

    @Override
    public PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(Set<Integer> cityIds, BatchDeliveryOrderMultiPkgDTO batchDeliveryOrderMultiPkgDTO) {

        //cityIds为空，默认调用全部城市
        if (CollectionUtils.isEmpty(cityIds)) {
            cityIds = routerCityService.getAllCity();
        }

        if (CollectionUtils.isEmpty(cityIds)) {
            return PlainResultBuilder.fail(0, "发货调用失败");
        }
        Map<Integer, BatchDeliveryOrderMultiPkgDTO> param = new HashMap<>();
        //多城
        if (cityIds.size() > 1) {
            cityIds.forEach(cityId -> {
                param.put(cityId, batchDeliveryOrderMultiPkgDTO);
            });
            return batchOrderMultiPkgSendMulti(param);
        } else {
            Integer cityId = new ArrayList<>(cityIds).get(0);
            param.put(cityId, batchDeliveryOrderMultiPkgDTO);
            return batchOrderMultiPkgSend(param);
        }
    }

    private PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(Map<Integer, BatchDeliveryOrderMultiPkgDTO> param) {
        RouterRequest request = createRouterRequest(param, "batchOrderMultiPkgSend", RouterType.SINGLE);
        PlainResult<BatchDeliveryResultDTO> result = (PlainResult<BatchDeliveryResultDTO>) routerService.invoke(request);

        return result != null ? result : PlainResultBuilder.fail(0, "单城批量发货调用失败");
    }

    private PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSendMulti(Map<Integer, BatchDeliveryOrderMultiPkgDTO> param) {
        RouterRequest request = createRouterRequest(param, "batchOrderMultiPkgSend", RouterType.MULTI);
        Map<Integer, PlainResult<BatchDeliveryResultDTO>> resultMap = (Map<Integer, PlainResult<BatchDeliveryResultDTO>>) routerService.invoke(request);

        if (MapUtils.isEmpty(resultMap)) {
            return PlainResultBuilder.fail(0, "多城批量发货失败");
        }

        //某个城市，所有都success，则返回
        for (Map.Entry<Integer, PlainResult<BatchDeliveryResultDTO>> entry : resultMap.entrySet()) {
            if (entry.getValue().getData().getAllSuccess()) {
                return entry.getValue();
            }
        }

        //合并结果，
        Map<String, ErrorOrderInfo> errMap = new HashMap<>();
        Set<String> errOrderNoSet = new HashSet<>();
        AtomicInteger count = new AtomicInteger(0);
        resultMap.forEach((k, v) -> {
            //错误信息暂存
            v.getData().getErrorOrderInfos().forEach(errorOrderInfo -> {
                if (!ORDERID_NOT_EXIST.equals(errorOrderInfo.getMessage()) || !errMap.containsKey(errorOrderInfo.getOrderNo()) ) {
                    if (ORDERID_NOT_EXIST.equals(errorOrderInfo.getMessage())) {
                        errorOrderInfo.setMessage("订单" + errorOrderInfo.getOrderNo() + "不存在");
                    }
                    errMap.put(errorOrderInfo.getOrderNo(), errorOrderInfo);
                }
            });
            //当前城调用返回结果
            List<String> cityErrorNos =
                    v.getData().getErrorOrderInfos()
                            .stream()
                            .map(ErrorOrderInfo::getOrderNo)
                            .collect(Collectors.toList());
            int countVal = count.incrementAndGet();
            if (countVal == 1) {
                errOrderNoSet.addAll(cityErrorNos);
            } else {
                errOrderNoSet.retainAll(cityErrorNos);
            }
        });

        if (CollectionUtils.isEmpty(errOrderNoSet)) {
            BatchDeliveryResultDTO batchDeliveryResultDTO = new BatchDeliveryResultDTO();
            batchDeliveryResultDTO.setAllSuccess(true);
            batchDeliveryResultDTO.setErrorOrderInfos(new ArrayList<>());

            return PlainResultBuilder.success(batchDeliveryResultDTO);
        }

        List<ErrorOrderInfo> resultErrorList =
                errOrderNoSet.stream()
                        .map(orderNo -> errMap.get(orderNo))
                        .collect(Collectors.toList());
        BatchDeliveryResultDTO batchDeliveryResultDTO = new BatchDeliveryResultDTO();
        batchDeliveryResultDTO.setAllSuccess(false);
        batchDeliveryResultDTO.setErrorOrderInfos(resultErrorList);

        return PlainResultBuilder.success(batchDeliveryResultDTO);
    }

    private RouterRequest createRouterRequest(Map<Integer, ?> param, String methodName, RouterType routerType) {
        RouterRequest routerRequest = new RouterRequest();
        routerRequest.setRouterType(routerType);
        routerRequest.setParamMap(param);
        routerRequest.setServiceClass(DeliveryOrderService.class);
        routerRequest.setMethodName(methodName);
        return routerRequest;
    }
}
