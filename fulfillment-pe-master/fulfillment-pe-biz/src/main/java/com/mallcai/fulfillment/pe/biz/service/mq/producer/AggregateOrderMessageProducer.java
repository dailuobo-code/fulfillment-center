package com.mallcai.fulfillment.pe.biz.service.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.mq.AggregateOrderMessage;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import com.mallcai.fulfillment.pe.common.mq.UnifiedMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 集单消息生产者
 * @author: chentao
 * @create: 2019-10-17 18:02:11
 */
@Service
@Slf4j
public class AggregateOrderMessageProducer {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * @param aggregateOrderMessage 开始集单消息
     */
    public void sendAggregateOrderMessage(AggregateOrderMessage aggregateOrderMessage) {

        boolean success;
        String msgStr = JSON.toJSONString(aggregateOrderMessage);

        log.info("发送集单消息开始,content:{}", msgStr);
        success = unifiedMqProducer.
                sendMq(BizConstant.AGGREGATE_ORDER_START_TOPIC, "", "", msgStr);
        log.info("发送集单消息结束，结果:{}", success);
        if (!success) {
            log.error("发送集单消息异常，content:{}", msgStr);
            throw new BizException(Errors.AGGREGATE_ORDER_MESSAGE_HANDLE_ERROR);
        }
    }
}
