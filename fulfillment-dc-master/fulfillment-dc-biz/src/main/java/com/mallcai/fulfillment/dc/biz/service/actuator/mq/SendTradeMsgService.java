package com.mallcai.fulfillment.dc.biz.service.actuator.mq;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.mq.TradeMqProducer;
import com.mallcai.fulfillment.dc.biz.service.actuator.mq.bo.OrderMsqBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 向交易发送消息
 *
 * @author bh.zhong
 * @date 2019/8/17 10:39 AM
 */
@Service
@Slf4j
public class SendTradeMsgService {

    @Resource
    private TradeMqProducer tradeMqProducer;

    private static int retryTimes = 3;

    /**
     * 批量订单发送给交易
     * @param bathOrderNo
     * @param orderMsqBOs
     * @param cityname
     */
    public void sendBatchTradeMsg(String bathOrderNo, List<OrderMsqBO> orderMsqBOs, String cityname) {

        boolean isSucess;
        int count = 0;
        do {
            count++;
            isSucess = tradeMqProducer.
                    sendTradeMq(Constants.TRADE_TOPIC, "test Nanjing", JSON.toJSONString(orderMsqBOs));
        } while (!isSucess && count < retryTimes);
        if (!isSucess) {
            log.error("SendTradeMsgService.sendTradeMsg send msg error,bathOrderNo:{}",bathOrderNo);
        }
    }

    /**
     * 单笔订单发送消息给交易
     * @param orderMsqBO
     * @param cityname
     */
    public void sendTradeMsg(OrderMsqBO orderMsqBO, String cityname) {

        boolean isSucess;
        int count = 0;
        do {
            count++;
            isSucess = tradeMqProducer.
                    sendTradeMq(Constants.TRADE_TOPIC, "test Nanjing", JSON.toJSONString(orderMsqBO));
        } while (!isSucess && count < retryTimes);
        if (!isSucess) {
            log.error("SendTradeMsgService.sendTradeMsg send msg error,orderMsqBO:{}",orderMsqBO);
        }
    }


}
