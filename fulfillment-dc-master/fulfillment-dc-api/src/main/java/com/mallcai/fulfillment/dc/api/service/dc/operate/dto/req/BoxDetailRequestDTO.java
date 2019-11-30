package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.Data;

/**
 * @author bh.zhong
 * @date 2019/9/1 8:07 PM
 */
@Data
public class BoxDetailRequestDTO {


    /**
     * 对外：生产单号（发货单号）
     */
    private String produceOrderNo;
    /**
     * 总部商品编号
     */
    private String productNo;
    /**
     * 城市商品Id
     */
    private Integer cityId;
    /**
     * 数量(出库商品数量，收货商品数量)
     */
    private Integer productNum;

}
