package com.mallcai.fulfillment.dc.biz.service.bo;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 18:04:28
 */
@Data
public class FlowAndFlowStepBO {

    private OrderWorkFlowDO orderWorkFlowDO;

    private OrderWorkFlowStepDO orderWorkFlowStepDO;

}
