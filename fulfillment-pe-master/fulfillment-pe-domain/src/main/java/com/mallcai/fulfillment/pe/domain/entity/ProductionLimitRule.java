package com.mallcai.fulfillment.pe.domain.entity;

import lombok.Data;

/**
 * 生产规则-阈值
 * @author zhanghao
 * @date 2019年08月13日23:56:52
 */
@Data
public class ProductionLimitRule {

    /** 天数阈值*/
    private int dayLimit;

    /** 件数阈值*/
    private int amountLimit;
}
