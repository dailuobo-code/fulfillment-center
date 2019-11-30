package com.mallcai.fulfillment.dp.infrastructure.redis;

import com.google.common.base.Preconditions;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁服务
 *
 * @author lidege
 * @date 2019/06/12
 */
@Slf4j
@Service
public class RedisLockService {

  /**
   * 默认睡眠时间(单位毫秒)
   */
  public static final long DEFAULT_SLEEP_TIME = 10;

  @Autowired
  private JedisClientUtil jedisClientUtil;

  /**
   * 获取分布式锁
   *
   * @param lockKey 分布式锁key
   * @param lockExpireTime 分布式锁到期时间
   * @param repeatTimes 重复获取次数，为1时表示获取1次，为2时表示第1次获取不到时，再重试1次
   * @return boolean
   */
  public boolean tryLock(String lockKey, long lockExpireTime, long repeatTimes) {
    Preconditions.checkArgument(StringUtils.isNotBlank(lockKey), "lockKey invalid");
    Preconditions.checkArgument(lockExpireTime > 0, "lockExpireTime invalid");
    Preconditions.checkArgument(repeatTimes > 0, "repeatTimes invalid");
    while (repeatTimes > 0) {
      String expireStr = String.valueOf(System.currentTimeMillis() + lockExpireTime + 1);
      if (1L == jedisClientUtil.setnx(lockKey, expireStr)) {
        //目前没有线程占用此锁
        return true;
      }
      String currentValueStr = jedisClientUtil.get(lockKey);
      if (currentValueStr == null) {
        //锁已经被其他线程删除,马上重试获取锁
        continue;
      } else if (Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
        //此处判断出锁已经超过了其有效的存活时间
        expireStr = String.valueOf(System.currentTimeMillis() + lockExpireTime + 1);
        String oldValueStr = jedisClientUtil.getSet(lockKey, expireStr);
        if (oldValueStr == null || oldValueStr.equals(currentValueStr)) {
          //1.如果拿到的旧值是空,则说明在此线程做getSet之前已经有线程将锁删除,由于此线程getSet操作之后已经对锁设置了值,实际上相当于它已经占有了锁
          //2.如果拿到的旧值不为空,且等于前面查到的值,则说明在此线程进行getSet操作之前没有其他线程对锁设置了值,则此线程是第一个占有锁的
          return true;
        }
      }

      repeatTimes--;

      try {
        if (repeatTimes > 0) {
          TimeUnit.MILLISECONDS.sleep(DEFAULT_SLEEP_TIME);
        }
      } catch (Exception e) {
        log.error("重试获取锁异常 lockKey={}", lockKey, e);
        throw new RuntimeException("重试获取锁异常", e);
      }
    }
    return false;
  }

  public void unlock(String lockKey) {
    String value = jedisClientUtil.get(lockKey);
    if (value != null) {
      jedisClientUtil.del(lockKey);
    }
  }

}
