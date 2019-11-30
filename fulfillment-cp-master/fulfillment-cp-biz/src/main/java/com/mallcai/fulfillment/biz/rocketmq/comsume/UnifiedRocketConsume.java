package com.mallcai.fulfillment.biz.rocketmq.comsume;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import com.mallcai.fulfillment.api.msg.UnifiedCompareMsg;
import com.mallcai.fulfillment.biz.service.UnifiedCompareService;
import com.mallcai.manager.common.constant.Constants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/** 对外统一对账MQ接口
 * @author Liu Yang
 * @date 2019/11/14 10:09 PM
 */
//@Component
@Slf4j
public class UnifiedRocketConsume extends DefaultConsumerConfig {

    @Value("${rocketmq.consumer.fulfillment.purchase.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    @Resource
    private UnifiedCompareService unifiedCompareService;

    @Getter
    private static final String topic= Constants.CP_UNIFIED_COMPARE_TOPIC;

    private static final String tag="*";

    private static final boolean isOrderly =false;


    @PostConstruct
    public void start() throws Exception{
        listener(ImmutableMap.of(topic,tag), ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, groupName, namesrvAddr,isOrderly);
    }

    @PreDestroy
    public void destory(){
        destroy();
    }

    /**
     * 普通消费场景
      * @param msgs
     * @return
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessageConcurrently(List<MessageExt> msgs) {
        for(MessageExt msg : msgs){
//            MdcTraceIdUtil.addTraceId();
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

    public boolean consume(String body,MessageExt msg){
        UnifiedCompareMsg unifiedCompareMsg;
        try {
            unifiedCompareMsg = JSONObject.parseObject(body, UnifiedCompareMsg.class);
        } catch (Exception e) {
            log.error("UnifiedRocketConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }
        Boolean result=false;
        try {
            result=unifiedCompareService.processData(unifiedCompareMsg);
        }catch (Exception e){
            log.error("执行统一对账消息处理异常",e);
        }
        return result;
    }

    /**
     * 顺序消费场景
     * @param msgs
     * @param context
     * @return
     */
    @Override
    public ConsumeOrderlyStatus consumeMessageOrderly(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
