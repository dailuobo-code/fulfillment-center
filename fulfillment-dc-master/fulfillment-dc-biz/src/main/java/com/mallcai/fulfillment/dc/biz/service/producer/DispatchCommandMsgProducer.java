package com.mallcai.fulfillment.dc.biz.service.producer;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.exception.BizException;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 调度指令消息生产者
 * @author: chentao
 * @create: 2019-11-14 10:48:12
 */
@Slf4j
@Service
public class DispatchCommandMsgProducer {

    @Autowired
    private UnifiedMqProducer unifiedMqProducer;

    public void sendDispatchCommandMsg(DispatchCommandMsg dispatchCommandMsg) {

        boolean success;
        String msgStr = JSON.toJSONString(dispatchCommandMsg);
        log.info("发送调度指令消息开始,commandNo:{},msgStr:{}", dispatchCommandMsg.getCommandNo(), msgStr);
        success = unifiedMqProducer.
                sendMq(Constants.DISPATCH_COMMAND_TOPIC, "", dispatchCommandMsg.getCommandNo(), msgStr);
        log.info("发送调度指令消息结束,commandNo:{},结果:{}", dispatchCommandMsg.getCommandNo(), success);

        if (!success) {
            log.error("发送批处理任务异常,commandNo:{}", dispatchCommandMsg.getCommandNo());
            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }
    }
}
