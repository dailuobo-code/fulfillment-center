package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yl
 * @description OrderDO
 * @date 2019-06-19
 */
@Data
@ToString
public class TradeOrderDTO implements Serializable {

    private static final long serialVersionUID = 8845924324772603915L;

    private Integer id;

    private String orderId;

    private Integer storeId;

    private Integer cityId;

    private Integer userId;

    private String orderName;

    private Integer status;

    private String asynPayStatus;

    private String orderPic;

    private Date payTime;

    private Date payCompleteTime;

    private Date generateTime;

    private Date closeTime;

    private Date pickupTime;

    private BigDecimal orderPrice;

    private BigDecimal totalPrice;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

    private String pickupStartTime;

    private String pickupEndTime;

    private String memo;

    private String pickupCode;

    private BigDecimal weight;

    private Integer pickupTypeId;

    private Integer isWilling;

    private Integer balance;

    private String isDel;

    private String extras;

    private String evaluateContent;

    private Integer couponId;

    private String couponDesc;

    private Integer pickupTimeId;

    private Integer completeUserId;

    private Date completeTime;

    private Date lockTime;

    private Integer cancelType;

    private Integer balanceType;

    private Integer change;

    private Integer version;

    private Integer payType;

    private Integer couponValue;

    private Integer deliveryMode;

    private Integer lockOrderUserId;

    private String recipients;

    private String recipientsPhone;

    private String recipientsAddr;

    private Integer freight;

    private Integer warehouseId;

    private Integer residenceId;

    private String residenceName;

    private Integer limitFee;

    private String limitFeeDesc;

    private Byte fFlag;

    private Byte orderType;

    private Integer presellId;

    private Integer groupId;

    private Integer totalVip;

    private Integer totalCoupon;

    private Integer totalFull;

    private Integer totalPointPrice;

    private Integer platform;

    /**
     * 用户内卡号
     */
    private String innerCardId;

    /**
     * 用户外卡号
     */
    private String outerCardId;

    /**
     * 时间付款金额
     */
    private Integer actualOrderPrice;

}
