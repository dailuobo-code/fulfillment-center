package com.mallcai.fulfillment.pe.infrastructure.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-02 11:59
 */
public interface PurchaseMapperExtend {

  /**
   * 作废采购
   * @param idList
   * @return
   */
  int cancelPurchase(@Param("idList") List<Long> idList);
}
