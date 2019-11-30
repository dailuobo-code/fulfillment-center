package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 取奶查询DTO
 * @author zhanghao
 * @date 2019-08-15 17:54:21
 */
@Data
@ToString
public class MilkQueryParamDTO implements Serializable {
    private static final long serialVersionUID = -6237494693568679635L;

    /** 城市ID **/
    private Integer cityId;
    /** 门店ID **/
    private Integer storeId;
    /** 手机号 **/
    private String phone;
    /** sku **/
    private String productNo;
    /** 开始时间 **/
    private Date beginTime;
    /** 结束时间 **/
    private Date endTime;
    /** 开始位置 **/
    private Integer offset;
    /** 状态  0:未取 1:已取**/
    private Integer getStatus;
    /** 每页条数 **/
    private Integer limit;
    /** 状态：0：正常，1：暂停**/
    private Integer status;
    /** 用户ID **/
    private Integer userId;

    /**
     * 时间类型 {@link}
     */
    private Integer timeType;
}
