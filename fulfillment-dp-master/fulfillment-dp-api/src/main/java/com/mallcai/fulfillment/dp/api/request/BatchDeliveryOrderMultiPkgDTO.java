package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-11-05 10:45:14
 */
@Data
public class BatchDeliveryOrderMultiPkgDTO implements Serializable {
    private static final long serialVersionUID = -8368723967069857236L;
    /**
     * 批量信息
     */
    private List<DeliveryOrderMultiPkgDTO> deliveryOrderMultiPkgDTOList;
}