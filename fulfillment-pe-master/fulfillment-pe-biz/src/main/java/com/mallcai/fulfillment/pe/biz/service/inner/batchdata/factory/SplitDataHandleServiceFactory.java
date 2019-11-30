package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory;

import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.SplitDataHandleService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 分页业务处理工厂
 * @author: chentao
 * @create: 2019-10-10 00:59:53
 */
@Component
public class SplitDataHandleServiceFactory implements ApplicationContextAware {

    private static Map<BatchDataTypeEnum, SplitDataHandleService> splitDataHandleServiceBeanMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, SplitDataHandleService> map = applicationContext.getBeansOfType(SplitDataHandleService.class);
        splitDataHandleServiceBeanMap = new HashMap<>();
        map.forEach((key, value) -> splitDataHandleServiceBeanMap.put(value.getHandleBatchDataType(), value));
    }

    public static SplitDataHandleService getSplitDataHandleService(BatchDataTypeEnum batchDataTypeEnum) {

        return splitDataHandleServiceBeanMap.get(batchDataTypeEnum);
    }
}
