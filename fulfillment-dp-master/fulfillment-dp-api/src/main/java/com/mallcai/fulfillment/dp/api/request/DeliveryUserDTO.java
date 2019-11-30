package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 配送用户信息  发货人信息  收货人信息
 * @author bh.zhong
 * @date 2019/9/6 2:05 PM
 */
@Data
public class DeliveryUserDTO implements Serializable {
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 用户id
     */
    private Integer userId;

}
