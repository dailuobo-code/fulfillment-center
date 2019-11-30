package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单商品信息
 * @author bh.zhong
 * @date 2019/8/27 7:00 PM
 */
@Data
public class OrderItemRequestDTO implements Serializable {

    private String orderNo;

    private Integer itemOrderId;

    private Integer orderType;

    private Integer cityProductId;

    private Integer storeId;

    private Integer userId;

    private Integer cityId;

    private Integer specId;

    private Integer productNum;

    private String pickupTime;

    private String expectPushTime;


}
