package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportLinePoMapperExtend extends TransportLinePoMapper{
    int batchInsertSelective(List<TransportLinePo> list);

    int updateByPlanNoAndOrgCode(@Param("record")TransportLinePo record);

}