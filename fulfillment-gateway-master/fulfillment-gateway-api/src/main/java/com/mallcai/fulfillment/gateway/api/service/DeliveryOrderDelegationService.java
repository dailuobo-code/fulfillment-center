package com.mallcai.fulfillment.gateway.api.service;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDeliveryResultDTO;

import java.util.Set;

/**
 * @author gaoguoming
 * @date 2019-11-12 20:03:20
 */
public interface DeliveryOrderDelegationService {
    PlainResult<Boolean> orderMultiPkgSend(Set<Integer> cityIds, DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO);
    PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(Set<Integer> cityIds, BatchDeliveryOrderMultiPkgDTO batchDeliveryOrderMultiPkgDTO);
}
