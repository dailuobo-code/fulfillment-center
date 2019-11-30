package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class OrderWorkFlowStepDO implements Serializable {
    private Long id;

    private Long workFlowId;

    private Byte bizType;

    private Byte type;

    private String flowGroup;

    private String node;

    private String stepName;

    private Byte status;

    private String handler;

    private String features;

    private Date executeTime;

    private Boolean isLocked;

    private Date completeTime;

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

    public Long getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(Long workFlowId) {
        this.workFlowId = workFlowId;
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

    public String getFlowGroup() {
        return flowGroup;
    }

    public void setFlowGroup(String flowGroup) {
        this.flowGroup = flowGroup == null ? null : flowGroup.trim();
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node == null ? null : node.trim();
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName == null ? null : stepName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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
        sb.append(", workFlowId=").append(workFlowId);
        sb.append(", bizType=").append(bizType);
        sb.append(", type=").append(type);
        sb.append(", flowGroup=").append(flowGroup);
        sb.append(", node=").append(node);
        sb.append(", stepName=").append(stepName);
        sb.append(", status=").append(status);
        sb.append(", handler=").append(handler);
        sb.append(", features=").append(features);
        sb.append(", executeTime=").append(executeTime);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}