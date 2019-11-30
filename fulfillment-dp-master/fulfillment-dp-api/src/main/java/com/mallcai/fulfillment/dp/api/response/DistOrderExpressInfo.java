package com.mallcai.fulfillment.dp.api.response;

import com.mallcai.fulfillment.dp.api.request.DeliveryAddressDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryUserDTO;
import com.mallcai.fulfillment.dp.api.response.ExpressInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 单个配送单 快递信息
 * @author gaoguoming
 * @date 2019-10-28 20:33:31
 */
@Data
public class DistOrderExpressInfo implements Serializable {

    private static final long serialVersionUID = -986332418303745350L;

    /**
     * 配送单号
     */
    private String distNo;
    /**
     * 快递信息
     */
    private ExpressInfo expressInfo;

    /**
     * 发货时间
     */
    private Date deliveryTime;
}
