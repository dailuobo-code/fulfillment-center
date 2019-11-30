package com.mallcai.fulfillment.pe.biz.service.task;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.biz.service.bo.AnalysisAggregate;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderFileService;
import com.mallcai.fulfillment.pe.biz.service.service.FrzonOrderSumService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@JobHandler(value = "FrozenOrderSumTask")
@Slf4j
@Component
public class FrozenOrderSumTask extends IJobHandler {

    @Autowired
    private Environment environment;

    @Autowired
    private FrzonOrderSumService frzonOrderSumService;


    @Override
    public ReturnT<String> execute(String param){


        log.info("开始执行冻品数据采集任务[FrozenOrderSumTask]，当前执行环境:{},集单参数:{}", environment.getActiveProfiles()[0],param);
        long beg=System.currentTimeMillis();
        frzonOrderSumService.sumRecord();
        log.info("冻品数据采集任务[FrozenOrderSumTask]执行完成,，耗时:{}ms", System.currentTimeMillis()-beg);

        return SUCCESS;
    }
}
