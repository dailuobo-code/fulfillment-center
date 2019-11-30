package com.mallcai.fulfillment.pe.common.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地缓存和kv缓存处理类
 *
 * @author bh.zhong
 * @date 2019/10/10 9:38 PM
 */
@Slf4j
@Component
public class LocalKvCacheHandle extends AbstractLocalCacheManager {

    private static final Integer INITIAL_CAPACITY = 1000;

    /**
     * 缓存清理
     *
     * @param keyPrefix
     * @param keyParam
     */
    public void cleanLocalKv(String keyPrefix, String keyParam) {
        clear(keyPrefix, keyParam);
    }

    /**
     * 保存缓存数据,不抛出任何异常
     *
     * @param localExpireTime local cache超时时间
     * @param cacheObject     保存的数据
     * @param keyPrefix       cache key前缀
     * @param keyParam        cache 后缀
     */
    public <V> void saveJsonCache(Integer localExpireTime,
                              V cacheObject, String keyPrefix, String keyParam) {
        try {
            put(cacheObject, localExpireTime, keyPrefix, keyParam);
        } catch (Exception e) {
            log.error("AbstractLocalKvCacheHandle，缓存保存失败，keyPrefix{},keyParam{},cacheObject{}",
                    keyPrefix, keyParam, cacheObject);
        }
    }

    /**
     * 批量获取缓存数据
     *
     * @param keyPrefix       cache key前缀
     * @param keyParams       cache 后缀
     * @param localExpireTime 本地缓存超时时间
     * @return 批量数据，泛型T
     */
    public <V> List<V> batchGetCache(String keyPrefix,
                                                   List<String> keyParams, Integer localExpireTime) {
        List<V> listResults = new ArrayList<>(keyParams.size());
        for (String keyParam : keyParams) {
            V cacheHandleBO = getLocalCache(keyPrefix, keyParam, localExpireTime);
            if (cacheHandleBO != null) {
                listResults.add(cacheHandleBO);
            }
        }
        return listResults;
    }

    public <V> V getLocalCache(String keyPrefix, String keyParam, Integer localExpireTime) {
        V object = getIfPresent(localExpireTime, keyPrefix, keyParam, INITIAL_CAPACITY);
        return object;
    }

}
