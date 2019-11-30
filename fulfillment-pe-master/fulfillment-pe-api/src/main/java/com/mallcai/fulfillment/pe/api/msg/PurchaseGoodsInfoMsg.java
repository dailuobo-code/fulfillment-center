package com.mallcai.fulfillment.pe.api.msg;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bh.zhong
 * @date 2019/10/14 11:13 AM
 */
@Data
public class PurchaseGoodsInfoMsg {
    /**
     * 库存数量
     */
    private BigDecimal goodsTotalAmount;
    /**
     * 库存单位
     */
    private String stockUnit;
    /**
     * 货品ID
     */
    private String goodsId;
}
