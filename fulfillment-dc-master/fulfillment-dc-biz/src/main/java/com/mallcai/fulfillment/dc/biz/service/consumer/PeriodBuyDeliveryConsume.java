package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.PeriodBuyService;
import com.mallcai.open.api.model.gm.DeliveryOrderInfo;
import com.mallcai.trade.business.api.aftersales.enums.AfterSalesOuterStatusEnum;
import com.mallcai.trade.business.api.aftersales.msg.AfterSalesStatusChangingDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/8/20 2:09 PM
 */
@Slf4j
@Component
public class PeriodBuyDeliveryConsume extends AbstractListener {

    @Value("${rocketmq.consumer.fulfillment.period.groupName:Fulfillment_PeriodBuy_Delivery}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr:}")
    private String namesrvAddr;

    private boolean isOrderly;

    @Resource
    private PeriodBuyService periodBuyService;

    public PeriodBuyDeliveryConsume() {
        super("OpenApi_Delivery_N");
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("PeriodBuyDeliveryConsume.consume receive msg body:[{}]", body);

        DeliveryOrderInfo dto = JSON.parseObject(body, DeliveryOrderInfo.class);

        try {
            periodBuyService.deliveryPeriodBuy(dto);
        }catch (Exception e){
            log.error("周期购配送信息接收错误. [{}] !!",body,e);
        }

        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, groupName, namesrvAddr, isOrderly);
        } catch (MQClientException e) {
            log.error("PeriodBuyDeliveryConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
