package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.mapper.dc.FlowStepDataInfoMapper;
import com.mallcai.fulfillment.dc.valueobject.dc.FlowStepDataInfoCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.FlowStepDataInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-17 22:03:12
 */
@Slf4j
@Repository
public class FlowStepDataInfoDAO {

    @Autowired
    private FlowStepDataInfoMapper flowStepDataInfoMapper;

    public FlowStepDataInfoDO getFlowStepData(Long flowStepId, String bizKey){

        FlowStepDataInfoCriteria flowStepDataInfoCriteria = new FlowStepDataInfoCriteria();

        flowStepDataInfoCriteria.createCriteria().andWorkFlowStepIdEqualTo(flowStepId)
                .andBizKeyEqualTo(bizKey);

        List<FlowStepDataInfoDO> flowStepDataInfoDOS = flowStepDataInfoMapper.selectByExample(flowStepDataInfoCriteria);

        if (flowStepDataInfoDOS.size() > 1){

            throw new BizException();
        }

        return flowStepDataInfoDOS.get(0);
    }

    public FlowStepDataInfoDO getFlowStepData(Long flowId, String stepName, String bizKey){

        FlowStepDataInfoCriteria flowStepDataInfoCriteria = new FlowStepDataInfoCriteria();

        flowStepDataInfoCriteria.createCriteria().andWorkFlowIdEqualTo(flowId)
                .andStepNameEqualTo(stepName)
                .andBizKeyEqualTo(bizKey);

        List<FlowStepDataInfoDO> flowStepDataInfoDOS = flowStepDataInfoMapper.selectByExample(flowStepDataInfoCriteria);

        if (flowStepDataInfoDOS.size() > 1){

            throw new BizException();
        }

        return flowStepDataInfoDOS.get(0);
    }

    public FlowStepDataInfoDO createFlowStepDataInfo(FlowStepDataInfoDO record){

        int count = flowStepDataInfoMapper.insertSelective(record);

        if (count < 1){

            throw new BizException();
        }

        return record;
    }

    @Transactional(value = "fdcTransactionManager", rollbackFor = Exception.class)
    public void batchInsert(List<FlowStepDataInfoDO> records){

        for (FlowStepDataInfoDO flowStepDataInfoDO : records){

            createFlowStepDataInfo(flowStepDataInfoDO);
        }
    }
}
