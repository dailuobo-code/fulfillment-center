package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.Data;

import java.util.List;

/**
 * @description: 货品灰度白名单
 * @author: chentao
 * @create: 2019-10-13 16:04:07
 */
@Data
public class GoodsWhiteConfig {

    private boolean isAllCitys;

    private List<GoodsCityWhiteConfig> citys;
}
