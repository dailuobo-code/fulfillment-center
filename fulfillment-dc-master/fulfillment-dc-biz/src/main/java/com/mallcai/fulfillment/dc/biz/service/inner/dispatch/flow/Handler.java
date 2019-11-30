package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17
 */
public interface Handler {

    StepResult handle(EngineExecuteCtx ctx);
}
