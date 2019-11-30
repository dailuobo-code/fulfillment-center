package com.mallcai.fulfillment.dp.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取分布式锁模板
 *
 * @author lidege
 * @date 2019/06/12
 */
@Slf4j
@Component
public class DistributeLockTemplate {

  @Autowired
  private RedisLockService redisLockService;

  public <T> T doWithLock(String lockKey, long lockExpireTime, long repeatTimes,
      DistributeLockCallback<T> callback) {
    boolean isLockSucc = false;
    try {
      if (!(isLockSucc = redisLockService.tryLock(lockKey, lockExpireTime, repeatTimes))) {
        log.info("获取分布式锁失败 lockKey={} lockExpireTime={} repeatTimes={}", lockKey, lockExpireTime,
            repeatTimes);
        throw new RuntimeException("系统繁忙,请稍候再试");
      }

      return callback.doWithLock();

    } finally {
      // 释放分布式锁
      if (isLockSucc) {
        redisLockService.unlock(lockKey);
      }
    }
  }

}
