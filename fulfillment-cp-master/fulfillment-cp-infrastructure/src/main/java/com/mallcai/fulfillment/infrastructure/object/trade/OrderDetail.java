package com.mallcai.fulfillment.infrastructure.object.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail implements Serializable {
    private Integer id;

    private String orderId;

    private Integer specId;

    private Integer productNum;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private Integer cityId;

    private Integer storeId;

    private String isPay;

    private Date payTime;

    private Integer cityProductId;

    private String prodShowName;

    private String isLock;

    private String prodIcon;

    private String specName;

    private Boolean starNum;

    private Byte changeFlag;

    private BigDecimal minWeight;

    private BigDecimal maxWeight;

    private Integer minNum;

    private Integer maxNum;

    private String numUnit;

    private String weightUnit;

    private Boolean unitType;

    private Integer packageMaxWeight;

    private Byte isGift;

    private BigDecimal originalPrice;

    private BigDecimal couponPrice;

    private BigDecimal vipPrice;

    private String remark1;

    private String remark2;

    private Integer fullPrice;

    private Integer fullId;

    private Integer pointPrice;

    private BigDecimal totalDiscount;

    private BigDecimal remark6;

    private Integer merchantId;

    private String productInfo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay == null ? null : isPay.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getProdShowName() {
        return prodShowName;
    }

    public void setProdShowName(String prodShowName) {
        this.prodShowName = prodShowName == null ? null : prodShowName.trim();
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }

    public String getProdIcon() {
        return prodIcon;
    }

    public void setProdIcon(String prodIcon) {
        this.prodIcon = prodIcon == null ? null : prodIcon.trim();
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Boolean getStarNum() {
        return starNum;
    }

    public void setStarNum(Boolean starNum) {
        this.starNum = starNum;
    }

    public Byte getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Byte changeFlag) {
        this.changeFlag = changeFlag;
    }

    public BigDecimal getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(BigDecimal minWeight) {
        this.minWeight = minWeight;
    }

    public BigDecimal getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(BigDecimal maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMinNum() {
        return minNum;
    }

    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getNumUnit() {
        return numUnit;
    }

    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit == null ? null : numUnit.trim();
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

    public Boolean getUnitType() {
        return unitType;
    }

    public void setUnitType(Boolean unitType) {
        this.unitType = unitType;
    }

    public Integer getPackageMaxWeight() {
        return packageMaxWeight;
    }

    public void setPackageMaxWeight(Integer packageMaxWeight) {
        this.packageMaxWeight = packageMaxWeight;
    }

    public Byte getIsGift() {
        return isGift;
    }

    public void setIsGift(Byte isGift) {
        this.isGift = isGift;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Integer getFullId() {
        return fullId;
    }

    public void setFullId(Integer fullId) {
        this.fullId = fullId;
    }

    public Integer getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(Integer pointPrice) {
        this.pointPrice = pointPrice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getRemark6() {
        return remark6;
    }

    public void setRemark6(BigDecimal remark6) {
        this.remark6 = remark6;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo == null ? null : productInfo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", specId=").append(specId);
        sb.append(", productNum=").append(productNum);
        sb.append(", price=").append(price);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userId=").append(userId);
        sb.append(", cityId=").append(cityId);
        sb.append(", storeId=").append(storeId);
        sb.append(", isPay=").append(isPay);
        sb.append(", payTime=").append(payTime);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", prodShowName=").append(prodShowName);
        sb.append(", isLock=").append(isLock);
        sb.append(", prodIcon=").append(prodIcon);
        sb.append(", specName=").append(specName);
        sb.append(", starNum=").append(starNum);
        sb.append(", changeFlag=").append(changeFlag);
        sb.append(", minWeight=").append(minWeight);
        sb.append(", maxWeight=").append(maxWeight);
        sb.append(", minNum=").append(minNum);
        sb.append(", maxNum=").append(maxNum);
        sb.append(", numUnit=").append(numUnit);
        sb.append(", weightUnit=").append(weightUnit);
        sb.append(", unitType=").append(unitType);
        sb.append(", packageMaxWeight=").append(packageMaxWeight);
        sb.append(", isGift=").append(isGift);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", vipPrice=").append(vipPrice);
        sb.append(", remark1=").append(remark1);
        sb.append(", remark2=").append(remark2);
        sb.append(", fullPrice=").append(fullPrice);
        sb.append(", fullId=").append(fullId);
        sb.append(", pointPrice=").append(pointPrice);
        sb.append(", totalDiscount=").append(totalDiscount);
        sb.append(", remark6=").append(remark6);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", productInfo=").append(productInfo);
        sb.append("]");
        return sb.toString();
    }
}