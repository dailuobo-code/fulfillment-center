package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateQueryConf;
import com.mallcai.fulfillment.pe.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import com.mallcai.fulfillment.pe.biz.service.task.OrderAggregationTask;
import com.mallcai.fulfillment.pe.biz.service.task.OrderPushTask;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.deploy.test.BaseTestCase;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import com.mallcai.fulfillment.promise.configcenter.ConfigCenter;
import com.mallcai.fulfillment.promise.configcenter.ConfigReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 17:46:23
 */
public class AgreegateOrderTest extends BaseTestCase {

    @Autowired
    private OrderAggregationTask orderAggregationTask;

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Autowired
    ConfigCenter configCenter;

    @Autowired
    OrderDOMapperExtend orderDOMapperExtend;

    @Autowired
    OrderPushTask orderPushTask;

    @Autowired
    GoodsGrayRouterService goodsGrayRouterService;

    @Test
    public void testGoodRoute(){

        boolean needRoute = goodsGrayRouterService.isNeedRoute(28);
        System.out.println("first-->"+needRoute);

        System.out.println("---------------------------------------------");

        needRoute = goodsGrayRouterService.isNeedRoute(28);
        System.out.println("second-->"+needRoute);







    }



    @Test
    public void FillmentPushDay() {

        ConfigReq req = ConfigReq.builder().categoryType(1).build();

        Integer fulfillTimeLine = configCenter.getFulfillTimeLine(req);

        System.out.println("--->"+fulfillTimeLine);

    }



    @Test
    public void pushOrder() throws Exception{

        orderPushTask.execute("123");

    }

    @Test
    public void testTask() throws Exception{

        AggregateQueryConf config =new AggregateQueryConf();
        config.setCities(ImmutableList.of(30));
        config.setWarehouses(ImmutableList.of(25));
        config.setGroupValue("1");
        String confStr= JSON.toJSONString(config);

        orderAggregationTask.execute(confStr);
    }


    @Test
    public void testMapper(){

        //每次取数都是一天的数据
        OrderDOCriteria orderCriteria =new  OrderDOCriteria();
        OrderDOCriteria.Criteria criteria = orderCriteria.createCriteria();
        criteria.andExpectPushTimeGreaterThanOrEqualTo(DateUtil.parseDateTime("2019-10-12 00:00:00"));
        criteria.andExpectPushTimeLessThanOrEqualTo(DateUtil.parseDateTime("2019-10-12 23:59:59"));
        criteria.andStatusEqualTo(OrderStatusEnum.INIT.getStatus());
        criteria.andGroupValueEqualTo("1");
        criteria.andCityIdIn(ImmutableList.of(30));


        List<Long> longs = orderDOMapperExtend.selectIdListByExample(orderCriteria);

        System.out.println(longs.size());

    }


    @Test
    public void testMapper1(){


        List<OrderDO> orderDOS = orderDOMapperExtend.selectByIds(ImmutableList.of(35l, 36l, 37l, 38l, 39l));

        System.out.println(orderDOS.size());

    }



    @Test
    public void testAggreOrder(){


        Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> orderAggregationTypeEnumMapMap = aggreOrderQueryService.aggregateById(ImmutableList.of(35l, 36l, 37l, 38l, 39l));

        System.out.println(orderAggregationTypeEnumMapMap);

    }


    public static void main(String[] args) {
        AggregateQueryConf config =new AggregateQueryConf();
        config.setCities(ImmutableList.of(30));
        config.setWarehouses(ImmutableList.of(25));
        config.setGroupValue("1");
        String confStr= JSON.toJSONString(config);

        System.out.println(confStr);
    }

}
