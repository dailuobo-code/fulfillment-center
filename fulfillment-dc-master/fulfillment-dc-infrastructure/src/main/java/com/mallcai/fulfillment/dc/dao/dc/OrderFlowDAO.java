package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.enums.FlowStatusEnum;
import com.mallcai.fulfillment.dc.mapper.dc.OrderWorkFlowMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 17:59:05
 */
@Slf4j
@Repository
public class OrderFlowDAO {

    @Autowired
    private OrderWorkFlowMapper orderWorkFlowMapper;

    public OrderWorkFlowDO getFlow(Byte bizType, String group, String orderNo) {

        OrderWorkFlowCriteria orderWorkFlowCriteria = new OrderWorkFlowCriteria();

        orderWorkFlowCriteria.createCriteria().andOrderNoEqualTo(orderNo)
                .andBizTypeEqualTo(bizType)
                .andFlowGroupEqualTo(group);

        return orderWorkFlowMapper.selectByExample(orderWorkFlowCriteria).get(0);
    }

    public OrderWorkFlowDO getFlow(Long flowId) {

        return orderWorkFlowMapper.selectByPrimaryKey(flowId);
    }

    public OrderWorkFlowDO createOrderFlow(OrderWorkFlowDO orderWorkFlowDO) {

        int count = orderWorkFlowMapper.insertSelective(orderWorkFlowDO);

        if (count < 1) {

            throw new BizException();
        }

        return orderWorkFlowDO;
    }

    public void updateCurrStepById(Long id, String currStep) {

        OrderWorkFlowDO update = new OrderWorkFlowDO();

        update.setId(id);
        update.setCurrStep(currStep);

        int count = orderWorkFlowMapper.updateByPrimaryKeySelective(update);

        if (count < 1) {

            throw new BizException();
        }
    }

    public void completeFlow(Long id) {

        OrderWorkFlowCriteria orderWorkFlowCriteria = new OrderWorkFlowCriteria();
        OrderWorkFlowDO update = new OrderWorkFlowDO();

        update.setStatus(FlowStatusEnum.SUCCESS.getStatus());

        orderWorkFlowCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(FlowStatusEnum.PROCESS.getStatus());

        int count = orderWorkFlowMapper.updateByExampleSelective(update, orderWorkFlowCriteria);

        if (count < 1) {

            throw new BizException();
        }
    }
}
