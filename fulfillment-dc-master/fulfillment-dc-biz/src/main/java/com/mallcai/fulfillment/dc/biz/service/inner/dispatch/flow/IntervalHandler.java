package com.mallcai.fulfillment.dc.biz.service.inner.dispatch.flow;

import java.util.Date;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18
 */
public interface IntervalHandler {

    /**
     * 获取delay节点需要执行的时间
     * @return
     */
    Date getExecuteTime();
}
