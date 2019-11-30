package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.command;

import com.mallcai.fulfillment.dc.biz.service.enums.StepDataInfoBizKeyEnum;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;

import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
public interface StepDataInfoService {

    /**
     * 存储节点数据
     * @param orderWorkFlowStepDO
     * @param datas
     */
    void handleStepDataInfo(OrderWorkFlowStepDO orderWorkFlowStepDO, Map<StepDataInfoBizKeyEnum, String> datas);

    /**
     * 获取节点数据
     * @param orderWorkFlowStepDO
     * @param preStepName
     * @return
     */
    String getStepData(OrderWorkFlowStepDO orderWorkFlowStepDO, String preStepName);
}
