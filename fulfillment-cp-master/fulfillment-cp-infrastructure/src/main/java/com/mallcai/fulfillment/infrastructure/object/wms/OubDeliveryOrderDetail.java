package com.mallcai.fulfillment.infrastructure.object.wms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OubDeliveryOrderDetail implements Serializable {
    private Long id;

    private String warehouseCode;

    private String deliveryOrderNo;

    private Date pickupDate;

    private Integer storeNo;

    private String skuCode;

    private String skuName;

    private String skuBarcode;

    private String rawSkuCode;

    private String rawSkuName;

    private String rawSkuBarcode;

    private String soldUnit;

    private BigDecimal soldQty;

    private Short soldContainerQty;

    private BigDecimal soldLeftWeight;

    private BigDecimal upwardFloatingRatio;

    private BigDecimal deliveryQty;

    private BigDecimal deliveryContainerQty;

    private BigDecimal pickProQty;

    private BigDecimal pickBoxQty;

    private Long collectQty;

    private Long stockoutQty;

    private String deliveryContainerUnit;

    private BigDecimal deliverLeftWeight;

    private Byte type;

    private Byte status;

    private Date weighingTime;

    private Integer weightingUserId;

    private Date sorterTime;

    private Integer sorterUserId;

    private String leftWeightQrcode;

    private Date createdTime;

    private Integer createdUserId;

    private Date updatedTime;

    private Integer updatedUserId;

    private Integer version;

    private Byte lackStatus;

    private Date weightStartTime;

    private Date weightConfirmTime;

    private Integer weightUserScore;

    private BigDecimal skuPercent;

    private Byte scanStatus;

    private String accountingUnit;

    private String receivingUnit;

    private String inventoryUnit;

    private String deliveryUnit;

    private BigDecimal weightPerEach;

    private String weightPerEachUnit;

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

    public String getDeliveryOrderNo() {
        return deliveryOrderNo;
    }

    public void setDeliveryOrderNo(String deliveryOrderNo) {
        this.deliveryOrderNo = deliveryOrderNo == null ? null : deliveryOrderNo.trim();
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
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

    public String getSkuBarcode() {
        return skuBarcode;
    }

    public void setSkuBarcode(String skuBarcode) {
        this.skuBarcode = skuBarcode == null ? null : skuBarcode.trim();
    }

    public String getRawSkuCode() {
        return rawSkuCode;
    }

    public void setRawSkuCode(String rawSkuCode) {
        this.rawSkuCode = rawSkuCode == null ? null : rawSkuCode.trim();
    }

    public String getRawSkuName() {
        return rawSkuName;
    }

    public void setRawSkuName(String rawSkuName) {
        this.rawSkuName = rawSkuName == null ? null : rawSkuName.trim();
    }

    public String getRawSkuBarcode() {
        return rawSkuBarcode;
    }

    public void setRawSkuBarcode(String rawSkuBarcode) {
        this.rawSkuBarcode = rawSkuBarcode == null ? null : rawSkuBarcode.trim();
    }

    public String getSoldUnit() {
        return soldUnit;
    }

    public void setSoldUnit(String soldUnit) {
        this.soldUnit = soldUnit == null ? null : soldUnit.trim();
    }

    public BigDecimal getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(BigDecimal soldQty) {
        this.soldQty = soldQty;
    }

    public Short getSoldContainerQty() {
        return soldContainerQty;
    }

    public void setSoldContainerQty(Short soldContainerQty) {
        this.soldContainerQty = soldContainerQty;
    }

    public BigDecimal getSoldLeftWeight() {
        return soldLeftWeight;
    }

    public void setSoldLeftWeight(BigDecimal soldLeftWeight) {
        this.soldLeftWeight = soldLeftWeight;
    }

    public BigDecimal getUpwardFloatingRatio() {
        return upwardFloatingRatio;
    }

    public void setUpwardFloatingRatio(BigDecimal upwardFloatingRatio) {
        this.upwardFloatingRatio = upwardFloatingRatio;
    }

    public BigDecimal getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public BigDecimal getDeliveryContainerQty() {
        return deliveryContainerQty;
    }

    public void setDeliveryContainerQty(BigDecimal deliveryContainerQty) {
        this.deliveryContainerQty = deliveryContainerQty;
    }

    public BigDecimal getPickProQty() {
        return pickProQty;
    }

    public void setPickProQty(BigDecimal pickProQty) {
        this.pickProQty = pickProQty;
    }

    public BigDecimal getPickBoxQty() {
        return pickBoxQty;
    }

    public void setPickBoxQty(BigDecimal pickBoxQty) {
        this.pickBoxQty = pickBoxQty;
    }

    public Long getCollectQty() {
        return collectQty;
    }

    public void setCollectQty(Long collectQty) {
        this.collectQty = collectQty;
    }

    public Long getStockoutQty() {
        return stockoutQty;
    }

    public void setStockoutQty(Long stockoutQty) {
        this.stockoutQty = stockoutQty;
    }

    public String getDeliveryContainerUnit() {
        return deliveryContainerUnit;
    }

    public void setDeliveryContainerUnit(String deliveryContainerUnit) {
        this.deliveryContainerUnit = deliveryContainerUnit == null ? null : deliveryContainerUnit.trim();
    }

    public BigDecimal getDeliverLeftWeight() {
        return deliverLeftWeight;
    }

    public void setDeliverLeftWeight(BigDecimal deliverLeftWeight) {
        this.deliverLeftWeight = deliverLeftWeight;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getWeighingTime() {
        return weighingTime;
    }

    public void setWeighingTime(Date weighingTime) {
        this.weighingTime = weighingTime;
    }

    public Integer getWeightingUserId() {
        return weightingUserId;
    }

    public void setWeightingUserId(Integer weightingUserId) {
        this.weightingUserId = weightingUserId;
    }

    public Date getSorterTime() {
        return sorterTime;
    }

    public void setSorterTime(Date sorterTime) {
        this.sorterTime = sorterTime;
    }

    public Integer getSorterUserId() {
        return sorterUserId;
    }

    public void setSorterUserId(Integer sorterUserId) {
        this.sorterUserId = sorterUserId;
    }

    public String getLeftWeightQrcode() {
        return leftWeightQrcode;
    }

    public void setLeftWeightQrcode(String leftWeightQrcode) {
        this.leftWeightQrcode = leftWeightQrcode == null ? null : leftWeightQrcode.trim();
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

    public Byte getLackStatus() {
        return lackStatus;
    }

    public void setLackStatus(Byte lackStatus) {
        this.lackStatus = lackStatus;
    }

    public Date getWeightStartTime() {
        return weightStartTime;
    }

    public void setWeightStartTime(Date weightStartTime) {
        this.weightStartTime = weightStartTime;
    }

    public Date getWeightConfirmTime() {
        return weightConfirmTime;
    }

    public void setWeightConfirmTime(Date weightConfirmTime) {
        this.weightConfirmTime = weightConfirmTime;
    }

    public Integer getWeightUserScore() {
        return weightUserScore;
    }

    public void setWeightUserScore(Integer weightUserScore) {
        this.weightUserScore = weightUserScore;
    }

    public BigDecimal getSkuPercent() {
        return skuPercent;
    }

    public void setSkuPercent(BigDecimal skuPercent) {
        this.skuPercent = skuPercent;
    }

    public Byte getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(Byte scanStatus) {
        this.scanStatus = scanStatus;
    }

    public String getAccountingUnit() {
        return accountingUnit;
    }

    public void setAccountingUnit(String accountingUnit) {
        this.accountingUnit = accountingUnit == null ? null : accountingUnit.trim();
    }

    public String getReceivingUnit() {
        return receivingUnit;
    }

    public void setReceivingUnit(String receivingUnit) {
        this.receivingUnit = receivingUnit == null ? null : receivingUnit.trim();
    }

    public String getInventoryUnit() {
        return inventoryUnit;
    }

    public void setInventoryUnit(String inventoryUnit) {
        this.inventoryUnit = inventoryUnit == null ? null : inventoryUnit.trim();
    }

    public String getDeliveryUnit() {
        return deliveryUnit;
    }

    public void setDeliveryUnit(String deliveryUnit) {
        this.deliveryUnit = deliveryUnit == null ? null : deliveryUnit.trim();
    }

    public BigDecimal getWeightPerEach() {
        return weightPerEach;
    }

    public void setWeightPerEach(BigDecimal weightPerEach) {
        this.weightPerEach = weightPerEach;
    }

    public String getWeightPerEachUnit() {
        return weightPerEachUnit;
    }

    public void setWeightPerEachUnit(String weightPerEachUnit) {
        this.weightPerEachUnit = weightPerEachUnit == null ? null : weightPerEachUnit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", warehouseCode=").append(warehouseCode);
        sb.append(", deliveryOrderNo=").append(deliveryOrderNo);
        sb.append(", pickupDate=").append(pickupDate);
        sb.append(", storeNo=").append(storeNo);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuName=").append(skuName);
        sb.append(", skuBarcode=").append(skuBarcode);
        sb.append(", rawSkuCode=").append(rawSkuCode);
        sb.append(", rawSkuName=").append(rawSkuName);
        sb.append(", rawSkuBarcode=").append(rawSkuBarcode);
        sb.append(", soldUnit=").append(soldUnit);
        sb.append(", soldQty=").append(soldQty);
        sb.append(", soldContainerQty=").append(soldContainerQty);
        sb.append(", soldLeftWeight=").append(soldLeftWeight);
        sb.append(", upwardFloatingRatio=").append(upwardFloatingRatio);
        sb.append(", deliveryQty=").append(deliveryQty);
        sb.append(", deliveryContainerQty=").append(deliveryContainerQty);
        sb.append(", pickProQty=").append(pickProQty);
        sb.append(", pickBoxQty=").append(pickBoxQty);
        sb.append(", collectQty=").append(collectQty);
        sb.append(", stockoutQty=").append(stockoutQty);
        sb.append(", deliveryContainerUnit=").append(deliveryContainerUnit);
        sb.append(", deliverLeftWeight=").append(deliverLeftWeight);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", weighingTime=").append(weighingTime);
        sb.append(", weightingUserId=").append(weightingUserId);
        sb.append(", sorterTime=").append(sorterTime);
        sb.append(", sorterUserId=").append(sorterUserId);
        sb.append(", leftWeightQrcode=").append(leftWeightQrcode);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createdUserId=").append(createdUserId);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", updatedUserId=").append(updatedUserId);
        sb.append(", version=").append(version);
        sb.append(", lackStatus=").append(lackStatus);
        sb.append(", weightStartTime=").append(weightStartTime);
        sb.append(", weightConfirmTime=").append(weightConfirmTime);
        sb.append(", weightUserScore=").append(weightUserScore);
        sb.append(", skuPercent=").append(skuPercent);
        sb.append(", scanStatus=").append(scanStatus);
        sb.append(", accountingUnit=").append(accountingUnit);
        sb.append(", receivingUnit=").append(receivingUnit);
        sb.append(", inventoryUnit=").append(inventoryUnit);
        sb.append(", deliveryUnit=").append(deliveryUnit);
        sb.append(", weightPerEach=").append(weightPerEach);
        sb.append(", weightPerEachUnit=").append(weightPerEachUnit);
        sb.append("]");
        return sb.toString();
    }
}