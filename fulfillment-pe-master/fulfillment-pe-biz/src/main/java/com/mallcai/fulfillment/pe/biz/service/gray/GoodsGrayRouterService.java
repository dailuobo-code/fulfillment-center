package com.mallcai.fulfillment.pe.biz.service.gray;

import java.util.List;

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


    /**
     * 获取所有城市开关是否打开
     */
    boolean isAllOpen();


    /**
     * 获取所有的货品白名单城市
     */
    List<Integer> getAllCities();
}
