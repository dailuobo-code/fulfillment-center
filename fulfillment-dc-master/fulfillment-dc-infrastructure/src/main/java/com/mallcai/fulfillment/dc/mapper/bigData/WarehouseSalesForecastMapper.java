package com.mallcai.fulfillment.dc.mapper.bigData;

import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastDO;
import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WarehouseSalesForecastMapper {
    int countByExample(WarehouseSalesForecastCriteria example);

    int deleteByExample(WarehouseSalesForecastCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(WarehouseSalesForecastDO record);

    int insertSelective(WarehouseSalesForecastDO record);

    List<WarehouseSalesForecastDO> selectByExampleWithRowbounds(
        WarehouseSalesForecastCriteria example, RowBounds rowBounds);

    List<WarehouseSalesForecastDO> selectByExample(WarehouseSalesForecastCriteria example);

    WarehouseSalesForecastDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WarehouseSalesForecastDO record,
        @Param("example") WarehouseSalesForecastCriteria example);

    int updateByExample(@Param("record") WarehouseSalesForecastDO record,
        @Param("example") WarehouseSalesForecastCriteria example);

    int updateByPrimaryKeySelective(WarehouseSalesForecastDO record);

    int updateByPrimaryKey(WarehouseSalesForecastDO record);
}