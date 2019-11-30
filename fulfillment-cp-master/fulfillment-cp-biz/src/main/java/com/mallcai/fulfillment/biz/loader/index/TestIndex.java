package com.mallcai.fulfillment.biz.loader.index;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("TestIndex")
@Slf4j
public class TestIndex implements DataIndex {
    @Override
    public List<String> findIndex(CheckContext context,int start,int pageSize) {
        log.info("INDEX--->get params:{}", JSON.toJSONString(context.getBizParam()));

        return ImmutableList.of("1231","1232","1233","1234");
    }

    @Override
    public int getTotalCount(CheckContext context) {
        return 0;
    }
}
