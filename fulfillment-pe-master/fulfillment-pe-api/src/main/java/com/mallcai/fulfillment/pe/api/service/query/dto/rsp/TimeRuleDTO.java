package com.mallcai.fulfillment.pe.api.service.query.dto.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TimeRuleDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 主键*/
    private Long id;

    /** 仓库id*/
    private Integer warehouseId;

    /** 城市id */
    private Integer cityId;

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
