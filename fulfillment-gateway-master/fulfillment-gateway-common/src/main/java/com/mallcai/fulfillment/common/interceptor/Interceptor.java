package com.mallcai.fulfillment.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 拦截器
 *
 * @author admin
 * @date 2019-08-13 23:48:14
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
