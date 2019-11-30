package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FrozenGoodSumPo implements Serializable {
    private Long id;

    private Integer cityId;

    private Integer storeId;

    private Integer goodId;

    private BigDecimal goodSum;

    private String sumDate;

    private Date createTime;

    private Integer cityProductId;

    private BigDecimal productSum;

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getGoodSum() {
        return goodSum;
    }

    public void setGoodSum(BigDecimal goodSum) {
        this.goodSum = goodSum;
    }

    public String getSumDate() {
        return sumDate;
    }

    public void setSumDate(String sumDate) {
        this.sumDate = sumDate == null ? null : sumDate.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public BigDecimal getProductSum() {
        return productSum;
    }

    public void setProductSum(BigDecimal productSum) {
        this.productSum = productSum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityId=").append(cityId);
        sb.append(", storeId=").append(storeId);
        sb.append(", goodId=").append(goodId);
        sb.append(", goodSum=").append(goodSum);
        sb.append(", sumDate=").append(sumDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", productSum=").append(productSum);
        sb.append("]");
        return sb.toString();
    }
}