package com.mallcai.fulfillment.infrastructure.mapper.cp;

import com.mallcai.fulfillment.infrastructure.object.cp.TaskRecord;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskRecordMapper {
    int countByExample(TaskRecordCriteria example);

    int deleteByExample(TaskRecordCriteria example);

    int deleteByPrimaryKey(String checkNo);

    int insert(TaskRecord record);

    int insertSelective(TaskRecord record);

    List<TaskRecord> selectByExampleWithRowbounds(TaskRecordCriteria example, RowBounds rowBounds);

    List<TaskRecord> selectByExample(TaskRecordCriteria example);

    TaskRecord selectByPrimaryKey(String checkNo);

    int updateByExampleSelective(@Param("record") TaskRecord record, @Param("example") TaskRecordCriteria example);

    int updateByExample(@Param("record") TaskRecord record, @Param("example") TaskRecordCriteria example);

    int updateByPrimaryKeySelective(TaskRecord record);

    int updateByPrimaryKey(TaskRecord record);
}