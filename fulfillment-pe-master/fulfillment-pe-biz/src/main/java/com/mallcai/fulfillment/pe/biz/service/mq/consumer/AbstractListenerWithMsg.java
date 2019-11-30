package com.mallcai.fulfillment.pe.biz.service.mq.consumer;

import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import java.util.List;

import com.mallcai.fulfillment.pe.common.utils.MdcTraceIdUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;

/**
 * 获取消息
 * @author bh.zhong
 * @date 2019/8/20 11:54 AM
 */
@Slf4j
public abstract class AbstractListenerWithMsg extends DefaultConsumerConfig implements InitializingBean{

    @Getter
    private final String topic;

    public AbstractListenerWithMsg(String topic){
        this.topic  = topic;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs) {
        for(MessageExt msg : msgs){

            MdcTraceIdUtil.addTraceId();

            String body = new String(msg.getBody());
            log.info(getTopic() + "----->:接受消息:{},msgId:{}", body, msg.getMsgId());
            try {
                if (consume(body,msg)) {
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

    public abstract boolean consume(String body,MessageExt msg);

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
