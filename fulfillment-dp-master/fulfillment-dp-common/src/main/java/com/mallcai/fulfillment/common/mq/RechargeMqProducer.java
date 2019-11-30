package com.mallcai.fulfillment.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig.MultiCity;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wangtao
 * @description: 充值券mq生产者
 * @title: RechargeMqProducer
 * @date 2019-06-04 16:51
 */
@Slf4j
@Component
public class RechargeMqProducer extends BaseMqProducer {

    @Value("${rocketmq.producer.namesrvAddr}")
    private String srvAddr;

    private String groupName = "Trade_CreateRecharge";

    private String topic = "Trade_Recharge_N";

    @PostConstruct
    public void init() throws MQClientException {
        this.producer = new DefaultMQProducer(this.groupName);
        producer.setMultiCity(MultiCity.ENABLE);
        producer.setNamesrvAddr(this.srvAddr);
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        log.info("default rocket mq producer started.");
    }

    public boolean sendDefaultCity(String message) {
        return send(topic, "DefaultCity", message);
    }
}
