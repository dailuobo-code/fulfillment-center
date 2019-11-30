package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class FlowStepDataInfoDO implements Serializable {
    private Long id;

    private Long workFlowId;

    private Long workFlowStepId;

    private Byte bizType;

    private String flowGroup;

    private String node;

    private String stepName;

    private String bizKey;

    private String data;

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

    public Long getWorkFlowStepId() {
        return workFlowStepId;
    }

    public void setWorkFlowStepId(Long workFlowStepId) {
        this.workFlowStepId = workFlowStepId;
    }

    public Byte getBizType() {
        return bizType;
    }

    public void setBizType(Byte bizType) {
        this.bizType = bizType;
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

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey == null ? null : bizKey.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
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
        sb.append(", workFlowStepId=").append(workFlowStepId);
        sb.append(", bizType=").append(bizType);
        sb.append(", flowGroup=").append(flowGroup);
        sb.append(", node=").append(node);
        sb.append(", stepName=").append(stepName);
        sb.append(", bizKey=").append(bizKey);
        sb.append(", data=").append(data);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}