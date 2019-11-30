package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class OrderWorkFlowDO implements Serializable {
    private Long id;

    private Byte bizType;

    private Byte type;

    private String orderNo;

    private String flowGroup;

    private Byte status;

    private Boolean isLocked;

    private Long parentWorkFlowId;

    private String currStep;

    private Byte version;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getBizType() {
        return bizType;
    }

    public void setBizType(Byte bizType) {
        this.bizType = bizType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getFlowGroup() {
        return flowGroup;
    }

    public void setFlowGroup(String flowGroup) {
        this.flowGroup = flowGroup == null ? null : flowGroup.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Long getParentWorkFlowId() {
        return parentWorkFlowId;
    }

    public void setParentWorkFlowId(Long parentWorkFlowId) {
        this.parentWorkFlowId = parentWorkFlowId;
    }

    public String getCurrStep() {
        return currStep;
    }

    public void setCurrStep(String currStep) {
        this.currStep = currStep == null ? null : currStep.trim();
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
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
        sb.append(", bizType=").append(bizType);
        sb.append(", type=").append(type);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", flowGroup=").append(flowGroup);
        sb.append(", status=").append(status);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", parentWorkFlowId=").append(parentWorkFlowId);
        sb.append(", currStep=").append(currStep);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}