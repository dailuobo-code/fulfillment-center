package com.mallcai.fulfillment.biz.service;

import com.mallcai.fulfillment.api.msg.UnifiedCompareMsg;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-11-14 10:49
 */
public interface UnifiedCompareService {
    /**
     * 根据不同的比较类型，选择不同的对账比较器进行比较并返回结果
     * @param unifiedCompareMsg
     * @return
     */
    boolean processData(UnifiedCompareMsg unifiedCompareMsg);
}
