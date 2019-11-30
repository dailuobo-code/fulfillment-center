package com.mallcai.fulfillment.dc.biz.service.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.OrderItemInfoListMsg;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 发送消息给PE
 * @author Liu Yang
 * @date 2019-09-29 00:03:14
 */
@Service("sendPeSortingForecastMsg")
@Slf4j
public class SendPeSortingForecastMsg {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * 给PE发送分拣预测信息
     * @param orderItemInfoListMsg  大数据分拣预测消息体
     */
    public void sendBatchMsg(OrderItemInfoListMsg orderItemInfoListMsg) {

        boolean success;
        int count = 0;
        int retryTimes = 3;
        do {
            count++;
            String msgStr = JSON.toJSONString(orderItemInfoListMsg);
            log.info("发送大数据采购预测消息开始,城市ID:{}，仓库ID:{}，版本号:{},批次:{}，预测时间:{}", orderItemInfoListMsg.getCityId(),orderItemInfoListMsg.getWarehouseId(),
                orderItemInfoListMsg.getVersion(),orderItemInfoListMsg.getCount(),orderItemInfoListMsg.getForecastDate());
            success = unifiedMqProducer.
                    sendMq(Constants.PE_SORTING_FORECAST_TOPIC, Constants.PE_SORTING_FORECAST_TAGS,"", msgStr);
            log.info("发送大数据采购预测消息结束,城市ID:{}，仓库ID:{}，版本号:{},批次:{},预测时间:{},结果:{}",  orderItemInfoListMsg.getCityId(),orderItemInfoListMsg.getWarehouseId(),
                orderItemInfoListMsg.getVersion(),orderItemInfoListMsg.getCount(),orderItemInfoListMsg.getForecastDate(),success);
        } while (!success && count < retryTimes);
        if (!success) {
            log.error("SendPeSortingForecastMsg.sendBatchMsg send msg error,error msg is orderItemInfoListMsg:{}",JSON.toJSONString(orderItemInfoListMsg));
        }
    }

}
