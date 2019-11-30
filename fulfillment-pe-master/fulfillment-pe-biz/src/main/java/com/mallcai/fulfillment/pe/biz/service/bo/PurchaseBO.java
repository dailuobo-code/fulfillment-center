package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.Date;

/**
 * 采购
 * @author bh.zhong
 * @date 2019/10/11 4:24 PM
 */
@Data
public class PurchaseBO {
    /**
     * 城市id
     */
    private Integer cityId;
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
     * 当前需要处理数据量
     */
    private Integer count;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 重试次数
     */
    private Integer retryTimes;
}
