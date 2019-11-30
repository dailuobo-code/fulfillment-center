package com.mallcai.fulfillment.dc.biz.service.actuator.strategy;

import com.mallcai.fulfillment.dc.biz.service.actuator.mq.SendTradeMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发送给WMS执行器
 * @author bh.zhong
 * @date 2019/8/19 9:28 AM
 */
@Component
@Slf4j
public class SendWmsActuator implements BaseActuator {

    @Resource
    private SendTradeMsgService sendTradeMsgService;

    @Override
    public void invoke() {
        //批量数据处理

        //给交易发送消息,订单状态 已支付->备货中
        sendTradeMsgService.sendBatchTradeMsg(null,null,null);
    }
}
