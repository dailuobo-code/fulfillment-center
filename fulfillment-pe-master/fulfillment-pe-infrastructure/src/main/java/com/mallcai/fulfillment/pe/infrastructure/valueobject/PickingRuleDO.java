package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import lombok.Data;

import java.util.Date;

@Data
public class PickingRuleDO {

    /** 主键 */
    private Long id;

    /** 仓库id*/
    private Integer warehouseId;

    /** 商品编码 */
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
