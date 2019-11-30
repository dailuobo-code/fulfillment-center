package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.ProduceOrderRedisKeyService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataMergeHandleService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums.BatchDataTaskParamKeyEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.lock.DataReloadAndAggregateOrderLockService;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 集单批分页数据聚合实现类
 * @author: chentao
 * @create: 2019-10-10 00:57:05
 */
@Slf4j
@Service
public class ProduceOrderCreateSplitDataMergeHandleServiceImpl implements SplitDataMergeHandleService {

    @Autowired
    private DataReloadAndAggregateOrderLockService dataReloadAndAggregateOrderLockService;

    @Autowired
    private ProduceOrderRedisKeyService produceOrderRedisKeyService;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {
        return BatchDataTypeEnum.CREATE_PRODUCE_ORDER;
    }

    @Override
    public void mergeSplitDataHandleResult(BatchDataHandleSucMessage batchDataHandleSucMessage) {

        JSONObject param = JSONObject.parseObject(batchDataHandleSucMessage.getParam());

        log.info("创建生产单成功，message:{}", JSON.toJSONString(batchDataHandleSucMessage));

        Long result = null;

        // 记录所有生产单redis key 的集合
        String parentTaskId = batchDataHandleSucMessage.getParentBatchTaskIds().get(0);
        log.info("parentTaskId:{}", parentTaskId);
        String batchAllProduceOrderRedisKey = produceOrderRedisKeyService.buildAggregateOrderBatchAllKey(parentTaskId);
        try {
            result = jedisClientUtil.del(batchAllProduceOrderRedisKey);
        } catch (Exception e) {
            log.error("删除发送成功消息key异常,batchId:{},key:{}", batchDataHandleSucMessage.getBatchId(), batchAllProduceOrderRedisKey);
        }

        log.info("创建生产单成功，删除记录对应批次所有生产单的key。batchId:{},key:{},reuslt:{}", batchDataHandleSucMessage.getBatchId(), batchAllProduceOrderRedisKey, result);
        // 生鲜的需要删除指定仓库的集单锁
        if (param != null && GroupValueEnum.FRESH_PRODUCT.getGroupValue().equals(param.get(BatchDataTaskParamKeyEnum.GROUP_VALUE.getKey()))){

            Integer wareHouseId = param.getInteger(BatchDataTaskParamKeyEnum.WARE_HOUSE_ID.getKey());
            log.info("删除生鲜集单锁");
            dataReloadAndAggregateOrderLockService.releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.AGGREGATE_ORDER ,wareHouseId);
        }
    }
}
