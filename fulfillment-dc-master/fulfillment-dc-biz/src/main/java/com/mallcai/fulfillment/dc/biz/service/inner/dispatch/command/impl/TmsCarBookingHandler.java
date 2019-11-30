package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.impl;

import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.biz.service.enums.CallbackStepNameEnum;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.StepDataInfoService;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.EngineExecuteCtx;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.Handler;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.StepResult;
import com.mallcai.fulfillment.dc.biz.service.producer.DispatchCommandMsgProducer;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
@Slf4j
@Service
public class TmsCarBookingHandler implements Handler {

    @Autowired
    private DispatchCommandMsgProducer dispatchCommandMsgProducer;

    @Autowired
    private StepDataInfoService stepDataInfoService;

    @Override
    public StepResult handle(EngineExecuteCtx ctx) {

        DispatchCommandMsg dispatchCommandMsg = new DispatchCommandMsg();

        dispatchCommandMsg.setCommandNo(ctx.getOrderNo());
        dispatchCommandMsg.setCommandType(CommandTypeEnum.TMS_BOOKING_CAR_18);

        dispatchCommandMsg.setData(stepDataInfoService.getStepData(ctx.getOrderWorkFlowStepDO(), CallbackStepNameEnum.PRODUCE_ORDER_CREATE.getCallbackStepName()));

        dispatchCommandMsgProducer.sendDispatchCommandMsg(dispatchCommandMsg);

        return StepResult.succ();
    }
}
