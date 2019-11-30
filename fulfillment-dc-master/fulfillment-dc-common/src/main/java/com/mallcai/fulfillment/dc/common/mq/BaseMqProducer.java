package com.mallcai.fulfillment.dc.common.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.Objects;

/**
 * rocketmq消息发送基础类
 * @author bh.zhong
 * @date 2019-08-14 00:03:14
 */
@Slf4j
public class BaseMqProducer {

  DefaultMQProducer producer;



  boolean send(String topic, String tags, String message) {
    return send(topic,tags,null,message);
  }

  boolean send(String topic, String tags, String keys, String message) {
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
