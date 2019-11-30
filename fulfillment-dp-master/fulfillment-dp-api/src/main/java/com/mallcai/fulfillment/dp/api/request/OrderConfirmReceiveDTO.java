package com.mallcai.fulfillment.dp.api.request;

import java.io.Serializable;
import lombok.Data;

/**
 * 确认收货DTO
 * @author bh.zhong
 * @date 2019/9/6 3:16 PM
 */
@Data
public class OrderConfirmReceiveDTO implements Serializable {

    private static final long serialVersionUID = -821870426180761471L;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 城市id
     */
    private Integer cityId;

}
