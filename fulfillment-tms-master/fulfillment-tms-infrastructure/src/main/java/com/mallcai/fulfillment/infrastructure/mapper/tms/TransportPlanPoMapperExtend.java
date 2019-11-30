package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface TransportPlanPoMapperExtend extends TransportPlanPoMapper{
    TransportPlanPo selectByPlanNo(@Param("planNo") String planNo);
}