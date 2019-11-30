package com.mallcai.fulfillment.pe.deploy.test;


import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoListMsg;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoMsg;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CommodityInfo;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/8/24 5:02 PM
 */
public class TestSendMsg {

    /*@Resource
    private RocketMqProducerTemplate rocketMqProducerTemplate;


    public static void main(String[] args) {
        ProduceOrderInfoListMsg listMsg = new ProduceOrderInfoListMsg();
        List<ProduceOrderInfoMsg> infoMsgs = new ArrayList<>();taskScheduler
        List<CommodityInfo> commodities = new ArrayList<>();
        CommodityInfo commodityInfo = new CommodityInfo();
        commodityInfo.setCityProductId(222);
        commodityInfo.setProductNum(2);
        commodities.add(commodityInfo);
        ProduceOrderInfoMsg infoMsg = new ProduceOrderInfoMsg();
        infoMsg.setCityId(1);
        infoMsg.setCommodities(commodities);
        infoMsg.setFreshWarehouseId(222);
        infoMsg.setInvoiceId(222L);
        infoMsg.setPickupTime("2019-08-30");
        infoMsg.setStoreId(111);
        infoMsg.setRecipients("小李");
        infoMsg.setRecipientsPhone("18244445555");
        infoMsgs.add(infoMsg);
        listMsg.setProduceOrderInfoMsgList(infoMsgs);
        System.out.println( JSON.toJSONString(listMsg));
        //Message message = new Message("PRODUCE_ORDER_LIST", JSON.toJSONString(listMsg).getBytes());

    }*/

}
