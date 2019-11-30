package com.mallcai.fulfillment.infrastructure.object.wms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OubDeliveryOrder implements Serializable {
    private Long id;

    private String warehouseCode;

    private String waveNumber;

    private String deliveryOrder;

    private Date pickupDate;

    private Byte pushTmsStatus;

    private Byte pushStoreStatus;

    private String skuGroupCode;

    private Integer storeId;

    private Integer storeNo;

    private String storeName;

    private Integer trafficSrlNumber;

    private Integer serialNumber;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private String receiver;

    private String tel;

    private BigDecimal soldCount;

    private BigDecimal deliveryCount;

    private BigDecimal totalWeight;

    private BigDecimal totalVolume;

    private String locationCode;

    private Integer deliveryUserId;

    private Byte status;

    private Byte type;

    private Date createdTime;

    private Integer createdUserId;

    private Date updatedTime;

    private Integer updatedUserId;

    private Integer version;

    private String sourceOrderNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public String getWaveNumber() {
        return waveNumber;
    }

    public void setWaveNumber(String waveNumber) {
        this.waveNumber = waveNumber == null ? null : waveNumber.trim();
    }

    public String getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(String deliveryOrder) {
        this.deliveryOrder = deliveryOrder == null ? null : deliveryOrder.trim();
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Byte getPushTmsStatus() {
        return pushTmsStatus;
    }

    public void setPushTmsStatus(Byte pushTmsStatus) {
        this.pushTmsStatus = pushTmsStatus;
    }

    public Byte getPushStoreStatus() {
        return pushStoreStatus;
    }

    public void setPushStoreStatus(Byte pushStoreStatus) {
        this.pushStoreStatus = pushStoreStatus;
    }

    public String getSkuGroupCode() {
        return skuGroupCode;
    }

    public void setSkuGroupCode(String skuGroupCode) {
        this.skuGroupCode = skuGroupCode == null ? null : skuGroupCode.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getTrafficSrlNumber() {
        return trafficSrlNumber;
    }

    public void setTrafficSrlNumber(Integer trafficSrlNumber) {
        this.trafficSrlNumber = trafficSrlNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public BigDecimal getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(BigDecimal soldCount) {
        this.soldCount = soldCount;
    }

    public BigDecimal getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(BigDecimal deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public Integer getDeliveryUserId() {
        return deliveryUserId;
    }

    public void setDeliveryUserId(Integer deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Integer createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Integer updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSourceOrderNo() {
        return sourceOrderNo;
    }

    public void setSourceOrderNo(String sourceOrderNo) {
        this.sourceOrderNo = sourceOrderNo == null ? null : sourceOrderNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", warehouseCode=").append(warehouseCode);
        sb.append(", waveNumber=").append(waveNumber);
        sb.append(", deliveryOrder=").append(deliveryOrder);
        sb.append(", pickupDate=").append(pickupDate);
        sb.append(", pushTmsStatus=").append(pushTmsStatus);
        sb.append(", pushStoreStatus=").append(pushStoreStatus);
        sb.append(", skuGroupCode=").append(skuGroupCode);
        sb.append(", storeId=").append(storeId);
        sb.append(", storeNo=").append(storeNo);
        sb.append(", storeName=").append(storeName);
        sb.append(", trafficSrlNumber=").append(trafficSrlNumber);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", cityId=").append(cityId);
        sb.append(", areaId=").append(areaId);
        sb.append(", address=").append(address);
        sb.append(", receiver=").append(receiver);
        sb.append(", tel=").append(tel);
        sb.append(", soldCount=").append(soldCount);
        sb.append(", deliveryCount=").append(deliveryCount);
        sb.append(", totalWeight=").append(totalWeight);
        sb.append(", totalVolume=").append(totalVolume);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", deliveryUserId=").append(deliveryUserId);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createdUserId=").append(createdUserId);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", updatedUserId=").append(updatedUserId);
        sb.append(", version=").append(version);
        sb.append(", sourceOrderNo=").append(sourceOrderNo);
        sb.append("]");
        return sb.toString();
    }
}