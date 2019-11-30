package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.biz.service.enums.FlowBizTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.FlowStepTypeEnum;
import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import com.mallcai.fulfillment.dc.dao.dc.FlowAndFlowStepDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderFlowDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderFlowStepDAO;
import com.mallcai.fulfillment.dc.enums.FlowStepStatusEnum;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 流程引擎执行器
 * @author: chentao
 * @create: 2019-11-17 16:02:33
 */
@Slf4j
@Component
public class FlowEngine {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private FlowHelper flowHelper;

    @Autowired
    private FlowAndFlowStepDAO flowAndFlowStepDAO;

    @Autowired
    private OrderFlowStepDAO orderFlowStepDAO;

    @Autowired
    private OrderFlowDAO orderFlowDAO;

    public void startFlow(EngineExecuteCtx ctx, String bizTypeName, String group, String nodeName, String stepName) {

        log.info("开始执行引擎,ctx:{},bizTypeName:{},group:{},nodeName:{},stepName:{}", JSON.toJSONString(ctx), bizTypeName, group, nodeName, stepName);
        boolean isFlowFirstStep = flowHelper.isFLowFirstStep(bizTypeName, group, stepName);
        FlowNodeStepConfig flowNodeStepConfig = flowHelper.getFlowNodeStepConfig(bizTypeName, group, nodeName, stepName);

        ctx.flowNodeStepConfig(flowNodeStepConfig);

        if (isFlowFirstStep) {

            startFirstStepFlow(ctx);
        } else {
            startNonFirstStepFlow(ctx);
        }
    }

    private void startFirstStepFlow(EngineExecuteCtx ctx) {
        log.info("开始执行流程第一步，ctx:{}", JSON.toJSONString(ctx));
        OrderWorkFlowStepDO orderWorkFlowStepDO = createFirstFlowStep(ctx);
        log.info("创建流程节点记录，ctx:{}，step:{}", JSON.toJSONString(ctx), JSON.toJSONString(orderWorkFlowStepDO));
        execute(false, orderWorkFlowStepDO, ctx);
    }

    private void startNonFirstStepFlow(EngineExecuteCtx ctx) {

        FlowNodeStepConfig flowNodeStepConfig = ctx.getFlowNodeStepConfig();

        OrderWorkFlowDO flow = orderFlowDAO.getFlow(FlowBizTypeEnum.fromName(flowNodeStepConfig.getBizTypeName()).getType(), flowNodeStepConfig.getGroup(), ctx.getOrderNo());

        if (flow == null){

            log.error("流程不存在,orderNo:{},bizTypeName:{}", ctx.getOrderNo(), flowNodeStepConfig.getBizTypeName());
            throw new BizException(Errors.FLOW_NOT_EXIST_ERROR);
        }

        ctx.orderWorkFlowDO(flow);

        OrderWorkFlowStepDO orderWorkFlowStepDO = createNonFirstFlowStep(ctx, flowNodeStepConfig);

        // 需要delay的节点等待节点任务被捞起后执行
        if (Boolean.TRUE.toString().equals(flowNodeStepConfig.getIsNeedDelay())){

            return;
        }

        execute(false, orderWorkFlowStepDO, ctx);
    }

    /**
     * 创建流程第一步
     * @param ctx
     * @return
     */
    private OrderWorkFlowStepDO createFirstFlowStep(EngineExecuteCtx ctx) {

        FlowNodeStepConfig flowNodeStepConfig = ctx.getFlowNodeStepConfig();

        OrderWorkFlowStepDO orderWorkFlowStepDO = flowHelper.buildFlowStep(flowNodeStepConfig);

        OrderWorkFlowDO flow = flowHelper.buildOrderWorkFlow(ctx.getOrderNo(), flowNodeStepConfig);

        flowAndFlowStepDAO.createFlowAndStep(flow, orderWorkFlowStepDO);

        ctx.orderWorkFlowDO(flow);
        return orderWorkFlowStepDO;
    }

    private OrderWorkFlowStepDO createNonFirstFlowStep(EngineExecuteCtx ctx, FlowNodeStepConfig flowNodeStepConfig) {

        OrderWorkFlowStepDO orderWorkFlowStepDO = flowHelper.buildFlowStep(flowNodeStepConfig);
        orderWorkFlowStepDO.setWorkFlowId(ctx.getOrderWorkFlowDO().getId());
        flowAndFlowStepDAO.createNonFirstStep(orderWorkFlowStepDO);

        return orderWorkFlowStepDO;
    }

    /**
     * 执行流程步骤
     * @param isDelayStep
     * @param orderWorkFlowStepDO
     * @param ctx
     */
    public void execute(boolean isDelayStep, OrderWorkFlowStepDO orderWorkFlowStepDO, EngineExecuteCtx ctx) {

        // delay节点任务执行需要  先置为处理中
        if (isDelayStep){

            processStep(orderWorkFlowStepDO);
        }

        ctx.orderWorkFlowStepDO(orderWorkFlowStepDO);

        boolean notNeedExecuteHandler = FlowStepTypeEnum.CALLBACK.getName().equals(orderWorkFlowStepDO.getType()) && StringUtils.isEmpty(orderWorkFlowStepDO.getHandler());

        if (!notNeedExecuteHandler) {

            Handler handler = listableBeanFactory.getBean(orderWorkFlowStepDO.getHandler(), Handler.class);

            StepResult stepResult;
            try {
                stepResult = handler.handle(ctx);
            } catch (Exception e) {
                log.error("handler执行异常，flowStepId:{},stepName:{}",orderWorkFlowStepDO.getId(), orderWorkFlowStepDO.getStepName(), e);
                retryStep(orderWorkFlowStepDO);
                return;
            }

            if (!stepResult.isSuc()) {

                retryStep(orderWorkFlowStepDO);

                return;
            }
        }

        completeStep(orderWorkFlowStepDO);

        FlowNodeStepConfig flowNodeStepConfig = ctx.getFlowNodeStepConfig();

        // 非驱动节点则不往下走
        if (Boolean.FALSE.toString().equals(flowNodeStepConfig.getIsDrive())){

            return;
        }

        nextStep(ctx, orderWorkFlowStepDO);
    }

    private void completeStep(OrderWorkFlowStepDO orderWorkFlowStepDO) {

        orderFlowStepDAO.completeById(orderWorkFlowStepDO.getId());
    }

    private void nextStep(EngineExecuteCtx ctx, OrderWorkFlowStepDO currFlowStep) {

        // 目前最多只有一个
        List<FlowNodeStepConfig> nextFlowNodeStepConfigs = flowHelper.getNextStepConfig(currFlowStep);

        if (nextFlowNodeStepConfigs.size() == 0) {

            completeFlow(currFlowStep);
        }

        // 如果下一步是callback，则流程结束
        FlowNodeStepConfig nextFlowNodeStepConfig = nextFlowNodeStepConfigs.get(0);

        if (FlowStepTypeEnum.CALLBACK.getName().equals(nextFlowNodeStepConfig.getType())){

            return;
        }

        ctx.flowNodeStepConfig(nextFlowNodeStepConfig);
        startNonFirstStepFlow(ctx);
    }

    private void completeFlow(OrderWorkFlowStepDO orderWorkFlowStepDO) {

        orderFlowDAO.completeFlow(orderWorkFlowStepDO.getWorkFlowId());
    }

    /**
     * 节点处理中
     *
     * @param orderWorkFlowStepDO
     */
    private void processStep(OrderWorkFlowStepDO orderWorkFlowStepDO) {

        orderFlowStepDAO.updateStatusById(orderWorkFlowStepDO.getId(), FlowStepStatusEnum.PROCESS.getStatus(), FlowStepStatusEnum.INIT.getStatus());
    }

    /**
     * 改变下状态待任务重试
     *
     * @param orderWorkFlowStepDO
     */
    private void retryStep(OrderWorkFlowStepDO orderWorkFlowStepDO) {

        orderFlowStepDAO.updateStatusById(orderWorkFlowStepDO.getId(), FlowStepStatusEnum.INIT.getStatus(), FlowStepStatusEnum.PROCESS.getStatus());
    }
}
