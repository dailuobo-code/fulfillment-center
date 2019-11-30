package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory;

import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataMergeHandleService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-10-10 01:00:40
 */
@Component
public class SplitDataMergeHandleServiceFactory implements ApplicationContextAware {

    private static Map<BatchDataTypeEnum, SplitDataMergeHandleService> splitDataMergeHandleServiceBeanMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, SplitDataMergeHandleService> map = applicationContext.getBeansOfType(SplitDataMergeHandleService.class);
        splitDataMergeHandleServiceBeanMap = new HashMap<>();
        map.forEach((key, value) -> splitDataMergeHandleServiceBeanMap.put(value.getHandleBatchDataType(), value));
    }

    public static SplitDataMergeHandleService getSplitDataMergeHandleService(BatchDataTypeEnum batchDataTypeEnum) {

        return splitDataMergeHandleServiceBeanMap.get(batchDataTypeEnum);
    }
}
