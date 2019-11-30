package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.ProduceOrderHandleMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataMergeHandleService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums.BatchDataTaskParamKeyEnum;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.BatchDataTaskMsgProducer;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @description: 集单批分页数据聚合实现类
 * @author: chentao
 * @create: 2019-10-10 00:57:05
 */
@Slf4j
@Service
public class AggregateOrderSplitDataMergeHandleServiceImpl implements SplitDataMergeHandleService {

    /**
     * 单次处理生产单数量
     */
    private Integer CREATE_PRODUCE_ORDER_BATCH_DATA_SPLIT_COUNT = 10;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Autowired
    private BatchDataTaskMsgProducer batchDataTaskMsgProducer;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {
        return BatchDataTypeEnum.AGGREGATE_ORDER;
    }

    @Override
    public void mergeSplitDataHandleResult(BatchDataHandleSucMessage batchDataHandleSucMessage) {

        log.info("集单成功,sucmessage:{}", JSON.toJSONString(batchDataHandleSucMessage));
        String allProduceOrderMapKey = String.format(BizConstant.AGGREGATE_ORDER_BATCH_ALL_KEY_FORMAT, batchDataHandleSucMessage.getBatchId());
        log.info("获取所有生产单集合的key:{}", allProduceOrderMapKey);

        sendCreateProduceOrderBatchDataTaskMsg(batchDataHandleSucMessage, allProduceOrderMapKey);
    }

    /**
     * 发送生产单床架批处理任务消息
     */
    private void sendCreateProduceOrderBatchDataTaskMsg(BatchDataHandleSucMessage batchDataHandleSucMessage, String allProduceOrderMapKey){

        BatchDataTaskMessage batchDataTaskMessage = new BatchDataTaskMessage();

        batchDataTaskMessage.setBatchTaskId(UUID.randomUUID().toString());
        batchDataTaskMessage.setBatchDataTypeEnum(BatchDataTypeEnum.CREATE_PRODUCE_ORDER);
        batchDataTaskMessage.setSplitCount(CREATE_PRODUCE_ORDER_BATCH_DATA_SPLIT_COUNT);
        batchDataTaskMessage.setParentBatchTaskIds(batchDataHandleSucMessage.getParentBatchTaskIds());

        JSONObject paramOri = JSONObject.parseObject(batchDataHandleSucMessage.getParam());

        paramOri.put(BatchDataTaskParamKeyEnum.ALL_PRODUCE_ORDER_KEY.getKey(), allProduceOrderMapKey);
        batchDataTaskMessage.setParam(paramOri.toJSONString());

        batchDataTaskMsgProducer.sendBatchDataTaskMsg(batchDataTaskMessage);
    }

    /**
     * 删除所有produceOrderKey集合中发送成功分页生产单消息的key
     *
     * @param batchId
     * @param sendSucMessage
     */
    private void delSendSucKey(String batchId, ProduceOrderHandleMessage sendSucMessage) {

        List<String> sucKeys = sendSucMessage.getProduceOrderKeyAndSizeList().stream().map(map -> map.getValue()).collect(Collectors.toList());

        Long result = null;
        try {
            result = jedisClientUtil.hdel(String.format(BizConstant.AGGREGATE_ORDER_BATCH_ALL_KEY_FORMAT, batchId), (String[]) sucKeys.toArray());
        } catch (Exception e) {
            log.error("删除发送成功消息key异常,batchId:{},sucKeys:{}", batchId, StringUtils.join(sucKeys, Constants.SYMBOL_COMMA));
        }
        log.info("删除发送成功消息key成功,batchId:{},sucKeys:{},result:{}", batchId, StringUtils.join(sucKeys, Constants.SYMBOL_COMMA), result);
    }
}
