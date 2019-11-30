package com.mallcai.fulfillment.pe.biz.service.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderGoodsInfoListMsg;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoListMsg;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import com.mallcai.fulfillment.pe.common.mq.UnifiedMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送货品生产单消息给ERP
 *
 * @author bh.zhong
 * @date 2019/8/24 3:24 PM
 */
@Service
@Slf4j
public class ProduceOrderGoodsMsgProducer {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    private final String PRODUCE_ORDER_GOODS_MSG_TOPIC = "Fulfillment_ProduceOrderGoods_N";

    /**
     * 给ERP发送信息
     *
     * @param produceOrderGoodsInfoListMsg 生产订单消息体
     */
    public void sendProduceOrderGoodsMsg(ProduceOrderGoodsInfoListMsg produceOrderGoodsInfoListMsg) {

        boolean success;
        String produceOrderNo = produceOrderGoodsInfoListMsg.getProduceOrderGoodsInfoMsgs().get(0).getProduceOrderNo();
        String msgStr = JSON.toJSONString(produceOrderGoodsInfoListMsg);
        log.info("发送生产单消息开始,消息内容:{}", msgStr);
        success = unifiedMqProducer.
                sendMq(PRODUCE_ORDER_GOODS_MSG_TOPIC, "", produceOrderNo, msgStr);
        log.info("发送生产单消息结束,生产单号:{},结果:{}", produceOrderNo, success);
        if (!success) {
            log.error("SendErpMsg.sendBatchTradeMsg send msg error,produceOrderNO:{}", produceOrderNo);
            throw new BizException(Errors.ERP_MESSAGE_ERROR);
        }
    }

}
