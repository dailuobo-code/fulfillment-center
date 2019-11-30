package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gaoguoming
 * @date 2019-11-16 14:23:07
 */
@Data
public class ProduceOrderItemDO {
    /**
     * id
     */
    private Long id;

    /**
     * 关联的生产单号
     */
    private String produceOrderNo;

    /**
     * 门店id
     */
    private Integer storeId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 仓id
     */
    private Integer warehouseId;

    /**
     * 货品id，未转货品的城市为空''
     */
    private String goodId;

    /**
     * 城市商品id
     */
    private Integer cityProductId;

    /**
     * 总的数量，已经是转成货品的城市，货品的总量，未转货品的城市，为商品的总量
     */
    private BigDecimal totalNum;

    /**
     * 货品的计量单位，未转货品的城市为''
     */
    private String goodUnit;

    private Date createTime;

    private Date updateTime;
}
