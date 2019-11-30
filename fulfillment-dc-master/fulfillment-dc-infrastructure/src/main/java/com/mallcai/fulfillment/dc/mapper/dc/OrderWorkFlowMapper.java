package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderWorkFlowCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderWorkFlowMapper {
    int countByExample(OrderWorkFlowCriteria example);

    int deleteByExample(OrderWorkFlowCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderWorkFlowDO record);

    int insertSelective(OrderWorkFlowDO record);

    List<OrderWorkFlowDO> selectByExampleWithRowbounds(OrderWorkFlowCriteria example, RowBounds rowBounds);

    List<OrderWorkFlowDO> selectByExample(OrderWorkFlowCriteria example);

    OrderWorkFlowDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderWorkFlowDO record, @Param("example") OrderWorkFlowCriteria example);

    int updateByExample(@Param("record") OrderWorkFlowDO record, @Param("example") OrderWorkFlowCriteria example);

    int updateByPrimaryKeySelective(OrderWorkFlowDO record);

    int updateByPrimaryKey(OrderWorkFlowDO record);
}