package com.mallcai.fulfillment.dc.biz.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author bh.zhong
 * @date 2019/10/18 7:40 PM
 */
@Slf4j
@Component
public class UnifiedMqProducer extends BaseMqProducer {

    @Value("${rocketmq.producer.namesrvAddr}")
    private String srvAddr;

    @Value("${rocketmq.producer.fulfillment.groupName.unified}")
    private String groupName;

    @PostConstruct
    public void init() throws MQClientException {
        this.producer = new DefaultMQProducer(this.groupName);
        producer.setMultiCity(ClientConfig.MultiCity.ENABLE);
        producer.setNamesrvAddr(this.srvAddr);
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("default rocket mq producer started.");
    }

    public boolean sendMq(String topic, String tags, String keys, String message) {
        return send(topic, tags, keys, message);
    }

//    public boolean sendMq(String topic,String cityName,String message) {
//        return send(topic, cityName, message);
//    }

}
