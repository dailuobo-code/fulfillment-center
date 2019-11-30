package com.mallcai.fulfillment.dc.biz.service.actuator.mq.bo;

import lombok.Data;

/**
 * @author bh.zhong
 * @date 2019/8/17 11:26 AM
 */
@Data
public class OrderMsqBO {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 需要变更状态
     */
    private Integer status;
}
