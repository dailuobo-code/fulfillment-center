package com.mallcai.fulfillment.biz.dubbo.impl;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service(registry = "global")
public class DemoServiceImpl implements DemoService {


    @Override
    public PlainResult<?> demoMethod(String method) {
        System.out.println("success");
        return PlainResult.ok();
    }
}