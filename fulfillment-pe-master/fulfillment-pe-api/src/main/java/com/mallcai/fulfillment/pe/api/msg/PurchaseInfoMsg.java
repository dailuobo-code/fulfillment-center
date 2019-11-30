package com.mallcai.fulfillment.pe.api.msg;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/10/14 11:13 AM
 */
@Data
public class PurchaseInfoMsg {
    /**
     * 仓库id
     */
    private Integer warehouseId;
    /**
     * 一级分类 1：水果 2：蔬菜
     */
    private Byte categoryType;
    /**
     * 预测类型 1-当日预测(补采) 2-提前预测
     */
    private Byte forecastType;
    /**
     * 预测时间
     */
    private Date forecastDate;
    /**
     * 总数据量
     */
    private Integer categoryGoodsTotalAmount;
    /**
     * 当前版本号
     */
    private Integer version;
    /**
     * 货品明细
     */
    private List<PurchaseGoodsInfoMsg> purchaseGoodsInfoMsgs;
}
