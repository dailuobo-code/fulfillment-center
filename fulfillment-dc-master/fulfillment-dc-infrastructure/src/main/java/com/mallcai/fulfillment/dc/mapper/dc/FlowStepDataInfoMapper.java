package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.FlowStepDataInfoDO;
import com.mallcai.fulfillment.dc.valueobject.dc.FlowStepDataInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FlowStepDataInfoMapper {
    int countByExample(FlowStepDataInfoCriteria example);

    int deleteByExample(FlowStepDataInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(FlowStepDataInfoDO record);

    int insertSelective(FlowStepDataInfoDO record);

    List<FlowStepDataInfoDO> selectByExampleWithRowbounds(FlowStepDataInfoCriteria example, RowBounds rowBounds);

    List<FlowStepDataInfoDO> selectByExample(FlowStepDataInfoCriteria example);

    FlowStepDataInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FlowStepDataInfoDO record, @Param("example") FlowStepDataInfoCriteria example);

    int updateByExample(@Param("record") FlowStepDataInfoDO record, @Param("example") FlowStepDataInfoCriteria example);

    int updateByPrimaryKeySelective(FlowStepDataInfoDO record);

    int updateByPrimaryKey(FlowStepDataInfoDO record);
}