package com.mallcai.fulfillment.pe.biz.service.mq;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoListMsg;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送消息给ERP
 * @author bh.zhong
 * @date 2019/8/24 3:24 PM
 */
@Service("sendErpMsg")
@Slf4j
public class SendErpMsg {

    @Resource
    private ErpMqProducer erpMqProducer;

    /**
     * 给ERP发送信息
     * @param produceOrderInfoListMsg  生产订单消息体
     */
    public void sendBatchTradeMsg(ProduceOrderInfoListMsg produceOrderInfoListMsg) {

        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(produceOrderInfoListMsg);
            log.info("发送生产单消息开始,消息内容:{}", msgStr);
            success = erpMqProducer.
                    sendMq(Constants.ERP_TOPIC, "erpProduceOrderMsg", produceOrderInfoListMsg.getProduceOrderInfoMsgList().get(0).getProduceOrderNo(), msgStr);
            log.info("发送生产单消息结束,生产单号:{},结果:{}", produceOrderInfoListMsg.getProduceOrderInfoMsgList().get(0).getProduceOrderNo(), success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("SendErpMsg.sendBatchTradeMsg send msg error,error msg is produceOrderInfoListMsg:{}",produceOrderInfoListMsg);
            throw new BizException(Errors.ERP_MESSAGE_ERROR);
        }
    }

}
