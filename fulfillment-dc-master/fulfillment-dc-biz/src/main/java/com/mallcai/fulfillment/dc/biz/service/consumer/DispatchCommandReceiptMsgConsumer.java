package com.mallcai.fulfillment.dc.biz.service.consumer;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.DispatchCommandReceiptMsg;
import com.mallcai.fulfillment.dc.biz.service.consumer.config.DispatchCommandReceiptMsgConsumerConfigure;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 调度指令回执消费者
 */
@Slf4j
@Component
public class DispatchCommandReceiptMsgConsumer extends AbstractListener {

    @Autowired
    private DispatchCommandReceiptMsgConsumerConfigure configure;

    public DispatchCommandReceiptMsgConsumer() {
        super(Constants.DISPATCH_COMMAND_RECEIPT_TOPIC);
    }

    /**
     * 接收消息
     *
     * @param body
     */
    @Override
    public boolean consume(String body) {
        log.info("DispatchCommandReceiptMsgConsumer.consume receive order msg body:[{}]", body);

        DispatchCommandReceiptMsg dispatchCommandReceiptMsg;
        try {
            dispatchCommandReceiptMsg = JSONObject.parseObject(body, DispatchCommandReceiptMsg.class);
        } catch (Exception e) {
            log.error("DispatchCommandReceiptMsgConsumer.consume msg analysis error，msg body:[{}], ", body, e);
            return true;
        }

        try {
//             dispatchCommandService.acceptDispatchCommandReceipt(dispatchCommandReceiptMsg);
        } catch (Exception e) {
            log.error("接收调度指令回执异常,commandNo", dispatchCommandReceiptMsg.getCommandNo(), e);
            throw e;
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
            log.error("DispatchCommandReceiptMsgConsumer.afterPropertiesSet start exception topic:{}", this.getTopic(), e);
            throw new RuntimeException(e);
        }
    }

}
