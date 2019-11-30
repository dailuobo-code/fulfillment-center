package com.mallcai.fulfillment.pe.dependency.client;

import com.mallcai.fulfillment.pe.common.bo.StoreWareInfoBo;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 16:21:18
 */
public interface StoreServiceClient {

    List<StoreRespDTO> searchStoreByIds(Set<Integer> ids);

    void buildStoreId2StoreAndWarehouseInfoMap(Set<Integer> storeIds, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap);

    Map<Integer, StoreWareInfoBo> findStoreToWareHouse(Set<Integer> stores, WareHouseTypeEnum type);
}
