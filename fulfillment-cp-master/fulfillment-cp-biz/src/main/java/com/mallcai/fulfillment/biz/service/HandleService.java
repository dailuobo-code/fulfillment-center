package com.mallcai.fulfillment.biz.service;

import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.reshandle.ResultHandler;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HandleService {

    @Autowired
    ApplicationContext applicationContext;


    public void handle(CheckContext context){

        TaskConfig config = context.getConfig();
        // find dataIndex
        ResultHandler handler = applicationContext.getBean(config.getResultBean(), ResultHandler.class);
        handler.handleResult(context);
        //数据处理完成后，将大对象清空掉
        context.getKeyToSourceTable().clear();
        context.getResult().clear();
    }
}
