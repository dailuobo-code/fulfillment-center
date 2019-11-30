package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.PeriodDelivery;
import com.mallcai.fulfillment.dc.valueobject.dc.PeriodDeliveryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PeriodDeliveryMapper {
    long countByExample(PeriodDeliveryCriteria example);

    int deleteByExample(PeriodDeliveryCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PeriodDelivery record);

    int insertSelective(PeriodDelivery record);

    List<PeriodDelivery> selectByExampleWithRowbounds(PeriodDeliveryCriteria example, RowBounds rowBounds);

    List<PeriodDelivery> selectByExample(PeriodDeliveryCriteria example);

    PeriodDelivery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PeriodDelivery record, @Param("example") PeriodDeliveryCriteria example);

    int updateByExample(@Param("record") PeriodDelivery record, @Param("example") PeriodDeliveryCriteria example);

    int updateByPrimaryKeySelective(PeriodDelivery record);

    int updateByPrimaryKey(PeriodDelivery record);
}