package com.mallcai.fulfillment.pe.api.service.query.dto.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 生产规则实体类
 *
 * @author dongda
 */
@Data
public class ProductionRuleDTO implements Serializable {

    private static final long serialVersionUID = -1;

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

    /** 天数阈值*/
    private Integer dayLimit;

    /** 件数阈值*/
    private Integer amountLimit;

    /** 状态 0-失效 1-有效*/
    private Integer status;
}
