package com.mallcai.fulfillment.pe.biz.service.inner.purchase.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.msg.PurchaseDTO;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.pe.biz.service.bo.PurchaseBO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.RedisService;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.PurchaseInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.BeanCopierUtils;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseDAO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-09 21:57
 */
@Slf4j
@Service
public class ReceivePurchaseForecastInnerServiceImpl implements
    ReceivePurchaseForecastInnerService {
  @Resource
  private PurchaseDAO purchaseDAO;
  @Resource
  private IcProductGoodsServiceFacade icProductGoodsServiceFacade;
  @Resource
  private RedisService redisService;
  @Resource
  private PurchaseInnerService purchaseInnerService;
  /**
   * redis失效时间 默认一天 86400秒
   */
  private final Integer REDIS_EXPIRE_TIME=86400;

  @Override
  public boolean receiveMsg(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {

    List<PurchaseDTO> purchaseDTOList=purchaseForecastInfoListDTO.getPurchaseDOList();
    if(CollectionUtils.isEmpty(purchaseDTOList)){
      log.warn("预测数量为空，无法进行PE层数据插入:{}", JSON.toJSONString(purchaseForecastInfoListDTO));
      return true;
    }
    List<ItemGoodsInfoBO> itemGoodsInfoBOList=getGoodsList(purchaseDTOList);
    List<PurchaseDO> purchaseDOList=convertPurchaseDTO(purchaseDTOList,itemGoodsInfoBOList);
    //执行插入
    int modifyCount=0;
    StringBuffer modifyCountBuffer=new StringBuffer();
    boolean result=executePurchaseSave(purchaseDOList,modifyCountBuffer);
    modifyCount=Integer.parseInt(modifyCountBuffer.toString());
    if(!result){
      log.error("执行PE层采购预测数据插入失败,purchaseDOList:{}",purchaseDOList);
      return false;
    }
    //判断PE插入数量是否和大数据本次提供数据总条数相等了
    redisService.incrBy(purchaseForecastInfoListDTO.getRedisKey()+"_"+purchaseForecastInfoListDTO.getVersion(),modifyCount,REDIS_EXPIRE_TIME);
    String countValue=redisService.get(purchaseForecastInfoListDTO.getRedisKey()+"_"+purchaseForecastInfoListDTO.getVersion());
    int count = (countValue == null ? 0 : Integer.valueOf(countValue));
    if(count==purchaseForecastInfoListDTO.getTotalCount()){
      //插入完毕后检查，本库该仓数据已经和大数据预测总量一致，插入完成！
      //更新redis
      log.info("即将调用采购单推送接口！,purchaseForecastInfoListDTO:{}",JSON.toJSONString(purchaseForecastInfoListDTO));
      redisService.set(purchaseForecastInfoListDTO.getRedisKey(),String.valueOf(purchaseForecastInfoListDTO.getVersion()),REDIS_EXPIRE_TIME);
      executePurchaseOperation(purchaseForecastInfoListDTO);
    }
    return true;
  }
  public void executePurchaseOperation(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO){
    PurchaseBO purchaseBO=new PurchaseBO();
    purchaseBO.setCityId(purchaseForecastInfoListDTO.getCityId());
    purchaseBO.setCategoryType(purchaseForecastInfoListDTO.getCategoryType());
    purchaseBO.setCount(purchaseForecastInfoListDTO.getTotalCount());
    purchaseBO.setForecastDate(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()));
    purchaseBO.setForecastType(purchaseForecastInfoListDTO.getForecastType());
    purchaseBO.setWarehouseId(purchaseForecastInfoListDTO.getWarehouseId());
    purchaseBO.setVersion(purchaseForecastInfoListDTO.getVersion());
    purchaseInnerService.purchaseExecute(purchaseBO,false);
  }

  public List<PurchaseDO> selectByExample(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
    //需要根据采购还是补采，查询已有的数据
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    if (Constants.PREDICT_T.equals(purchaseForecastInfoListDTO.getForecastType())) {
      //补采预测
      purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseForecastInfoListDTO.getCityId()).
          andWarehouseIdEqualTo(purchaseForecastInfoListDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()))
          .andForecastTypeEqualTo(purchaseForecastInfoListDTO.getForecastType())
          .andIsDeletedEqualTo(Constants.NOT_DELETED)
          .andVersionEqualTo(purchaseForecastInfoListDTO.getVersion());
    } else if (Constants.PREDICT_T_2.equals(purchaseForecastInfoListDTO.getForecastType())) {
      //采购预测
      purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseForecastInfoListDTO.getCityId()).
          andWarehouseIdEqualTo(purchaseForecastInfoListDTO.getWarehouseId())
          .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()))
          .andForecastTypeEqualTo(purchaseForecastInfoListDTO.getForecastType())
          .andCategoryTypeEqualTo(purchaseForecastInfoListDTO.getCategoryType().byteValue())
          .andIsDeletedEqualTo(Constants.NOT_DELETED)
          .andVersionEqualTo(purchaseForecastInfoListDTO.getVersion());
    }
    return purchaseDAO.selectByExample(purchaseCriteria);
  }




  public List<PurchaseDO> convertPurchaseDTO(List<PurchaseDTO> purchaseDTOList,List<ItemGoodsInfoBO> itemGoodsInfoBoList){
      List<PurchaseDO> purchaseDOList=new ArrayList<>();
      for(PurchaseDTO purchaseDTO:purchaseDTOList){
        PurchaseDO purchaseDO=new PurchaseDO();
        BeanCopierUtils.copyProperties(purchaseDTO,purchaseDO);
        purchaseDO.setStatus((byte)0);
        purchaseDO.setIsDeleted((byte)0);
        // 商品信息扩展字段
        buildPurchaseCommodityInfo(purchaseDO,itemGoodsInfoBoList);
        purchaseDOList.add(purchaseDO);
      }
      return purchaseDOList;
  }

  @Override
  public PlainResult executeDateRepair(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO){
      log.info("即将开始数据修复");
      List<PurchaseDO> purchaseDOList=selectByExample(purchaseForecastInfoListDTO);
      if(CollectionUtils.isNotEmpty(purchaseDOList)){
        purchaseForecastInfoListDTO.setTotalCount(purchaseDOList.size());
        List<ItemGoodsInfoBO> itemGoodsInfoBOList=getGoodsListByPurchaseDo(purchaseDOList);
        updateExistPurchaseDo(purchaseDOList,itemGoodsInfoBOList);
      }else{
        log.info("无未关联货品的商品数据,purchaseForecastInfoListDTO:{}",JSON.toJSONString(purchaseForecastInfoListDTO));
      }
      return PlainResultBuilder.success("执行系统修复完成");
  }

  @Override
  public PlainResult executeDateRePush(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
    log.info("即将对数据进行重推,purchaseForecastInfoListDTO:{}",JSON.toJSONString(purchaseForecastInfoListDTO));
    executePurchaseOperation(purchaseForecastInfoListDTO);
    return PlainResultBuilder.success("执行系统数据推送完成");
  }

  public void updateExistPurchaseDo(List<PurchaseDO> purchaseDOList,List<ItemGoodsInfoBO> itemGoodsInfoBoList){
    for(PurchaseDO purchaseDO:purchaseDOList){
      if(StringUtils.isEmpty(purchaseDO.getCommodityInfoExt())||purchaseDO.getCommodityInfoExt().length()<5){
        // 商品信息扩展字段
        log.info("数据修复中..查询到的有商品未关联货品,将为其关联货品，purchaseDO:{}",JSON.toJSONString(purchaseDO));
        buildPurchaseCommodityInfo(purchaseDO,itemGoodsInfoBoList);
        purchaseDAO.updateByPrimaryKeySelective(purchaseDO);
      }
    }
  }

  public List<ItemGoodsInfoBO> getGoodsListByPurchaseDo(List<PurchaseDO> purchaseDOList) {
    List<ItemGoodsInfoBO> result = new ArrayList<>();
    List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
    for (PurchaseDO purchaseDO : purchaseDOList) {
      if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
        boolean flag = false;
        for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
          if (queryGoodsRelBO.getCityProductId().equals(purchaseDO.getCityProductId())) {
            flag = true;
          }
        }
        if (!flag) {
          QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
          queryGoodsRelBO.setCityId(purchaseDO.getCityId());
          queryGoodsRelBO.setCityProductId(purchaseDO.getCityProductId());
          queryGoodsRelBOList.add(queryGoodsRelBO);
        }
      } else {
        QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
        queryGoodsRelBO.setCityId(purchaseDO.getCityId());
        queryGoodsRelBO.setCityProductId(purchaseDO.getCityProductId());
        queryGoodsRelBOList.add(queryGoodsRelBO);
      }
    }
    if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
      result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
    }
    return result;
  }

  public List<ItemGoodsInfoBO> getGoodsList(List<PurchaseDTO> purchaseDTOList) {
    List<ItemGoodsInfoBO> result = new ArrayList<>();
    List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
    for (PurchaseDTO purchaseDTO : purchaseDTOList) {
      if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
        boolean flag = false;
        for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
          if (queryGoodsRelBO.getCityId().equals(purchaseDTO.getCityId()) && queryGoodsRelBO
              .getCityProductId().equals(purchaseDTO.getCityProductId())) {
            flag = true;
          }
        }
        if (!flag) {
          QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
          queryGoodsRelBO.setCityId(purchaseDTO.getCityId());
          queryGoodsRelBO.setCityProductId(purchaseDTO.getCityProductId());
          queryGoodsRelBOList.add(queryGoodsRelBO);
        }
      } else {
        QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
        queryGoodsRelBO.setCityId(purchaseDTO.getCityId());
        queryGoodsRelBO.setCityProductId(purchaseDTO.getCityProductId());
        queryGoodsRelBOList.add(queryGoodsRelBO);
      }
    }
    if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
      result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
    }
    return result;
  }

  public void buildPurchaseCommodityInfo(PurchaseDO purchaseDO,List<ItemGoodsInfoBO> itemGoodsInfoBoList){
// 商品信息扩展字段
    JSONObject commodityInfoExt = new JSONObject();
    if(CollectionUtils.isNotEmpty(itemGoodsInfoBoList)){
      for(ItemGoodsInfoBO itemGoodsInfoBO:itemGoodsInfoBoList){
        if(itemGoodsInfoBO.getCityId().equals(purchaseDO.getCityId())&&itemGoodsInfoBO.getCityProductId().equals(purchaseDO.getCityProductId())){
          commodityInfoExt.put(CommodityInfoExtEnum.GOODS_ID.getKey(),itemGoodsInfoBO.getGoodsId());
          commodityInfoExt.put(CommodityInfoExtEnum.GOODS_UNIT.getKey(),itemGoodsInfoBO.getGoodsUnit());
          commodityInfoExt.put(CommodityInfoExtEnum.REL_NUM.getKey(),itemGoodsInfoBO.getRelNum());
        }
      }
    }
    purchaseDO.setCommodityInfoExt(commodityInfoExt.toJSONString());
  }

  public List<PurchaseDO> processPurchaseList(List<PurchaseDO> purchaseDOList,
      List<PurchaseDO> existPurchaseList) {
    List<PurchaseDO> result = new ArrayList<>();
    for (PurchaseDO tmp : purchaseDOList) {
      boolean flag = false;
      for (PurchaseDO exist : existPurchaseList) {
        if (exist.getCityId().equals(tmp.getCityId()) && exist.getCityProductId()
            .equals(tmp.getCityProductId()) && exist.getWarehouseId().equals(tmp.getWarehouseId())
            && exist.getForecastDate().equals(tmp.getForecastDate())) {
          flag = true;
        }
      }
      if (!flag) {
        result.add(tmp);
      }
    }
    return result;
  }

  public Boolean executePurchaseSave(List<PurchaseDO> purchaseDOLists,StringBuffer stringBuffer) {
    boolean flag = true;
    int modifyCount=0;
    for(PurchaseDO purchaseDO:purchaseDOLists){
      if(checkIfNotExistHighVersion(purchaseDO)){
        if(checkIfNotExistPurchaseDO(purchaseDO)){
          try {
            purchaseDAO.insert(purchaseDO);
            modifyCount++;
          }catch (Exception e){
            log.error("插入采购数据时发生错误,purchaseDO:{}",purchaseDO,e);
            flag=false;
          }
        }
      }
    }
    stringBuffer.append(modifyCount);
    return flag;
  }

  public boolean checkIfNotExistHighVersion(PurchaseDO purchaseDO){
    PurchaseCriteria purchaseCriteria=new PurchaseCriteria();
    purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseDO.getCityId()).andWarehouseIdEqualTo(purchaseDO.getWarehouseId()).
        andForecastDateEqualTo(purchaseDO.getForecastDate()).andForecastTypeEqualTo(purchaseDO.getForecastType()).andCategoryTypeEqualTo(purchaseDO.getCategoryType()).
        andVersionGreaterThan(purchaseDO.getVersion());
    int count=purchaseDAO.countSpecialByExample(purchaseCriteria);
    if(count==0){
      return true;
    }
    return false;
  }

  public boolean checkIfNotExistPurchaseDO(PurchaseDO purchaseDO){
    PurchaseCriteria purchaseCriteria=new PurchaseCriteria();
    purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseDO.getCityId()).andWarehouseIdEqualTo(purchaseDO.getWarehouseId()).
        andCityProductIdEqualTo(purchaseDO.getCityProductId()).andForecastDateEqualTo(purchaseDO.getForecastDate()).andForecastTypeEqualTo(purchaseDO.
        getForecastType()).andCategoryTypeEqualTo(purchaseDO.getCategoryType()).
        andVersionEqualTo(purchaseDO.getVersion()).andIsDeletedEqualTo(Constants.NOT_DELETED);
    int count=purchaseDAO.countSpecialByExample(purchaseCriteria);
    if(count==0){
      return true;
    }
    return false;
  }
}
