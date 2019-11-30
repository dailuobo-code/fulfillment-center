package com.mallcai.fulfillment.gateway.biz.service.impl;

import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gaoguoming
 * @date 2019-11-13 10:27:59
 */
@Service("routerCityService")
public class RouterCityService {
    @ConfigValue(key = "/global/gateway/cityRouter")
    private Properties cityRouter;

    public Set<Integer> getAllCity() {
        return cityRouter.keySet().stream().map(key -> Integer.parseInt((String) key)).collect(Collectors.toSet());
    }
}
