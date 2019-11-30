package com.mallcai.fulfillment.dc.biz.service.bo;

import java.util.List;
import lombok.Data;

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
