package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.impl;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateQueryConf;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.UniqueIdLoadService;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 集单uniqueId获取实现
 * @author: chentao
 * @create: 2019-10-22 15:41:21
 */
@Slf4j
@Service
public class AggregateOrderUniqueIdLoadServiceImpl implements UniqueIdLoadService {

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Override
    public BatchDataTypeEnum getHandleBatchDataType() {
        return BatchDataTypeEnum.AGGREGATE_ORDER;
    }

    @Override
    public List<Object> getUniqueIds(BatchDataTaskMessage batchDataTaskMessage) {

        log.info("开始获取集单uniqueIds, message:{}", JSON.toJSONString(batchDataTaskMessage));
        String param = batchDataTaskMessage.getParam();

        AggregateQueryConf aggregateQueryConf = JSON.parseObject(param, AggregateQueryConf.class);
        List<Long> orderIds = aggreOrderQueryService.queryOrderIds(aggregateQueryConf);

        return orderIds.stream().map(id -> (Object)id).collect(Collectors.toList());
    }
}
