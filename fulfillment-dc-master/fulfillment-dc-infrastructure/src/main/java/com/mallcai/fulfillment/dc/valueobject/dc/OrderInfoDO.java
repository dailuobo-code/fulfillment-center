package com.mallcai.fulfillment.dc.valueobject.dc;

import lombok.Data;

import java.util.Date;

/**
 * 调度记录订单的一笔信息
 * @author bh.zhong
 * @date 2019/8/19 3:12 PM
 */
@Data
public class OrderInfoDO {

    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 取货时间
     */
    private String pickupTime;
    /**
     * 扩展字段
     */
    private String orderInfoExt;
    /**
     * 交易订单状态
     */
    private Integer status;
    /**
     * 订单品类
     */
    private Integer orderType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
