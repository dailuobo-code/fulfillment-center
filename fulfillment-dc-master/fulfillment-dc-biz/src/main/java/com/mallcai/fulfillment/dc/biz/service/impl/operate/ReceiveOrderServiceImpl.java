package com.mallcai.fulfillment.dc.biz.service.impl.operate;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.ReceiveOrderService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 接收订单
 * @author bh.zhong
 * @date 2019/8/17 4:30 PM
 */
@Service
@Slf4j
public class ReceiveOrderServiceImpl implements ReceiveOrderService {

    @Resource
    private ReceiveOrderInnerService receiveOrderInnerService;

    @Override
    public PlainResult<?> receiveOrder(OrderRequestDTO req) {
        PlainResult result  = new PlainResult();
        //result.setResult(receiveOrderInnerService.receiveOrder(req));
        return result;
    }

}
