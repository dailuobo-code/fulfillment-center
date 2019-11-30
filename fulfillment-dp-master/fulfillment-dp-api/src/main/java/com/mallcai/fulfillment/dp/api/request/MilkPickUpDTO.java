package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 取奶还瓶DTO
 * @author zhanghao
 * @date 2019-08-16 14:58:22
 */
@ToString
@Data
public class MilkPickUpDTO implements Serializable {
    private static final long serialVersionUID = -7906327187012951418L;

    private Integer userId;

    private Integer Id;

    private Integer returnNum;
}
