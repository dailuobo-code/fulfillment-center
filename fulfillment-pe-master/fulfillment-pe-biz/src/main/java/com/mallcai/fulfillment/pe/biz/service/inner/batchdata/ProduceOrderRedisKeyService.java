package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

/**
 * @description: 生产单redis key 处理业务接口
 * @author: chentao
 * @create: 2019-10-20 02:03:20
 */
public interface ProduceOrderRedisKeyService {

    /**
     * 构建记录该批次所有的生产单key的集合的key
     * @param batchId
     * @return
     */
    String buildAggregateOrderBatchAllKey(String batchId);
}
