package com.mallcai.fulfillment.infrastructure.mapper.cp;


import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import org.apache.ibatis.annotations.Param;

public interface TaskConfigMapperExtend extends TaskConfigMapper{

    TaskConfig selectByBiztag(@Param("biztag") String biztag);

}