package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.enums.FlowStepStatusEnum;
import com.mallcai.fulfillment.dc.mapper.dc.OrderWorkFlowStepMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 18:01:38
 */
@Slf4j
@Repository
public class OrderFlowStepDAO {

    @Autowired
    private OrderWorkFlowStepMapper orderWorkFlowStepMapper;

    public List<OrderWorkFlowStepDO> getNeedExecuteFlowStep(){

        OrderWorkFlowStepCriteria orderWorkFlowStepCriteria = new OrderWorkFlowStepCriteria();

        orderWorkFlowStepCriteria.createCriteria().andStatusEqualTo(FlowStepStatusEnum.INIT.getStatus())
                .andExecuteTimeLessThanOrEqualTo(new Date());

        return orderWorkFlowStepMapper.selectByExample(orderWorkFlowStepCriteria);
    }

    public OrderWorkFlowStepDO createFlowStep(OrderWorkFlowStepDO orderWorkFlowStepDO){

        int count = orderWorkFlowStepMapper.insertSelective(orderWorkFlowStepDO);

        if (count < 1){

            throw new BizException();
        }

        return orderWorkFlowStepDO;
    }

    public void updateStatusById(Long id, Byte targetStatus, Byte underStatus){

        OrderWorkFlowStepCriteria orderWorkFlowStepCriteria = new OrderWorkFlowStepCriteria();
        OrderWorkFlowStepDO update = new OrderWorkFlowStepDO();
        update.setStatus(targetStatus);

        orderWorkFlowStepCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(underStatus);

        int count = orderWorkFlowStepMapper.updateByExampleSelective(update, orderWorkFlowStepCriteria);

        if (count < 1){
            throw new BizException();
        }
    }

    public void completeById(Long id){

        OrderWorkFlowStepCriteria orderWorkFlowStepCriteria = new OrderWorkFlowStepCriteria();
        OrderWorkFlowStepDO update = new OrderWorkFlowStepDO();

        update.setStatus(FlowStepStatusEnum.SUCCESS.getStatus());
        update.setCompleteTime(new Date());

        orderWorkFlowStepCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(FlowStepStatusEnum.PROCESS.getStatus());

        int count = orderWorkFlowStepMapper.updateByExampleSelective(update, orderWorkFlowStepCriteria);

        if (count < 1){
            throw new BizException();
        }
    }
}
