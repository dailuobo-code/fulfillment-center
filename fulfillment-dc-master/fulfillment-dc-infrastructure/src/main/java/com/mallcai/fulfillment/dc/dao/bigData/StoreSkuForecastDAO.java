package com.mallcai.fulfillment.dc.dao.bigData;

import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.mapper.bigData.StoreSkuForecastCorrectionMapper;
import com.mallcai.fulfillment.dc.mapper.bigData.StoreSkuForecastCorrectionMapperExtend;
import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionCriteria;
import com.mallcai.fulfillment.dc.valueobject.bigData.StoreSkuForecastCorrectionDO;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-11 18:05
 */
@Slf4j
@Repository
public class StoreSkuForecastDAO {

  @Resource
  private StoreSkuForecastCorrectionMapper storeSkuForecastCorrectionMapper;
  @Resource
  private StoreSkuForecastCorrectionMapperExtend storeSkuForecastCorrectionMapperExtend;

  public int countByExample(Integer cityId,Integer warehouseId, Date forecastDate){
    StoreSkuForecastCorrectionCriteria storeSkuForecastCorrectionCriteria=new StoreSkuForecastCorrectionCriteria();
    storeSkuForecastCorrectionCriteria.createCriteria().andCityIdEqualTo(cityId).andWarehouseIdEqualTo(warehouseId).andCaluDateEqualTo(forecastDate);
    return storeSkuForecastCorrectionMapper.countByExample(storeSkuForecastCorrectionCriteria);
  }

  public List<StoreSkuForecastCorrectionDO> selectByLimit(Integer cityId,Integer warehouseId,Date forecastDate,int start,int pageSize){
    return storeSkuForecastCorrectionMapperExtend.selectByLimit(cityId,warehouseId,forecastDate,start,pageSize);
  }

  public int countAll(){
    StoreSkuForecastCorrectionCriteria storeSkuForecastCorrectionCriteria=new StoreSkuForecastCorrectionCriteria();
    storeSkuForecastCorrectionCriteria.createCriteria().andCreateTimeGreaterThan(DateUtil.addDays(new Date(),-5));
    return storeSkuForecastCorrectionMapper.countByExample(storeSkuForecastCorrectionCriteria);
  }


}
