package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.ProduceOrderRedisKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 生产单redis key 处理实现类
 * @author: chentao
 * @create: 2019-10-20 02:03:57
 */
@Slf4j
@Service
public class ProduceOrderRedisKeyServiceImpl implements ProduceOrderRedisKeyService {

    @Override
    public String buildAggregateOrderBatchAllKey(String batchId) {
        return String.format(BizConstant.AGGREGATE_ORDER_BATCH_ALL_KEY_FORMAT, batchId);
    }
}
