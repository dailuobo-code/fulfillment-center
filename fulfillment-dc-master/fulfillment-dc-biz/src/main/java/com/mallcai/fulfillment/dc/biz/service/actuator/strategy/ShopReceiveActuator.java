package com.mallcai.fulfillment.dc.biz.service.actuator.strategy;

import com.mallcai.fulfillment.dc.biz.service.actuator.mq.SendTradeMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author bh.zhong
 * @date 2019/8/19 9:31 AM
 */
@Component
@Slf4j
public class ShopReceiveActuator implements BaseActuator {

    @Resource
    private SendTradeMsgService sendTradeMsgService;

    @Override
    public void invoke() {
        //批量数据处理

        //给交易发送消息,订单状态 备货中->待取菜
        sendTradeMsgService.sendBatchTradeMsg(null,null,null);
    }
}
