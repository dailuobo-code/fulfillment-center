package com.mallcai.fulfillment.infrastructure.mapper.cp;

import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfigCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskConfigMapper {
    int countByExample(TaskConfigCriteria example);

    int deleteByExample(TaskConfigCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskConfig record);

    int insertSelective(TaskConfig record);

    List<TaskConfig> selectByExampleWithRowbounds(TaskConfigCriteria example, RowBounds rowBounds);

    List<TaskConfig> selectByExample(TaskConfigCriteria example);

    TaskConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskConfig record, @Param("example") TaskConfigCriteria example);

    int updateByExample(@Param("record") TaskConfig record, @Param("example") TaskConfigCriteria example);

    int updateByPrimaryKeySelective(TaskConfig record);

    int updateByPrimaryKey(TaskConfig record);
}