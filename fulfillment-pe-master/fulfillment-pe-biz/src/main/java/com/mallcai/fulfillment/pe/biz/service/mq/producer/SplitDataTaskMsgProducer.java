package com.mallcai.fulfillment.pe.biz.service.mq.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
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
 * 发送货品生产单消息给ERP
 *
 * @author bh.zhong
 * @date 2019/8/24 3:24 PM
 */
@Service
@Slf4j
public class SplitDataTaskMsgProducer {

    @Resource
    private UnifiedMqProducer unifiedMqProducer;

    /**
     * @param splitDataMessage 批处理子任务消息
     */
    public void sendSplitDataTaskMsg(SplitDataMessage splitDataMessage) {

        boolean success;
        String batchTaskId = splitDataMessage.getBatchId();
        String pageId = splitDataMessage.getPageId();

        String msgStr = JSON.toJSONString(splitDataMessage);

        log.info("发送批处理子任务消息开始,batchTaskId:{},pageId:{},content:{}", batchTaskId, pageId, msgStr);
        success = unifiedMqProducer.
                sendMq(BizConstant.SPLIT_DATA_TASK_MSG_TOPIC, splitDataMessage.getBatchDataTypeEnum().name(), batchTaskId, msgStr);
        log.info("发送批处理子任务消息结束,batchTaskId:{},pageId:{},结果:{}", batchTaskId, pageId, success);
        if (!success) {
            log.error("发送批处理子任务消息异常,batchTaskId:{},pageId:{}", batchTaskId, pageId);
            throw new BizException(Errors.BATCH_DATA_TASK_SEND_ERROR);
        }
    }

}
