package com.mallcai.fulfillment.job.task;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.Objects;
/**
 * @author bh.zhong
 * @date 2019/10/6 11:34 PM
 */
@Slf4j
public class JobProducer {

    DefaultMQProducer producer;



    public boolean publish(String topic, String tags, String keys, String message) {
        Message msg;
        if (Objects.isNull(tags)) {
            msg = new Message(topic, message.getBytes());
        } else {
            msg = new Message(topic, tags, keys, message.getBytes());
        }
        try {
            SendResult sendResult = producer.send(msg);
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                log.info("rocket mq send success, msg {}", JSON.toJSONString(msg));
                return true;
            }
            log.info("rocket mq send failed, msg {}", JSON.toJSONString(msg));
        } catch (Exception e) {
            log.error("rocket mq send error, msg {}", JSON.toJSONString(msg), e);
            return false;
        }
        return false;
    }

}
