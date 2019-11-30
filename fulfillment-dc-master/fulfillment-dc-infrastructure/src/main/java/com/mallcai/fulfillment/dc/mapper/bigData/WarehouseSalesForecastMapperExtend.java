package com.mallcai.fulfillment.dc.mapper.bigData;

import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastDO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseSalesForecastMapperExtend {

    /**
     * 分页查询
     * @param cityId
     * @param warehouseId
     * @param predictDate
     * @param forecastType
     * @param categoryType
     * @param start
     * @param pageSize
     * @return
     */
    List<WarehouseSalesForecastDO> selectByLimit(@Param("cityId") int cityId,@Param("warehouseId")int warehouseId,@Param("predictDate") Date predictDate,
        @Param("forecastType")Byte forecastType,@Param("categoryType")Integer categoryType,@Param("start")int start,@Param("pageSize")int pageSize);

}