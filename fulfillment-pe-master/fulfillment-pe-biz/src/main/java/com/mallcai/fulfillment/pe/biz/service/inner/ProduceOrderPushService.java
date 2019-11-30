package com.mallcai.fulfillment.pe.biz.service.inner;

import java.util.Date;

/**
 * @description: 生产单推送接口
 * @author: chentao
 * @create: 2019-08-24 20:00:26
 */
public interface ProduceOrderPushService {

    /**
     * 推送发货单，时间左闭右开
     * @param startTime 推送开始时间
     * @param endTime 推送截止时间
     */
    void pushProduceOrder(Date startTime, Date endTime);
}
