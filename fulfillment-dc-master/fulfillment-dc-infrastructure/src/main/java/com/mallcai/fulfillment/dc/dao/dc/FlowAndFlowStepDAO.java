package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 17:57:43
 */
@Repository
public class FlowAndFlowStepDAO {

    @Autowired
    private OrderFlowDAO orderFlowDAO;

    @Autowired
    private OrderFlowStepDAO orderFlowStepDAO;

    @Transactional(value = "fdcTransactionManager", rollbackFor = Exception.class)
    public OrderWorkFlowStepDO createFlowAndStep(OrderWorkFlowDO orderWorkFlowDO, OrderWorkFlowStepDO orderWorkFlowStepDO){

        orderFlowDAO.createOrderFlow(orderWorkFlowDO);

        orderWorkFlowStepDO.setWorkFlowId(orderWorkFlowDO.getId());
        return orderFlowStepDAO.createFlowStep(orderWorkFlowStepDO);
    }

    @Transactional(value = "fdcTransactionManager", rollbackFor = Exception.class)
    public void createNonFirstStep(OrderWorkFlowStepDO orderWorkFlowStepDO){

        orderFlowStepDAO.createFlowStep(orderWorkFlowStepDO);

        orderFlowDAO.updateCurrStepById(orderWorkFlowStepDO.getWorkFlowId(), orderWorkFlowStepDO.getStepName());
    }
}
