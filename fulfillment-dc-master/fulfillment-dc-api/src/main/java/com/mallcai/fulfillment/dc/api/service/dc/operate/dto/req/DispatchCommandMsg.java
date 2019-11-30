package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 调度指令消息
 * @author: chentao
 * @create: 2019-11-14 10:39:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchCommandMsg {

    /**
     * 指令类型
     */
    private CommandTypeEnum commandType;

    /**
     * 指令号
     */
    private String commandNo;

    /**
     * 参数
     */
    private String data;
}
