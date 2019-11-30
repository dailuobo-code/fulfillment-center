package com.mallcai.fulfillment.dc.api.service.dc.msg;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单条目明细
 * @author bh.zhong
 * @date 2019/11/5 2:12 PM
 */
@Data
public class OrderItemInfoMsg {
    //商品信息
    /**
     * 1.标品 2.冻品 3.生鲜
     */
    private Integer groupValue;
    /**
     * 总部商品编号
     */
    private String productNo;
    /**
     * 商品城市编码
     */
    private Integer cityProductId;
    /**
     * 商品数量
     */
    private BigDecimal productNum;
    //货品信息
    /**
     * 货品id
     */
    private String goodsId;

    /**RealTimeOrderInfoMsgBuild
     * 货品单位
     */
    private String goodsUnit;

    /**
     * 货品数量
     */
    private BigDecimal goodsNum;
    /**
     * 仓库id
     */
    private Integer warehouseId;
}
