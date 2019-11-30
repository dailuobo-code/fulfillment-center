package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;

import java.util.List;

public interface ProduceOrderDetailMapperExtend {

    int batchInsertSelective(List<ProduceOrderDetail> records);
}