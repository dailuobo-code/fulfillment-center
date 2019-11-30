package com.mallcai.fulfillment.dc.api.service.dc.msg;

import lombok.Data;

import java.util.List;

/**
 * 实时订单数据
 * @author bh.zhong
 * @date 2019/11/5 2:09 PM
 */
@Data
public class RealTimeOrderInfoMsg {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 门店Id
     */
    private Integer storeId;
    /**
     * 城市Id
     */
    private Integer cityId;
    /**
     * 取货日期
     */
    private String pickupTime;
    /**
     * 商品条目
     */
    private List<OrderItemInfoMsg>  orderItemInfoList;
    /**
     * 支付状态
     * 提供枚举类 {@link com.mallcai.fulfillment.dc.api.service.dc.enums.OrderStatus}
     */
    private Integer payStatus;
}
