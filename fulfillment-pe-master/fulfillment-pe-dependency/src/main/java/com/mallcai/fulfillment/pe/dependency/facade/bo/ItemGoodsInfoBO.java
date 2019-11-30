package com.mallcai.fulfillment.pe.dependency.facade.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品&货品信息
 * @author bh.zhong
 * @date 2019/10/10 3:59 PM
 */
@Data
public class ItemGoodsInfoBO {
    /** 城市id **/
    private Integer cityId;

    /** 城市商品id **/
    private Integer cityProductId;

    /** 货品编号 **/
    private String goodsId;

    /** 货品名称 **/
    private String goodsName;

    /** 货品计量单位 **/
    private String goodsUnit;

    /** 货品关联数量 **/
    private BigDecimal relNum;

    /**
     * 品类 1:标品，其它:非标品
     */
    private Integer categoryType;
    /**
     * 属性 1:冷藏
     */
    private Integer attributeType;

}
