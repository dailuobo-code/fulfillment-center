package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class PurchaseDO implements Serializable {
    private Long id;

    private Integer cityId;

    private Integer warehouseId;

    private Byte categoryType;

    private Integer cityProductId;

    private Integer forecastNum;

    private Byte forecastType;

    private Date forecastDate;

    private Integer version;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Byte getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Byte categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getForecastNum() {
        return forecastNum;
    }

    public void setForecastNum(Integer forecastNum) {
        this.forecastNum = forecastNum;
    }

    public Byte getForecastType() {
        return forecastType;
    }

    public void setForecastType(Byte forecastType) {
        this.forecastType = forecastType;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityId=").append(cityId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", categoryType=").append(categoryType);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", forecastNum=").append(forecastNum);
        sb.append(", forecastType=").append(forecastType);
        sb.append(", forecastDate=").append(forecastDate);
        sb.append(", version=").append(version);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}