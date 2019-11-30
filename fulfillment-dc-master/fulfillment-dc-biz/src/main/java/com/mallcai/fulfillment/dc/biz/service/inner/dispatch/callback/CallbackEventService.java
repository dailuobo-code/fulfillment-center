package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.callback;

import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.DispatchCommandReceiptMsg;

/**
 * @description: 回调事件处理
 * @author: chentao
 * @create: 2019-11-16
 */
public interface CallbackEventService {

    /**
     * 处理回调事件
     * @param dispatchCommandReceiptMsg
     */
    void handleCallback(DispatchCommandReceiptMsg dispatchCommandReceiptMsg);
}
