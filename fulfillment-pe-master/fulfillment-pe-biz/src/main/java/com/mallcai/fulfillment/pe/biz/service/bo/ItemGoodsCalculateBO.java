package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * 商品货品计算BO
 * @author bh.zhong
 * @date 2019/10/12 4:26 PM
 */
@Data
public class ItemGoodsCalculateBO extends GoodsCalculateBO {

    /** 城市id **/
    private Integer cityId;
    /** 城市商品id **/
    private Integer cityProductId;
}
