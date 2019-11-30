package com.mallcai.fulfillment.infrastructure.object.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private Integer id;

    private String orderId;

    private Integer storeId;

    private Integer cityId;

    private Integer userId;

    private String orderName;

    private Byte status;

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

    private Byte pickupTypeId;

    private Byte isWilling;

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

    private Boolean cancelType;

    private Boolean balanceType;

    private Integer change;

    private Byte version;

    private Byte payType;

    private Integer couponValue;

    private Byte deliveryMode;

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

    private Byte splitType;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAsynPayStatus() {
        return asynPayStatus;
    }

    public void setAsynPayStatus(String asynPayStatus) {
        this.asynPayStatus = asynPayStatus == null ? null : asynPayStatus.trim();
    }

    public String getOrderPic() {
        return orderPic;
    }

    public void setOrderPic(String orderPic) {
        this.orderPic = orderPic == null ? null : orderPic.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayCompleteTime() {
        return payCompleteTime;
    }

    public void setPayCompleteTime(Date payCompleteTime) {
        this.payCompleteTime = payCompleteTime;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPickupStartTime() {
        return pickupStartTime;
    }

    public void setPickupStartTime(String pickupStartTime) {
        this.pickupStartTime = pickupStartTime == null ? null : pickupStartTime.trim();
    }

    public String getPickupEndTime() {
        return pickupEndTime;
    }

    public void setPickupEndTime(String pickupEndTime) {
        this.pickupEndTime = pickupEndTime == null ? null : pickupEndTime.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getPickupCode() {
        return pickupCode;
    }

    public void setPickupCode(String pickupCode) {
        this.pickupCode = pickupCode == null ? null : pickupCode.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Byte getPickupTypeId() {
        return pickupTypeId;
    }

    public void setPickupTypeId(Byte pickupTypeId) {
        this.pickupTypeId = pickupTypeId;
    }

    public Byte getIsWilling() {
        return isWilling;
    }

    public void setIsWilling(Byte isWilling) {
        this.isWilling = isWilling;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras == null ? null : extras.trim();
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponDesc() {
        return couponDesc;
    }

    public void setCouponDesc(String couponDesc) {
        this.couponDesc = couponDesc == null ? null : couponDesc.trim();
    }

    public Integer getPickupTimeId() {
        return pickupTimeId;
    }

    public void setPickupTimeId(Integer pickupTimeId) {
        this.pickupTimeId = pickupTimeId;
    }

    public Integer getCompleteUserId() {
        return completeUserId;
    }

    public void setCompleteUserId(Integer completeUserId) {
        this.completeUserId = completeUserId;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Boolean getCancelType() {
        return cancelType;
    }

    public void setCancelType(Boolean cancelType) {
        this.cancelType = cancelType;
    }

    public Boolean getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Boolean balanceType) {
        this.balanceType = balanceType;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Integer getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Integer couponValue) {
        this.couponValue = couponValue;
    }

    public Byte getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Byte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getLockOrderUserId() {
        return lockOrderUserId;
    }

    public void setLockOrderUserId(Integer lockOrderUserId) {
        this.lockOrderUserId = lockOrderUserId;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone == null ? null : recipientsPhone.trim();
    }

    public String getRecipientsAddr() {
        return recipientsAddr;
    }

    public void setRecipientsAddr(String recipientsAddr) {
        this.recipientsAddr = recipientsAddr == null ? null : recipientsAddr.trim();
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    public String getResidenceName() {
        return residenceName;
    }

    public void setResidenceName(String residenceName) {
        this.residenceName = residenceName == null ? null : residenceName.trim();
    }

    public Integer getLimitFee() {
        return limitFee;
    }

    public void setLimitFee(Integer limitFee) {
        this.limitFee = limitFee;
    }

    public String getLimitFeeDesc() {
        return limitFeeDesc;
    }

    public void setLimitFeeDesc(String limitFeeDesc) {
        this.limitFeeDesc = limitFeeDesc == null ? null : limitFeeDesc.trim();
    }

    public Byte getfFlag() {
        return fFlag;
    }

    public void setfFlag(Byte fFlag) {
        this.fFlag = fFlag;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public Integer getPresellId() {
        return presellId;
    }

    public void setPresellId(Integer presellId) {
        this.presellId = presellId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getTotalVip() {
        return totalVip;
    }

    public void setTotalVip(Integer totalVip) {
        this.totalVip = totalVip;
    }

    public Integer getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(Integer totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    public Integer getTotalFull() {
        return totalFull;
    }

    public void setTotalFull(Integer totalFull) {
        this.totalFull = totalFull;
    }

    public Integer getTotalPointPrice() {
        return totalPointPrice;
    }

    public void setTotalPointPrice(Integer totalPointPrice) {
        this.totalPointPrice = totalPointPrice;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Byte getSplitType() {
        return splitType;
    }

    public void setSplitType(Byte splitType) {
        this.splitType = splitType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", storeId=").append(storeId);
        sb.append(", cityId=").append(cityId);
        sb.append(", userId=").append(userId);
        sb.append(", orderName=").append(orderName);
        sb.append(", status=").append(status);
        sb.append(", asynPayStatus=").append(asynPayStatus);
        sb.append(", orderPic=").append(orderPic);
        sb.append(", payTime=").append(payTime);
        sb.append(", payCompleteTime=").append(payCompleteTime);
        sb.append(", generateTime=").append(generateTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", pickupStartTime=").append(pickupStartTime);
        sb.append(", pickupEndTime=").append(pickupEndTime);
        sb.append(", memo=").append(memo);
        sb.append(", pickupCode=").append(pickupCode);
        sb.append(", weight=").append(weight);
        sb.append(", pickupTypeId=").append(pickupTypeId);
        sb.append(", isWilling=").append(isWilling);
        sb.append(", balance=").append(balance);
        sb.append(", isDel=").append(isDel);
        sb.append(", extras=").append(extras);
        sb.append(", evaluateContent=").append(evaluateContent);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponDesc=").append(couponDesc);
        sb.append(", pickupTimeId=").append(pickupTimeId);
        sb.append(", completeUserId=").append(completeUserId);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", cancelType=").append(cancelType);
        sb.append(", balanceType=").append(balanceType);
        sb.append(", change=").append(change);
        sb.append(", version=").append(version);
        sb.append(", payType=").append(payType);
        sb.append(", couponValue=").append(couponValue);
        sb.append(", deliveryMode=").append(deliveryMode);
        sb.append(", lockOrderUserId=").append(lockOrderUserId);
        sb.append(", recipients=").append(recipients);
        sb.append(", recipientsPhone=").append(recipientsPhone);
        sb.append(", recipientsAddr=").append(recipientsAddr);
        sb.append(", freight=").append(freight);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", residenceId=").append(residenceId);
        sb.append(", residenceName=").append(residenceName);
        sb.append(", limitFee=").append(limitFee);
        sb.append(", limitFeeDesc=").append(limitFeeDesc);
        sb.append(", fFlag=").append(fFlag);
        sb.append(", orderType=").append(orderType);
        sb.append(", presellId=").append(presellId);
        sb.append(", groupId=").append(groupId);
        sb.append(", totalVip=").append(totalVip);
        sb.append(", totalCoupon=").append(totalCoupon);
        sb.append(", totalFull=").append(totalFull);
        sb.append(", totalPointPrice=").append(totalPointPrice);
        sb.append(", platform=").append(platform);
        sb.append(", splitType=").append(splitType);
        sb.append("]");
        return sb.toString();
    }
}