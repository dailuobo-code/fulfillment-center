package com.mallcai.fulfillment.infrastructure.object.tms;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransportPlanPo implements Serializable {
    private Long id;

    private String planNo;

    private String planName;

    private String dispatchOrgCode;

    private String dispatchOrgName;

    private String vehicleNo;

    private String driverName;

    private String driverPhone;

    private Date sendCar;

    private String vehicleType;

    private Integer transportType;

    private BigDecimal loadOfVehicle;

    private BigDecimal cubeOfVehicle;

    private String remark;

    private String transportCompany;

    private Boolean complete;

    private Date completeTime;

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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getDispatchOrgCode() {
        return dispatchOrgCode;
    }

    public void setDispatchOrgCode(String dispatchOrgCode) {
        this.dispatchOrgCode = dispatchOrgCode == null ? null : dispatchOrgCode.trim();
    }

    public String getDispatchOrgName() {
        return dispatchOrgName;
    }

    public void setDispatchOrgName(String dispatchOrgName) {
        this.dispatchOrgName = dispatchOrgName == null ? null : dispatchOrgName.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public Date getSendCar() {
        return sendCar;
    }

    public void setSendCar(Date sendCar) {
        this.sendCar = sendCar;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public BigDecimal getLoadOfVehicle() {
        return loadOfVehicle;
    }

    public void setLoadOfVehicle(BigDecimal loadOfVehicle) {
        this.loadOfVehicle = loadOfVehicle;
    }

    public BigDecimal getCubeOfVehicle() {
        return cubeOfVehicle;
    }

    public void setCubeOfVehicle(BigDecimal cubeOfVehicle) {
        this.cubeOfVehicle = cubeOfVehicle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany == null ? null : transportCompany.trim();
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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
        sb.append(", planName=").append(planName);
        sb.append(", dispatchOrgCode=").append(dispatchOrgCode);
        sb.append(", dispatchOrgName=").append(dispatchOrgName);
        sb.append(", vehicleNo=").append(vehicleNo);
        sb.append(", driverName=").append(driverName);
        sb.append(", driverPhone=").append(driverPhone);
        sb.append(", sendCar=").append(sendCar);
        sb.append(", vehicleType=").append(vehicleType);
        sb.append(", transportType=").append(transportType);
        sb.append(", loadOfVehicle=").append(loadOfVehicle);
        sb.append(", cubeOfVehicle=").append(cubeOfVehicle);
        sb.append(", remark=").append(remark);
        sb.append(", transportCompany=").append(transportCompany);
        sb.append(", complete=").append(complete);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append("]");
        return sb.toString();
    }
}