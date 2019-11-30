package com.mallcai.fulfillment.dp.infrastructure.redis;

/**
 * 分布式锁回调
 *
 * @author lidege
 * @date 2019/06/12
 */
public interface DistributeLockCallback<T> {

  /**
   * 分布式锁回调
   * @return
   */
  T doWithLock();

}
