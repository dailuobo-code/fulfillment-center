package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 生产规则实体类
 *
 * @author dongda
 */
@Data
public class OperateProductionRuleDTO implements Serializable {

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

    /** 操作人用户id*/
    private Integer userId;

    /** 天数阈值*/
    private Integer dayLimit;

    /** 件数阈值*/
    private Integer amountLimit;
}
