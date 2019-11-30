package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import com.google.common.collect.Lists;
import com.mallcai.fulfillment.dc.biz.service.enums.FlowBizTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.FlowStepTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.FlowTypeEnum;
import com.mallcai.fulfillment.dc.biz.service.utils.XmlReadUtil;
import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.enums.FlowStatusEnum;
import com.mallcai.fulfillment.dc.enums.FlowStepStatusEnum;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 14:27:51
 */
@Slf4j
@Component
public class FlowHelper {

    private static final String CONFIG_MAP_KEY_SPLIT = "_";

    public static final Map<String, OrderFlowProcessConfig> processKey2FlowMap = new HashMap<>();

    private static Map<String, FlowNodeConfig> nodeConfigMap = new HashMap<>();

    private static Map<String, NodeStepLinkList> stepLinkListMap = new LinkedHashMap<>();

    private static Map<String, NodeLinkList> group2NodeLinkListMap = new LinkedHashMap<>();

    private static Map<String, FlowNodeStepConfig> stepConfigMap = new HashMap<>();

    private static Map<String, FlowNodeStepConfig> groupFirstTaskConfigMap = new HashMap<>();

    private static NodeLinkList<FlowNodeConfig> nodeLinkList = new NodeLinkList();

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @PostConstruct
    public void init() {

        loadFlowConfig();
    }

    private void loadFlowConfig() {

        OrderFlowProcessConfig produceOrderProcessConfig = XmlReadUtil.readXmlToDto("workflow-produceorder.xml", OrderFlowProcessConfig.class);

        processKey2FlowMap.put(buildProcessKey(produceOrderProcessConfig.getBizTypeName(), produceOrderProcessConfig.getGroup()), produceOrderProcessConfig);

        putNodeAndStepConfig2Map(produceOrderProcessConfig);

    }

    private void buildNodeStepLinkList(OrderFlowProcessConfig processConfig, FlowNodeConfig nodeConfig) {
        NodeStepLinkList nodeLinkList = new NodeStepLinkList();
        List<FlowNodeStepConfig> stepConfigs = nodeConfig.getSteps();
        for (FlowNodeStepConfig stepConfig : stepConfigs) {
//            check(taskConfig);
            Node<FlowNodeStepConfig> stepNode = new Node<>();
            stepNode.setNode(stepConfig);
            nodeLinkList.add(stepNode);
        }
        String key = buildNodeKey(processConfig.getBizTypeName(), processConfig.getGroup(), nodeConfig.getName());
        stepLinkListMap.put(key, nodeLinkList);
    }

    private void putNodeAndStepConfig2Map(OrderFlowProcessConfig processConfig) {
        for (FlowNodeConfig nodeConfig : processConfig.getNodes()) {
            nodeConfig.setGroup(processConfig.getGroup());
            String nodeConfigMapKey = buildNodeKey(processConfig.getBizTypeName(), processConfig.getGroup(), nodeConfig.getName());
            nodeConfigMap.put(nodeConfigMapKey, nodeConfig);

            nodeLinkList.add(nodeConfig.getName());

            putStepConfigMap(processConfig, nodeConfig);
            buildNodeStepLinkList(processConfig, nodeConfig);
        }

        group2NodeLinkListMap.put(buildProcessKey(processConfig.getBizTypeName(), processConfig.getGroup()), nodeLinkList);
    }

    public static String buildProcessKey(String bizTypeName, String group) {
        StringBuilder sb = new StringBuilder();
        sb.append(bizTypeName).append(CONFIG_MAP_KEY_SPLIT).append(group);
        return sb.toString();
    }

    public static String buildNodeKey(String bizTypeName, String group, String nodeName) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildProcessKey(bizTypeName, group))
                .append(CONFIG_MAP_KEY_SPLIT).append(nodeName);
        return sb.toString();
    }

    public static String buildStepKey(String bizTypeName, String group, String nodeName, String stepName) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildNodeKey(bizTypeName, group, nodeName))
                .append(CONFIG_MAP_KEY_SPLIT).append(stepName);
        return sb.toString();
    }

    public FlowNodeStepConfig getFlowNodeStepConfig(String bizTypeName, String group, String nodeName, String stepName) {

        String stepConfigMapKey = buildStepKey(bizTypeName, group, nodeName, stepName);
        return stepConfigMap.get(stepConfigMapKey);
    }

    private void putStepConfigMap(OrderFlowProcessConfig processConfig, FlowNodeConfig nodeConfig) {
        List<FlowNodeStepConfig> stepList = nodeConfig.getSteps();
        boolean isFirstNode = false;
        if (StringUtils.isNotBlank(nodeConfig.getIsFirst()) && Boolean.TRUE.toString().equals(nodeConfig.getIsFirst())) {
            isFirstNode = true;
        }
        for (int i = 0; i < stepList.size(); i++) {
            FlowNodeStepConfig stepConfig = stepList.get(i);

            stepConfig.build(processConfig.getBizTypeName(), processConfig.getGroup(), nodeConfig.getName(), processConfig.getVersion());
            String stepConfigMapKey = buildStepKey(processConfig.getBizTypeName(), processConfig.getGroup(), stepConfig.getNode(), stepConfig.getName());
            stepConfigMap.put(stepConfigMapKey, stepConfig);
//            taskMapByName.put(taskConfig.getGroup() + "_" + taskConfig.getName(),taskConfig);
            if (isFirstNode && i == 0) {
                groupFirstTaskConfigMap.put(buildProcessKey(processConfig.getBizTypeName(), processConfig.getGroup()), stepConfig);
            }
        }
    }

    public List<FlowNodeStepConfig> getNextStepConfig(OrderWorkFlowStepDO orderWorkFlowStepDO) {
        NodeStepLinkList nodeLinkList = getNodeLinkList(FlowBizTypeEnum.fromType(orderWorkFlowStepDO.getBizType()).getName(), orderWorkFlowStepDO.getFlowGroup(), orderWorkFlowStepDO.getNode());
        FlowNodeStepConfig flowNodeStepConfig = getNextTaskConfig(orderWorkFlowStepDO, nodeLinkList, orderWorkFlowStepDO.getStepName());
        if (flowNodeStepConfig == null) {
            return Lists.newArrayList();
        }

        return Lists.newArrayList(flowNodeStepConfig);
    }

    public boolean isFLowFirstStep(String bizTypeName, String group, String stepName) {

        FlowNodeStepConfig flowNodeStepConfig = groupFirstTaskConfigMap.get(buildProcessKey(bizTypeName, group));

        if (flowNodeStepConfig == null) {

            throw new BizException(Errors.FLOW_NOT_EXIST_ERROR);
        }

        if (flowNodeStepConfig.getName().equals(stepName)) {

            return true;
        }

        return false;
    }

    public OrderFlowProcessConfig getProcessConfig(String bizTypeName, String group) {

        return processKey2FlowMap.get(buildProcessKey(bizTypeName, group));
    }

    public OrderWorkFlowDO buildOrderWorkFlow(String orderNo, FlowNodeStepConfig flowNodeStepConfig) {

        OrderFlowProcessConfig orderFlowProcessConfig = processKey2FlowMap.get(buildProcessKey(flowNodeStepConfig.getBizTypeName(), flowNodeStepConfig.getGroup()));

        OrderWorkFlowDO flow = new OrderWorkFlowDO();

        flow.setBizType(FlowBizTypeEnum.fromName(orderFlowProcessConfig.getBizTypeName()).getType());
        flow.setFlowGroup(orderFlowProcessConfig.getGroup());
        flow.setCurrStep(flowNodeStepConfig.getName());
        flow.setVersion(Byte.valueOf(orderFlowProcessConfig.getVersion()));
        flow.setType(Byte.valueOf(orderFlowProcessConfig.getType()));
        flow.setOrderNo(orderNo);
        flow.setStatus(FlowStatusEnum.PROCESS.getStatus());

        return flow;
    }

    public OrderWorkFlowStepDO buildFlowStep(String bizTypeName, String group, String nodeName, String stepName) {

        String stepConfigMapKey = buildStepKey(bizTypeName, group, nodeName, stepName);
        FlowNodeStepConfig stepConfig = stepConfigMap.get(stepConfigMapKey);

        return buildFlowStep(stepConfig);
    }

    public OrderWorkFlowStepDO buildFlowStep(FlowNodeStepConfig stepConfig) {

        OrderWorkFlowStepDO newStep = new OrderWorkFlowStepDO();

        Date now = new Date();

        boolean isNeedDelay = Boolean.TRUE.toString().equals(stepConfig.getIsNeedDelay());

        if (isNeedDelay) {

            if (StringUtils.isEmpty(stepConfig.getIntervalHandler()) && StringUtils.isEmpty(stepConfig.getInterval())) {

                log.error("延迟时间未配置");
                throw new BizException(Errors.FLOW_CONFIG_ERROR);
            }

            if (!StringUtils.isEmpty(stepConfig.getIntervalHandler())) {

                IntervalHandler intervalHandler = listableBeanFactory.getBean(stepConfig.getIntervalHandler(), IntervalHandler.class);

                newStep.setExecuteTime(intervalHandler.getExecuteTime());

            } else {

                newStep.setExecuteTime(DateUtil.addMinutes(now, Integer.valueOf(stepConfig.getInterval())));
            }

            newStep.setStatus(FlowStepStatusEnum.INIT.getStatus());
        } else {

            newStep.setExecuteTime(now);
            newStep.setStatus(FlowStepStatusEnum.PROCESS.getStatus());
        }

        newStep.setBizType(FlowBizTypeEnum.fromName(stepConfig.getBizTypeName()).getType());
        newStep.setHandler(stepConfig.getHandler());
        newStep.setNode(stepConfig.getNode());
        newStep.setVersion(Byte.valueOf(stepConfig.getVersion()));
        newStep.setFlowGroup(stepConfig.getGroup());
        newStep.setType(FlowStepTypeEnum.fromName(stepConfig.getType()).getType());
        newStep.setStepName(stepConfig.getName());

        return newStep;
    }

    private FlowTypeEnum getFlowType(String flowBizTypeName) {

        FlowBizTypeEnum flowBizTypeEnum = FlowBizTypeEnum.fromName(flowBizTypeName);

        FlowTypeEnum flowTypeEnum = FlowTypeEnum.NON_CHILD;

        switch (flowBizTypeEnum) {

            case ORDER:
                PRODUCE_ORDER:

                break;

            case SKU:
                flowTypeEnum = FlowTypeEnum.CHILD;
                break;
            default:
                throw new BizException();
        }

        return flowTypeEnum;
    }

    public NodeStepLinkList<FlowNodeStepConfig> getNodeLinkList(String bizTypeName, String group, String node) {
        String key = buildNodeKey(bizTypeName, group, node);
        return stepLinkListMap.get(key);
    }

    private FlowNodeStepConfig getNextTaskConfig(OrderWorkFlowStepDO orderWorkFlowStepDO, NodeStepLinkList nodeStepLinkList, String currStepName) {
        Node<FlowNodeStepConfig> currTaskNode = nodeStepLinkList.getTaskNode(nodeStepLinkList.getHead(), currStepName);
        if (null == currTaskNode) {
            return null;
        }

        if (currTaskNode.getNext() != null) {

            return currTaskNode.getNext().getNode();
        }

        String nextNodeName = nodeLinkList.getNextNodeName(orderWorkFlowStepDO.getNode());

        if (nextNodeName == null) {

            return null;
        }

        NodeStepLinkList<FlowNodeStepConfig> nextNodeStepLinkList = getNodeLinkList(FlowBizTypeEnum.fromType(orderWorkFlowStepDO.getBizType()).getName(), orderWorkFlowStepDO.getFlowGroup(), nextNodeName);

        return nextNodeStepLinkList.getHead().getNode();
    }

    public FlowNodeConfig getOrderStatusNodeConfig(String bizTypeName, String group, String node) {
        String key = buildNodeKey(bizTypeName, group, node);
        return nodeConfigMap.get(key);
    }
}
