package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TransportPlanPoMapper {
    long countByExample(TransportPlanPoCriteria example);

    int deleteByExample(TransportPlanPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TransportPlanPo record);

    int insertSelective(TransportPlanPo record);

    List<TransportPlanPo> selectByExampleWithRowbounds(TransportPlanPoCriteria example, RowBounds rowBounds);

    List<TransportPlanPo> selectByExample(TransportPlanPoCriteria example);

    TransportPlanPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TransportPlanPo record, @Param("example") TransportPlanPoCriteria example);

    int updateByExample(@Param("record") TransportPlanPo record, @Param("example") TransportPlanPoCriteria example);

    int updateByPrimaryKeySelective(TransportPlanPo record);

    int updateByPrimaryKey(TransportPlanPo record);
}