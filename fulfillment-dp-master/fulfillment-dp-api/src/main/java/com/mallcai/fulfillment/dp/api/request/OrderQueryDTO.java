package com.mallcai.fulfillment.dp.api.request;

import com.mallcai.fulfillment.dp.api.common.dto.OrderAggregationOption;
import com.mallcai.fulfillment.dp.api.common.dto.OrderQuerySelector;
import com.mallcai.fulfillment.dp.api.enums.PickUpSourceEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description 订单查询dto
 * @date 2019-07-15
 */
@Data
public class OrderQueryDTO implements Serializable {

    private static final long serialVersionUID = -1814135757416009037L;
    /**
     * 订单查询选择器
     */
    private OrderQuerySelector orderQuerySelector;

    /**
     * 订单聚合
     */
    private OrderAggregationOption orderAggregationOption;

    /**
     * 来源 {@link PickUpSourceEnum}
     */
    private Integer operateSource;

}
