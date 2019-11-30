package com.mallcai.fulfillment.pe.biz.service.mq.consumer.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-13 23:52:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@ToString
public class AggregateOrderMessageConsumerConfigure extends AbstractConsumerConfigure{

    @Value("${rocketmq.consumer.fulfillment.aggregateOrderMessage.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    private boolean isOrderly;
}
