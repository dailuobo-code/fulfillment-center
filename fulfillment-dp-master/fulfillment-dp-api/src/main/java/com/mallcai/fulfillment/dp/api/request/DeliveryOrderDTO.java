package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 基于订单进行发货
 * @author bh.zhong
 * @date 2019/9/6 10:27 AM
 */
@Data
public class DeliveryOrderDTO implements Serializable {
    private static final long serialVersionUID = -5720212905829238685L;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 门店id（商家自配送传0，表示城市级别）
     */
    private Long storeId;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 发货商品明细
     */
    private List<DeliveryItemDTO> deliveryItemDTOList;
    /**
     * 配送信息
     */
    private DeliveryInfoDTO deliveryInfoDTO;


}
