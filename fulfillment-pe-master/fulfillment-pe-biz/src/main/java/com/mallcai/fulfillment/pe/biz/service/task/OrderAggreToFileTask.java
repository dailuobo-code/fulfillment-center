package com.mallcai.fulfillment.pe.biz.service.task;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.biz.service.bo.AnalysisAggregate;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderFileService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@JobHandler(value = "OrderAggreToFileTask")
@Slf4j
@Component
public class OrderAggreToFileTask extends IJobHandler {

    @Autowired
    private Environment environment;

    @Autowired
    private AggreOrderFileService aggreOrderFileService;


    @Override
    public ReturnT<String> execute(String param){

        log.info("开始执行文件集单任务[OrderAggreToFileTask]，当前执行环境:{},集单参数:{}", environment.getActiveProfiles()[0],param);
        AnalysisAggregate analysisConfig = JSONObject.parseObject(param, AnalysisAggregate.class);

        aggreOrderFileService.AggreXlsFile(analysisConfig);

        return SUCCESS;
    }
}
