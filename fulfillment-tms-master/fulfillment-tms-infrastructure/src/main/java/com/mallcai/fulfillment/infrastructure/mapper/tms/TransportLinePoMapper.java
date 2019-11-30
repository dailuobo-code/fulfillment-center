package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TransportLinePoMapper {
    long countByExample(TransportLinePoCriteria example);

    int deleteByExample(TransportLinePoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TransportLinePo record);

    int insertSelective(TransportLinePo record);

    List<TransportLinePo> selectByExampleWithRowbounds(TransportLinePoCriteria example, RowBounds rowBounds);

    List<TransportLinePo> selectByExample(TransportLinePoCriteria example);

    TransportLinePo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TransportLinePo record, @Param("example") TransportLinePoCriteria example);

    int updateByExample(@Param("record") TransportLinePo record, @Param("example") TransportLinePoCriteria example);

    int updateByPrimaryKeySelective(TransportLinePo record);

    int updateByPrimaryKey(TransportLinePo record);
}