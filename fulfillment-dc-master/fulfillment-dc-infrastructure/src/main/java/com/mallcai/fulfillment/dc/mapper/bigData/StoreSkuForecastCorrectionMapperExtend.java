package com.mallcai.fulfillment.dc.mapper.bigData;

import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionDO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author Liu Yang
 */
public interface StoreSkuForecastCorrectionMapperExtend {
  public List<StoreSkuForecastCorrectionDO> selectByLimit(@Param("cityId") int cityId,@Param("warehouseId")int warehouseId,
      @Param("forecastDate") Date forecastDate,@Param("start")int start,@Param("pageSize")int pageSize);

}