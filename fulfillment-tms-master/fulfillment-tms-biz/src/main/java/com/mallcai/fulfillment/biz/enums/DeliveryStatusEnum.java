package com.mallcai.fulfillment.biz.enums;

import com.mallcai.wms.oub.client.enums.OutboundBillTypeEnum;
import com.mallcai.wms.oub.client.enums.PerformDeliveryOrderDetailTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 分组值枚举，所有值不重复(方便后期扩展，只使用一个字段也可以
 *  定位属于哪个分组)
 * @author: chentao
 * @create: 2019-09-16 17:00:44
 */
@Getter
@AllArgsConstructor
public enum DeliveryStatusEnum {
    INIT("INT","初始化订单"),
    SEND_WMS("SEND_WMS","已发送WMS"),
    SEND_FAIL("SEND_FAIL","发送WMS失败"),
    TRANS_PLAN("TRANS_PLAN","通知排线"),
    TRANS_CAR("TRANS_CAR","通知排车"),
    TRANS_RES("TRANS_RES","送达通知");


    private String key;

    private String desc;

}
