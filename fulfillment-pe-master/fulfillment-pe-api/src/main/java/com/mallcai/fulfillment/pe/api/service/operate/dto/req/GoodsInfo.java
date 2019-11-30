package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 货品信息
 * @author: chentao
 * @create: 2019-10-13 21:50:17
 */
@Data
public class GoodsInfo {

    /**
     * 货品id
     */
    private String goodsId;

    /**
     * 货品单位
     */
    private String goodsUnit;

    /**
     * 货品数量
     */
    private BigDecimal goodsNum;
}
