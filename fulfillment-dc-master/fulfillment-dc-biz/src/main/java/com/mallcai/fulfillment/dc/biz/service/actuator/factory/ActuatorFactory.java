package com.mallcai.fulfillment.dc.biz.service.actuator.factory;

import com.mallcai.fulfillment.dc.common.utils.SpringContextUtil;
import com.mallcai.fulfillment.dc.biz.service.actuator.strategy.BaseActuator;
import com.mallcai.fulfillment.dc.biz.service.actuator.strategy.SendWmsActuator;
import com.mallcai.fulfillment.dc.biz.service.actuator.strategy.ShopReceiveActuator;
import com.mallcai.fulfillment.dc.biz.service.enums.EventAssembleEnum;

/**
 * 执行器工厂（获取执行器）
 *
 * @author bh.zhong
 * @date 2019/8/15 2:12 PM
 */
public class ActuatorFactory {

    /**
     * 获取执行器
     * @return 具体执行器
     */
    public static BaseActuator getActuator() {
        return getActuator(null, null);
    }

    /**
     * 获取执行器
     * @return 具体执行器
     */
    public static BaseActuator getActuator(String eventName,String fromApp) {
        EventAssembleEnum eventEnum = EventAssembleEnum.getEvent(eventName, fromApp);
        if (eventEnum == null) {
            return null;
        }
        switch (eventEnum) {
            case SEND_WMS:
                return (SendWmsActuator) SpringContextUtil.getBean(eventEnum.getActuatorName());
            case SHOP_ALREADY_RECEIVE:
                return (ShopReceiveActuator) SpringContextUtil.getBean(eventEnum.getActuatorName());
            default:
                return null;
        }
    }


}
