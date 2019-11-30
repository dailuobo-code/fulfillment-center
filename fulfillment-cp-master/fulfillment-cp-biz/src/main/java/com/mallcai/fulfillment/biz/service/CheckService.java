package com.mallcai.fulfillment.biz.service;

import com.mallcai.fulfillment.biz.check.Checker;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckService {

    @Autowired
    ApplicationContext applicationContext;


    public void checkData(CheckContext context){
        log.info("即将开始进行对比数据填充...checkNo={}",context.getCheckNo());

        TaskConfig config = context.getConfig();
        // find dataIndex
        Checker checker = applicationContext.getBean(config.getCheckBean(), Checker.class);
        if(!context.getResult().isEmpty()){
            checker.check(context);
        }
        log.info("对比数据填充完成...checkNo={}",context.getCheckNo());
    }
}
