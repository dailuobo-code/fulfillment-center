package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

/**
 * @description: 流程节点业务接口
 * @author: chentao
 * @create: 2019-11-16
 */
public interface FlowStepService {

    /**
     * 处理需要执行的delay节点
     */
    void handleNeedExecuteFlowStep();
}
