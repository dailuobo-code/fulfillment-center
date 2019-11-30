package com.mallcai.fulfillment.infrastructure.object.tms;

import java.io.Serializable;
import java.util.Date;

public class TransportLinePo implements Serializable {
    private Long id;

    private String planNo;

    private String orderNo;

    private String address;

    private String orgCode;

    private String sequence;

    private Date minArrive;

    private Date maxArrive;

    private Date minLeave;

    private Date maxLeave;

    private Date realArrive;

    private Date realLeave;

    private Integer deliveryAmt;

    private Boolean delivery;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo == null ? null : planNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence == null ? null : sequence.trim();
    }

    public Date getMinArrive() {
        return minArrive;
    }

    public void setMinArrive(Date minArrive) {
        this.minArrive = minArrive;
    }

    public Date getMaxArrive() {
        return maxArrive;
    }

    public void setMaxArrive(Date maxArrive) {
        this.maxArrive = maxArrive;
    }

    public Date getMinLeave() {
        return minLeave;
    }

    public void setMinLeave(Date minLeave) {
        this.minLeave = minLeave;
    }

    public Date getMaxLeave() {
        return maxLeave;
    }

    public void setMaxLeave(Date maxLeave) {
        this.maxLeave = maxLeave;
    }

    public Date getRealArrive() {
        return realArrive;
    }

    public void setRealArrive(Date realArrive) {
        this.realArrive = realArrive;
    }

    public Date getRealLeave() {
        return realLeave;
    }

    public void setRealLeave(Date realLeave) {
        this.realLeave = realLeave;
    }

    public Integer getDeliveryAmt() {
        return deliveryAmt;
    }

    public void setDeliveryAmt(Integer deliveryAmt) {
        this.deliveryAmt = deliveryAmt;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", planNo=").append(planNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", address=").append(address);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", sequence=").append(sequence);
        sb.append(", minArrive=").append(minArrive);
        sb.append(", maxArrive=").append(maxArrive);
        sb.append(", minLeave=").append(minLeave);
        sb.append(", maxLeave=").append(maxLeave);
        sb.append(", realArrive=").append(realArrive);
        sb.append(", realLeave=").append(realLeave);
        sb.append(", deliveryAmt=").append(deliveryAmt);
        sb.append(", delivery=").append(delivery);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append("]");
        return sb.toString();
    }
}