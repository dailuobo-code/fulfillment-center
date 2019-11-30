package com.mallcai.fulfillment.pe.domain.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeRule {

    /** 主键*/
    private Long id;

    /** 城市id*/
    private Integer cityId;

    /** 仓库id*/
    private Integer warehouseId;

    /** 门店列表 */
    private List<Integer> storeIds;

    /** 取货时间 T+N 机制, N就是这里的deliveryDays*/
    private Integer deliveryDays;

    /** 创建人用户id*/
    private Integer createUserId;

    /** 创建时间*/
    private Date createTime;

    /** 更新人用户id*/
    private Integer updateUserId;

    /** 更新时间*/
    private Date updateTime;
}
