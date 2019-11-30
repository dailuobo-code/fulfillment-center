package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowStepCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderWorkFlowStepMapper {
    int countByExample(OrderWorkFlowStepCriteria example);

    int deleteByExample(OrderWorkFlowStepCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderWorkFlowStepDO record);

    int insertSelective(OrderWorkFlowStepDO record);

    List<OrderWorkFlowStepDO> selectByExampleWithRowbounds(OrderWorkFlowStepCriteria example, RowBounds rowBounds);

    List<OrderWorkFlowStepDO> selectByExample(OrderWorkFlowStepCriteria example);

    OrderWorkFlowStepDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderWorkFlowStepDO record, @Param("example") OrderWorkFlowStepCriteria example);

    int updateByExample(@Param("record") OrderWorkFlowStepDO record, @Param("example") OrderWorkFlowStepCriteria example);

    int updateByPrimaryKeySelective(OrderWorkFlowStepDO record);

    int updateByPrimaryKey(OrderWorkFlowStepDO record);
}