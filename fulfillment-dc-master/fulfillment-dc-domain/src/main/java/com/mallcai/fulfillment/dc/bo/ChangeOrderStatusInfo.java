package com.mallcai.fulfillment.dc.bo;

import lombok.Data;

/**
 * @author wangjingcheng
 *
 * 调用交易修改订单状态时的 记录
 *
 * eg:第几批次的订单，哪些订单ID修改状态失败
 */

@Data
public class ChangeOrderStatusInfo {

    /**
     * 订单Id
     */
    private Long id ;

    /**
     * 订单批次号
     */
    private Long batchNo;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 执行阶段
     */
    private int stage;
}
