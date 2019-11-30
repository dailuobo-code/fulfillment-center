package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateQueryConf;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;
import com.mallcai.fulfillment.pe.biz.service.gray.SwitchService;
import com.mallcai.fulfillment.pe.biz.service.inner.lock.DataReloadAndAggregateOrderLockService;
import com.mallcai.fulfillment.pe.biz.service.mq.AggregateOrderMessage;
import com.mallcai.fulfillment.pe.biz.service.mq.consumer.config.AggregateOrderMessageConsumerConfigure;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 集单消息处理消费者
 * @author: chentao
 * @create: 2019-10-17 18:10:35
 */
@Slf4j
@Component
public class AggregateOrderMessageConsumer extends AbstractListener{

    public AggregateOrderMessageConsumer() {
        super(BizConstant.AGGREGATE_ORDER_START_TOPIC);
    }

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Autowired
    private AggregateOrderMessageConsumerConfigure configure;

    @Autowired
    private SwitchService switchService;

    @Autowired
    private DataReloadAndAggregateOrderLockService dataReloadAndAggregateOrderLockService;



    @Override
    public boolean consume(String body) {

        log.info("AggregateOrderMessageConsumer.consume receive order msg body:[{}]", body);

        AggregateOrderMessage aggregateOrderMessage;
        try {
            aggregateOrderMessage = JSONObject.parseObject(body, AggregateOrderMessage.class);
        } catch (Exception e) {
            log.error("AggregateOrderMessageConsumer.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }

        try {

            dataReloadAndAggregateOrderLockService.getDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.AGGREGATE_ORDER, aggregateOrderMessage.getWarehousesId());

            AggregateQueryConf aggregateQueryConf = new AggregateQueryConf();

            aggregateQueryConf.setGroupValue(aggregateOrderMessage.getGroupValue());
            aggregateQueryConf.setWarehouses(Lists.newArrayList(aggregateOrderMessage.getWarehousesId()));

            if (switchService.isDistributedAggregateOpened()) {

                aggregateQueryConf.setDistFlag(true);
            }

            aggreOrderQueryService.aggregateOrder(aggregateQueryConf);
        } catch (Exception e) {
            log.error("消费集单消息异常", e);
            return false;
        }

        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("AggregateOrderMessageConsumer.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }
}
