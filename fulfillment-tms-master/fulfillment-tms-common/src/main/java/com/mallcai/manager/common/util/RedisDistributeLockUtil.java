package com.mallcai.manager.common.util;

import com.google.common.collect.Lists;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.manager.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: redis分布式锁
 * @author: chentao
 * @create: 2019-08-30 17:56:40
 */
@Slf4j
@Component
public class RedisDistributeLockUtil {

    @Autowired
    private JedisClientUtil jedisClientUtil;

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间,单位：秒
     * @return 是否获取成功
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, long expireTime) {

        log.info("redis加锁开始，key:{},requestId:{},expireTime:{}", lockKey, requestId, expireTime);

        String result = null;
        try {
            result = jedisClientUtil.set(lockKey, requestId, JedisClientUtil.SetPremise.NX, JedisClientUtil.ExpireType.Seconds, expireTime);
        } catch (Exception e) {

            log.info("获取redis锁异常,key:{}", lockKey, e);
            throw new BizException();
        }

        log.info("redis加锁结束，key:{},requestId:{},result:{}", lockKey, requestId, result);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    public boolean releaseDistributedLock(String lockKey, String requestId) {

        log.info("redis解锁开始，key:{},requestId:{}", lockKey, requestId);

        String lockValue = jedisClientUtil.get(lockKey);

        if (lockValue == null) {

            log.info("redis锁:{}不存在", lockKey);
            return false;
        }

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = null;
        try {
            result = jedisClientUtil.eval(script, Lists.newArrayList(lockKey), Lists.newArrayList(requestId));
        } catch (Exception e) {
            log.info("redis解锁异常,key:{}", lockKey, e);
            throw new BizException();
        }

        log.info("redis解锁结束，key:{},requestId:{},结果:{}", lockKey, requestId, result);

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

}
