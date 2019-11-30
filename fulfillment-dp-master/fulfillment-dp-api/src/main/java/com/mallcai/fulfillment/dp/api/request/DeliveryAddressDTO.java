package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 配送地址信息
 * @author bh.zhong
 * @date 2019/9/6 1:56 PM
 */
@Data
public class DeliveryAddressDTO implements Serializable {
    /**
     * 省  例子：浙江省
     */
    private String province;
    /**
     * 市  例子：杭州市
     */
    private String city;
    /**
     * 区县 例子：西湖区
     */
    private String county;
    /**
     * 详细地址 例子：文一西路益展大厦
     */
    private String addressDetail;
}
