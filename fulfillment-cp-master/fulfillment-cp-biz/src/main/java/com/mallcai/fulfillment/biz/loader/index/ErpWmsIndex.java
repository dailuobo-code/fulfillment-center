package com.mallcai.fulfillment.biz.loader.index;

import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("erpWmsIndex")
@Slf4j
public class ErpWmsIndex implements DataIndex {

    @Override
    public List<String> findIndex(CheckContext context, int start, int pageSize) {
        return null;
    }

    @Override
    public int getTotalCount(CheckContext context) {
        return 0;
    }
}
