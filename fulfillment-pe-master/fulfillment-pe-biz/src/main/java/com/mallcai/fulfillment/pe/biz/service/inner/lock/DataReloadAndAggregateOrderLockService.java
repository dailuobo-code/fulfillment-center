package com.mallcai.fulfillment.pe.biz.service.inner.lock;

import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;

/**
 * @description: 生鲜数据重入与集单锁业务
 * @author: chentao
 * @create: 2019-10-19 18:19:54
 */
public interface DataReloadAndAggregateOrderLockService {

    /**
     * 获取数据重入与集单锁
     * @param requester 请求方
     * @return
     */
    boolean getDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum requester, Integer warehouseId);

    /**
     * 释放数据重入与集单锁
     * @param requester 请求方
     * @return
     */
    boolean releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum requester, Integer warehouseId);

}
