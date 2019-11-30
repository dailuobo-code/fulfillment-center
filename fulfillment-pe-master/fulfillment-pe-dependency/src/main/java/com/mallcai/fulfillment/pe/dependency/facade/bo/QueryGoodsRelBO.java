package com.mallcai.fulfillment.pe.dependency.facade.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询商品货品信息入参
 * @author bh.zhong
 * @date 2019/10/10 11:13 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryGoodsRelBO {
    /**
     * 城市ID
     */
    private Integer cityId;
    /**
     * 城市商品id
     */
    private Integer cityProductId;

    @Override
    public boolean equals(Object bo) {
        QueryGoodsRelBO obj=(QueryGoodsRelBO) bo;
        return obj.cityId.equals(this.cityId) && obj.cityProductId.equals(this.cityProductId);
    }

    public int hashCode() {
        return cityProductId.hashCode();
    }

}
