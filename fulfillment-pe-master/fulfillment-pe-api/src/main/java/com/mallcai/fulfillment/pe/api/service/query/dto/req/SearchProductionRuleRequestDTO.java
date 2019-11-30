package com.mallcai.fulfillment.pe.api.service.query.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 生产规则搜索条件
 *
 * TODO 分页统一处理
 * @author
 */
@Data
public class SearchProductionRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 规则名称，支持模糊搜索*/
    private String name;

    /** 城市id */
    private Integer cityId;

    /** 门店分组id*/
    private Integer storeGroupId;

    /** 仓库id*/
    private Integer warehouseId;

    /** 创建时间开始*/
    private Date createTimeBegin;

    /** 创建时间截止点*/
    private Date createTimeEnd;

    /** 查询的页数, 默认第一页*/
    private Integer page;

    /** 每页的数目, 默认20*/
    private Integer pageSize;
}
