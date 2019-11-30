package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bh.zhong
 * @date 2019/9/18 3:16 PM
 */
@Data
public class DeliveryOrderQueryDTO implements Serializable {
    private static final long serialVersionUID = -8171066623837978520L;
    /**
     * 订单号
     */
    private String orderNo;

}
