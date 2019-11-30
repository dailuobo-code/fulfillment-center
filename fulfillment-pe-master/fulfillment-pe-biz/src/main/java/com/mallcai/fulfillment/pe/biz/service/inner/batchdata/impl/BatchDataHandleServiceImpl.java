package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.*;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory.SplitDataHandleServiceFactory;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory.SplitDataMergeHandleServiceFactory;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory.UniqueIdLoadServiceFactory;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.BatchDataHandleSucMsgProducer;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.SplitDataTaskMsgProducer;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 批量数据处理抽象类
 * @author: chentao
 * @create: 2019-10-08 21:04:20
 */
@Slf4j
@Service
public class BatchDataHandleServiceImpl implements BatchDataHandleService {

    /**
     * 批量数据计数key失效时间，单位：秒
     */
    private final Integer BATCH_DATA_COUNT_EXPIRE_TIME = 24 * 60 * 60;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Autowired
    private SplitDataTaskMsgProducer splitDataTaskMsgProducer;

    @Autowired
    private BatchDataHandleSucMsgProducer batchDataHandleSucMsgProducer;

    @Override
    public void splitData(BatchDataTaskMessage batchDataTaskMessage){

        List<Object> uniqueIds = getUniqueIds(batchDataTaskMessage);

        Integer totalCount = uniqueIds.size();

        if (totalCount == 0){

            log.info("数据总数为0，不需要处理,dataType:{}", batchDataTaskMessage.getBatchDataTypeEnum().getType());
            return;
        }

        List<List<Object>> splitUniqueIds = Lists.partition(uniqueIds, batchDataTaskMessage.getSplitCount());

        String batchId = batchDataTaskMessage.getBatchTaskId();
        jedisClientUtil.setex(buildBatchDataCountKey(batchId, batchDataTaskMessage.getBatchDataTypeEnum()), BATCH_DATA_COUNT_EXPIRE_TIME, totalCount.toString());

        log.info("任务总数入redis成功,batchId:{},size:{}", batchId, totalCount);

        List<SplitDataMessage> messages = new ArrayList<>();

        for (int i = 0; i < splitUniqueIds.size(); i++) {

            String pageId = batchId + Constants.SYMBOL_UNDERLINE + i;

            messages.add(new SplitDataMessage(batchId, pageId, splitUniqueIds.get(i), splitUniqueIds.get(i).size(), batchDataTaskMessage.getBatchDataTypeEnum(), batchDataTaskMessage.getParentBatchTaskIds(), batchDataTaskMessage.getParam()));
        }

        boolean allSplitDataMessageSendSuc = true;

        // todo 可以异常少发些重复消息
        // 若由因异常导致了重发由业务方保证幂等（异常业务重新删选数据已经会做状态过滤，处理中的业务方查询数据时也应该不会查询出来）
        for (SplitDataMessage message : messages){

            try {
                sendSplitData(message);
            } catch (Exception e) {

                allSplitDataMessageSendSuc = false;
            }
        }

        if (!allSplitDataMessageSendSuc){

            throw new BizException(Errors.BATCH_DATA_SPLIT_ERROR);
        }

    }

    @Override
    public boolean handleSplitData(SplitDataMessage message){

        log.info("子任务处理开始,message:{}", JSON.toJSONString(message));
        // step 消费or拆分
        handleSplitDataBusiness(message);

        handleSplitDataResult(message);

        return true;
    }

    @Override
    public void mergeSplitDataHandleResult(BatchDataHandleSucMessage batchDataHandleSucMessage) {

        SplitDataMergeHandleService splitDataMergeHandleService = SplitDataMergeHandleServiceFactory.getSplitDataMergeHandleService(batchDataHandleSucMessage.getBatchDataTypeEnum());

        // 有些业务场景没有分布式处理成功后还需要处理的场景
        if (splitDataMergeHandleService == null){

            return;
        }

        splitDataMergeHandleService.mergeSplitDataHandleResult(batchDataHandleSucMessage);

    }

    /**
     * 获取唯一ids
     * @param batchDataTaskMessage
     * @return
     */
    private List<Object> getUniqueIds(BatchDataTaskMessage batchDataTaskMessage){

        UniqueIdLoadService uniqueIdLoadService = UniqueIdLoadServiceFactory.getUniqueIdLoadService(batchDataTaskMessage.getBatchDataTypeEnum());

        return uniqueIdLoadService.getUniqueIds(batchDataTaskMessage);
    }
    /**
     * 处理分页业务
     * @param message
     */
    private void handleSplitDataBusiness(SplitDataMessage message){

        SplitDataHandleService splitDataHandleService = SplitDataHandleServiceFactory.getSplitDataHandleService(message.getBatchDataTypeEnum());

        splitDataHandleService.handle(message);
    }

    /**
     * 批处理子任务处理成功后的结果处理
     * @param message
     */
    private void handleSplitDataResult(SplitDataMessage message){

        // 总计数器key
        String batchDataCountKey = buildBatchDataCountKey(message.getBatchId(), message.getBatchDataTypeEnum());

        Long decrResult = null;
        try {
            decrResult = jedisClientUtil.decrBy(batchDataCountKey, message.getCount());
        } catch (Exception e) {
            log.error("分页处理完成计数扣减异常,batchDataCountKey:{},batchId:{},pageId:{}",batchDataCountKey, message.getBatchId(), message.getPageId(), e);
            throw new BizException();
        }

        String pageKey = buildBatchDataPageKey(message);
        try {
            jedisClientUtil.del(pageKey);
        } catch (Exception e) {
            log.error("删除pageKey异常,pageKey:{}", pageKey, e);
        }

        log.info("批处理扣减结果，batchDataCountKey:{},datatype:{},batchId:{},,pageId:{},decrResult:{}",batchDataCountKey, message.getBatchDataTypeEnum().getDesc(), message.getBatchId(), message.getPageId(), decrResult);

        // 该批次总数扣减为0时则证明该批次处理完成,防止消息重复消费产生超扣的情况，改为<=0
        if (decrResult <= 0){

            log.info("批处理成功,开始发送消息，message:{}", JSON.toJSONString(message));
            sendBatchDataHandleSuccessMessage(message);
            log.info("批处理处理成功消息发送成功，batchId:{}", message.getBatchId());
        }
    }


    /**
     * 发送分页消息
     * @param splitDataMessage
     */
    private void sendSplitData(SplitDataMessage splitDataMessage){

        // 可以考虑加分布式锁，目前感觉没必要
        String pageKey = buildBatchDataPageKey(splitDataMessage);

        if (jedisClientUtil.exists(pageKey)){

            log.info("该消息已发送，pageKey:{}", pageKey);
            return;
        }

        splitDataTaskMsgProducer.sendSplitDataTaskMsg(splitDataMessage);

        // 消息发送成功后记录下分页消息，以方消息重复消费时重复发送
        jedisClientUtil.setex(pageKey, BizConstant.ONE_DAY_SECS, splitDataMessage.getCount().toString());
        log.info("分布式消息发送成功,pageKey:{}", pageKey);
    }

    private void sendBatchDataHandleSuccessMessage(SplitDataMessage message){

        List<String> parentBatchTaskIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(message.getParentBatchTaskIds())){

            parentBatchTaskIds.addAll(message.getParentBatchTaskIds());
            parentBatchTaskIds.add(message.getBatchId());
        }else {

            parentBatchTaskIds.add(message.getBatchId());
        }

        BatchDataHandleSucMessage batchDataHandleSucMessage = new BatchDataHandleSucMessage(message.getBatchId(), message.getBatchDataTypeEnum(), parentBatchTaskIds, message.getParam());



        batchDataHandleSucMsgProducer.sendBatchDataHandleSucMsg(batchDataHandleSucMessage);

    }

    private String buildBatchDataCountKey(String batchId, BatchDataTypeEnum batchDataTypeEnum){

        StringBuilder sb = new StringBuilder();

        sb.append(Constant.BATCH_DATA_COUNT_PREFIX).
                append(batchDataTypeEnum.getType()).
                append(Constants.SYMBOL_UNDERLINE).
                append(batchId);

        return sb.toString();
    }

    private String buildBatchDataPageKey(SplitDataMessage splitDataMessage){

        String pageKey = String.format(Constant.BATCH_DATA_PAGE_PREFIX, splitDataMessage.getBatchId(), splitDataMessage.getPageId());
        return pageKey;
    }
}
