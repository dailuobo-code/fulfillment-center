package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yl
 * @description 批量查询结果
 * @date 2019-07-22
 */
@Data
public class PickUpResultDTO implements Serializable {

    private static final long serialVersionUID = 8853788698803263469L;

    /**
     * 订单数据组装
     */
    List<TradeOrderAssemblyDTO> tradeOrderAssemblyList;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 总记录数量
     */
    private Integer count;

    /**
     * 是否需要下一页面
     */
    private Boolean hasNext;
}
