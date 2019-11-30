package com.mallcai.fulfillment.pe.biz.service.inner.transorder;

import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;

/**
 * @description: 交易订单信息处理
 * @author: chentao
 * @create: 2019-09-23 16:41:00
 */
public interface TransOrderInfoHandleService {

    boolean isGroupBuyingOrder(OrderAcceptRequestDTO transOrder);
}
