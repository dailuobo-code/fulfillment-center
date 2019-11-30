package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.impl;

import com.mallcai.fulfillment.dc.biz.service.enums.StepDataInfoBizKeyEnum;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.StepDataInfoService;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.EngineExecuteCtx;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.Handler;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.StepResult;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
@Component
public class ProduceOrderCreateCallbackHandler implements Handler {

    @Autowired
    private StepDataInfoService stepDataInfoService;

    @Override
    public StepResult handle(EngineExecuteCtx ctx) {
        OrderWorkFlowStepDO orderWorkFlowStepDO = ctx.getOrderWorkFlowStepDO();

        Map<StepDataInfoBizKeyEnum, String> data = new HashMap<>();

        data.put(StepDataInfoBizKeyEnum.PRODUCE_ORDER, ctx.getCallbackData());
        stepDataInfoService.handleStepDataInfo(orderWorkFlowStepDO, data);

        return StepResult.succ();
    }
}
