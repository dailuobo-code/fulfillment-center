package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

/**
 * @description: 商品详情
 * @author: bh.zhong
 * @create: 2019-08-22 23:03:57
 */
@Data
public class CommodityInfo {

    /**
     * 商品城市编码
     */
    private Integer cityProductId;

    /**
     * 商品数量
     */
    private Integer productNum;
}
