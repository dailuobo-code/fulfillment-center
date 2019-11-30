package com.mallcai.fulfillment.pe.api.service.operate.dto.req;


import lombok.Data;

import java.util.Date;

/**
 * @author wangjingcheng
 */
@Data
public class DeliverOrderDTO implements java.io.Serializable {

    private Integer id;
    /**
     * 批次订单号
     */
    private String batchOrderNo;
    /**
     * 门店ID
     */
    private Integer storeId;
    /**
     * 订单id
     */
    private String orderNo;
    /**
     * 城市id
     */
    private Integer cityId;

    private Date createTime;

    private Date updateTime;
}
