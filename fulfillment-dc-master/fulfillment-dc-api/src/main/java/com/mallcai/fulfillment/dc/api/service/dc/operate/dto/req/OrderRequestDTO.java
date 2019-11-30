package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/8/17 4:27 PM
 */
@Data
public class OrderRequestDTO implements Serializable {

    private static final long serialVersionUID = 3038037836111550274L;
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

    private List<OrderItemRequestDTO> orderItemRequestDTOList;

}
