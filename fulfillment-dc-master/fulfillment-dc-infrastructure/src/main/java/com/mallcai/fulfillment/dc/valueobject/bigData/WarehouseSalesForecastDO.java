package com.mallcai.fulfillment.dc.valueobject.bigData;

import java.io.Serializable;
import java.util.Date;
/**
 * @author: Liu Yang
 * @description 仓库采购预测/补采预测
 * @date: 2019-10-02 12:01
 */
public class WarehouseSalesForecastDO implements Serializable {
    private Long id;

    private Integer cityId;

    private Integer warehouseId;

    private Integer lv1ClassifyType;

    private Integer cityProductId;

    private Integer forecastQty;

    private Date forecastDate;

    private Byte forecastType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getLv1ClassifyType() {
        return lv1ClassifyType;
    }

    public void setLv1ClassifyType(Integer lv1ClassifyType) {
        this.lv1ClassifyType = lv1ClassifyType;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getForecastQty() {
        return forecastQty;
    }

    public void setForecastQty(Integer forecastQty) {
        this.forecastQty = forecastQty;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Byte getForecastType() {
        return forecastType;
    }

    public void setForecastType(Byte forecastType) {
        this.forecastType = forecastType;
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
        sb.append(", cityId=").append(cityId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", lv1ClassifyType=").append(lv1ClassifyType);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", forecastQty=").append(forecastQty);
        sb.append(", forecastDate=").append(forecastDate);
        sb.append(", forecastType=").append(forecastType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}