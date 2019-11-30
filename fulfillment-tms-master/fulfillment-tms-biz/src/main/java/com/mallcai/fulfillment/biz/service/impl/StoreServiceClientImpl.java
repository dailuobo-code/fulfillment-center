package com.mallcai.fulfillment.biz.service.impl;

import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.biz.service.StoreServiceClient;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.shop.api.service.IStoreService;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 17:03:13
 */
@Slf4j
@Service
public class StoreServiceClientImpl implements StoreServiceClient {

    @Reference(registry="global", timeout = 30000)
    private IStoreService iStoreService;

    @Override
    public List<StoreRespDTO> searchStoreByIds(Set<Integer> ids) {

        if(CollectionUtils.isEmpty(ids)){
            return Lists.newArrayList();
        }
        String idsStr = StringUtils.join(ids, Constants.SYMBOL_COMMA);

        PlainResult<List<StoreRespDTO>> result = null;
        try {
            log.info("查询门店信息开始，ids:{}", idsStr);
            result = iStoreService.searchStoreByIds(new ArrayList<>(ids));
            log.info("查询门店信息结束，res.size:{}", result.getData().size());
        } catch (Exception e) {
            log.error("获取门店信息异常,ids:{}", idsStr, e);
            throw new BizException(Errors.DEPENDENCY_SERVICE_EXCEPTION);
        }

        if (!result.isSuccess()){

            log.error("获取门店信息失败，code:{}; msg:{}; ids:{}", result.getCode(), result.getMessage(), idsStr);
            throw new BizException(Errors.DEPENDENCY_SERVICE_EXCEPTION);
        }

        if (CollectionUtils.isEmpty(result.getData())){

            log.error("查询门店信息结果为空,ids:{}", idsStr);
            throw new BizException(Errors.DEPENDENCY_DATA_ERROR);
        }

        List<StoreRespDTO> storeInfos = result.getData();

        for (StoreRespDTO storeRespDTO : storeInfos){

            if (CollectionUtils.isEmpty(storeRespDTO.getWareHouseDTOS())){

                log.error("门店仓库信息为空，storeId:{}", storeRespDTO.getStoreId());
                throw new BizException(Errors.DEPENDENCY_DATA_ERROR);
            }
        }

        return storeInfos;
    }

//    @Override
//    public void buildStoreId2StoreAndWarehouseInfoMap(Set<Integer> storeIds, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap) {
//
//        List<StoreRespDTO> storeInfos = this.searchStoreByIds(storeIds);
//
//        for (StoreRespDTO storeRespDTO : storeInfos) {
//
//            Map<Integer, WareHouseRespDTO> storeType2WareHouseMap = new HashMap<>();
//
//            for (WareHouseRespDTO wareHouseRespDTO : storeRespDTO.getWareHouseDTOS()) {
//
//                if (WareHouseTypeEnum.WARE_HOUSE_FRESH.getType().equals(wareHouseRespDTO.getType())) {
//
//                    storeType2WareHouseMap.put(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType(), wareHouseRespDTO);
//                } else if (WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType().equals(wareHouseRespDTO.getType())) {
//
//                    storeType2WareHouseMap.put(WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType(), wareHouseRespDTO);
//                }
//            }
//
//            storeId2WareHouseInfoMap.put(storeRespDTO.getStoreId(), storeType2WareHouseMap);
//            storId2StoreInfoMap.put(storeRespDTO.getStoreId(), storeRespDTO);
//        }
//    }
//
//    public Map<Integer, StoreWareInfoBo> findStoreToWareHouse(Set<Integer> stores, WareHouseTypeEnum type){
//        List<StoreRespDTO> storeRes = this.searchStoreByIds(stores);
//        Map<Integer, StoreWareInfoBo> result= Maps.newHashMap();
//
//        for (StoreRespDTO storeRespDTO : storeRes) {
//            StoreWareInfoBo storeInfo= StoreWareInfoBo.builder()
//                    .storeId(storeRespDTO.getStoreId())
//                    .storeName(storeRespDTO.getStoreName())
//                    .storeNo(storeRespDTO.getStoreNo())
//                    .storePhone(storeRespDTO.getTelephone())
//                    .StoreManager(storeRespDTO.getManager())
//                    .storeAddr(storeRespDTO.getAddress())
//                    .build();
//            for (WareHouseRespDTO wareHouseDTO : storeRespDTO.getWareHouseDTOS()) {
//                if(type.getType().equals(wareHouseDTO.getType())){
//                    storeInfo.setWareId(wareHouseDTO.getId());
//                    storeInfo.setWareName(wareHouseDTO.getName());
//                    storeInfo.setWareCode(wareHouseDTO.getCode());
//                }
//            }
//            result.put(storeRespDTO.getStoreId(),storeInfo);
//        }
//        return result;
//    }
}
