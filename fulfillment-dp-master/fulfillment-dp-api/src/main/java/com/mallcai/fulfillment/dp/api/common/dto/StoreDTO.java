package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description 门店dto
 * @date 2019-07-15
 */
@Data
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 6166391977952426118L;
    /**
     * 门店id
     */
    private Long storeId;

    /**
     * 门店名称
     */
    private String storeName;
}
