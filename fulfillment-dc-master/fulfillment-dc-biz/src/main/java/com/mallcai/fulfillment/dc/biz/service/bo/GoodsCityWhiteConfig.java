package com.mallcai.fulfillment.dc.biz.service.bo;

import lombok.Data;

/**
 * @description: 货品灰度城市白名单配置
 * @author: chentao
 * @create: 2019-10-13 15:38:23
 */
@Data
public class GoodsCityWhiteConfig {

    /**
     * 城市id
     */
    Integer cityId;

    /**
     * 生效时间
     */
    String effectTime;
}
