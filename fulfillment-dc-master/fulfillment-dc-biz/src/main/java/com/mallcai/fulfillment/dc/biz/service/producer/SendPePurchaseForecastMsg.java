package com.mallcai.fulfillment.dc.biz.service.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发送消息给PE
 * @author Liu Yang
 * @date 2019-09-29 00:03:14
 */
@Service("sendPePurchaseForecastMsg")
@Slf4j
public class SendPePurchaseForecastMsg {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * 给PE发送采购预测信息
     * @param purchaseForecastInfoListMsg  大数据采购预测消息体
     */
    public void sendBatchMsg(PurchaseForecastInfoListDTO purchaseForecastInfoListMsg) {

        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(purchaseForecastInfoListMsg);
            log.info("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}", purchaseForecastInfoListMsg.getCityId(),purchaseForecastInfoListMsg.getWarehouseId(),
                purchaseForecastInfoListMsg.getVersion(),purchaseForecastInfoListMsg.getCount());
            success = unifiedMqProducer.
                    sendMq(Constants.PE_PURCHASE_FORECAST_TOPIC, Constants.PE_PURCHASE_FORECAST_TAGS,"", msgStr);
            log.info("发送大数据采购预测消息结束,城市ID:{}，仓库ID:{}，版本号:{},批次:{},结果:{}",  purchaseForecastInfoListMsg.getCityId(),purchaseForecastInfoListMsg.getWarehouseId(),
                purchaseForecastInfoListMsg.getVersion(),purchaseForecastInfoListMsg.getCount(),success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("SendPePurchaseForecastMsg.sendBatchMsg send msg error,error msg is produceOrderInfoListMsg:{}",JSON.toJSONString(purchaseForecastInfoListMsg));
        }
    }

}
