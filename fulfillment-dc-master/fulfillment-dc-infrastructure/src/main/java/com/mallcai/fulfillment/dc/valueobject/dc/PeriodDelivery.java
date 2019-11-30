package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PeriodDelivery implements Serializable {
    private Long id;

    private String orderNo;

    private String serialNo;

    private String pdtId;

    private Integer prodAmount;

    private Integer item;

    private BigDecimal price;

    private Date deliveryDate;

    private String deliveryMan;

    private String deliveryManPhone;

    private Date createTime;

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

    public String getPdtId() {
        return pdtId;
    }

    public void setPdtId(String pdtId) {
        this.pdtId = pdtId == null ? null : pdtId.trim();
    }

    public Integer getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(Integer prodAmount) {
        this.prodAmount = prodAmount;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan == null ? null : deliveryMan.trim();
    }

    public String getDeliveryManPhone() {
        return deliveryManPhone;
    }

    public void setDeliveryManPhone(String deliveryManPhone) {
        this.deliveryManPhone = deliveryManPhone == null ? null : deliveryManPhone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", pdtId=").append(pdtId);
        sb.append(", prodAmount=").append(prodAmount);
        sb.append(", item=").append(item);
        sb.append(", price=").append(price);
        sb.append(", deliveryDate=").append(deliveryDate);
        sb.append(", deliveryMan=").append(deliveryMan);
        sb.append(", deliveryManPhone=").append(deliveryManPhone);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}