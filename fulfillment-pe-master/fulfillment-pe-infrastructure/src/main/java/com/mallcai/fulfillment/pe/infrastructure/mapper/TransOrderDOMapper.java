package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDOCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TransOrderDOMapper {

    int insertSelective(TransOrderDO record);

    List<TransOrderDO> selectByExample(TransOrderDOCriteria example);

}