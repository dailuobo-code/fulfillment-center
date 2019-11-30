package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.impl;

import com.mallcai.fulfillment.dc.biz.service.enums.StepDataInfoBizKeyEnum;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command.StepDataInfoService;
import com.mallcai.fulfillment.dc.dao.dc.FlowStepDataInfoDAO;
import com.mallcai.fulfillment.dc.valueobject.dc.FlowStepDataInfoDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
@Slf4j
@Component
public class StepDataInfoServiceImpl implements StepDataInfoService {

    @Autowired
    private FlowStepDataInfoDAO flowStepDataInfoDAO;

    @Override
    public void handleStepDataInfo(OrderWorkFlowStepDO orderWorkFlowStepDO, Map<StepDataInfoBizKeyEnum, String> datas) {

        List<FlowStepDataInfoDO> flowStepDataInfoDOS = new ArrayList<>();

        for (Map.Entry<StepDataInfoBizKeyEnum, String> entry : datas.entrySet()){

            flowStepDataInfoDOS.add(buildDataInfo(orderWorkFlowStepDO, entry.getKey(), entry.getValue()));
        }

        flowStepDataInfoDAO.batchInsert(flowStepDataInfoDOS);
    }

    @Override
    public String getStepData(OrderWorkFlowStepDO orderWorkFlowStepDO, String preStepName) {

        return flowStepDataInfoDAO.getFlowStepData(orderWorkFlowStepDO.getWorkFlowId(), preStepName, StepDataInfoBizKeyEnum.PRODUCE_ORDER.getKey()).getData();
    }

    private FlowStepDataInfoDO buildDataInfo(OrderWorkFlowStepDO orderWorkFlowStepDO, StepDataInfoBizKeyEnum stepDataInfoBizKeyEnum, String data){

        FlowStepDataInfoDO flowStepDataInfoDO = new FlowStepDataInfoDO();

        flowStepDataInfoDO.setWorkFlowId(orderWorkFlowStepDO.getWorkFlowId());
        flowStepDataInfoDO.setWorkFlowStepId(orderWorkFlowStepDO.getId());
        flowStepDataInfoDO.setBizType(orderWorkFlowStepDO.getBizType());
        flowStepDataInfoDO.setFlowGroup(orderWorkFlowStepDO.getFlowGroup());
        flowStepDataInfoDO.setNode(orderWorkFlowStepDO.getNode());
        flowStepDataInfoDO.setStepName(orderWorkFlowStepDO.getStepName());
        flowStepDataInfoDO.setBizKey(stepDataInfoBizKeyEnum.getKey());
        flowStepDataInfoDO.setData(data);

        return flowStepDataInfoDO;
    }
}
