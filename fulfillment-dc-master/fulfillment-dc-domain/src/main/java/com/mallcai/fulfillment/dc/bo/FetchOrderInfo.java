package com.mallcai.fulfillment.dc.bo;

import lombok.Data;

/**
 * 记录拉取订单的信息
 * eg: 在第几批次拉取成功多少条，做累计统计
 */

@Data
public class FetchOrderInfo {

    /**
     * 数据库自增ID
     */
    private Long id;
    /**
     * 订单批次号
     */
    private Long batchNo;

    /**
     * 拉取成功条数
     */
    private Long fetchSuccessCount;
}
