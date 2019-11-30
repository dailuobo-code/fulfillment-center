package com.mallcai.fulfillment.infrastructure.mapper.cp;

import com.mallcai.fulfillment.infrastructure.object.cp.RecordResult;

import java.util.List;

public interface RecordResultMapperExtend extends RecordResultMapper{

    int batchInsertSelective(List<RecordResult> list);

}