package com.mallcai.fulfillment.dc.api.service.dc.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/11/5 2:15 PM
 */
public enum OrderStatus {

    /**
     * 已推送给WMS
     */
    PAID(2, "支付成功"),
    /**
     * 门店已经接受
     */
    CANCEL(8, "已取消/退款");

    private static List<Integer> cancelStatus = Arrays.asList(3,8);

    /**
     * 事件名称
     */
    @Getter
    private String desc;

    @Getter
    private Integer status;


    OrderStatus(Integer status, String desc) {
        this.desc = desc;
        this.status = status;
    }

    public static Integer getStatus(Integer status) {
        if (cancelStatus.contains(status)) {
            return OrderStatus.CANCEL.getStatus();
        }
        return OrderStatus.PAID.getStatus();
    }

}
