package com.mallcai.fulfillment.pe.deploy.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.WareTransInfo;
import com.mallcai.fulfillment.pe.biz.service.bo.AnalysisAggregate;
import com.mallcai.fulfillment.pe.biz.service.impl.query.TransDetailServiceImpl;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderFileService;
import com.mallcai.fulfillment.pe.biz.service.service.FrzonOrderSumService;
import com.mallcai.fulfillment.pe.biz.service.task.OrderAggreToFileTask;
import com.mallcai.fulfillment.promise.api.req.PickProdTimeReq;
import com.mallcai.fulfillment.promise.api.req.WarehouseInfo;
import com.mallcai.fulfillment.promise.repository.PickProdTimeService;
import com.mallcai.fulfillment.promise.rule.rules.RuleEnginee;
import com.mallcai.fulfillment.promise.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

public class PromisRuleTest extends BaseTestCase{
    @Autowired
    RuleEnginee ruleEnginee;

    @Autowired
    PickProdTimeService pickProdTimeService;


    @Autowired
    OrderAggreToFileTask orderAggreToFileTask;


    @ConfigValue(key = "/fulfillment-rule/fulfillment-rule/fulfillment.period.buy")
    private JSONObject periodConfig;

    @Autowired
    TransDetailServiceImpl transDetailService;

    @Autowired
    FrzonOrderSumService frzonOrderSumService;

    @Test
    public void testfrzonOrderSumService(){
//        frzonOrderSumService.sumRecord();

        ImmutableList<Integer> stores = ImmutableList.of(15);
        PlainResult<List<WareTransInfo>> listPlainResult = transDetailService.standTransPlan(stores, DateUtils.startDate(new Date()));

        System.out.println(JSON.toJSONString(listPlainResult));

    }

    @Test
    public void testConfig(){

        ImmutableList<Integer> city = ImmutableList.of(30);

        PlainResult<List<WareTransInfo>> listPlainResult = transDetailService.fronzeTransPlan(city, DateUtils.startDate(new Date()));

        System.out.println(listPlainResult);


    }


    @Test
    public void testPickProdTime(){

        List<WarehouseInfo> house= Lists.newArrayList();
        house.add(new WarehouseInfo(1,1));
        house.add(new WarehouseInfo(2,2));

        PickProdTimeReq req = PickProdTimeReq.builder()
                .cityId(220)
                .warehouses(house)
                .storeId(10014)
                .categoryType(1)
                .attributeType(1)
                .goodsList(Arrays.asList(1l, 2l, 3l))
                .orderTime(new Date())
                .build();

        String result=pickProdTimeService.calcPickProdTime(req);

        System.out.println("------->"+result);

    }
    @Test
    public void test(){
        Set<String> params = ruleEnginee.getRuleParam("fulfillTime222");
        System.out.println("param --------->"+params);


        HashMap<String, String> param = Maps.newHashMap();
        param.put("cur_time","17:10:10");
        param.put("prod_group_one","1");

        System.out.println("res1 --------->"+ruleEnginee.patternRules(param, "fulfillTime"));

        param.put("cur_time","21:10:10");
        param.put("prod_group_one","2");

        System.out.println("res2 --------->"+ruleEnginee.patternRules(param, "fulfillTime"));


        param.put("cur_time","17:10:10");
        param.put("prod_group_one","2");

        System.out.println("res3 --------->"+ruleEnginee.patternRules(param, "fulfillTime"));

        


    }



    @Test
    public void testFileAggreOrder(){

        AnalysisAggregate config = AnalysisAggregate.builder()
                .pushTime(DateUtils.parseDate("2019-10-12"))
                .groupValue("2")
                .build();

        orderAggreToFileTask.execute(JSON.toJSONString(config));
    }


}
