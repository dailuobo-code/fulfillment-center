package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 多包裹发货--修改快递信息
 * @author gaoguoming
 * @date 2019-10-28 20:45:47
 */
@Data
public class MultiPkgModifyExpressDTO implements Serializable {
    private static final long serialVersionUID = 3910908544423669798L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 配送单列表
     */
    private List<DistOrderInfo> distOrderInfoList;
}
