package com.mallcai.fulfillment.dc.biz.service.impl.operate;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.ReceiveCallbackService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.FpeCallbackRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.ReceiveOrderRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.UnityReceiveResponseDTO;
import com.mallcai.fulfillment.dc.biz.service.actuator.factory.ActuatorFactory;
import com.mallcai.fulfillment.dc.biz.service.actuator.strategy.BaseActuator;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveCallbackInnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类 {@code ReceiveCallbackServiceImpl} 调度接收，门面层.
 *
 * <p> 主要包括 WMS,TMS,门店等系统回调
 * @author bh.zhong
 * @date 2019/8/16 11:22 AM
 */
@Service("dispatchReceiveService")
public class ReceiveCallbackServiceImpl implements ReceiveCallbackService {

    @Resource
    private ReceiveCallbackInnerService receiveCallbackInnerService;
    @Override
    public PlainResult<UnityReceiveResponseDTO> fpeCallbackTest(ReceiveOrderRequestDTO req) {
        //step1:参数前置校验

        //step2:业务校验

        //step3:数据存储

        //step4：业务执行
        BaseActuator actuator = ActuatorFactory.getActuator(req.getStatusMsg(),req.getAppSource());
        if (actuator == null) {
            return null;
        }
        actuator.invoke();

        return null;
    }

    @Override
    public PlainResult<?> fpeCallback(FpeCallbackRequestDTO req) {
        //step1:参数前置校验

        //step2:业务校验

        //step3:回传中心数据存储
        //receiveCallbackInnerService.saveCallbackMsg(req);

        //step4：业务执行
        BaseActuator actuator = ActuatorFactory.getActuator(req.getStatusMsg(),req.getAppSource());
        if (actuator == null) {
            return null;
        }
        actuator.invoke();

        return null;
    }
}
