package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;

import java.util.List;

/**
 * @description: uniqueId获取接口
 * @author: chentao
 * @create: 2019-10-22 15:22:40
 */
public interface UniqueIdLoadService {

    /**
     *
     * @return
     */
    BatchDataTypeEnum getHandleBatchDataType();

    /**
     * 获取uniqueIds
     * @param batchDataTaskMessage
     * @return
     */
    List<Object> getUniqueIds(BatchDataTaskMessage batchDataTaskMessage);
}
