package com.mallcai.fulfillment.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig.MultiCity;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author YL
 * @description: 订单状态变更mq
 * @title: OrderStatusMqProducer
 * @date 2019-06-04 16:51
 */
@Slf4j
@Component
public class OrderMqProducer extends BaseMqProducer {

    @Value("${rocketmq.producer.namesrvAddr}")
    private String srvAddr;

    @Value("${rocketmq.producer.mail.groupName}")
    private String groupName;

    @PostConstruct
    public void init() throws MQClientException {
        try {
            this.producer = new DefaultMQProducer(this.groupName);
            producer.setMultiCity(MultiCity.ENABLE);
            producer.setNamesrvAddr(this.srvAddr);
            producer.setVipChannelEnabled(false);
            producer.setRetryTimesWhenSendAsyncFailed(10);
            //producer.start();
            log.info("default rocket mq producer started.");
        }catch (Exception e){
            log.error("fail start mq producer:",e);
        }
    }

}
