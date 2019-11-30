package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-09-09 18:00:28
 */
@Data
public class MessageRetryBo {

    private String topic;

    private String tag;

    private String key;

    /**
     *  消息体jsonStr
     */
    private String message;
}
