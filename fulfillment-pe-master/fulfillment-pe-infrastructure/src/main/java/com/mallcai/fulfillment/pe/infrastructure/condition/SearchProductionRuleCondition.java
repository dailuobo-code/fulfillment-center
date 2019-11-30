package com.mallcai.fulfillment.pe.infrastructure.condition;

import com.mallcai.fulfillment.pe.common.page.PageQuery;
import lombok.Data;

import java.util.Date;

/**
 * 搜索生产规则条件
 * @author dongda
 * @date 2019-08-14 00:03:14
 */
@Data
public class SearchProductionRuleCondition extends PageQuery {

    /** 规则名称，支持模糊搜索*/
    private String name;

    /** 门店分组id*/
    private Integer storeGroupId;

    /** 仓库id*/
    private Integer warehouseId;

    /** 创建时间开始*/
    private Date createTimeBegin;

    /** 创建时间截止点*/
    private Date createTimeEnd;

    /** 城市id */
    private Integer cityId;
}
