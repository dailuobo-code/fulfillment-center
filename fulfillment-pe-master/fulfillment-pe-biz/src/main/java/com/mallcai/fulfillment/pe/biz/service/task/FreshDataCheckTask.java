package com.mallcai.fulfillment.pe.biz.service.task;

import com.mallcai.fulfillment.pe.biz.service.inner.FreshDataCheckService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 生鲜预测命令是否下发至履约的检查时间配置
 * @author: liuyang
 * @create: 2019-10-28 21:26:17
 */
@JobHandler(value = "freshDataCheckTask")
@Slf4j
@Component
public class FreshDataCheckTask extends IJobHandler {

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Resource
    private FreshDataCheckService freshDataCheckService;


    private final long LOCK_EXPIRE_TIME = 10 * 60L;

    @Override
    public ReturnT<String> execute(String cron) throws Exception {

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_FRESH_DATA_CHECK;

        boolean lockResult = false;
        try {

             lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);

            if (lockResult == false) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return FAIL;
            }

            Date todayStart = DateUtil.todayStart();
            log.info("生鲜预测命令是否下发至履约的检查任务开始,日期:{}", DateUtil.formatDate(todayStart));
            freshDataCheckService.checkFreshData();
            log.info("生鲜预测命令是否下发至履约的检查任务结束,日期:{}", DateUtil.formatDate(todayStart));

        } catch (Exception e) {
            log.error("生鲜预测命令是否下发至履约的检查任务异常", e);

            throw e;
        } finally {

            if (lockResult == true) {
                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }

        return SUCCESS;
    }

}
