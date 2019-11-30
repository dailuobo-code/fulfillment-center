package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 支持多包裹发货 请求数据
 * @author gaoguoming
 * @date 2019-10-28 20:06:49
 */
@Data
public class DeliveryOrderMultiPkgDTO implements Serializable {

    private static final long serialVersionUID = -5535391104707869569L;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 门店id（商家自配送传0，表示城市级别）
     */
    private Long storeId;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 配送单列表
     */
    private List<DistOrderInfo> distOrderInfoList;
}
