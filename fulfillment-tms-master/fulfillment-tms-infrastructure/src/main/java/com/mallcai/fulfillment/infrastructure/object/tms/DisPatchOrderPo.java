package com.mallcai.fulfillment.infrastructure.object.tms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DisPatchOrderPo implements Serializable {
    private Long id;

    private String dispatchNo;

    private Date pickupDate;

    private String warehouseCode;

    private Integer storeNo;

    private BigDecimal soldCount;

    private String outbondType;

    private String receiver;

    private String tel;

    private Date createTime;

    private String createBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo == null ? null : dispatchNo.trim();
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public Integer getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(Integer storeNo) {
        this.storeNo = storeNo;
    }

    public BigDecimal getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(BigDecimal soldCount) {
        this.soldCount = soldCount;
    }

    public String getOutbondType() {
        return outbondType;
    }

    public void setOutbondType(String outbondType) {
        this.outbondType = outbondType == null ? null : outbondType.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dispatchNo=").append(dispatchNo);
        sb.append(", pickupDate=").append(pickupDate);
        sb.append(", warehouseCode=").append(warehouseCode);
        sb.append(", storeNo=").append(storeNo);
        sb.append(", soldCount=").append(soldCount);
        sb.append(", outbondType=").append(outbondType);
        sb.append(", receiver=").append(receiver);
        sb.append(", tel=").append(tel);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }
}