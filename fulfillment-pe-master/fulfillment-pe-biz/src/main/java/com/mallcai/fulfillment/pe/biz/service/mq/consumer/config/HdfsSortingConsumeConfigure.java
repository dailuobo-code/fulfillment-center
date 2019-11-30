package com.mallcai.fulfillment.pe.biz.service.mq.consumer.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liu Yang
 * @date 2019/9/27 12:01 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@ToString
public class HdfsSortingConsumeConfigure extends AbstractConsumerConfigure {

    @Value("${rocketmq.consumer.fulfillment.pe.sorting.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    private boolean isOrderly;
}
