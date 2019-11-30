package com.mallcai.fulfillment.dp.api.request;

import com.mallcai.fulfillment.dp.api.common.dto.TimeRangeDTO;
import com.mallcai.fulfillment.dp.api.enums.NumberTypeEnum;
import com.mallcai.fulfillment.dp.api.enums.PayTypeEnum;
import com.mallcai.fulfillment.dp.api.enums.PickUpSourceEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 订单关联查询dto
 * @date 2019-07-15
 */
@Data
public class OrderRelationQueryDTO implements Serializable {

    private static final long serialVersionUID = 1969055844828648070L;
    /**
     * 编号类型 {@link NumberTypeEnum}
     */
    private Integer numberType;

    /**
     * 编号
     */
    private String number;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 门店数据
     */
    private Long storeId;

    /**
     * 时间控件
     */
    private TimeRangeDTO timeRange;

    /**
     * 支付类型{@link PayTypeEnum}
     */
    private Integer payType;

    /**
     * 来源 {@link PickUpSourceEnum}
     */
    private Integer operateSource;

    /**
     * 订单状态
     */
    private List<Integer> orderStatusList;

}
