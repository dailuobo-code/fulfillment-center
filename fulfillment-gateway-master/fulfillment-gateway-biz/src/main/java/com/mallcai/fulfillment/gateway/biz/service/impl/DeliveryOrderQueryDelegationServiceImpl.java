package com.mallcai.fulfillment.gateway.biz.service.impl;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderQueryService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderMultiPkgDTO;
import com.mallcai.fulfillment.gateway.api.service.DeliveryOrderQueryDelegationService;
import com.mallcai.router.api.RouterRequest;
import com.mallcai.router.api.RouterService;
import com.mallcai.router.api.RouterType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gaoguoming
 * @date 2019-11-12 21:14:27
 */
@Service("deliveryOrderQueryDelegationService")
public class DeliveryOrderQueryDelegationServiceImpl implements DeliveryOrderQueryDelegationService {

    @Resource
    private RouterService routerService;

    @Resource
    private RouterCityService routerCityService;

    @Override
    public PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(Set<Integer> cityIds, DeliveryOrderQueryDTO deliveryOrderQueryDTO) {
        if (CollectionUtils.isEmpty(cityIds)) {
            cityIds = routerCityService.getAllCity();
        }

        if (CollectionUtils.isEmpty(cityIds)) {
            return PlainResultBuilder.fail(0, "修改物流信息调用失败");
        }
        Map<Integer, DeliveryOrderQueryDTO> param = new HashMap<>();
        if (cityIds.size() > 1) {
            cityIds.forEach(cityId -> {
                param.put(cityId, deliveryOrderQueryDTO);
            });
            return queryMultiPkgByOrderNoMulti(param);
        } else {
            Integer cityId = new ArrayList<>(cityIds).get(0);
            param.put(cityId, deliveryOrderQueryDTO);
            return queryMultiPkgByOrderNo(param);
        }
    }

    private PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(Map<Integer, DeliveryOrderQueryDTO> param) {
        RouterRequest request = createRouterRequest(param, "queryMultiPkgByOrderNo", RouterType.SINGLE);
        PlainResult<DistOrderMultiPkgDTO> result = (PlainResult<DistOrderMultiPkgDTO>) routerService.invoke(request);

        return result != null ? result : PlainResultBuilder.fail(0, "单城查询物流信息调用失败");
    }

    private PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNoMulti(Map<Integer, DeliveryOrderQueryDTO> param) {
        RouterRequest request = createRouterRequest(param, "queryMultiPkgByOrderNo", RouterType.MULTI);
        Map<Integer, PlainResult<DistOrderMultiPkgDTO>> resultMap = (Map<Integer, PlainResult<DistOrderMultiPkgDTO>>) routerService.invoke(request);

        if (MapUtils.isNotEmpty(resultMap)) {
            for (Map.Entry<Integer, PlainResult<DistOrderMultiPkgDTO>> entry : resultMap.entrySet()) {
                if (entry.getValue().isSuccess()) {
                    return entry.getValue();
                }
            }

            new ArrayList<>(resultMap.values()).get(0);
        }

        return PlainResultBuilder.fail(0, "多城查询物流信息失败");
    }

    @Override
    public PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(Set<Integer> cityIds, BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO) {
        if (CollectionUtils.isEmpty(cityIds)) {
            cityIds = routerCityService.getAllCity();
        }

        if (CollectionUtils.isEmpty(cityIds)) {
            return PlainResultBuilder.fail(0, "修改物流信息调用失败");
        }
        Map<Integer, BatchDeliveryOrderQueryDTO> param = new HashMap<>();
        if (cityIds.size() > 1) {
            cityIds.forEach(cityId -> {
                param.put(cityId, batchDeliveryOrderQueryDTO);
            });
            return batchQueryMultiPkgByOrderNosMulti(param);
        } else {
            Integer cityId = new ArrayList<>(cityIds).get(0);
            param.put(cityId, batchDeliveryOrderQueryDTO);
            return batchQueryMultiPkgByOrderNos(param);
        }
    }

    private PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(Map<Integer, BatchDeliveryOrderQueryDTO> param) {
        RouterRequest request = createRouterRequest(param, "batchQueryMultiPkgByOrderNos", RouterType.SINGLE);
        PlainResult<BatchDistOrderMultiPkgDTO> result = (PlainResult<BatchDistOrderMultiPkgDTO>) routerService.invoke(request);

        return result != null ? result : PlainResultBuilder.fail(0, "单城批量查询物流信息调用失败");
    }

    private PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNosMulti(Map<Integer, BatchDeliveryOrderQueryDTO> param) {
        RouterRequest request = createRouterRequest(param, "batchQueryMultiPkgByOrderNos", RouterType.MULTI);
        Map<Integer, PlainResult<BatchDistOrderMultiPkgDTO>> resultMap = (Map<Integer, PlainResult<BatchDistOrderMultiPkgDTO>>) routerService.invoke(request);

        if (MapUtils.isEmpty(resultMap)) {
            return PlainResultBuilder.fail(0, "多城批量查询物流信息调用失败");
        }

        List<DistOrderMultiPkgDTO> distOrderMultiPkgDTOList = new ArrayList<>();
        Set<String> processedOrderNos = new HashSet<>();

        resultMap.forEach((k, v) -> {
            if (v.isSuccess()) {
                List<DistOrderMultiPkgDTO> list = v.getData().getDistOrderMultiPkgDTOList();
                List<DistOrderMultiPkgDTO> filteredList = list.stream()
                        .filter(distOrderMultiPkgDTO -> !processedOrderNos.contains(distOrderMultiPkgDTO.getOrderNo()))
                        .collect(Collectors.toList());
                processedOrderNos.addAll(filteredList.stream().map(DistOrderMultiPkgDTO::getOrderNo).collect(Collectors.toSet()));
                distOrderMultiPkgDTOList.addAll(filteredList);
            }
        });

        BatchDistOrderMultiPkgDTO batchDistOrderMultiPkgDTO = new BatchDistOrderMultiPkgDTO();
        batchDistOrderMultiPkgDTO.setDistOrderMultiPkgDTOList(distOrderMultiPkgDTOList);
        return PlainResultBuilder.success(batchDistOrderMultiPkgDTO);
    }

    private RouterRequest createRouterRequest(Map<Integer, ?> param, String methodName, RouterType routerType) {
        RouterRequest routerRequest = new RouterRequest();
        routerRequest.setRouterType(routerType);
        routerRequest.setParamMap(param);
        routerRequest.setServiceClass(DeliveryOrderQueryService.class);
        routerRequest.setMethodName(methodName);
        return routerRequest;
    }
}
