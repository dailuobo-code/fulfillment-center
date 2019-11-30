package com.mallcai.fulfillment.dp.api.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangtao
 * @description: 订单状态
 * @title: OrderStatusEnum
 * @date 2019-05-09 15:17
 */
public enum OrderStatusEnum {

    /**
     * 1:待付款
     */
    WAIT_PAY(1),

    /**
     * 2:已付款待取货
     */
    PAID(2),

    /**
     * 3:已取消
     */
    CANCEL(3),

    /**
     * 4:待评价
     */
    WAIT_EVALUATE(4),

    /**
     * 5:退款/售后
     */
    REFUND(5),

    /**
     * 6:已完成
     */
    FINISH(6),

    /**
     * 7:在线退款处理中
     */
    ONLINE_REFUNDING(7),

    /**
     * 8:在线退款处理完成
     */
    ONLINE_REFUNDED(8),

    /**
     * 9:售后处理中
     */
    AFTER_SALE_PROCESSING(9),

    /**
     * 10:售后处理完成
     */
    AFTER_SALE_DONE(10),

    /**
     * 11:已放弃
     */
    ABANDONED(11),

    /**
     * 99:门店提货，锁订单
     */
    LOCK_ORDER(99),

    /**
     * -2 已取消，支付出现异常
     */
    CANCEL_EXCEPTION(-2);

    private Integer type;

    OrderStatusEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
    }

    /**
     * 售后申请相关状态
     */
    public static List<Integer> afterSaleStatuses() {
        List<Integer> list = new ArrayList<>();
        list.add(AFTER_SALE_PROCESSING.getType());
        list.add(AFTER_SALE_DONE.getType());
        return list;
    }

    /**
     * 订单完成相关状态
     */
    public static List<Integer> listActiveOrderStatus() {
        return Arrays
                .asList(OrderStatusEnum.FINISH.getType(), OrderStatusEnum.CANCEL.getType(),
                        OrderStatusEnum.CANCEL_EXCEPTION.getType(),
                        OrderStatusEnum.REFUND.getType(), OrderStatusEnum.ONLINE_REFUNDED.getType(),
                        OrderStatusEnum.ABANDONED.getType(),
                        OrderStatusEnum.AFTER_SALE_DONE.getType(), OrderStatusEnum.WAIT_EVALUATE.getType(),
                        OrderStatusEnum.LOCK_ORDER.getType()
                );
    }

    /**
     * 退款相关状态
     */
    public static List<Integer> refundStatuses() {
        List<Integer> list = new ArrayList<>();
        list.add(REFUND.getType());
        list.add(ONLINE_REFUNDING.getType());
        list.add(ONLINE_REFUNDED.getType());
        return list;
    }
}
