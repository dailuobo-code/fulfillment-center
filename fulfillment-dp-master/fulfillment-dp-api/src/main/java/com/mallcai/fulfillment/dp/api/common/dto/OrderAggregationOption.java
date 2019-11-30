package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description 订单聚合选项
 * @date 2019-07-15
 */
@Data
public class OrderAggregationOption implements Serializable {

    private static final long serialVersionUID = -7127726549903969239L;
    /**
     * 是否包含订单基础信息
     */
    private boolean includeOrderBase;

    /**
     * 是否包含订单条目
     */
    private boolean includeOrderItem;

}
