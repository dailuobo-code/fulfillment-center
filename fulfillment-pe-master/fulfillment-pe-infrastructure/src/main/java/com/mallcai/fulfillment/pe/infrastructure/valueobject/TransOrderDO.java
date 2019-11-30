package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class TransOrderDO implements Serializable {
    private Long id;

    private String transOrderId;

    private Integer storeId;

    private Integer userId;

    private Integer cityId;

    private Date pickupTime;

    private String orderInfoExt;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransOrderId() {
        return transOrderId;
    }

    public void setTransOrderId(String transOrderId) {
        this.transOrderId = transOrderId == null ? null : transOrderId.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getOrderInfoExt() {
        return orderInfoExt;
    }

    public void setOrderInfoExt(String orderInfoExt) {
        this.orderInfoExt = orderInfoExt == null ? null : orderInfoExt.trim();
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
        sb.append(", transOrderId=").append(transOrderId);
        sb.append(", storeId=").append(storeId);
        sb.append(", userId=").append(userId);
        sb.append(", cityId=").append(cityId);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append(", orderInfoExt=").append(orderInfoExt);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}