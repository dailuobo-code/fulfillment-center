package com.mallcai.fulfillment.common.exception;

import lombok.Data;

@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;

    public ErrorCode(){};

    public ErrorCode(Integer code, String msg){

        this.code = code;
        this.msg = msg;
    }
}
