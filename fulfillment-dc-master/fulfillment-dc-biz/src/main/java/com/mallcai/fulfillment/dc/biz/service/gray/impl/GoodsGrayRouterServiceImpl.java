package com.mallcai.fulfillment.dc.biz.service.gray.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.dc.biz.service.bo.GoodsCityWhiteConfig;
import com.mallcai.fulfillment.dc.biz.service.bo.GoodsWhiteConfig;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @description: 灰度路由实现类
 * @author: chentao
 * @create: 2019-10-09 21:41:49
 */
@Component
public class GoodsGrayRouterServiceImpl implements GoodsGrayRouterService {

    @ConfigValue(key = "/global/system-gray/goods.city.white.config")
    private JSONObject goodsGrayConfig;

    @Override
    public boolean isNeedRoute(Integer cityId) {

        GoodsWhiteConfig goodsWhiteConfig = JSON.toJavaObject(goodsGrayConfig, GoodsWhiteConfig.class);

        if (goodsWhiteConfig.isAllCitys()){

            return true;
        }

        List<Integer> allOpenCityIds = getAllOpenCityIds(goodsWhiteConfig.getCitys());

        if (allOpenCityIds.contains(cityId)){

            return true;
        }

        return false;
    }

    private List<Integer> getAllOpenCityIds(List<GoodsCityWhiteConfig> cityConfigs){

        List<Integer> citys = new ArrayList<>();

        for (GoodsCityWhiteConfig goodsCityWhiteConfig : cityConfigs){

            Date now = new Date();
            Date effectiveDate = DateUtil.parseDate(goodsCityWhiteConfig.getEffectTime());

            if (now.compareTo(effectiveDate) > 0){

                citys.add(goodsCityWhiteConfig.getCityId());
            }
        }

        return citys;
    }
}
