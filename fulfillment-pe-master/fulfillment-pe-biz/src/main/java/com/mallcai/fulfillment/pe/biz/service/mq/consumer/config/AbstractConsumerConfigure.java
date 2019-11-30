package com.mallcai.fulfillment.pe.biz.service.mq.consumer.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

/**
 * @author bh.zhong
 * @date 2019/8/20 12:00 PM
 */
@Data
@Configuration
@ToString
public abstract class AbstractConsumerConfigure {
    private String groupName;

    private String namesrvAddr;

    private boolean isOrderly;
}