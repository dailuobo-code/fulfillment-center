package com.mallcai.fulfillment.pe.biz.service.gray.impl;

import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.biz.service.gray.SwitchService;
import org.springframework.stereotype.Service;

/**
 * @description: 开关实现类
 * @author: chentao
 * @create: 2019-10-17 22:58:28
 */
@Service
public class SwitchServiceImpl implements SwitchService {

    @ConfigValue(key = "/app_fulfillment/common/distributed.aggregate.switch")
    private String distributedAggregateSwitch;

    @Override
    public boolean isDistributedAggregateOpened() {

        return Boolean.parseBoolean(distributedAggregateSwitch);
    }
}
