package com.mallcai.fulfillment.test;

import com.alibaba.fastjson.JSON;
import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.WareStoreService;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareAreaInfo;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareListReq;
import com.mallcai.fulfillment.deploy.Application;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RPCInvokeTest {

    @Reference(url="")
    private WareStoreService wareStoreService;


    @Test
    public void testInvode(){
        WareListReq req = WareListReq.builder().build();
        PlainResult<List<WareAreaInfo>> list = wareStoreService.queryWareAreaList(req);
        System.out.println(JSON.toJSONString(list.getResult()));
    }


}
