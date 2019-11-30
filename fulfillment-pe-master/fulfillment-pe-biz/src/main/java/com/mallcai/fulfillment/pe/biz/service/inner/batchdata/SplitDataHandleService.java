package com.mallcai.fulfillment.pe.biz.service.inner.batchdata;

import com.mallcai.fulfillment.pe.biz.service.bo.SplitDataMessage;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;

/**
 * @description: 批分页数据业务处理类
 * @author: chentao
 * @create: 2019-10-10 00:50:57
 */
public interface SplitDataHandleService {

    BatchDataTypeEnum getHandleBatchDataType();

    void handle(SplitDataMessage splitDataMessage);
}
