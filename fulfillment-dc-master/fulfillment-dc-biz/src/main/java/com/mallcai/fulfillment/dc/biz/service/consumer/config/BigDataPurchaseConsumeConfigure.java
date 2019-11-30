package com.mallcai.fulfillment.dc.biz.service.consumer.config;

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
public class
BigDataPurchaseConsumeConfigure extends AbstractConsumerConfigure {

    @Value("${rocketmq.consumer.fulfillment.purchase.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    private boolean isOrderly;
}
