package com.mallcai.fulfillment.infrastructure.object.pe;

import java.io.Serializable;
import java.util.Date;

public class PeOrder implements Serializable {
    private Long id;

    private String transOrderId;

    private Byte status;

    private Integer orderType;

    private Integer cityProductId;

    private Integer storeId;

    private Integer userId;

    private Integer cityId;

    private String productNo;

    private Byte orderSource;

    private Byte groupType;

    private String groupValue;

    private Integer warehouseId;

    private String commodityInfoExt;

    private Integer version;

    private Date expectPushTime;

    private Date createTime;

    private Date updateTime;

    private Date pickupTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransOrderId() {
        return transOrderId;
    }

    public void setTransOrderId(String transOrderId) {
        this.transOrderId = transOrderId == null ? null : transOrderId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Byte getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Byte orderSource) {
        this.orderSource = orderSource;
    }

    public Byte getGroupType() {
        return groupType;
    }

    public void setGroupType(Byte groupType) {
        this.groupType = groupType;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue == null ? null : groupValue.trim();
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCommodityInfoExt() {
        return commodityInfoExt;
    }

    public void setCommodityInfoExt(String commodityInfoExt) {
        this.commodityInfoExt = commodityInfoExt == null ? null : commodityInfoExt.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getExpectPushTime() {
        return expectPushTime;
    }

    public void setExpectPushTime(Date expectPushTime) {
        this.expectPushTime = expectPushTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", transOrderId=").append(transOrderId);
        sb.append(", status=").append(status);
        sb.append(", orderType=").append(orderType);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", storeId=").append(storeId);
        sb.append(", userId=").append(userId);
        sb.append(", cityId=").append(cityId);
        sb.append(", productNo=").append(productNo);
        sb.append(", orderSource=").append(orderSource);
        sb.append(", groupType=").append(groupType);
        sb.append(", groupValue=").append(groupValue);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", commodityInfoExt=").append(commodityInfoExt);
        sb.append(", version=").append(version);
        sb.append(", expectPushTime=").append(expectPushTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append("]");
        return sb.toString();
    }
}