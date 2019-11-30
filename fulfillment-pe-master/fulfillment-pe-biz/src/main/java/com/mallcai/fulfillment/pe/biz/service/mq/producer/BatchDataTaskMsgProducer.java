package com.mallcai.fulfillment.pe.biz.service.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
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
public class BatchDataTaskMsgProducer {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * @param batchDataTaskMessage 批处理任务消息
     */
    public void sendBatchDataTaskMsg(BatchDataTaskMessage batchDataTaskMessage) {

        boolean success;
        String batchTaskId = batchDataTaskMessage.getBatchTaskId();
        String msgStr = JSON.toJSONString(batchDataTaskMessage);

        log.info("发送批处理任务开始,batchTaskId:{},content:{}", batchTaskId, msgStr);
        success = unifiedMqProducer.
                sendMq(BizConstant.BATCH_DATA_TASK_MSG_TOPIC, "", batchTaskId, msgStr);
        log.info("发送批处理任务结束,batchTaskId:{},结果:{}", batchTaskId, success);
        if (!success) {
            log.error("发送批处理任务异常,batchTaskId:{}", batchTaskId);
            throw new BizException(Errors.BATCH_DATA_TASK_SEND_ERROR);
        }
    }

}
