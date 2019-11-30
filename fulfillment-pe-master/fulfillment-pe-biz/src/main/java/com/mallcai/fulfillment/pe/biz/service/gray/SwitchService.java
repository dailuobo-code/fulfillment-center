package com.mallcai.fulfillment.pe.biz.service.gray;

/**
 * @description: 开关接口
 * @author: chentao
 * @create: 2019-10-17 22:55:38
 */
public interface SwitchService {

    /**
     * 分布式集单开关是否打开，true：打开；false：关闭
     * @return
     */
    boolean isDistributedAggregateOpened();
}
