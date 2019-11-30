package com.mallcai.fulfillment.pe.api.service.query.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 时间规则搜索条件
 *
 * @author
 */
@Data
public class SearchPickingRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 查询的页数, 默认第一页*/
    private Integer page;

    /** 每页的数目, 默认20*/
    private Integer pageSize;
}
