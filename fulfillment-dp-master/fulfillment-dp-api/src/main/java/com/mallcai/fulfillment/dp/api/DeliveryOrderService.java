package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDeliveryResultDTO;
import com.mallcai.fulfillment.dp.api.response.BathDeliveryOrderDTO;

/**
 * 订单发货
 * @author bh.zhong
 * @date 2019/9/6 10:24 AM
 */
public interface DeliveryOrderService {
    /**
     * 发货
     * @return
     */
    PlainResult<Boolean> orderSend(DeliveryOrderDTO deliveryOrderDTO);

    /**
     * 发货
     */
    PlainResult<BathDeliveryOrderDTO> batchOrderSend(BatchDeliveryOrderDTO batchDeliveryOrderDTO);

    /**
     * 单品多运单发货
     * @return
     */
    PlainResult<Boolean> orderMultiPkgSend(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO);

    /**
     * 批量
     * @return
     */
    PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(BatchDeliveryOrderMultiPkgDTO batchDeliveryOrderMultiPkgDTO);
}
