package com.mallcai.fulfillment.biz.rocketmq.provide;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

/**
 * 向ERP发送生产单信息
 * @author bh.zhong
 * @date 2019/8/17 10:40 AM
 */
@Slf4j
//@Component
public class MyRocketProducer {

    @Value("${rocketmq.producer.namesrvAddr}")
    private String srvAddr;

    @Value("${rocketmq.producer.fulfillment.groupName}")
    private String groupName;

    DefaultMQProducer producer;


    private static final String topicName="";

    private static final String topicTag=null;

    @PostConstruct
    public void init() throws MQClientException {
        producer = new DefaultMQProducer(this.groupName);
        producer.setMultiCity(ClientConfig.MultiCity.DISABLE);
        producer.setNamesrvAddr(this.srvAddr);
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("rocket mq [{}] producer started.",groupName);
    }

    public boolean send(String keys, String message) {
        Message msg;
        if (Objects.isNull(topicTag)) {
            msg = new Message(topicName, message.getBytes());
        } else {
            msg = new Message(topicName, topicTag, keys, message.getBytes());
        }
        try {
            SendResult sendResult = producer.send(msg);
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                log.info("rocket mq send success,topic:{}, msg {}", topicName,JSON.toJSONString(msg));
                return true;
            }
            log.info("rocket mq send failed,topic:{} ,msg {},status:{}", topicName,JSON.toJSONString(msg),sendResult.getSendStatus());
        } catch (Exception e) {
            log.error("rocket mq send error,topic:{}, msg {}", topicName,JSON.toJSONString(msg), e);
            return false;
        }
        return false;
    }

    @PreDestroy
    public void destory() {
        if(producer!=null){
            producer.shutdown();
        }
    }

}
