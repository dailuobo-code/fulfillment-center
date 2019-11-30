package com.mallcai.fulfillment.pe.biz.service.task;

import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationConditionProcessor;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.MdcTraceIdUtil;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @description: 订单聚合task
 * @author: chentao
 * @create: 2019-08-26 21:25:53
 */
@JobHandler(value = "frozenProductOrderAggregationTask")
@Component
@Slf4j
public class FrozenProductOrderAggregationTask extends IJobHandler {

    @Autowired
    private ProduceOrderService produceOrderService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;

    @Autowired
    private Environment environment;

    @Autowired
    private ConfigurationProcessor configurationPorcessor;

    @Autowired
    private ConfigurationConditionProcessor configurationConditionProcessor;

    private final String TASK_NAME = "frozenProductOrderAggregationTask";

    @Override
    public ReturnT<String> execute(String cron) throws Exception {

        MdcTraceIdUtil.addTraceId();

        log.info("开始执行冻品集单任务，当前执行环境:{},cron:{}", environment.getActiveProfiles()[0], cron);

        if (!environment.getActiveProfiles()[0].contains("product")){

            log.error("非生产环境触发定时任务");
        }

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_FROZEN_ORDER_AGGREGATE;

        boolean lockResult = false;
        try {

            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);

            if (lockResult == false) {

                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return FAIL;
            }

            Date todayStart = DateUtil.todayStart();

            DataBo dataBo = configurationConditionProcessor.getQueryAndFilterCondition(TASK_NAME + cron);
            OrderQueryDto orderQueryDto = dataBo.getOrderQueryDto();

            orderQueryDto.setStartTime(todayStart);
            orderQueryDto.setEndTime(DateUtil.addDays(todayStart, 1));
            orderQueryDto.setGroupValue(GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue());

            Integer orderHoldMins = configurationPorcessor.getOrderHoldMins(orderQueryDto);

            orderQueryDto.setOrderHoldMins(orderHoldMins);

            produceOrderService.createProduceOrder(dataBo);

            log.info("执行聚单任务结束,日期:{}", DateUtil.formatDate(todayStart));
        } catch (Exception e) {
            log.error("执行聚单任务异常", e);
            throw e;
        } finally {

            if (lockResult == true) {
                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }

        return SUCCESS;
    }
}
