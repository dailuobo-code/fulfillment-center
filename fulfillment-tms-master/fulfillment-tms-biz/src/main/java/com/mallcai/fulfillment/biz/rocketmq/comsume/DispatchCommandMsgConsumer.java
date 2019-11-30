package com.mallcai.fulfillment.biz.rocketmq.comsume;

import com.alibaba.fastjson.JSON;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-18 11:42:35
 */
public class DispatchCommandMsgConsumer {

    void consume(){

//        DispatchCommandMsg

//        String msgStr = JSON.toJSONString(dispatchCommandMsg);
//        log.info("发送调度指令消息开始,commandNo:{},msgStr:{}", dispatchCommandMsg.getCommandNo(), msgStr);
//        success = unifiedMqProducer.
//                sendMq(Constants.DISPATCH_COMMAND_TOPIC, dispatchCommandMsg.getCommandNo(), "", msgStr);
//        log.info("发送调度指令消息结束,commandNo:{},结果:{}", dispatchCommandMsg.getCommandNo(), success);
//
//        if (!success) {
//            log.error("发送批处理任务异常,commandNo:{}", dispatchCommandMsg.getCommandNo());
//            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
//        }
    }

}
