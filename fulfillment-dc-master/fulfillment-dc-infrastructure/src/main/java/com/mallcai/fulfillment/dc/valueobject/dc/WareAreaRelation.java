package com.mallcai.fulfillment.dc.valueobject.dc;

import java.io.Serializable;
import java.util.Date;

public class WareAreaRelation implements Serializable {
    private Long id;

    private Integer warehouseId;

    private String warehouseName;

    private Integer warehouseType;

    private String areaList;

    private String gpsLongitude;

    private String gpsDimension;

    private Date effectTime;

    private Byte status;

    private String createBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getAreaList() {
        return areaList;
    }

    public void setAreaList(String areaList) {
        this.areaList = areaList == null ? null : areaList.trim();
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude == null ? null : gpsLongitude.trim();
    }

    public String getGpsDimension() {
        return gpsDimension;
    }

    public void setGpsDimension(String gpsDimension) {
        this.gpsDimension = gpsDimension == null ? null : gpsDimension.trim();
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", warehouseType=").append(warehouseType);
        sb.append(", areaList=").append(areaList);
        sb.append(", gpsLongitude=").append(gpsLongitude);
        sb.append(", gpsDimension=").append(gpsDimension);
        sb.append(", effectTime=").append(effectTime);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}