package com.mallcai.fulfillment.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.biz.service.DcCommondExecService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DispatchCommandMsg;
import com.mallcai.fulfillment.dc.common.enums.CommandTypeEnum;
import com.mallcai.fulfillment.deploy.Application;
import com.mallcai.fulfillment.pe.api.service.query.TransDetailService;
import com.mallcai.wms.common.base.CommonResult;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDTO;
import com.mallcai.wms.oub.client.dto.DeliveryOrderDetailDTO;
import com.mallcai.wms.oub.client.enums.OutboundBillTypeEnum;
import com.mallcai.wms.oub.client.service.TrafficPlanService;
import org.apache.dubbo.config.annotation.Reference;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import org.testng.collections.Lists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

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
    @Test
    public void testJedis(){
        //设置 key 和 失效时间(单位s)
        jedisClientUtil.setex("testKey1",100,"testValue");
        // 分布式锁使用方式,只能 key 不存在的时候,在设置 key 的值
        jedisClientUtil.set("testKey2","testValue", JedisClientUtil.SetPremise.NX,JedisClientUtil.ExpireType.Seconds,10);


        String testvalue = jedisClientUtil.get("testKey1");

        System.out.println(testvalue);

    }


    @Test
    public void Myconfig(){

        DispatchCommandMsg msg=new DispatchCommandMsg();
        msg.setCommandNo("123123123");
        msg.setCommandType(CommandTypeEnum.TMS_BOOKING_CAR_18);
        dcCommondExecService.execDataCommond(msg);
        System.out.println(tmsConfig);
    }


    @Test
    public void testRPC(){

        String tmp ="[{\n" +
                "                \"skuCode\":\"11118\",\n" +
                "                \"skuCount\":6,\n" +
                "                \"skuName\":\"小娃娃菜\",\n" +
                "                \"skuType\":\"FINISHED_PRODUCT\",\n" +
                "                \"skuUnit\":\"袋\",\n" +
                "                \"skuVolume\":0,\n" +
                "                \"skuWeight\":0\n" +
                "            },\n" +
                "            {\n" +
                "                \"skuCode\":\"13434\",\n" +
                "                \"skuCount\":3,\n" +
                "                \"skuName\":\"南非橙（5个）\",\n" +
                "                \"skuType\":\"FINISHED_PRODUCT\",\n" +
                "                \"skuUnit\":\"个\",\n" +
                "                \"skuVolume\":0,\n" +
                "                \"skuWeight\":0\n" +
                "            },\n" +
                "            {\n" +
                "                \"skuCode\":\"11503\",\n" +
                "                \"skuCount\":4,\n" +
                "                \"skuName\":\"小葱\",\n" +
                "                \"skuType\":\"FINISHED_PRODUCT\",\n" +
                "                \"skuUnit\":\"份\",\n" +
                "                \"skuVolume\":0,\n" +
                "                \"skuWeight\":0\n" +
                "            },\n" +
                "            {\n" +
                "                \"skuCode\":\"11311\",\n" +
                "                \"skuCount\":6,\n" +
                "                \"skuName\":\"胡萝卜\",\n" +
                "                \"skuType\":\"FINISHED_PRODUCT\",\n" +
                "                \"skuUnit\":\"个\",\n" +
                "                \"skuVolume\":0,\n" +
                "                \"skuWeight\":0\n" +
                "            }]";

        Map<String,String> infos=new HashMap<>();
        infos.put("1","FW005");
        infos.put("2","FW005");
        infos.put("3","FW005");
        infos.put("4","FW005");
        infos.put("6","FW005");
        infos.put("7","FW005");
        infos.put("8","FW005");
        infos.put("10","FW005");
        infos.put("11","FW005");
        infos.put("12","FW005");
        infos.put("15","FW005");
        infos.put("16","FW005");
        infos.put("17","FW005");
        infos.put("18","FW005");
        infos.put("48001","LA_SX");


        List<DeliveryOrderDTO> deliverylist
        = infos.entrySet().stream().map(p->{
            List<DeliveryOrderDetailDTO> details = JSONObject.parseArray(tmp, DeliveryOrderDetailDTO.class);
            DeliveryOrderDTO order = new DeliveryOrderDTO();
            order.setPickupDate(LocalDate.now().plusDays(1));
            order.setDeliveryOrderDetailDtoList(details);
            order.setReceiver("李阳");
            order.setSoldCount(new BigDecimal(6));
            order.setStoreNo(Integer.valueOf(p.getKey()));
            order.setWarehouseCode(p.getValue());
            order.setTel("4009600960");
            order.setType(OutboundBillTypeEnum.FINISHED_PRODUCT);
            return order;
        }).collect(Collectors.toList());








//        List<DeliveryOrderDTO> deliveryOrderDTOS = JSONObject.parseObject(tmp,new TypeReference<List<DeliveryOrderDTO>>(){});

//        transDetailService.freshTransPlan(citys,new Date());
        long beg=System.currentTimeMillis();
        CommonResult<Boolean> booleanCommonResult = trafficPlanService.sendStoreSales(deliverylist);
        System.out.println(String.format("耗时:%d ms,返回 %s",System.currentTimeMillis()-beg,JSON.toJSONString(booleanCommonResult)));
        System.out.println(JSON.toJSONString(booleanCommonResult));

    }


}
