package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/9/16 7:25 PM
 */
@Data
public class BatchDeliveryOrderDTO implements Serializable {

    List<DeliveryOrderDTO> deliveryOrderDTOList;

}
