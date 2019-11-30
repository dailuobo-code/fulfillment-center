package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataHandleSucMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @description: 批量数据处理接口
 * @author: chentao
 * @create: 2019-10-08 20:58:35
 */
public interface BatchDataHandleService {

    /**
     * 分割批量数据
     *
     */
    void splitData(BatchDataTaskMessage batchDataTaskMessage);

    /**
     * 处理分页消息
     * @param message
     * @return
     */
    boolean handleSplitData(SplitDataMessage message);

    /**
     * merge分布式结果
     * @param batchDataHandleSucMessage
     */
    void mergeSplitDataHandleResult(BatchDataHandleSucMessage batchDataHandleSucMessage);
}
