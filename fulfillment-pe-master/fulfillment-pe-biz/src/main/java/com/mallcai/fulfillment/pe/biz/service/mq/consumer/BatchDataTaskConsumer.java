package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.BatchDataHandleService;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.BatchDataTaskConsumerConfigure;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 批处理任务消费者
 * @author: chentao
 * @create: 2019-10-13 23:50:54
 */
@Slf4j
@Component
public class BatchDataTaskConsumer extends AbstractListener{

    public BatchDataTaskConsumer() {
        super(BizConstant.BATCH_DATA_TASK_MSG_TOPIC);
    }

    @Autowired
    private BatchDataHandleService batchDataHandleService;

    @Autowired
    private BatchDataTaskConsumerConfigure configure;

    @Override
    public boolean consume(String body) {

        log.info("batchDataTaskConsumerConfigure.consume receive order msg body:[{}]", body);

        BatchDataTaskMessage batchDataTaskMessage;
        try {
            batchDataTaskMessage = JSONObject.parseObject(body, BatchDataTaskMessage.class);
        } catch (Exception e) {
            log.error("BatchDataTaskMessage.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }

        batchDataHandleService.splitData(batchDataTaskMessage);

        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("BatchDataTaskConsumer.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }
}
