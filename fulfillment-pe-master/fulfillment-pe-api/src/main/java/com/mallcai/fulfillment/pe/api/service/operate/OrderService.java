package com.mallcai.fulfillment.pe.api.service.operate;

import com.mallcai.backend.common.api.PlainResult;

/**
 * @description: 订单业务接口
 * @author: chentao
 * @create: 2019-08-27 22:56:47
 */
public interface OrderService {

    PlainResult<?> cancelOrder(String transOrderId);
}
