package com.mallcai.fulfillment.biz.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TmsSkuVO implements Serializable {
    /**
     * 货物编号
     */
    private String GoodsNo;

    /**
     * 货物名称
     */
    private String GoodsName;

    /**
     * 货物单位
     */
    private String GoodsUnit;

    /**
     * 货物类型
     */
    private String GoodsType;

    /**
     * 包装类型
     */
    private String PackingType;

    /**
     * 包装容器编码
     */
    private String PackagingContainerCoding;

    /**
     * 单位重量kg
     */
    private BigDecimal UnitTotalWeight;

    /**
     * 单位体积m³
     */
    private BigDecimal UnitTotalVolume;

    /**
     * 数量
     */
    private int Qty;

    /**
     * 总体积
     */
    private BigDecimal TotalVolume;

    /**
     * 总重量
     */
    private BigDecimal TotalWeight;

    /**
     * 货物规格
     */
    private String CargoSpecification;

    /**
     * 销售金额
     */
    private BigDecimal SalesAmount;
}
