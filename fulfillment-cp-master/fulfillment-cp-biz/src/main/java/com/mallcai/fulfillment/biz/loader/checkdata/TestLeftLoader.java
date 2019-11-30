package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.loader.Loader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("TestLeftLoader")
@Slf4j
public class TestLeftLoader implements Loader {
    @Override
    public Map<String, Integer> loadResource(CheckContext checkContext) {
        List<String> indexs =checkContext.getIndex();
        log.info("LEFT-LOADER-->get index:{}", JSON.toJSONString(indexs));

        Map<String,Integer> result= Maps.newHashMap();
        result.put("prod1",23);
        result.put("prod2",24);
        result.put("prod3",25);
        result.put("prod4",26);
        result.put("prod5",27);
        return result;
    }
}
