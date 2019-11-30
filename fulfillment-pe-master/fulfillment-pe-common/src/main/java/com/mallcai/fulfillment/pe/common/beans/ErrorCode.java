package com.mallcai.fulfillment.pe.common.beans;

import lombok.Data;

/**
 * @description: 错误码常量类
 * @author: chentao
 * @create: 2019-08-23 17:19:41
 */
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
