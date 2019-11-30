package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description city相关
 * @date 2019-07-15
 */
@Data
public class CityDTO implements Serializable {

    private static final long serialVersionUID = -3119995925878797323L;
    /**
     * 城市Id
     */
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;
}
