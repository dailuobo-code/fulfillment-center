package com.mallcai.fulfillment.dc.dao.bigData;

import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.mapper.bigData.WarehouseSalesForecastMapper;
import com.mallcai.fulfillment.dc.mapper.bigData.WarehouseSalesForecastMapperExtend;
import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastCriteria;
import com.mallcai.fulfillment.dc.valueobject.bigData.WarehouseSalesForecastDO;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseDO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 16:11
 */
@Slf4j
@Repository
public class WarehouseSalesForecastDAO {

  @Resource
  private WarehouseSalesForecastMapper warehouseSalesForecastMapper;
  @Resource
  private WarehouseSalesForecastMapperExtend warehouseSalesForecastMapperExtend;


  /**
   * 根据条件查询对象数量
   * @param example
   * @return
   */
  public int countByExample(WarehouseSalesForecastCriteria example){
    return warehouseSalesForecastMapper.countByExample(example);
  }

  /**
   * 分页查询
   * @param cityId
   * @param warehouseId
   * @param predictDate
   * @param start
   * @param pageSize
   * @return
   */
  public List<PurchaseDO> selectByLimit(int cityId,int warehouseId, Date predictDate,Byte forecastType,Integer categoryType,int start,int pageSize,int version){
      List<WarehouseSalesForecastDO> forecastResultDOList=warehouseSalesForecastMapperExtend.selectByLimit(cityId,warehouseId,predictDate,forecastType,categoryType,start,pageSize);
      return convertForecastToPurchase(forecastResultDOList,version);
  }

  public List<PurchaseDO> convertForecastToPurchase(List<WarehouseSalesForecastDO> warehouseSalesForecastDOList,int version){
    List<PurchaseDO> result=new ArrayList<>();
    if(CollectionUtils.isNotEmpty(warehouseSalesForecastDOList)){
      for(WarehouseSalesForecastDO warehouseSalesForecastDO:warehouseSalesForecastDOList){
        PurchaseDO purchaseDO=new PurchaseDO();
        purchaseDO.setCityId(warehouseSalesForecastDO.getCityId());
        purchaseDO.setWarehouseId(warehouseSalesForecastDO.getWarehouseId());
        if(!Objects.isNull(warehouseSalesForecastDO.getLv1ClassifyType())){
          purchaseDO.setCategoryType(warehouseSalesForecastDO.getLv1ClassifyType().byteValue());
        }
        purchaseDO.setCityProductId(warehouseSalesForecastDO.getCityProductId());
        purchaseDO.setForecastNum(warehouseSalesForecastDO.getForecastQty());
        purchaseDO.setForecastType(warehouseSalesForecastDO.getForecastType());
        purchaseDO.setIsDeleted(Constants.NOT_DELETED);
        purchaseDO.setForecastDate(warehouseSalesForecastDO.getForecastDate());
        purchaseDO.setVersion(version);
        purchaseDO.setCreateTime(new Date());
        purchaseDO.setUpdateTime(new Date());
        result.add(purchaseDO);
      }
    }
    return result;
  }
}
