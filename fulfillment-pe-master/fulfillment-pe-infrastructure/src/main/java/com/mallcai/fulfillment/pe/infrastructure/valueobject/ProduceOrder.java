package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import java.io.Serializable;
import java.util.Date;

public class ProduceOrder implements Serializable {
    private Long id;

    private String produceOrderNo;

    private Date expectPushTime;

    private Byte aggregateType;

    private String aggregateValue;

    private Byte groupType;

    private String groupValue;

    private Integer storeId;

    private Integer cityId;

    private Integer warehouseId;

    private Integer orderCount;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Date sucTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduceOrderNo() {
        return produceOrderNo;
    }

    public void setProduceOrderNo(String produceOrderNo) {
        this.produceOrderNo = produceOrderNo == null ? null : produceOrderNo.trim();
    }

    public Date getExpectPushTime() {
        return expectPushTime;
    }

    public void setExpectPushTime(Date expectPushTime) {
        this.expectPushTime = expectPushTime;
    }

    public Byte getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(Byte aggregateType) {
        this.aggregateType = aggregateType;
    }

    public String getAggregateValue() {
        return aggregateValue;
    }

    public void setAggregateValue(String aggregateValue) {
        this.aggregateValue = aggregateValue == null ? null : aggregateValue.trim();
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Date getSucTime() {
        return sucTime;
    }

    public void setSucTime(Date sucTime) {
        this.sucTime = sucTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", produceOrderNo=").append(produceOrderNo);
        sb.append(", expectPushTime=").append(expectPushTime);
        sb.append(", aggregateType=").append(aggregateType);
        sb.append(", aggregateValue=").append(aggregateValue);
        sb.append(", groupType=").append(groupType);
        sb.append(", groupValue=").append(groupValue);
        sb.append(", storeId=").append(storeId);
        sb.append(", cityId=").append(cityId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sucTime=").append(sucTime);
        sb.append("]");
        return sb.toString();
    }
}