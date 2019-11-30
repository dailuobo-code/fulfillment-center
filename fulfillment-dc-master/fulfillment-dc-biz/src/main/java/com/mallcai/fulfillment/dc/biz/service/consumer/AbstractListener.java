package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.AbstractConsumerConfigure;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 获取消息
 * @author bh.zhong
 * @date 2019/8/20 11:54 AM
 */
@Slf4j
public abstract class AbstractListener extends DefaultConsumerConfig implements InitializingBean {

    @Getter
    private final String topic;

    public AbstractConsumerConfigure consumerConfigure;

    public AbstractListener(String topic){
        this.topic  = topic;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs) {
        for(MessageExt msg : msgs){
            String body = new String(msg.getBody());
            log.info(getTopic() + "----->:接受消息=" + body);
            try {
                if (consume(body)) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            } catch (Exception e) {
                log.error("消费者消费失败, body={}",body, e);
            }
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;

    }

    @Override
    public ConsumeOrderlyStatus consumeMessageOrderly(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        return null;
    }

    public abstract boolean consume(String body);

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
