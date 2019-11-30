package com.mallcai.fulfillment.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.biz.service.DcCommondExecService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import com.mallcai.fulfillment.deploy.Application;
import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapperExtend;
import com.mallcai.wms.common.base.CommonResult;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDTO;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDetailDTO;
import com.mallcai.wms.oub.client.dto.TmsOrderDTO;
import com.mallcai.wms.oub.client.dto.TmsSkuDTO;
import com.mallcai.wms.oub.client.enums.OutboundBillTypeEnum;
import com.mallcai.wms.oub.client.service.TrafficPlanService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WmsTest {

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Autowired
    private DcCommondExecService dcCommondExecService;

//    @Reference(url = "dubbo://10.111.28.16:23240/com.mallcai.wms.oub.client.service.TrafficPlanService",version = "1.0",timeout = 30000)
    @Reference(url = "dubbo://10.111.28.25:23240/com.mallcai.wms.oub.client.service.TrafficPlanService",version = "1.0",timeout = 30000)
    private TrafficPlanService trafficPlanService;

//    @Reference(url = "dubbo://172.16.208.23:23001/com.mallcai.fulfillment.pe.api.service.query.TransDetailService")
//    private TransDetailService transDetailService;



    @ConfigValue(key="/app_fulfillment/common/tms.data.config")
    private Map<String,Object> tmsConfig;

    @Autowired
    DeliveryRecordPoMapperExtend deliveryRecordPoMapperExtend;


    @Test
    public void testRecord(){

        int i = deliveryRecordPoMapperExtend.updateTransPlanByWaveNo(new Date(), ImmutableList.of("prod-FW005-1-20-2019-11-19"));
        System.out.println("--->"+i);

    }


    @Test
    public void testFlow(){

        DispatchCommandMsg msg=new DispatchCommandMsg();
        msg.setCommandType(CommandTypeEnum.TMS_BOOKING_CAR_18);
        msg.setCommandNo("123abcdefg");
        msg.setData("{\"storeId\":\"8\"}");

        dcCommondExecService.execDataCommond(msg);
    }


    @Test
    public void testWmsTest(){
       String waveNumber="product-LA_SX-48001-20-2019-11-17";
        TmsOrderDTO order =new TmsOrderDTO();
        order.setReceiverAreaCode("48001");
        order.setReceiverName("李阳");
        order.setReceiverPhone("4009600960");
        // 发货方信息
        order.setShipperAreaCode("LA_SX");
        order.setShipperName("六安生鲜仓");
        order.setDispatchPointName("LA_SX");
        // 组成单号
        order.setWaybillNO(waveNumber);


        //货物信息
        List<TmsSkuDTO> skuList = Lists.newArrayList();
        TmsSkuDTO tmsSkuVO = new TmsSkuDTO();
        tmsSkuVO.setGoodsNo("0000");
        tmsSkuVO.setGoodsName("其他");
        //单位是吨 所以需要除以1000
        tmsSkuVO.setTotalWeight(new BigDecimal("0.137254"));
        tmsSkuVO.setTotalVolume(BigDecimal.ONE);
        // 之前备注里放的是吨数 现在放一个默认值
        order.setWaybillRemark("1");
        tmsSkuVO.setQty(25);
        skuList.add(tmsSkuVO);
        order.setGoodsList(skuList);


        CommonResult<Boolean> booleanCommonResult = trafficPlanService.sendDeliveryInfoToTms(order, waveNumber);
        System.out.println(booleanCommonResult);
    }


}
