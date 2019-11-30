package com.mallcai.fulfillment.dp.api.response;

import java.util.Date;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bh.zhong
 * @date 2019/9/18 3:18 PM
 */
@Data
public class DistOrderDTO implements Serializable {

    private static final long serialVersionUID = 3693168593173825145L;
    /**
     * 配送单号
     */
    private String distNo;

    /**
     * 交易订单号
     */
    private String orderNo;
    /**
     * 快递信息
     */
    private ExpressInfo expressInfo;
    /**
     * 发货时间
     */
    private Date deliveryTime;

}
