package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.mapper.dc.PurchaseMapper;
import com.mallcai.fulfillment.dc.mapper.dc.PurchaseMapperExtend;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseDO;
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

  public List<PurchaseDO> selectByExample(PurchaseCriteria purchaseCriteria) {
    return purchaseMapper.selectByExample(purchaseCriteria);
  }

  public void cancelPurchase(List<Long> purchaseIdList) {
    purchaseMapperExtend.cancelPurchase(purchaseIdList);
  }


  public void insert(PurchaseDO purchaseDO){
    purchaseMapper.insert(purchaseDO);
  }

  public int countSpecialByExample(PurchaseCriteria purchaseCriteria){
    return purchaseMapper.countByExample(purchaseCriteria);
  }


}
