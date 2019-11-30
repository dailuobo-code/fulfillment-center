package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ProduceOrderMapper {

    int insertSelective(ProduceOrder record);

    List<ProduceOrder> selectByExample(ProduceOrderCriteria example);

    int updateByExampleSelective(@Param("record") ProduceOrder record, @Param("example") ProduceOrderCriteria example);
}