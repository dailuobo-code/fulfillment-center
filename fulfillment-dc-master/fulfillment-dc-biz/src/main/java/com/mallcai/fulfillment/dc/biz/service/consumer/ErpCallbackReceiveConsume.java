package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.ErpCallbackRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.ErpConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.OrderConsumeConfigure;
import com.mallcai.fulfillment.dc.biz.service.inner.ErpCallbackInnerService;
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
 * @date 2019/9/1 8:14 PM
 */
@Slf4j
@Component
public class ErpCallbackReceiveConsume extends AbstractListener {

    @Autowired
    private ErpConsumeConfigure configure;

    @Resource
    private ErpCallbackInnerService erpCallbackInnerService;

    public ErpCallbackReceiveConsume() {
        super("Erp_InboundOutboundToFdc_N");
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("ErpCallbackReceiveConsume.consume receive order msg body:[{}]", body);

        ErpCallbackRequestDTO erpCallbackRequestDTO;
        try {
            erpCallbackRequestDTO = JSONObject.parseObject(body, ErpCallbackRequestDTO.class);
        } catch (Exception e) {
            log.error("ErpCallbackReceiveConsume.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }


        return erpCallbackInnerService.callBack(erpCallbackRequestDTO);
    }

    @Override
    public void afterPropertiesSet() {
        try {
            Map<String, String> topicMap = new HashMap<>(5);
            topicMap.put(this.getTopic(), "*");
            listener(topicMap, ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET, ClientConfig.MultiCity.DISABLE, configure.getGroupName(), configure.getNamesrvAddr(), configure.isOrderly());
        } catch (MQClientException e) {
            log.error("ErpCallbackReceiveConsume.afterPropertiesSet start exception topic:{}",this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
