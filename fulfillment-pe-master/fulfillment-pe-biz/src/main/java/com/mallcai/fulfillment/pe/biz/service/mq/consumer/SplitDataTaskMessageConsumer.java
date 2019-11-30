package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.BatchDataHandleService;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.SplitDataMessageConsumerConfigure;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 批分页数据处理消费者
 * @author: chentao
 * @create: 2019-10-10 00:21:47
 */
@Slf4j
@Component
public class SplitDataTaskMessageConsumer extends AbstractListener{

    public SplitDataTaskMessageConsumer() {
        super(BizConstant.SPLIT_DATA_TASK_MSG_TOPIC);
    }

    @Autowired
    private SplitDataMessageConsumerConfigure configure;

    @Autowired
    private BatchDataHandleService batchDataHandleService;

    @Override
    public boolean consume(String body) {

        log.info("SplitDataMessageConsumer.consume receive order msg body:[{}]", body);

        SplitDataMessage splitDataMessage;
        try {
            splitDataMessage = JSONObject.parseObject(body, SplitDataMessage.class);
        } catch (Exception e) {
            log.error("SplitDataMessageConsumer.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }


        return batchDataHandleService.handleSplitData(splitDataMessage);
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("SplitDataMessageConsumer.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }
}
