package com.mallcai.fulfillment.pe.biz.service.mq;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoListMsg;
import com.mallcai.fulfillment.pe.api.msg.PurchaseInfoMsg;
import com.mallcai.fulfillment.pe.biz.service.bo.PurchaseBO;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import com.mallcai.fulfillment.pe.common.mq.UnifiedMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author bh.zhong
 * @date 2019/10/14 1:41 PM
 */
@Service("mqEventMsg")
@Slf4j
public class MqEventMsg {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    public void sendPurchaseMsg(PurchaseInfoMsg purchaseInfoMsg) {
        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(purchaseInfoMsg);
            log.info("发送货品开始,消息内容:{}", msgStr);
            success = unifiedMqProducer.
                    sendMq(Constants.PURCHASE_TOPIC, "erpPurchaseMsg",null, msgStr);
            log.info("发送货品消息结束,货品信息:{},结果:{}", purchaseInfoMsg, success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("MqEventMsg.sendPurchaseMsg send msg error,error msg is purchaseInfoMsg:{}",purchaseInfoMsg);
            throw new BizException(Errors.MQ_SENDMSG_ERROR);
        }
    }

    public void sendPurchaseCompensateMsg(PurchaseBO purchaseBO) {
        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(purchaseBO);
            log.info("发送货品补偿消息开始,消息内容:{}", msgStr);
            success = unifiedMqProducer.
                    sendMq(Constants.PURCHASE_RETRY_TOPIC, "erpPurchaseMsg",null, msgStr);
            log.info("发送货品补偿消息结束,补偿信息:{},结果:{}", purchaseBO, success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("MqEventMsg.sendPurchaseRetryMsg send msg error,error msg is purchaseBO:{}",purchaseBO);
            throw new BizException(Errors.ERP_MESSAGE_ERROR);
        }
    }

}
