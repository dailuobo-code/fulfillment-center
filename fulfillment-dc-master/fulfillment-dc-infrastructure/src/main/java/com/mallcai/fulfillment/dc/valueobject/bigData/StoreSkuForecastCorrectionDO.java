package com.mallcai.fulfillment.dc.valueobject.bigData;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author: Liu Yang
 * @description 分拣预测
 * @date: 2019-10-02 12:01
 */
@Data
public class StoreSkuForecastCorrectionDO implements Serializable {
    private Long id;

    private Integer cityId;

    private Integer warehouseId;

    private Integer storeId;

    private Integer cityProductId;

    private BigDecimal productForecastNum;

    private Date caluDate;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cityId=").append(cityId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", storeId=").append(storeId);
        sb.append(", cityProductId=").append(cityProductId);
        sb.append(", productForecastNum=").append(productForecastNum);
        sb.append(", caluDate=").append(caluDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}