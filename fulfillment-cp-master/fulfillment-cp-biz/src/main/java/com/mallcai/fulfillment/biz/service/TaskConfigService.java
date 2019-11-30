package com.mallcai.fulfillment.biz.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.biz.object.bo.CheckConfigBo;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.infrastructure.mapper.cp.TaskConfigMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.manager.common.exception.BizException;
import com.mallcai.manager.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
public class TaskConfigService {

    @Autowired
    TaskConfigMapperExtend taskConfigMapperExtend;

    @Autowired
    RedisIdFactory redisIdFactory;


    public CheckContext buildContext(CheckConfigBo bo){
        log.info("即将开始初始化对账上下文,checkConfigBo:{}", JSON.toJSONString(bo));
        TaskConfig taskConfig = taskConfigMapperExtend.selectByBiztag(bo.getBizTag());
        if(taskConfig==null){
            throw new BizException(String.format("cp_task_config 没有对应的对账配置,bizTag:%s",bo.getBizTag()));
        }


        CheckContext context =new CheckContext();
        context.setBizTag(bo.getBizTag());
        context.setBizParam(bo.getBizParam());
        context.setConfig(taskConfig);

        //生成订单号
        String checkNo=String.format("CP%s%010d", DateUtil.formatLDate(new Date()),redisIdFactory.getIdByDay(Constants.CP_NO_KEY));
        context.setCheckNo(checkNo);
        context.setCheckTime(new Date());

        context.setResult(HashBasedTable.create());
        context.setKeyToSourceTable(HashBasedTable.create());
        context.setErrorKeyList(new ArrayList<>());
        return context;
    }

}
