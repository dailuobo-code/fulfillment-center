package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;

/**
 * @description: 批分页数据聚合处理业务接口
 * @author: chentao
 * @create: 2019-10-10 00:54:56
 */
public interface SplitDataMergeHandleService {

    BatchDataTypeEnum getHandleBatchDataType();

    void mergeSplitDataHandleResult(BatchDataHandleSucMessage batchDataHandleSucMessage);
}
