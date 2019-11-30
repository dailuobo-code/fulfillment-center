package com.mallcai.fulfillment.pe.api.service.operate.dto.req;


import lombok.Data;

import java.util.Date;

/**
 * @author wangjingcheng
 */

@Data
public class TransOrderDTO implements java.io.Serializable {

    /**
     * 数据库自增id
     */
    private Long id;

    /**
     * 交易订单ID
     */
    private Integer transOrderId;

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
     * 取货日期，格式：yyyy-MM-dd
     */
    private String pickupTime;

    /**
     * 订单额外扩展信息，如姓名、手机号等。json格式
     */
    private String orderInfoExt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
