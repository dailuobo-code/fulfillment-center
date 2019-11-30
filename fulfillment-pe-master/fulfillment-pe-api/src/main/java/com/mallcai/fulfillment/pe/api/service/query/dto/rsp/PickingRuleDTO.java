package com.mallcai.fulfillment.pe.api.service.query.dto.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PickingRuleDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 主键*/
    private Long id;

    /** 仓库id*/
    private Integer warehouseId;

    /** 城市id */
    private Integer cityId;

    /** 商品信息编号 */
    private String productNo;

    /** 创建人用户id*/
    private Integer createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 更新人用户id*/
    private Integer updateUserId;

    /** 更新时间*/
    private Date updateTime;
}
