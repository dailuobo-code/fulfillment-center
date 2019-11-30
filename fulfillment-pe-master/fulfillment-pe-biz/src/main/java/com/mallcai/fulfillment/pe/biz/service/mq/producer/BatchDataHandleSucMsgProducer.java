package com.mallcai.fulfillment.pe.biz.service.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import com.mallcai.fulfillment.pe.common.mq.UnifiedMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 批处理任务处理成功生产者
 * @author: chentao
 * @create: 2019-10-14 01:03:41
 */
@Service
@Slf4j
public class BatchDataHandleSucMsgProducer {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * @param batchDataHandleSucMessage 批处理任务处理成功消息
     */
    public void sendBatchDataHandleSucMsg(BatchDataHandleSucMessage batchDataHandleSucMessage) {

        boolean success;
        String batchTaskId = batchDataHandleSucMessage.getBatchId();

        String msgStr = JSON.toJSONString(batchDataHandleSucMessage);

        log.info("发送批处理子任务成功消息开始,batchTaskId:{},content:{}", batchTaskId, msgStr);
        success = unifiedMqProducer.
                sendMq(BizConstant.BATCH_DATA_TASK_HANDLE_SUC_TOPIC, "", batchTaskId, msgStr);
        log.info("发送批处理子任务成功消息结束,batchTaskId:{},结果:{}", batchTaskId, success);
        if (!success) {
            log.error("发送批处理子任务成功消息异常,batchTaskId:{}", batchTaskId);
            throw new BizException(Errors.BATCH_DATA_TASK_SEND_ERROR);
        }
    }
}
