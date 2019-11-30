package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * 门店白名单
 * @author bh.zhong
 * @date 2019/8/23 9:33 PM
 */
@Data
public class StoreWhiteBO {
    /**
     * 店铺ID
     */
    private Integer storeId;
    /**
     * 生效时间 "2019-08-01"  如果没有生效时间，默认不生效
     */
    private String effectTime;
    /**
     * 取货时间
     */
    private Integer deliveryDays;
}
