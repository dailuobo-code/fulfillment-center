package com.mallcai.manager.common.exception;

import lombok.Data;

import java.text.MessageFormat;

/**
 * 异常处理类
 * @author zhanghao
 * @date 2019年08月13日23:40:55
 */
@Data
public class DaoException extends RuntimeException {

    private int code;

    private String msg;

    private Exception e;

    public DaoException() {
    }

    public DaoException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public DaoException(ErrorCode errorCode){

        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }


    public DaoException(int code, String msg, Exception e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
        this.e = e;
    }

    public DaoException(int code, String msgFormat, Object... replacement) {
        this(code, MessageFormat.format(msgFormat, replacement));
    }

    public DaoException(int code, String msgFormat, Exception e, Object... replacement) {
        this(code, MessageFormat.format(msgFormat, replacement), e);
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Errors errors) {
        this(errors.getCode(), errors.getMessage());
    }

    public DaoException(Errors errors, Exception e) {
        this(errors.getCode(), errors.getMessage(), e);
    }

}
