package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 16:01:50
 */
@Data
public class EngineExecuteCtx {

    private String callbackData;

    private String orderNo;

    private OrderWorkFlowStepDO orderWorkFlowStepDO;

    private OrderWorkFlowDO orderWorkFlowDO;

    private FlowNodeStepConfig flowNodeStepConfig;

    public static EngineExecuteCtx build() {
        return new EngineExecuteCtx();
    }

    public EngineExecuteCtx callbackData(String callbackData) {
        this.callbackData = callbackData;
        return this;
    }

    public EngineExecuteCtx orderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public EngineExecuteCtx orderWorkFlowStepDO(OrderWorkFlowStepDO orderWorkFlowStepDO) {
        this.orderWorkFlowStepDO = orderWorkFlowStepDO;
        return this;
    }

    public EngineExecuteCtx orderWorkFlowDO(OrderWorkFlowDO orderWorkFlowDO) {
        this.orderWorkFlowDO = orderWorkFlowDO;
        return this;
    }

    public EngineExecuteCtx flowNodeStepConfig(FlowNodeStepConfig flowNodeStepConfig) {
        this.flowNodeStepConfig = flowNodeStepConfig;
        return this;
    }
}
