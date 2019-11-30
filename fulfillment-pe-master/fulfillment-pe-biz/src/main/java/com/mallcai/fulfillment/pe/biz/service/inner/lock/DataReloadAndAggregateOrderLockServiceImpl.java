package com.mallcai.fulfillment.pe.biz.service.inner.lock;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-19 18:25:41
 */
@Slf4j
@Service
public class DataReloadAndAggregateOrderLockServiceImpl implements DataReloadAndAggregateOrderLockService{

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    /**
     * 锁超时时间，3分钟
     */
    private static final Integer LOCK_EXPIRE_TIME = 60 * 3;

    @Override
    public boolean getDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum requester, Integer warehouseId) {
        log.info("即将加锁,requester:{},warehouseId:{}", JSON.toJSONString(requester),warehouseId);

        String lockKey = String.format(BizConstant.AGGREGATE_ORDER_DATA_RELOAD_LOCK_KEY_FORMAT, GroupValueEnum.FRESH_PRODUCT.getGroupValue(), warehouseId);

        boolean lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requester.getRequester(), LOCK_EXPIRE_TIME);

        if (lockResult == false) {

            log.error("获取数据重入锁失败,lockKey:{}", lockKey);
            throw new BizException(Errors.AGGREGATE_ORDER_DATA_RELOAD_LOCK_ERROR);
        }

        log.info("加锁成功,requester:{},warehouseId:{}", JSON.toJSONString(requester),warehouseId);

        return true;
    }

    @Override
    public boolean releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum requester, Integer warehouseId) {

        String lockKey = String.format(BizConstant.AGGREGATE_ORDER_DATA_RELOAD_LOCK_KEY_FORMAT, GroupValueEnum.FRESH_PRODUCT.getGroupValue(), warehouseId);
        boolean lockResult = false;
        try {
            lockResult = redisDistributeLockUtil.releaseDistributedLock(lockKey, requester.getRequester());
        } catch (Exception e) {

            log.error("释放数据重入与集单锁异常，key:{},requester:{}", lockKey, requester, e);
        }

        if (lockResult == false) {

            log.error("释放数据重入与集单锁失败,lockKey:{}", lockKey);
        }
        return false;
    }
}
