package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-23 17:15:58
 */
public interface OrderAcceptService {

    PlainResult<?> acceptOrder(OrderAcceptRequestDTO transOrder);
}
