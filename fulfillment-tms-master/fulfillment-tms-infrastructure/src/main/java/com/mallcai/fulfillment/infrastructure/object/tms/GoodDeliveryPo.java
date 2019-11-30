package com.mallcai.fulfillment.infrastructure.object.tms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodDeliveryPo implements Serializable {
    private Long id;

    private String waybillNo;

    private Integer cityId;

    private Integer storeId;

    private String skuCode;

    private String skuName;

    private BigDecimal skuCount;

    private BigDecimal skuWeight;

    private String skuUnit;

    private BigDecimal skuVolume;

    private String skuType;

    private Date createTime;

    private String createBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public BigDecimal getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(BigDecimal skuCount) {
        this.skuCount = skuCount;
    }

    public BigDecimal getSkuWeight() {
        return skuWeight;
    }

    public void setSkuWeight(BigDecimal skuWeight) {
        this.skuWeight = skuWeight;
    }

    public String getSkuUnit() {
        return skuUnit;
    }

    public void setSkuUnit(String skuUnit) {
        this.skuUnit = skuUnit == null ? null : skuUnit.trim();
    }

    public BigDecimal getSkuVolume() {
        return skuVolume;
    }

    public void setSkuVolume(BigDecimal skuVolume) {
        this.skuVolume = skuVolume;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType == null ? null : skuType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", waybillNo=").append(waybillNo);
        sb.append(", cityId=").append(cityId);
        sb.append(", storeId=").append(storeId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuCount=").append(skuCount);
        sb.append(", skuWeight=").append(skuWeight);
        sb.append(", skuUnit=").append(skuUnit);
        sb.append(", skuVolume=").append(skuVolume);
        sb.append(", skuType=").append(skuType);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }
}