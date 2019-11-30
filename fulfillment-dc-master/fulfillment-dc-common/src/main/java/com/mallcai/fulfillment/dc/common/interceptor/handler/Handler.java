package com.mallcai.fulfillment.dc.common.interceptor.handler;

/**
 * handler
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public interface Handler {

    /**
     * 处理器
     *
     * @param handleRequest
     * @return
     */
    HandleResponse handle(HandleRequest handleRequest);


}
