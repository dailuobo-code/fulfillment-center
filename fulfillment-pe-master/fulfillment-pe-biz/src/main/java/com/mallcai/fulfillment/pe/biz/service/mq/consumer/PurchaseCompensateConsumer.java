package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.PurchaseBO;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.PurchaseInnerService;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.PurchaseCompensateMessageConsumerConfigure;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/10/14 5:10 PM
 */
@Slf4j
@Component
public class PurchaseCompensateConsumer extends AbstractListener{

    @Autowired
    private PurchaseCompensateMessageConsumerConfigure configure;

    @Resource
    private PurchaseInnerService purchaseInnerService;

    public PurchaseCompensateConsumer() {
        super(Constants.PURCHASE_RETRY_TOPIC);
    }

    @Override
    public boolean consume(String body) {
        log.info("PurchaseCompensateConsumer.consume receive order msg body:[{}]", body);

        PurchaseBO purchaseBO;
        try {
            purchaseBO = JSONObject.parseObject(body, PurchaseBO.class);
        } catch (Exception e) {
            log.error("PurchaseCompensateConsumer.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        purchaseInnerService.purchaseExecute(purchaseBO,true);
        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("OrderPipelineReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }
}
