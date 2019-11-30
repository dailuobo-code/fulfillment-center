package com.mallcai.fulfillment.biz.rocketmq.comsume;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.common.utils.DefaultConsumerConfig;
import com.mallcai.fulfillment.biz.service.TransCarResultService;
import com.mallcai.fulfillment.biz.service.TransCarService;
import com.mallcai.fulfillment.biz.service.TransLineService;
import com.mallcai.manager.common.constant.AppConstant;
import com.mallcai.open.api.model.tms.TransportPlan;
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
public class TmsTransInfoConsume extends DefaultConsumerConfig {

    @Value("${rocketmq.consumer.fulfillment.transinfo.groupName:Fulfillment_TransInfo_Consume}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr:}")
    private String namesrvAddr;

    @Autowired
    TransLineService transLineService;

    @Autowired
    TransCarService transCarService;

    @Autowired
    TransCarResultService transCarResultService;


    private static final String topicName="OpenApi_Tms_N";

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
            TransportPlan transportPlan = JSONObject.parseObject(new String(msg.getBody()), TransportPlan.class);
            if(transportPlan!=null){
                String apiName = transportPlan.getApiName();
                //排线计划
                if(AppConstant.TansLine.equals(apiName)){
                    transLineService.saveTransPlan(transportPlan);
                }

                // 排车计划
                if(AppConstant.TansCar.equals(apiName)){
                    transCarService.updateTransCar(transportPlan);
                }

                //排车计划结果
                if(AppConstant.TansCarResult.equals(apiName)){
                    transCarResultService.updateTransCarResult(transportPlan);
                }
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
