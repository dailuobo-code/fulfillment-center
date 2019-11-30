package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class PeriodBuyRecord implements Serializable {
    private Long id;

    private String orderNo;

    private String serialNo;

    private Byte status;

    private String statusMsg;

    private String userName;

    private String userPhone1;

    private String userPhone2;

    private String address;

    private Integer cityId;

    private Integer merchantId;

    private String externProdId;

    private Date payTime;

    private String productInfo;

    private Date startTime;

    private Date endTime;

    private Integer prodAmount;

    private String bookRule;

    private Date cancelTime;

    private Date realEndDate;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg == null ? null : statusMsg.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone1() {
        return userPhone1;
    }

    public void setUserPhone1(String userPhone1) {
        this.userPhone1 = userPhone1 == null ? null : userPhone1.trim();
    }

    public String getUserPhone2() {
        return userPhone2;
    }

    public void setUserPhone2(String userPhone2) {
        this.userPhone2 = userPhone2 == null ? null : userPhone2.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getExternProdId() {
        return externProdId;
    }

    public void setExternProdId(String externProdId) {
        this.externProdId = externProdId == null ? null : externProdId.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo == null ? null : productInfo.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Integer prodAmount) {
        this.prodAmount = prodAmount;
    }

    public String getBookRule() {
        return bookRule;
    }

    public void setBookRule(String bookRule) {
        this.bookRule = bookRule == null ? null : bookRule.trim();
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", serialNo=").append(serialNo);
        sb.append(", status=").append(status);
        sb.append(", statusMsg=").append(statusMsg);
        sb.append(", userName=").append(userName);
        sb.append(", userPhone1=").append(userPhone1);
        sb.append(", userPhone2=").append(userPhone2);
        sb.append(", address=").append(address);
        sb.append(", cityId=").append(cityId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", externProdId=").append(externProdId);
        sb.append(", payTime=").append(payTime);
        sb.append(", productInfo=").append(productInfo);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", prodAmount=").append(prodAmount);
        sb.append(", bookRule=").append(bookRule);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", realEndDate=").append(realEndDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}