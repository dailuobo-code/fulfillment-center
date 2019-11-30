package com.mallcai.fulfillment.pe.common.handle;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存管理类
 * @author bh.zhong
 * @date 2019/10/10 9:38 PM
 */
@Slf4j
public abstract class AbstractLocalCacheManager {

    /**
     * 设置一个map存储每一个新增cache的keyPrefix,判断到底有多少种类型的keyPrefix,后面会给每一种keyPrefix new一个cache
     */
    private static final Map<String, Cache> KEY_PREFIX_MAP = new ConcurrentHashMap<>(5);

    /**
     * 清理当时的缓存值
     * @param keyPrefix   cache缓存key
     * @param param       对应值
     */
    public void clear(String keyPrefix,String param) {
        if (KEY_PREFIX_MAP.get(keyPrefix) == null) {
            return;
        }
        KEY_PREFIX_MAP.get(keyPrefix).invalidate(param);
    }

    /**
     * 只获取缓存数据
     * @param expireTime        缓存超时时间
     * @param keyPrefix         缓存key前缀
     * @param keyParam          缓存key后缀
     * @param initialCapacity   缓存容量
     * @return                  数据，泛型V
     */
    <V> V getIfPresent(Integer expireTime, String keyPrefix, String keyParam, Integer initialCapacity) {
        Cache<String, Optional<V>> cache = getCache(keyPrefix, initialCapacity, expireTime);
        if (cache == null) {
            return null;
        }
        Optional<V> optional = cache.getIfPresent(keyParam);
        if (optional != null) {
            return optional.orElse(null);
        } else {
            return null;
        }
    }

    /**
     * 将value放入缓存，会将相同keyPrefix放入一个cache，如果该keyPrefix是第一次放入会new一个cache
     * @param value        value对象类型
     * @param expireTime  缓存失效时间 单位秒，没有默认5秒，相同的keyPrefix只能有一个失效时间，该失效时间一旦设定不能改变
     * @param keyPrefix   key前缀
     * @param keyParam      key后缀
     */
    protected <V> void put(V value, Integer expireTime, String keyPrefix, String keyParam) {
        put(value, expireTime, keyPrefix, keyParam, 1000);
    }

    /**
     * 和put(Object value, Integer expireTime, String keyPrefix, String keyParam)方法基本一致，多了一个initialCapacity，用来指定初始大小
     * @param value        value对象类型
     * @param expireTime  缓存失效时间 单位秒，没有默认5秒，相同的keyPrefix只能有一个失效时间，该失效时间一旦设定不能改变
     * @param keyPrefix   key前缀
     * @param keyParam      key后缀
     * @param initialCapacity 初始大小
     */
    protected <V> void put(V value, Integer expireTime, String keyPrefix, String keyParam, Integer initialCapacity) {
        if (value == null) {
            return;
        }
        Cache<String, Optional<V>> cache = getCache(keyPrefix, initialCapacity, expireTime);
        if (cache == null) {
            return;
        }

        cache.put(keyParam, Optional.of(value));
    }

    /**
     * 初始化缓存
     * @param keyPrefix         key前缀
     * @param initialCapacity   初始大小
     * @param expireTime        缓存失效时间 单位秒
     * @return                  缓存Cache
     */
    @SuppressWarnings("unchecked")
    private <V> Cache<String, Optional<V>> getCache(String keyPrefix, Integer initialCapacity, Integer expireTime) {

        if (StringUtils.isBlank(keyPrefix)) {
            return null;
        }
        if (expireTime <= 0) {
            return null;
        }
        return KEY_PREFIX_MAP.computeIfAbsent(keyPrefix, k -> initCache(initialCapacity, expireTime));
    }

    /**
     * 初始化缓存
     * @param initialCapacity   缓存容量
     * @param expireTime        超时时间
     * @return                  Cache缓存
     */
    private <V> Cache<String, Optional<V>> initCache(Integer initialCapacity, Integer expireTime) {
        return CacheBuilder.newBuilder().initialCapacity(initialCapacity).maximumSize(20000)
            //设置并发数为10，即同一时间最多只能有10个线程往cache执行写入操作
            .concurrencyLevel(10)
            //设置cache中的数据在写入之后的存活时间
            .expireAfterWrite(expireTime, TimeUnit.SECONDS).build();
    }

}
