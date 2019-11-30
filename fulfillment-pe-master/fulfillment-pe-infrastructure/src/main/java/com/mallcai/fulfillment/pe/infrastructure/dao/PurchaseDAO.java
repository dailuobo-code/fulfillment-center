package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.infrastructure.mapper.PurchaseMapper;
import com.mallcai.fulfillment.pe.infrastructure.mapper.PurchaseMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseDO;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-02 12:01
 */
@Slf4j
@Repository
public class PurchaseDAO {

  @Resource
  private PurchaseMapper purchaseMapper;
  @Resource
  private PurchaseMapperExtend purchaseMapperExtend;

  public int updateByPrimaryKeySelective(PurchaseDO purchaseDO){
    PurchaseDO tmp=new PurchaseDO();
    tmp.setCommodityInfoExt(purchaseDO.getCommodityInfoExt());
    tmp.setId(purchaseDO.getId());
    return purchaseMapper.updateByPrimaryKeySelective(tmp);
  }

  public List<PurchaseDO> selectByExample(PurchaseCriteria purchaseCriteria) {
    return purchaseMapper.selectByExample(purchaseCriteria);
  }

  /**
   * 根据主键ID及初始状态更新目标状态
   * @param id
   * @param targetStatus
   * @param underStatus
   * @return
   */
  public boolean updateStatusByIdUnderStatus(List<Long> id, Byte targetStatus, Byte underStatus){
    PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
    purchaseCriteria.createCriteria().andIdIn(id)
            .andStatusEqualTo(underStatus);
    PurchaseDO purchaseDO = new PurchaseDO();
    purchaseDO.setStatus(targetStatus);
    int updateCount = purchaseMapper.updateByExampleSelective(purchaseDO, purchaseCriteria);
    if (updateCount != id.size()){
      log.error("订单状态更新异常");
      return false;
    }
    return true;
  }

  public boolean cancelPurchase(List<Long> purchaseIdList) {
    int count= purchaseMapperExtend.cancelPurchase(purchaseIdList);
    if(count<1){
      log.error("采购单状态更新失败,idList:{}", JSON.toJSONString(purchaseIdList));
      return false;
    }else{
      return true;
    }
  }


  public void insert(PurchaseDO purchaseDO){
    purchaseMapper.insert(purchaseDO);
  }

  public int countSpecialByExample(PurchaseCriteria purchaseCriteria){
    return purchaseMapper.countByExample(purchaseCriteria);
  }


}
