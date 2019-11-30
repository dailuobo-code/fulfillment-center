package com.mallcai.fulfillment.dc.biz.service.producer;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

/**
 * rocketmq消息发送基础类
 * @author Liu Yang
 * @date 2019-09-29 00:03:14
 */
@Slf4j
public class BaseMqProducer {

  DefaultMQProducer producer;



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
