package com.mallcai.fulfillment.dc.mapper.bigData;

import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionDO;
import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StoreSkuForecastCorrectionMapper {
    int countByExample(StoreSkuForecastCorrectionCriteria example);

    int deleteByExample(StoreSkuForecastCorrectionCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreSkuForecastCorrectionDO record);

    int insertSelective(StoreSkuForecastCorrectionDO record);

    List<StoreSkuForecastCorrectionDO> selectByExampleWithRowbounds(
        StoreSkuForecastCorrectionCriteria example, RowBounds rowBounds);

    List<StoreSkuForecastCorrectionDO> selectByExample(StoreSkuForecastCorrectionCriteria example);

    StoreSkuForecastCorrectionDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreSkuForecastCorrectionDO record,
        @Param("example") StoreSkuForecastCorrectionCriteria example);

    int updateByExample(@Param("record") StoreSkuForecastCorrectionDO record,
        @Param("example") StoreSkuForecastCorrectionCriteria example);

    int updateByPrimaryKeySelective(StoreSkuForecastCorrectionDO record);

    int updateByPrimaryKey(StoreSkuForecastCorrectionDO record);
}