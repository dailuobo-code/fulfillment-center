package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @description: 数据过滤条件
 * @author: chentao
 * @create: 2019-09-18 01:21:36
 */
@Data
public class OrderFilterDto {

    /**
     * 所有门店集合
     */
    private List<Integer> storeId;

    /**
     * 所有仓库集合
     */
    private List<Integer> produceWarehouseId;

    /**
     * 所有分组值集合
     */
    private List<String> groupValue;

    /**
     * 所有产品编码集合
     */
    private List<String> productNo;

    /**
     * 所有城市ID集合
     */
    private List<Integer> cityId;
}
