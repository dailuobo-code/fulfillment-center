package com.mallcai.fulfillment.dc.biz.service.task;

import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow.FlowStepService;
import com.mallcai.fulfillment.dc.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @description: 流程节点处理task
 * @author: chentao
 * @create: 2019-08-26 21:26:17
 */
@JobHandler(value = "flowStepHandleTask")
@Slf4j
@Component
public class FlowStepHandleTask extends IJobHandler {

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Autowired
    private FlowStepService flowStepService;

    private final long LOCK_EXPIRE_TIME = 30 * 60L;

    @Override
    public ReturnT<String> execute(String cron) throws Exception {

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_ORDER_PUSH;

        boolean lockResult = false;
        try {

             lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);

            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return FAIL;
            }

            Date todayStart = DateUtil.todayStart();
            log.info("执行流程节点处理任务开始,日期:{}", DateUtil.formatDate(todayStart));
            flowStepService.handleNeedExecuteFlowStep();
            log.info("执行流程节点处理任务结束,日期:{}", DateUtil.formatDate(todayStart));

        } catch (Exception e) {
            log.error("执行流程节点处理任务异常", e);

            throw e;
        } finally {

            if (lockResult == true) {
                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }

        return SUCCESS;
    }

}
