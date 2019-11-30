package com.mallcai.fulfillment.pe.common.utils;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.common.exception.Errors;

/**
 * @author yl
 * @description ResponseBuilder
 * @date 2019-06-25
 */
public class PlainResultBuilder {

    public static <T> PlainResult<T> success(T t) {
        PlainResult<T> plainResult = new PlainResult<>();
        plainResult.setSuccess(true);
        plainResult.setResult(t);
        return plainResult;
    }

    public static <T> PlainResult<T> fail(int code, String msg) {
        PlainResult<T> plainResult = new PlainResult<>();
        plainResult.setSuccess(false);
        plainResult.setCode(code);
        plainResult.setError(msg);
        return plainResult;
    }

    public static <T> PlainResult<T> fail(int code, String msg, T t) {
        PlainResult<T> plainResult = new PlainResult<>();
        plainResult.setSuccess(false);
        plainResult.setCode(code);
        plainResult.setError(msg);
        plainResult.setResult(t);
        return plainResult;
    }

    public static <T> PlainResult<T> fail(Errors errors) {
        PlainResult<T> plainResult = new PlainResult<>();
        plainResult.setSuccess(false);
        plainResult.setCode(errors.getCode());
        plainResult.setError(errors.getMessage());
        return plainResult;
    }

}
