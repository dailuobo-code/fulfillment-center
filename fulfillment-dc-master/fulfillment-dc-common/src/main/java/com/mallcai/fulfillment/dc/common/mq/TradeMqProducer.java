package com.mallcai.fulfillment.dc.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 向交易发送消息
 * @author bh.zhong
 * @date 2019/8/17 10:40 AM
 */
@Slf4j
@Component
public class TradeMqProducer extends BaseMqProducer {

    @Value("${rocketmq.producer.namesrvAddr}")
    private String srvAddr;

    @Value("${rocketmq.producer.mail.groupName}")
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


    public boolean sendTradeMq(String topic,String cityName,String message) {
        return send(topic, cityName, message);
    }

}
