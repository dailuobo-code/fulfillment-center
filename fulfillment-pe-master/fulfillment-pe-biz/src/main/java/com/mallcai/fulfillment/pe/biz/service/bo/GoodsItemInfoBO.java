package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 货品商品信息
 * @author bh.zhong
 * @date 2019/10/13 10:25 PM
 */
@Data
public class GoodsItemInfoBO {
    /**
     * 采购单id
     */
    private Long id;
    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品数量
     */
    private Integer itemNum;
    /** 货品编号 **/
    private String goodsId;
    /** 货品计量单位 **/
    private String goodsUnit;
    /** 货品关联数量 **/
    private BigDecimal relNum;
}
