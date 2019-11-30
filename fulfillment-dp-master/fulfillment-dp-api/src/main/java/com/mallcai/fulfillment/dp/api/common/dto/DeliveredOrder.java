package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 交付订单
 * @date 2019-07-15
 */
@Data
public class DeliveredOrder implements Serializable {

    private static final long serialVersionUID = -5038569742346216597L;
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品列表
     */
    private List<ProductDTO> productDtoList;
}
