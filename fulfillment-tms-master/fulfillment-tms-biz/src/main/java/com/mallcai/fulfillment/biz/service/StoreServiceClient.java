package com.mallcai.fulfillment.biz.service;

import com.mallcai.shop.api.service.response.StoreRespDTO;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 16:21:18
 */
public interface StoreServiceClient {

    List<StoreRespDTO> searchStoreByIds(Set<Integer> ids);

//    void buildStoreId2StoreAndWarehouseInfoMap(Set<Integer> storeIds, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap);
//
//    Map<Integer, StoreWareInfoBo> findStoreToWareHouse(Set<Integer> stores, WareHouseTypeEnum type);
}
