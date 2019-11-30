package com.mallcai.fulfillment.dc.api.service.dc.operate;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderRequestDTO;

/**
 * 类 {@code ReceiveOrderService} 接收订单，门面层.
 * @author bh.zhong
 * @date 2019/8/17 4:13 PM
 */
public interface ReceiveOrderService {

    /**
     * 接收订单
     */
    PlainResult<?> receiveOrder(OrderRequestDTO req);

}
