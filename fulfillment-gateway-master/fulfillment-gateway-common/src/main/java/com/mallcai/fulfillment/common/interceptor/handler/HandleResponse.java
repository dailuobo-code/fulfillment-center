package com.mallcai.fulfillment.common.interceptor.handler;

/**
 * HandleResponse
 *
 * @author admin
 * @date 2019-08-13 23:46:40
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
