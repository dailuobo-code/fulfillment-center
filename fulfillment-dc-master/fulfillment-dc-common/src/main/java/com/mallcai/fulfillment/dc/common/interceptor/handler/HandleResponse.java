package com.mallcai.fulfillment.dc.common.interceptor.handler;

/**
 * HandleResponse
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class HandleResponse {

    private Object result;

    private Class exceptionClass;

    public HandleResponse() {
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Class getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(Class exceptionClass) {
        this.exceptionClass = exceptionClass;
    }
}
