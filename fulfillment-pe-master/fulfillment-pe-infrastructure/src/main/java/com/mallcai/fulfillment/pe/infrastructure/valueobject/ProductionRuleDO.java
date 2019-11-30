package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import lombok.Data;

import java.util.Date;

/**
 * ProductionRuleDO
 * @author zhanghao
 * @date 2019-08-13 23:58:03
 */
@Data
public class ProductionRuleDO {

    /** 主键*/
    private Long id;

    /** 城市id */
    private Integer cityId;

    /** 规则名称*/
    private String name;

    /** 门店分组id*/
    private Integer storeGroupId;

    /** 仓库id*/
    private Integer warehouseId;

    /** 创建人用户id*/
    private Integer createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 更新人用户id*/
    private Integer updateUserId;

    /** 更新时间*/
    private Date updateTime;

    /** 状态 0-失效 1-有效*/
    private Integer status;

    /** 规则-阈值*/
    private String productionLimitRule;
}
