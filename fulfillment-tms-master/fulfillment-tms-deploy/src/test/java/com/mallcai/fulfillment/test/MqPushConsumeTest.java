package com.mallcai.fulfillment.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MqPushConsumeTest {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Fulfillment_TransInfo_Consume");
        consumer.setNamesrvAddr("10.111.28.22:9876");
        consumer.subscribe("OpenApi_Tms_N", "*");// topic  tag
        /**
         * 全新的消费组 才适用这些策略
         * CONSUME_FROM_LAST_OFFSET //默认策略，从该队列最尾开始消费，即跳过历史消息
         * CONSUME_FROM_FIRST_OFFSET //从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
         * CONSUME_FROM_TIMESTAMP//从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
         */
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.setConsumeTimestamp("20170422221800"); //时间格式 yyyyMMddHHmmss
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                for(MessageExt msg:msgs){
                    System.out.println("msgId:"+msg.getMsgId() + " body:" + new String(msg.getBody()));
                }
                /**
                 * CONSUME_SUCCESS 消费成功
                 * RECONSUME_LATER 重试消费，重试次数可以设置，默认是16次
                 */
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");

    }
}
