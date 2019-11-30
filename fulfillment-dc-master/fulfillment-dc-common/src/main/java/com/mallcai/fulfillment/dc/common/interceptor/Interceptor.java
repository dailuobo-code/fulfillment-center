package com.mallcai.fulfillment.dc.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 拦截器
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public interface Interceptor {

    /**
     * 拦截方法
     *
     * @param point
     * @return
     * @throws Throwable
     */
    Object intercept(ProceedingJoinPoint point) throws Throwable;

}
