

package com.mallcai.fulfillment.dc.common.interceptor.handler;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import com.mallcai.fulfillment.dc.common.utils.LazyLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * 异常拦截处理器
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class ExceptionHandler implements Handler {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final String
            EXCEPTION_MSG_FORMAT =
            "{} execute error!!!\nkey info below:\n\t\t{}\n\t\tresult: {}\n\t\texception: {}\n\t\tmsg: {}\nstack trace below: ";

    @Override
    public HandleResponse handle(HandleRequest handleRequest) {
        Exception e = handleRequest.getException();
        PlainResult rpcResult = new PlainResult();

        String method = HandlerHelper.getMethodString(handleRequest.getProceedingJoinPoint());
        String originalRequestParams = handleRequest.getOriginalRequestParam();
        String clazzName = e.getClass().getName();
        Class exceptionClass = null;

        //异常处理,报警,监控上报在这里做
        if (e instanceof BizException) {
            BizException exception = (BizException) e;
            buildBizExceptionResult(rpcResult, exception);
            LazyLogs.warn(logger,
                    EXCEPTION_MSG_FORMAT,
                    () -> method,
                    () -> originalRequestParams,
                    () -> toJSONString(rpcResult),
                    () -> clazzName,
                    exception::getMsg);
            exceptionClass = BizException.class;
        } else {
            buildUnknownExceptionResult(rpcResult, e);
            //error 级别
            LazyLogs.error(logger,
                    EXCEPTION_MSG_FORMAT,
                    () -> method,
                    () -> originalRequestParams,
                    () -> toJSONString(rpcResult),
                    () -> clazzName,
                    e::getMessage,
                    () -> e);
            exceptionClass = Exception.class;
        }
        HandleResponse response = new HandleResponse();
        response.setResult(rpcResult);
        response.setExceptionClass(exceptionClass);
        return response;
    }

    private void buildBizExceptionResult(PlainResult rpcResult, BizException te) {
        rpcResult.setSuccess(false);
        rpcResult.setCode(te.getCode());
        rpcResult.setError(te.getMsg());
    }

    private void buildUnknownExceptionResult(PlainResult rpcResult, Exception e) {
        rpcResult.setSuccess(false);
        rpcResult.setCode(Errors.FULFILLMENT_SYSTEM_ERROR.getCode());
        rpcResult.setError(Errors.FULFILLMENT_SYSTEM_ERROR.getMessage());
    }
}
