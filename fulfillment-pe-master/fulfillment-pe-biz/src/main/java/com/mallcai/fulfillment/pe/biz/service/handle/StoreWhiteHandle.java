package com.mallcai.fulfillment.pe.biz.service.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.StoreWhiteConfigBO;
import com.mallcai.fulfillment.pe.biz.service.bo.WhiteTypeBO;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.promise.api.req.TypeQueryRequest;
import com.mallcai.fulfillment.promise.enums.WhiteTypeEnum;
import com.mallcai.fulfillment.promise.factory.WhiteConfigFactory;
import com.mallcai.fulfillment.promise.impl.PickUpTimeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 门店白名单处理类
 *
 * @author bh.zhong
 * @date 2019/8/23 9:38 PM
 */
@Service("storeWhiteHandle")
@Slf4j
public class StoreWhiteHandle {

    @ConfigValue(key = "/app_fulfillment/common/shore.white.config")
    private JSONObject jsonObject;

    /**mallcai-config-plugin
     * 是否开启白名单
     *
     * @return  true 开启 ，false 不开启
     */
    public Boolean openWhite() {
        StoreWhiteConfigBO config = JSON.toJavaObject(jsonObject,StoreWhiteConfigBO.class);
        return config.getOpenWhite();
    }

    /**
     * 获取白名单列表
     *
     * @return  白名单列表数据
     */
    public List<Integer> getWhiteStores() {
        StoreWhiteConfigBO config = JSON.toJavaObject(jsonObject,StoreWhiteConfigBO.class);
        if (config.getOpenWhite()) {
            if (CollectionUtils.isNotEmpty(config.getStores())) {
                List<Integer> stores = new ArrayList<>();
                config.getStores().forEach(store -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = store.getEffectTime();
                    Date today = new Date();
                    try {
                        Date date = sdf.parse(dateStr);
                        if (today.getTime() >= date.getTime()) {
                            stores.add(store.getStoreId());
                        }
                    } catch (Exception e) {
                        log.error("StoreWhiteHandle.getWhiteStores get stores error,store, store: {}", store.getStoreId());
                    }
                });
                return stores;
            }
        }
        return null;
    }

    /**
     * 根据类型获取白名单数据
     * @param types  类型集合
     * @return
     */
    public Map<String, WhiteTypeBO> getWhiteByType(Set<GroupValueEnum> types) {
        Map<String, WhiteTypeBO> whiteTypeBOMap = new HashMap<>();
        types.forEach(type->{
            WhiteTypeBO whiteTypeBO = new WhiteTypeBO();
            if (GroupValueEnum.ORDER_TYPE_STANDARD.equals(type)) {
                whiteTypeBO.setStoreWhites(getWhiteStores());
            } else if (GroupValueEnum.STORE_FROZEN_PRODUCT.equals(type)) {
                TypeQueryRequest typeQueryRequest = new TypeQueryRequest();
                typeQueryRequest.setAttributeType(type.getAttributeType());
                typeQueryRequest.setCategoryType(type.getCategoryType());
                typeQueryRequest.setSkuId(type.getSku());
                whiteTypeBO.setCityWhites(WhiteConfigFactory.getConfigService(WhiteTypeEnum.CITY).getByType(typeQueryRequest));
            }
            whiteTypeBOMap.put(type.getGroupValue(),whiteTypeBO);
        });
        return whiteTypeBOMap;
    }

    /**
     * 获取店铺和时间
     *
     * @return  获取店铺和时间
     */
    public Map<Integer,Integer> getShopAndTime() {
        Map<Integer,Integer> shopTimes = new HashMap<>();
        StoreWhiteConfigBO config = JSON.toJavaObject(jsonObject,StoreWhiteConfigBO.class);
        if (config.getOpenWhite()) {
            if (CollectionUtils.isNotEmpty(config.getStores())) {
                config.getStores().forEach(store -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = store.getEffectTime();
                    Date today = new Date();
                    try {
                        Date date = sdf.parse(dateStr);
                        if (today.getTime() >= date.getTime()) {
                            shopTimes.put(store.getStoreId(),store.getDeliveryDays());
                        }
                    } catch (Exception e) {
                        log.error("StoreWhiteHandle.getShopAndTime get storeTimes error,store, store: {}", store.getStoreId());
                    }
                });
                return shopTimes;
            }
        }
        return null;
    }
}
