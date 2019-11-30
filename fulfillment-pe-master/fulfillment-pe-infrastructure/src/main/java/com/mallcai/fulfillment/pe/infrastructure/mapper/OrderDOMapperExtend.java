package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDOMapperExtend extends OrderDOMapper{

    int batchInsertSelective(List<OrderDO> orders);

    List<Long> selectIdListByExample(OrderDOCriteria example);

    List<OrderDO> selectByIds(List<Long> ids);
}