package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.DispatchCommandReceiptMsg;
import com.mallcai.fulfillment.dc.biz.service.inner.dispatch.callback.CallbackEventService;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import com.mallcai.fulfillment.dc.common.enums.DispatchEventEnum;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderGoodsInfoListMsg;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderGoodsInfoMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-16 01:12:36
 */
@Slf4j
@Component
public class ProduceOrderMsgConsumer extends AbstractListener{

    @Value("${rocketmq.consumer.fulfillment.productOrderDispatch.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    private boolean isOrderly;

    @ConfigValue(key = "/app_fulfillment/common/car.plain.citys")
    private String needCarPainCitys;

    @Autowired
    private CallbackEventService callbackEventService;

    public ProduceOrderMsgConsumer(){super(Constants.PRODUCE_ORDER_GOODS_MSG_TOPIC);}
    @Override
    public boolean consume(String body) {

        log.info("需要排车计划的城市id:{}", needCarPainCitys);
        List<Integer> needCarPlainCitys = Arrays.asList(needCarPainCitys.split(Constants.SYMBOL_COMMA)).stream().map(a -> Integer.valueOf(a)).collect(Collectors.toList());

        log.info("ProduceOrderMsgConsumer.consume receive order msg body:[{}]", body);

        ProduceOrderGoodsInfoListMsg produceOrderGoodsInfoListMsg;
        try {
            produceOrderGoodsInfoListMsg = JSONObject.parseObject(body, ProduceOrderGoodsInfoListMsg.class);
        } catch (Exception e) {
            log.error("ProduceOrderMsgConsumer.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }

        ProduceOrderGoodsInfoMsg produceOrderGoodsInfoMsg = produceOrderGoodsInfoListMsg.getProduceOrderGoodsInfoMsgs().get(0);

        // 只处理生鲜的
        if (!needCarPlainCitys.contains(produceOrderGoodsInfoMsg.getCityId()) || produceOrderGoodsInfoMsg.getOrderType() != 3){

            return true;
        }

        DispatchCommandReceiptMsg dispatchCommandReceiptMsg = new DispatchCommandReceiptMsg();

        dispatchCommandReceiptMsg.setCommandNo(produceOrderGoodsInfoMsg.getProduceOrderNo());
        dispatchCommandReceiptMsg.setDispatchEventEnum(DispatchEventEnum.PRODUCE_ORDER_CREATE);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeId", produceOrderGoodsInfoMsg.getStoreId().toString());
        dispatchCommandReceiptMsg.setData(jsonObject.toJSONString());

        callbackEventService.handleCallback(dispatchCommandReceiptMsg);

        return true;
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, groupName, namesrvAddr, isOrderly);
        } catch (MQClientException e) {
            log.error("ProduceOrderMsgConsumer.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }
}
