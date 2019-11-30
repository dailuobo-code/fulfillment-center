package com.mallcai.fulfillment.pe.biz.service.gray.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.bo.GoodsCityWhiteConfig;
import com.mallcai.fulfillment.pe.biz.service.bo.GoodsWhiteConfig;
import com.mallcai.fulfillment.pe.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 灰度路由实现类
 * @author: chentao
 * @create: 2019-10-09 21:41:49
 */
@Slf4j
@Component
public class GoodsGrayRouterServiceImpl implements GoodsGrayRouterService {

    @ConfigValue(key = "/global/system-gray/goods.city.white.config")
    private JSONObject goodsGrayConfig;

    @Override
    public boolean isNeedRoute(Integer cityId) {

        log.info("货品灰度路由执行开始,cityId:{}, config:{}", cityId, goodsGrayConfig.toJSONString());

        GoodsWhiteConfig goodsWhiteConfig = JSON.toJavaObject(goodsGrayConfig, GoodsWhiteConfig.class);

        if (goodsWhiteConfig.isAllCitys()){

            return true;
        }

        List<Integer> allOpenCityIds = getAllOpenCityIds(goodsWhiteConfig.getCitys());

        log.info("货品灰度开放城市cityIds:{}", StringUtils.join(allOpenCityIds, Constants.SYMBOL_COMMA));

        if (allOpenCityIds.contains(cityId)){

            return true;
        }

        return false;
    }

    @Override
    public boolean isAllOpen() {
        GoodsWhiteConfig goodsWhiteConfig = JSON.toJavaObject(goodsGrayConfig, GoodsWhiteConfig.class);
        return goodsWhiteConfig.isAllCitys();
    }

    @Override
    public List<Integer> getAllCities() {
        GoodsWhiteConfig goodsWhiteConfig = JSON.toJavaObject(goodsGrayConfig, GoodsWhiteConfig.class);
        return getAllOpenCityIds(goodsWhiteConfig.getCitys());
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
