package com.mallcai.fulfillment.dc.dependency.facade.bo;

import lombok.Data;

/**
 * 查询商品货品信息入参
 * @author bh.zhong
 * @date 2019/10/10 11:13 PM
 */
@Data
public class QueryGoodsRelBO {
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市商品id
     */
    private Integer cityProductId;

}
