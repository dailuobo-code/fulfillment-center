package com.mallcai.fulfillment.infrastructure.mapper.pe;

import com.mallcai.fulfillment.infrastructure.object.pe.PeOrder;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PeOrderMapper {
    int countByExample(PeOrderCriteria example);

    int deleteByExample(PeOrderCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PeOrder record);

    int insertSelective(PeOrder record);

    List<PeOrder> selectByExampleWithRowbounds(PeOrderCriteria example, RowBounds rowBounds);

    List<PeOrder> selectByExample(PeOrderCriteria example);

    PeOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PeOrder record, @Param("example") PeOrderCriteria example);

    int updateByExample(@Param("record") PeOrder record, @Param("example") PeOrderCriteria example);

    int updateByPrimaryKeySelective(PeOrder record);

    int updateByPrimaryKey(PeOrder record);
}