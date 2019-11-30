package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import lombok.Data;

import java.util.Date;

@Data
public class TimeRuleStoreDO {

    private Long id;

    private Long timeRuleId;

    private Integer storeId;

    private Integer isDeleted;

    /** 创建人用户id*/
    private Integer createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 更新人用户id*/
    private Integer updateUserId;

    /** 更新时间*/
    private Date updateTime;
}
