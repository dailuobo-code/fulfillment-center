package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.callback.impl;

import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.DispatchCommandReceiptMsg;
import com.mallcai.fulfillment.dc.biz.service.enums.CallbackStepNameEnum;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.callback.CallbackEventService;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.EngineExecuteCtx;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.FlowEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17
 */
@Service
public class CallbackEventServiceImpl implements CallbackEventService {

    @Autowired
    private FlowEngine flowEngine;

    @Override
    public void handleCallback(DispatchCommandReceiptMsg dispatchCommandReceiptMsg) {

        CallbackStepNameEnum callbackStepNameEnum = CallbackStepNameEnum.fromCallbackStepName(dispatchCommandReceiptMsg.getDispatchEventEnum().getEventName());

        if (callbackStepNameEnum == null){

            return;
        }

        EngineExecuteCtx ctx = EngineExecuteCtx.build()
                .orderNo(dispatchCommandReceiptMsg.getCommandNo())
                .callbackData(dispatchCommandReceiptMsg.getData());

        flowEngine.startFlow(ctx, callbackStepNameEnum.getBizTypeName(), callbackStepNameEnum.getGroup(),
                callbackStepNameEnum.getNode(), callbackStepNameEnum.getCallbackStepName());
    }
}
