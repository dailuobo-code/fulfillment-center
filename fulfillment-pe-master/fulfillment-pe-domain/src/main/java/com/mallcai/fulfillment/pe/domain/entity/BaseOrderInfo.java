package com.mallcai.fulfillment.pe.domain.entity;


import lombok.Data;

import java.util.Date;

/***
 * @author wangjingcheng
 * 订单基本信息
 */
@Data
public class BaseOrderInfo {

    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 交易订单ID
     */
    private String transOrderId;


    /**
     * 交易订单item项订单ID
     */
    private Integer itemOrderId;

    /**
     * 产品ID
     */
    private Integer cityProductId;

    /**
     * 门店ID
     */
    private Integer storeId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 规格主键ID
     */
    private String specId;

    /**
     * 产品数量
     */
    private Integer productNum;

    /**
     * 取货日期，格式：yyyy-MM-dd
     */
    private String pickupTime;

    /**
     * 预期推单时间
     */
    private Date expectPushTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
