package com.mallcai.fulfillment.biz.reshandle;

import com.mallcai.fulfillment.biz.object.context.CheckContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("DefaultHandler")
@Slf4j
public class DefaultHandler implements ResultHandler{
    @Override
    public void handleResult(CheckContext checkContext) {
        log.info("do nothing at DefaultHandler !!");
    }
}
