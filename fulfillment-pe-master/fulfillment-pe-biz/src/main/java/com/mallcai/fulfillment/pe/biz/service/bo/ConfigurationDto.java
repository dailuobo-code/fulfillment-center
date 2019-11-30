package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

/**
 * @description:
 * @author: chentao
 * @create: 2019-09-17 14:36:22
 */
@Data
public class ConfigurationDto{

    /**
     * 配置项
     */

    /**
     * hold单时间
     */
    private Integer orderHoldMins;

    /**
     * 订单取消截止时间
     */
    private String orderCancelExpireTime;


    /**
     * 条件项
     */

    /**
     * 门店ID
     */
    private Integer storeId;

    /**
     * 生产仓库ID
     */
    private Integer produceWarehouseId;

    /**
     * 订单分组类型，暂时没用
     */
    private Byte groupType;

    /**
     * 订单分组值
     */
    private String groupValue;

    /**
     * 产品编码
     */
    private String productNo;

    /**
     * 城市ID
     */
    private Integer cityId;
}
