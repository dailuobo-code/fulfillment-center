package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetailCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ProduceOrderDetailMapper {

    List<ProduceOrderDetail> selectByExample(ProduceOrderDetailCriteria example);

}