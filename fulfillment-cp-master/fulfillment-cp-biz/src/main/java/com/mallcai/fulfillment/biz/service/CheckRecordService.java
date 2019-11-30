package com.mallcai.fulfillment.biz.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.RecordStatusEnum;
import com.mallcai.fulfillment.infrastructure.mapper.cp.RecordResultMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.cp.TaskConfigMapperExtend;
import com.mallcai.fulfillment.infrastructure.mapper.cp.TaskRecordMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.cp.RecordResult;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskConfig;
import com.mallcai.fulfillment.infrastructure.object.cp.TaskRecord;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.manager.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CheckRecordService {

    @Resource
    TaskRecordMapperExtend taskRecordMapperExtend;

    @Resource
    TaskConfigMapperExtend taskConfigMapperExtend;

    @Resource
    RecordResultMapperExtend recordResultMapperExtend;

    @Resource
    RedisService redisService;


    public void initRecord(CheckContext context){
        log.info("即将初始化对账任务，更新检查时间...checkNo={}",context.getCheckNo());
        TaskRecord record=new TaskRecord();
        record.setBizTag(context.getBizTag());
        record.setCheckNo(context.getCheckNo());
        record.setCheckTime(context.getCheckTime());
        record.setCheckParam(JSON.toJSONString(context.getBizParam()));
        record.setStatus(RecordStatusEnum.INIT.name());

        taskRecordMapperExtend.insertSelective(record);

        TaskConfig config = context.getConfig();
        config.setLastCheckTime(context.getCheckTime());
        taskConfigMapperExtend.updateByPrimaryKeySelective(config);
    }

    public void dealRecord(CheckContext context){
        log.info("即将开始进行对比记录保存...checkNo={}",context.getCheckNo());

        Table<String,String,Object> table = context.getResult();
        if(!table.isEmpty()){
            //记录明细
            List<RecordResult> resultList = table.rowKeySet().stream()
                    .map(p -> {
                        RecordResult result = new RecordResult();
                        result.setCheckNo(context.getCheckNo());
                        result.setBizTag(context.getBizTag());
                        result.setCheckKey(p);
                        result.setLeftValue(table.get(p, Constants.TBL_LEFT_V)==null?"0":String.valueOf(table.get(p, Constants.TBL_LEFT_V)));
                        result.setRightValue(table.get(p, Constants.TBL_RIGHT_V)==null?"0":String.valueOf(table.get(p, Constants.TBL_RIGHT_V)));
                        result.setIsPattern((Boolean) table.get(p, Constants.TBL_CHECK_RESULT));
                        return result;
                    }).collect(Collectors.toList());
            recordResultMapperExtend.batchInsertSelective(resultList);
        }

        TaskRecord taskRecord = taskRecordMapperExtend.selectByPrimaryKey(context.getCheckNo());
        taskRecord.setIndexSize(context.getTotalCount());
        taskRecord.setCheckSize(context.getResult().rowKeySet().size());

        int matchCnt=(int)table.rowKeySet().stream().filter(p->(Boolean)table.get(p, Constants.TBL_CHECK_RESULT)).count();
        int disMatchCnt=table.rowKeySet().size() - matchCnt;
        taskRecord.setMatchCnt(matchCnt);
        taskRecord.setDismatchCnt(disMatchCnt);
        taskRecord.setStatus(RecordStatusEnum.SUCC.name());
        int costTime = (int) ((System.currentTimeMillis() - context.getCheckTime().getTime()) /1000);
        taskRecord.setCostTime(costTime);

        taskRecordMapperExtend.updateByPrimaryKeySelective(taskRecord);

        //每次比对成功后，将本次结束时间更新到"上次成功对账"
        if(context.isIfNeedUpdateRedis()){
            redisService.set(Constants.REDIS_TRADE_SUCCESS_TIME, DateUtil.formatLDateTime(context.getEndTime()),Constants.REDIS_EXPIRE_TIME);
        }
        log.info("结果保存完成...checkNo={}",context.getCheckNo());
    }
}
