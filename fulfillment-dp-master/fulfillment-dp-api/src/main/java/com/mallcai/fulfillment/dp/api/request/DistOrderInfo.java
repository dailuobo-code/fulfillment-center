package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaoguoming
 * @date 2019-10-29 17:47:24
 */
@Data
public class DistOrderInfo implements Serializable {

    private static final long serialVersionUID = 3561698950298514369L;
    /**
     * 商品明细，包裹中的商品信息。若无法区分暂时按照整个单品订单的商品信息传入。
     */
    private DeliveryItemDTO deliveryItemDTO;
    /**
     * 配送信息
     */
    private DeliveryInfoDTO deliveryInfoDTO;
}
