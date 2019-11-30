package com.mallcai.fulfillment.infrastructure.mapper.cp;

import com.mallcai.fulfillment.infrastructure.object.cp.RecordResult;
import com.mallcai.fulfillment.infrastructure.object.cp.RecordResultCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RecordResultMapper {
    int countByExample(RecordResultCriteria example);

    int deleteByExample(RecordResultCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(RecordResult record);

    int insertSelective(RecordResult record);

    List<RecordResult> selectByExampleWithRowbounds(RecordResultCriteria example, RowBounds rowBounds);

    List<RecordResult> selectByExample(RecordResultCriteria example);

    RecordResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RecordResult record, @Param("example") RecordResultCriteria example);

    int updateByExample(@Param("record") RecordResult record, @Param("example") RecordResultCriteria example);

    int updateByPrimaryKeySelective(RecordResult record);

    int updateByPrimaryKey(RecordResult record);
}