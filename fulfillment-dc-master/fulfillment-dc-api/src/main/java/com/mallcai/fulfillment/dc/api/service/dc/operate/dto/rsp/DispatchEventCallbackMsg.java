package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp;

import com.mallcai.fulfillment.dc.common.enums.DispatchEventEnum;
import lombok.Data;

/**
 * @description: 调度事件回调消息
 * @author: chentao
 * @create: 2019-11-16 00:45:51
 */
@Data
public class DispatchEventCallbackMsg {

    /**
     *
     */
    private DispatchEventEnum dispatchEventEnum;

    private String data;
}
