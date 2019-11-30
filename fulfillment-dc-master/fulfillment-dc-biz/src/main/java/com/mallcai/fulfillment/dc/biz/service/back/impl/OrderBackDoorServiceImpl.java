package com.mallcai.fulfillment.dc.biz.service.back.impl;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.biz.service.back.OrderBackDoorService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author bh.zhong
 * @date 2019/11/7 3:39 PM
 */
@Service("orderBackDoorService")
public class OrderBackDoorServiceImpl implements OrderBackDoorService {
    @Resource
    private ReceiveOrderInnerService receiveOrderInnerService;

    @Override
    public PlainResult<?> backReceiveOrder(SOAOrderDetailVO soaOrderDetailVO) {

        Boolean bol = receiveOrderInnerService.receiveOrder(soaOrderDetailVO);

        if (bol) {
            return PlainResult.ok();
        }
        return PlainResult.fail("执行补偿失败");
    }
}
