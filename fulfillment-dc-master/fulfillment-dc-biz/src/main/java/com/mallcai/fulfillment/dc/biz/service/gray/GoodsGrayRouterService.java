package com.mallcai.fulfillment.dc.biz.service.gray;

/**
 * @description: 货品灰度路由器
 * @author: chentao
 * @create: 2019-10-09 21:38:17
 */
public interface GoodsGrayRouterService {

    /**
     * 是否需要路由
     * @return
     */
    boolean isNeedRoute(Integer cityId);
}
