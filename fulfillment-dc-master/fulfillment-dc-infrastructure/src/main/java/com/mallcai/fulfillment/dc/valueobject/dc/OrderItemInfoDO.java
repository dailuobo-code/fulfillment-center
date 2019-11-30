package com.mallcai.fulfillment.dc.valueobject.dc;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderItemInfoDO implements Serializable {
    private Long id;

    private String orderNo;

    private Byte orderType;

    private Byte orderSource;

    private Integer cityProductId;

    private Integer specId;

    private BigDecimal productNum;

    private Integer storeId;

    private Integer userId;

    private String pickupTime;

    private Integer cityId;

    private Integer warehouseId;

    private String productNo;

    private String itemExt;

    private Integer version;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderSource=").append(orderSource);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", specId=").append(specId);
        sb.append(", productNum=").append(productNum);
        sb.append(", storeId=").append(storeId);
        sb.append(", userId=").append(userId);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append(", cityId=").append(cityId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", productNo=").append(productNo);
        sb.append(", itemExt=").append(itemExt);
        sb.append(", version=").append(version);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}