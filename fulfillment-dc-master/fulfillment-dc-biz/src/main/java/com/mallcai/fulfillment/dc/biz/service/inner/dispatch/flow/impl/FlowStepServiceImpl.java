package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.impl;

import com.mallcai.fulfillment.dc.biz.service.enums.FlowBizTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.*;
import com.mallcai.fulfillment.dc.dao.dc.OrderFlowDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderFlowStepDAO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 流程节点实现类
 * @author: chentao
 * @create: 2019-11-16 01:41:02
 */
@Slf4j
@Service
public class FlowStepServiceImpl implements FlowStepService {

    @Autowired
    private OrderFlowStepDAO orderFlowStepDAO;

    @Autowired
    private OrderFlowDAO orderFlowDAO;

    @Autowired
    private FlowEngine flowEngine;

    @Autowired
    private FlowHelper flowHelper;

    @Override
    public void handleNeedExecuteFlowStep() {

        List<OrderWorkFlowStepDO> orderWorkFlowStepDOS = orderFlowStepDAO.getNeedExecuteFlowStep();

        if (orderWorkFlowStepDOS.size() == 0) {

            log.info("没有需要处理的节点任务");
            return;
        }

        for (OrderWorkFlowStepDO orderWorkFlowStepDO : orderWorkFlowStepDOS) {

            try {
                executeStep(orderWorkFlowStepDO);
            } catch (Exception e) {
                log.error("流程执行异常,workStepId:{}", orderWorkFlowStepDO.getId(), e);
            }
        }
    }

    private void executeStep(OrderWorkFlowStepDO orderWorkFlowStepDO) {

        OrderWorkFlowDO orderWorkFlowDO = orderFlowDAO.getFlow(orderWorkFlowStepDO.getWorkFlowId());

        FlowNodeStepConfig flowNodeStepConfig = flowHelper.getFlowNodeStepConfig(FlowBizTypeEnum.fromType(orderWorkFlowDO.getBizType()).getName(), orderWorkFlowDO.getFlowGroup(), orderWorkFlowStepDO.getNode(), orderWorkFlowStepDO.getStepName());

        EngineExecuteCtx engineExecuteCtx = EngineExecuteCtx.build().orderNo(orderWorkFlowDO.getOrderNo())
                .orderWorkFlowDO(orderWorkFlowDO)
                .orderWorkFlowStepDO(orderWorkFlowStepDO)
                .flowNodeStepConfig(flowNodeStepConfig);

        flowEngine.execute(true, orderWorkFlowStepDO, engineExecuteCtx);
    }
}
