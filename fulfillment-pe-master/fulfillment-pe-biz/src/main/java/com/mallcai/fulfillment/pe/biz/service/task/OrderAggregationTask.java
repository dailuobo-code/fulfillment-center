package com.mallcai.fulfillment.pe.biz.service.task;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateQueryConf;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationConditionProcessor;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.BatchDataTaskMsgProducer;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.DateUtils;
import com.mallcai.fulfillment.pe.common.utils.MdcTraceIdUtil;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JobHandler(value = "OrderAggregationTask")
@Slf4j
@Component
public class OrderAggregationTask extends IJobHandler {

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Autowired
    private Environment environment;

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Override
    public ReturnT<String> execute(String tag){

        MdcTraceIdUtil.addTraceId();

        log.info("开始执行集单任务[{}]，当前执行环境:{},集单参数:{}",tag, environment.getActiveProfiles()[0],tag);


        AggregateQueryConf config = JSON.parseObject(tag, AggregateQueryConf.class);
        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_STANDARD_ORDER_AGGREGATE+"-"+config.getTaskTag();
        try {
            if (!redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, 600)) {
                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return FAIL;
            }

            aggreOrderQueryService.aggregateOrder(config);

            log.info("执行聚单任务结束,日期:{},集单参数:{}", DateUtil.formatDate(DateUtil.getStartOfDate(new Date())),JSON.toJSONString(config));
        } catch (Exception e) {
            log.error("执行聚单任务异常", e);
            throw e;
        } finally {
            redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
        }

        return SUCCESS;
    }
}
