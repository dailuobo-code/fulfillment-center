package com.mallcai.fulfillment.dc.biz.service.mq;

import com.alibaba.fastjson.JSON;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.dc.api.service.dc.msg.RealTimeOrderInfoMsg;
import com.mallcai.fulfillment.dc.biz.service.producer.UnifiedMqProducer;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
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
    @ConfigValue(key = "/app_fulfillment/common/realTimeOrderNeedPushMsg")
    private String realTimeOrderNeedPushMsg;

    /**
     * 发送实时销量数据信息
     * @param realTimeOrderInfoMsg
     */
    public void sendRealTimeOrderInfoMsg(RealTimeOrderInfoMsg realTimeOrderInfoMsg) {
        if (!Constants.NEED_PUSH.equals(realTimeOrderNeedPushMsg)) {
            log.info("开关关闭，无需推送消息，订单消息内容:{}", realTimeOrderInfoMsg);
            return;
        }
        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(realTimeOrderInfoMsg);
            log.info("发送实时订单信息开始,订单消息内容:{}", msgStr);
            success = unifiedMqProducer.
                    sendMq(Constants.FULFILLMENT_REALTIMEORDERINFOTOPIC, "erpPurchaseMsg",null, msgStr);
            log.info("发送实时订单信息结束,实时订单信息:{},结果:{}", realTimeOrderInfoMsg, success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("MqEventMsg.sendRealTimeOrderInfoMsg send msg error,error msg is realTimeOrderInfoMsg:{}",realTimeOrderInfoMsg);
            throw new BizException(Errors.MQ_SENDMSG_ERROR);
        }
    }

}
