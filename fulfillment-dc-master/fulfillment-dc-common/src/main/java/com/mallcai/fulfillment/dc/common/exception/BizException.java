package com.mallcai.fulfillment.dc.common.exception;

import lombok.Data;

import java.text.MessageFormat;

/**
 * 异常处理类
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
@Data
public class BizException extends RuntimeException {

    private int code;

    private String msg;

    private Exception e;

    public BizException() {
    }

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    public BizException(int code, String msg, Exception e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.e = e;
    }

    public BizException(int code, String msgFormat, Object... replacement) {
        this(code, MessageFormat.format(msgFormat, replacement));
    }

    public BizException(int code, String msgFormat, Exception e, Object... replacement) {
        this(code, MessageFormat.format(msgFormat, replacement), e);
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Errors errors) {
        this(errors.getCode(), errors.getMessage());
    }

    public BizException(Errors errors, Exception e) {
        this(errors.getCode(), errors.getMessage(), e);
    }

}
