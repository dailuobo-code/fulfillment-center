package com.mallcai.fulfillment.test;


import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.biz.service.TmsCarArrangmentClientService;
import com.mallcai.fulfillment.deploy.Application;
import com.mallcai.fulfillment.infrastructure.mapper.tms.TransportLinePoMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportLinePo;
import com.mallcai.fulfillment.infrastructure.object.tms.TransportPlanPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TmsClientTest {

    @Autowired
    private TransportLinePoMapperExtend transportLinePoMapperExtend;

    @Autowired
    private TmsCarArrangmentClientService tmsCarArrangmentClientService;

    @ConfigValue(key="/app_fulfillment/common/tms.data.config")
    private Map<String,Object> tmsConfig;

    @Test
    public void testConfig(){
        boolean skipFlag = (Boolean) tmsConfig.getOrDefault("skipFlag", false);

        System.out.println(skipFlag);
    }

    @Test
    public void testCarAggrangment(){
        tmsCarArrangmentClientService.sendCarArrangmentCommand(3);
    }


    @Test
    public void testMapper(){

        TransportLinePo line=new TransportLinePo();
        line.setRealLeave(new Date());
        line.setRealArrive(new Date());
        line.setDelivery(true);
        line.setDeliveryAmt(30);
        line.setOrderNo("alpha-FW005-1-20-2019-11-19");

        transportLinePoMapperExtend.updateByPlanNoAndOrgCode(line);
    }
}
