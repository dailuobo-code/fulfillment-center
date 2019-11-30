package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.WareAreaRelation;
import org.apache.ibatis.annotations.Param;

public interface WareAreaRelationMapperExtend extends WareAreaRelationMapper{

    WareAreaRelation selectByWareHouse(@Param("wareId") Integer wareHouseId,@Param("status") Byte status);
}