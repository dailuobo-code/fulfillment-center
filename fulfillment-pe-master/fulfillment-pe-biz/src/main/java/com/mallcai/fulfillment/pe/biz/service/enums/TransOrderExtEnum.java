package com.mallcai.fulfillment.pe.biz.service.enums;

import lombok.Getter;

/**
 * @description: 交易订单扩展信息枚举
 * @author: chentao
 * @create: 2019-08-23 23:13:51
 */
@Getter
public enum TransOrderExtEnum {

    USER_NAME("recipients", "客户姓名"),
    TEL("recipientsPhone", "客户联系方式");

    private String key;

    private String desc;

    TransOrderExtEnum(String key, String desc){

        this.key = key;
        this.desc = desc;
    }
}
