package com.mallcai.fulfillment.dc.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author bh.zhong
 * @date 2019/8/19 9:49 AM
 */
public class SpringContextUtil implements ApplicationContextAware {
    /** Spring应用上下文环境 **/
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
    /**
     * 获取对象
     *
     * @param beanId
     * @return  Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

    /**
     * 获取对象
     *
     * @param requiredType
     * @return  Object
     * @throws BeansException 如果beanClass存在多个bean则报异常
     */
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }
}

