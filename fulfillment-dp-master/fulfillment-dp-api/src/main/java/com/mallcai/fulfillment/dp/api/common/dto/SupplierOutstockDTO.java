package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 取奶DTO
 * @author zhanghao
 * @date 2019-08-15 20:17:41
 */
@Data
@ToString
public class SupplierOutstockDTO implements Serializable {
    private static final long serialVersionUID = 8419122413629722043L;

    private Integer id;
    private Integer cityId;
    private Integer supplierId;
    private Integer storeId;
    private String storeName;
    private String orderId;
    private String pickupDate;
    private Integer cityProductId;
    private String productNo;
    private String showName;
    private Integer cycleDays;
    private Integer productNum;
    private String numUnit;
    private String weightUnit;
    private Integer unitType;
    private Integer packageMaxWeight;
    private String createTime;
    private Integer status;
    private Integer updateUserId;
    private String updateTime;
    private String phone;
    private Integer getStatus;
    private Integer returnNum;
    private Integer getNum;
    private Integer leaveNum;
    private Integer sumNum;
    private Integer vipStatus;
    private String cityName;
}
