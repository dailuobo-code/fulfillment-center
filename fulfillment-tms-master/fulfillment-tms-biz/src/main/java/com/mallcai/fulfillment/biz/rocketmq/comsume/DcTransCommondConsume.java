package com.mallcai.fulfillment.biz.rocketmq.comsume;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import com.mallcai.fulfillment.biz.service.DcCommondExecService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class DcTransCommondConsume extends DefaultConsumerConfig {

    @Value("${rocketmq.consumer.fulfillment.transcommand.groupName:Fulfillment_TransCommond_Consume}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    @Autowired
    DcCommondExecService dcCommondExecService;



    private static final String topicName="Fulfillment_DispatchCommand_N";

    private static final String topicTag="*";

    private static final boolean isOrderly =false;


    @PostConstruct
    public void start() throws Exception{
        listener(ImmutableMap.of(topicName,topicTag), ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, groupName, namesrvAddr,isOrderly);
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
        for (MessageExt msg : msgs) {
            DispatchCommandMsg commond = JSONObject.parseObject(new String(msg.getBody()), DispatchCommandMsg.class);
            if(commond!=null && commond.getCommandType().equals(CommandTypeEnum.TMS_BOOKING_CAR_18)){
                dcCommondExecService.execDataCommond(commond);
            }

            if(commond!=null && commond.getCommandType().equals(CommandTypeEnum.TMS_ADJUST_CAR)){
                dcCommondExecService.execEmptyCommond(commond);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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
