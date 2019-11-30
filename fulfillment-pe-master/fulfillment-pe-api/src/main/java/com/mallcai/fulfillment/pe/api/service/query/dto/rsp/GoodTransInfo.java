package com.mallcai.fulfillment.pe.api.service.query.dto.rsp;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodTransInfo implements Serializable {
    private String skuId;
    private String skuName;

    /**
     * 商品数量
     */
    private BigDecimal skuCount;

    /**
     * 商品重量
     */
    private BigDecimal skuWeight;

    /**
     * 商品单位
     */
    private String skuUnit;

    /**
     * 体积（立方米）
     */
    private BigDecimal skuVolume;

}
