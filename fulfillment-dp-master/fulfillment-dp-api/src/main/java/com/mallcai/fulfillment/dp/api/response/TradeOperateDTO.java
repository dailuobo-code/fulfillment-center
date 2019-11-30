package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description TradeOperateDTO
 * @date 2019-07-16
 */
@Data
public class TradeOperateDTO implements Serializable {

    private static final long serialVersionUID = 2342592327098886515L;

    /**
     * 失败订单
     */
    private List<String> errorOrderIdList;

    /**
     * 成功订单
     */
    private List<String> successOrderIdList;


}
