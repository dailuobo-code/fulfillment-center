package com.mallcai.fulfillment.pe.deploy.test;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.fulfillment.pe.api.service.operate.OrderAcceptService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderDetailDTO;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.dependency.client.IcProductGoodsServiceClient;
import com.mallcai.service.order.constants.OrderExtraKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;


/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 22:49:39
 */
public class OrderAcceptServiceTest extends BaseTestCase{

    @Autowired
    private OrderAcceptService orderAcceptService;

    @Test
    public void testAcceptOrder(){

        OrderAcceptRequestDTO orderAcceptRequestDTO = new OrderAcceptRequestDTO();

        for (int i =0; i < 4; i++) {

            String orderId = "pe92634199003" + i;
            orderAcceptRequestDTO.setOrderId(orderId);
            orderAcceptRequestDTO.setCityId(30);
            orderAcceptRequestDTO.setStoreId(3);
            orderAcceptRequestDTO.setPayTime("2019-09-23");
            orderAcceptRequestDTO.setUserId(1080681);
            orderAcceptRequestDTO.setPickupTime("2019-09-23");
            orderAcceptRequestDTO.setRecipients("张三");
            orderAcceptRequestDTO.setRecipientsPhone("13333333333");
            orderAcceptRequestDTO.setOrderType(1);
            orderAcceptRequestDTO.setCreateTime(DateUtil.formatDateTime(new Date()));

            JSONObject extraKeys = new JSONObject();
//            extraKeys.put(OrderExtraKeys.IS_FROZEN_ORDER, "1");
            extraKeys.put(OrderExtraKeys.GROUP_ACTIVITY_INFO, "ff");

            orderAcceptRequestDTO.setExtras(extraKeys.toJSONString());

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            OrderDetailDTO orderDetailDTO2 = new OrderDetailDTO();

            orderDetailDTO.setSpecId(348);
            orderDetailDTO.setProductNum(88);
            orderDetailDTO.setCityProductId(3);
            orderDetailDTO.setProductInfo("{product_no:11103}");
            orderDetailDTO.setMaxNum(2);

//        orderDetailDTO2.setSpecId(347);
//        orderDetailDTO2.setCityProductId(3);
//        orderDetailDTO2.setProductNum(66);

            orderAcceptRequestDTO.setOrderDetailLst(Lists.newArrayList(orderDetailDTO));

            orderAcceptService.acceptOrder(orderAcceptRequestDTO);
        }
    }
}
