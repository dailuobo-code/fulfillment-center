package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp;

import com.mallcai.fulfillment.dc.common.enums.DispatchEventEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 调度指令回执消息
 * @author: chentao
 * @create: 2019-11-14 10:39:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchCommandReceiptMsg {

    /**
     * 指令类型
     */
    private DispatchEventEnum dispatchEventEnum;

    /**
     * 指令号
     */
    private String commandNo;

    /**
     * 结果
     */
    private boolean result = true;

    /**
     * data json格式
     */
    private String data;
}
