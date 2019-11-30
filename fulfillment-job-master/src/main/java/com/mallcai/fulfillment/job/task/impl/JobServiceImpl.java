package com.mallcai.fulfillment.job.task.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.mallcai.fulfillment.job.api.JobContent;
import com.mallcai.fulfillment.job.enums.JobEvent;
import com.mallcai.fulfillment.job.task.JobContext;
import com.mallcai.fulfillment.job.task.JobProducer;
import com.mallcai.fulfillment.job.task.JobService;
import com.mallcai.fulfillment.job.util.UniqIDUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 任务执行
 * @author bh.zhong
 * @date 2019/10/6 11:27 PM
 */
@Slf4j
public class JobServiceImpl implements JobService {

    /**
     * 消息上下文
     */
    private JobContext context;

    /**
     * 消息发送实例
     */
    private JobProducer producer;

    private RateLimiter rateLimiter;

    @Setter
    private Map<String, String> topicMap;

    public JobServiceImpl(JobContext context, JobProducer producer, Map<String, String> topicMap, RateLimiter rateLimiter) {
        this.context = context;
        this.producer = producer;
        this.topicMap = topicMap;
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void split(JobContent content) {
        publish(newContext(JobEvent.SPLITOR, content));
    }

    @Override
    public void load(JobContent content) {
        publish(newContext(JobEvent.LOADER, content));
    }

    @Override
    public void execute(JobContent content) {
        publish(newContext(JobEvent.EXECUTOR, content));
    }

    private void publish(JobContext context) {

        String topic = topicMap.get(context.getEvent());

        if (null != rateLimiter && JobEvent.EXECUTOR.name().equals(context.getEvent())) {
            rateLimiter.acquire();
        }

        producer.publish(topic,null,null, JSON.toJSONString(context));
        if (log.isDebugEnabled()) {
            log.debug("发送任务消息成功");
        }
    }

    private JobContext newContext(JobEvent event, JobContent content) {
        JobContextImpl childContext = new JobContextImpl();
        String taskId = UniqIDUtil.create();
        String rootId = context != null ? context.getRootId() : null;
        String parentId = context != null ? context.getTaskId() : null;
        if (rootId == null || rootId.trim().length() == 0) {
            rootId = taskId;
        } else {
            childContext.setParentId(parentId);
        }
        childContext.setRootId(rootId);
        childContext.setEvent(event.name());
        childContext.setTaskId(taskId);
        childContext.setContent(content);
        return childContext;
    }

}
