package com.mallcai.fulfillment.biz.mallcaijob;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.biz.object.bo.CheckConfigBo;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.service.*;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@JobHandler(value = "erpWmsCheckTask")
@Slf4j
@Component
public class ErpWmsCheckTask extends IJobHandler {


    @Autowired
    private Environment environment;

    @Autowired
    private TaskConfigService taskConfigService;

    @Autowired
    private ErpWmsLoaderService erpWmsLoaderService;

    @Autowired
    private CheckService checkService;

    @Autowired
    private CheckRecordService checkRecordService;

    @Autowired
    private HandleService handleService;


    @Override
    public ReturnT<String> execute(String param) {

        CheckConfigBo config = JSON.parseObject(param, CheckConfigBo.class);
        log.info("开始执行对账任务[{}]，当前执行环境:{},对账参数:{}", config.getBizTag(), environment.getActiveProfiles()[0], config.getBizParam());

        //1. 构建context
        CheckContext checkContext = taskConfigService.buildContext(config);

        checkRecordService.initRecord(checkContext);

        //2. load  data
        erpWmsLoaderService.loadCheckDataAndProcess(checkContext);

        //3. check
        checkService.checkData(checkContext);
        //不需要更新redis
        checkContext.setIfNeedUpdateRedis(false);

        //4. record
        checkRecordService.dealRecord(checkContext);

        //5. handle result
        handleService.handle(checkContext);


        return SUCCESS;
    }
}
