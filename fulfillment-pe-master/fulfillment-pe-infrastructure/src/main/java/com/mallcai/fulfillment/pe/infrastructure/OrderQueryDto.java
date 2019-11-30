package com.mallcai.fulfillment.pe.infrastructure;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 订单查询参数
 * @author: chentao
 * @create: 2019-09-17 00:28:58
 */
@ToString
@Data
public class OrderQueryDto {

    /**
     *
     */
    private Date startTime;

    private Date endTime;

    private Integer orderHoldMins;

    private Integer storeId;

    private Integer produceWarehouseId;

    private Byte groupType;

    private String groupValue;

    private String productNo;

    private Integer cityId;

    public OrderQueryDto() {
    }

    /**
     * 字段比较多，最好还是用set，防止增减参数容易出错
     * @param startTime
     * @param endTime
     * @param orderHoldMins
     * @param storeId
     * @param produceWarehouseId
     * @param groupType
     * @param groupValue
     * @param productNo
     * @param cityId
     */
    public OrderQueryDto(Date startTime, Date endTime, Integer orderHoldMins, Integer storeId,
                         Integer produceWarehouseId, Byte groupType, String groupValue,
                         String productNo, Integer cityId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderHoldMins = orderHoldMins;
        this.storeId = storeId;
        this.produceWarehouseId = produceWarehouseId;
        this.groupType = groupType;
        this.groupValue = groupValue;
        this.productNo = productNo;
        this.cityId = cityId;
    }
}

