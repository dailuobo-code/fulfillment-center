package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-10-30 13:53:46
 */
@Data
public class BatchDeliveryOrderQueryDTO implements Serializable {

    private static final long serialVersionUID = 4104636594796126070L;

    /**
     * 查询的订单号列表
     */
    private List<String> orderNos;
}
