package com.mallcai.fulfillment.gateway.api.service;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderMultiPkgDTO;

import java.util.Set;

/**
 * @author gaoguoming
 * @date 2019-11-12 21:12:55
 */
public interface DeliveryOrderQueryDelegationService {
    PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(Set<Integer> cityIds, DeliveryOrderQueryDTO deliveryOrderQueryDTO);
    PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(Set<Integer> cityIds, BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO);
}
