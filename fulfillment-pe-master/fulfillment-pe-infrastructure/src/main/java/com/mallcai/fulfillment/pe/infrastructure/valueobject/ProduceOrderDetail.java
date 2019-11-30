package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import java.io.Serializable;
import java.util.Date;

public class ProduceOrderDetail implements Serializable {
    private Long id;

    private Long produceOrderId;

    private String produceOrderNo;

    private Long orderId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduceOrderId() {
        return produceOrderId;
    }

    public void setProduceOrderId(Long produceOrderId) {
        this.produceOrderId = produceOrderId;
    }

    public String getProduceOrderNo() {
        return produceOrderNo;
    }

    public void setProduceOrderNo(String produceOrderNo) {
        this.produceOrderNo = produceOrderNo == null ? null : produceOrderNo.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        sb.append(", produceOrderId=").append(produceOrderId);
        sb.append(", produceOrderNo=").append(produceOrderNo);
        sb.append(", orderId=").append(orderId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}