package com.mallcai.fulfillment.pe.biz.service.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.exception.NacosException;
import com.mallcai.framework.api.plugin.annotation.ConfigInfo;
import com.mallcai.framework.api.plugin.sdk.ConfigSdkUtil;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service("fulfillRuleService")
@Slf4j
@Import(ConfigInfo.class)
public class FulfillRuleHandle {

    @Autowired
    private ConfigSdkUtil configSdkUtil;


    //新增，修改配置
    public boolean saveBusiRule(String dataId,  Object rule) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("dataId", dataId); //配置标示
        paramMap.put("group", Constants.Rule.GROUPNAME); //
        paramMap.put("tenant", Constants.Rule.NAMESPACE);
        paramMap.put("type", Constants.Rule.RULETYPE);

        String ruleStr = JSON.toJSONString(rule);
        paramMap.put("content", ruleStr);

        try {
            configSdkUtil.execute(paramMap, null, "POST", null);
            return true;
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("保存nacos配置信息错误，dataId:{},groupId:{},ruleInfo:{}",dataId,Constants.Rule.GROUPNAME,ruleStr);
        }
        return false;
    }

    //查询配置
    public <T> T getBusiRule(String dataId,Class<T> clazz) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("dataId", dataId); //配置标示
        paramMap.put("group", Constants.Rule.GROUPNAME);
        paramMap.put("tenant", Constants.Rule.NAMESPACE);

        try {
           String result= configSdkUtil.execute(paramMap, null, "GET", null);
           return JSONObject.parseObject(result,clazz);
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("读取nacos配置信息错误，dataId:{},groupId:{}",dataId,Constants.Rule.GROUPNAME);
        }
        return null;
    }

    //删除配置
    public boolean deleteBusiRule(String dataId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("dataId", dataId); //配置标示
        paramMap.put("group", Constants.Rule.GROUPNAME);
        paramMap.put("tenant", Constants.Rule.NAMESPACE);

        try {
            String result= configSdkUtil.execute(paramMap, null, "DELETE", null);
            return true;
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("读取nacos配置信息错误，dataId:{},groupId:{}",dataId,Constants.Rule.GROUPNAME);
        }
        return false;
    }

    /**
     * 自动生成cron表达式
     * 限制时间 begHour -endHour 最大 0-23
     * 执行频率
     * 频率单位 HOUR/MIN
     * @return
     */
    public String genaratorCrobString(Integer begHour, Integer endHour, TimeUnit unit,Integer radio){
        if(begHour > 0 && begHour < endHour && begHour < 24){

        }else{
            throw new BizException(String.format("业务参数不符合要求,begHour:{},endHour:{}",begHour,endHour));
        }

        if(!unit.equals(TimeUnit.HOURS) && !unit.equals(TimeUnit.MINUTES)){
            throw new BizException("业务参数不符合要求,间隔单位近支持分钟，小时");
        }
        radio =radio == 0?1:radio;
        if(unit.equals(TimeUnit.MINUTES) && 60%radio>0){
            throw new BizException("业务参数不符合要求,间隔单位为分钟，执行频率仅支持60的因子");
        }

        if(unit.equals(TimeUnit.HOURS) && radio>(endHour-begHour)){
            throw new BizException("业务参数不符合要求,间隔单位为小时，执行频率需要在执行时间内");
        }

        if(unit.equals(TimeUnit.MINUTES)){
            return String.format("0 0/%d %d-%d * * ? *",radio,begHour,endHour);
        }

        if(unit.equals(TimeUnit.HOURS)){
            List<Integer> hours=new ArrayList<>();
            int execHour=begHour+radio;
            while(execHour<=endHour){
                hours.add(execHour);
                execHour +=radio;
            }

            return String.format("0 0 %s * * ? *",radio,hours.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
        return null;
    }


}
