package com.mallcai.fulfillment.biz.service;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NacosConfigService {

    @ConfigValue(key = "/fulfillment-rule/fulfillment-rule/fulfillment.center")
    private JSONObject beanConfig;



    public Map<String,Object> getBeanParam(String beanName){
        return (Map<String,Object>)beanConfig.get(beanName);
    }
}
