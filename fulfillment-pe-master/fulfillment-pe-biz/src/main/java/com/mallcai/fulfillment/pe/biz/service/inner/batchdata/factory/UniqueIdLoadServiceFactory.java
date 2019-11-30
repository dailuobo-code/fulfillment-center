package com.mallcai.fulfillment.pe.biz.service.inner.batchdata.factory;

import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.UniqueIdLoadService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 业务获取uniqueId实现工厂
 * @author: chentao
 * @create: 2019-10-10 00:59:53
 */
@Component
public class UniqueIdLoadServiceFactory implements ApplicationContextAware {

    private static Map<BatchDataTypeEnum, UniqueIdLoadService> uniqueIdLoadServiceBeanMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, UniqueIdLoadService> map = applicationContext.getBeansOfType(UniqueIdLoadService.class);
        uniqueIdLoadServiceBeanMap = new HashMap<>();
        map.forEach((key, value) -> uniqueIdLoadServiceBeanMap.put(value.getHandleBatchDataType(), value));
    }

    public static UniqueIdLoadService getUniqueIdLoadService(BatchDataTypeEnum batchDataTypeEnum) {

        return uniqueIdLoadServiceBeanMap.get(batchDataTypeEnum);
    }
}
