package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * 商品信息
 * @author bh.zhong
 * @date 2019/10/12 4:33 PM
 */
@Data
public class ItemInfoBO {

    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品数量
     */
    private Integer itemNum;

}
