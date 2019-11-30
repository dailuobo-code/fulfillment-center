package com.mallcai.fulfillment.infrastructure.object.tms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DeliveryRecordPo implements Serializable {
    private Long id;

    private String commandId;

    private String commandType;

    private Integer storeId;

    private String waybillNo;

    private Date pickupDate;

    private Long totalCnt;

    private BigDecimal totalVolume;

    private BigDecimal totalWeight;

    private String wmsParams;

    private String wmsRsp;

    private String status;

    private Date wmsReqTime;

    private Date transPlanTime;

    private Date transCarTime;

    private Date transResTime;

    private Date createTime;

    private String createBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId == null ? null : commandId.trim();
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType == null ? null : commandType.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo == null ? null : waybillNo.trim();
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Long getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Long totalCnt) {
        this.totalCnt = totalCnt;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getWmsParams() {
        return wmsParams;
    }

    public void setWmsParams(String wmsParams) {
        this.wmsParams = wmsParams == null ? null : wmsParams.trim();
    }

    public String getWmsRsp() {
        return wmsRsp;
    }

    public void setWmsRsp(String wmsRsp) {
        this.wmsRsp = wmsRsp == null ? null : wmsRsp.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getWmsReqTime() {
        return wmsReqTime;
    }

    public void setWmsReqTime(Date wmsReqTime) {
        this.wmsReqTime = wmsReqTime;
    }

    public Date getTransPlanTime() {
        return transPlanTime;
    }

    public void setTransPlanTime(Date transPlanTime) {
        this.transPlanTime = transPlanTime;
    }

    public Date getTransCarTime() {
        return transCarTime;
    }

    public void setTransCarTime(Date transCarTime) {
        this.transCarTime = transCarTime;
    }

    public Date getTransResTime() {
        return transResTime;
    }

    public void setTransResTime(Date transResTime) {
        this.transResTime = transResTime;
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
        sb.append(", commandId=").append(commandId);
        sb.append(", commandType=").append(commandType);
        sb.append(", storeId=").append(storeId);
        sb.append(", waybillNo=").append(waybillNo);
        sb.append(", pickupDate=").append(pickupDate);
        sb.append(", totalCnt=").append(totalCnt);
        sb.append(", totalVolume=").append(totalVolume);
        sb.append(", totalWeight=").append(totalWeight);
        sb.append(", wmsParams=").append(wmsParams);
        sb.append(", wmsRsp=").append(wmsRsp);
        sb.append(", status=").append(status);
        sb.append(", wmsReqTime=").append(wmsReqTime);
        sb.append(", transPlanTime=").append(transPlanTime);
        sb.append(", transCarTime=").append(transCarTime);
        sb.append(", transResTime=").append(transResTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }
}