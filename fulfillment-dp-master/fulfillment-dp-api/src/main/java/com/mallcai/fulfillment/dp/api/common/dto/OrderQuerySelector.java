package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 订单查询选择器
 * @date 2019-07-15
 */
@Data
public class OrderQuerySelector implements Serializable {

    private static final long serialVersionUID = 1837963228017178263L;
    /**
     * 城市信息
     */
    private CityDTO city;

    /**
     * 门店信息
     */
    private StoreDTO store;

    /**
     * 时间区间
     */
    private TimeRangeDTO timeRange;

    /**
     * 订单状态
     */
    private List<Integer> orderStatusList;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 分页查询
     */
    private PagingDTO paging;


}
