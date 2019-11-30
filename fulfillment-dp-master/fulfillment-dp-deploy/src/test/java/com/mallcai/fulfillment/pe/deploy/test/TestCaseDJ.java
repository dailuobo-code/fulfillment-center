package com.mallcai.fulfillment.pe.deploy.test;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.Application;
import com.mallcai.fulfillment.dp.api.request.*;
import com.mallcai.fulfillment.dp.api.response.BatchDistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.DeliveryOrderOperateServiceImpl;
import com.mallcai.fulfillment.dp.biz.service.impl.DeliveryOrderQueryServiceImpl;
import com.mallcai.fulfillment.dp.biz.service.impl.DeliveryOrderServiceImpl;
import com.mallcai.fulfillment.dp.biz.service.impl.task.ConfirmDeliveryTask;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-10-30 20:29:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCaseDJ {
    @Resource
    private DeliveryOrderServiceImpl deliveryOrderService;
    @Resource
    private DeliveryOrderQueryServiceImpl deliveryOrderQueryServiceImpl;
    @Resource
    private DeliveryOrderOperateServiceImpl deliveryOrderOperateServiceImpl;

    @Test
    public void queryMultiPkgByOrderNoTest(){
        DeliveryOrderQueryDTO deliveryOrderQueryDTO=new DeliveryOrderQueryDTO();
        deliveryOrderQueryDTO.setOrderNo("1024809699320304882");
        PlainResult<DistOrderMultiPkgDTO> result = deliveryOrderQueryServiceImpl.queryMultiPkgByOrderNo(deliveryOrderQueryDTO);
        Assert.assertEquals("不相等",result.getData().getOrderNo(),"1024809699320304882");
        Assert.assertEquals("不相等",result.getData().getDistOrderInfoList().size(), 3);
    }

    @Test
    public void batchQueryMultiPkgByOrderNosTest(){
        BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO = new BatchDeliveryOrderQueryDTO();
        List<String> orderNos = new ArrayList<>();
        orderNos.add("1024809699320304882");
        orderNos.add("1024809699320304992");
        batchDeliveryOrderQueryDTO.setOrderNos(orderNos);
        PlainResult<BatchDistOrderMultiPkgDTO> result =
                deliveryOrderQueryServiceImpl.batchQueryMultiPkgByOrderNos(batchDeliveryOrderQueryDTO);
        List<DistOrderMultiPkgDTO> distOrderMultiPkgDTOList = result.getData().getDistOrderMultiPkgDTOList();
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.size(), 2);
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(0).getOrderNo(),"1024809699320304882");
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(0).getDistOrderInfoList().size(),3);
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(1).getOrderNo(),"1024809699320304992");
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(1).getDistOrderInfoList().size(),2);
    }

    @Test
    public void batchQueryMultiPkgByOrderNosTest1(){
        BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO = new BatchDeliveryOrderQueryDTO();
        List<String> orderNos = new ArrayList<>();
        orderNos.add("1024809699320304872");
        batchDeliveryOrderQueryDTO.setOrderNos(orderNos);
        PlainResult<BatchDistOrderMultiPkgDTO> result =
                deliveryOrderQueryServiceImpl.batchQueryMultiPkgByOrderNos(batchDeliveryOrderQueryDTO);
        List<DistOrderMultiPkgDTO> distOrderMultiPkgDTOList = result.getData().getDistOrderMultiPkgDTOList();
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.size(), 1);
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(0).getOrderNo(),"1024809699320304872");
        Assert.assertEquals("不相等", distOrderMultiPkgDTOList.get(0).getDistOrderInfoList().size(),1);
    }

    @Test
    public void testMultiPkgSend(){

        //1
        DeliveryAddressDTO receiverAddress = new DeliveryAddressDTO();
        receiverAddress.setAddressDetail("test");
        receiverAddress.setCity("test");
        receiverAddress.setCounty("test");
        receiverAddress.setProvince("test");
        DeliveryUserDTO receiverUser = new DeliveryUserDTO();
        receiverUser.setName("test");
        receiverUser.setPhone("12233344");
        receiverUser.setUserId(333444);
        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        ExpressMerchantDTO expressMerchantDTO = new ExpressMerchantDTO();
        expressMerchantDTO.setExpressNo("test-222333");
        expressMerchantDTO.setExpressName("东风快递");
        deliveryInfoDTO.setDeliveryType(1);
        deliveryInfoDTO.setExpressMerchantDTO(expressMerchantDTO);
        deliveryInfoDTO.setReceiverAddress(receiverAddress);
        deliveryInfoDTO.setReceiverUser(receiverUser);
        DeliveryItemDTO deliveryItemDTO = new DeliveryItemDTO();
        deliveryItemDTO.setCityProductId(33344L);
        deliveryItemDTO.setProductNo("333444");
        deliveryItemDTO.setNum(2);

        //2
        DeliveryAddressDTO receiverAddress1 = new DeliveryAddressDTO();
        receiverAddress1.setAddressDetail("test1");
        receiverAddress1.setCity("test1");
        receiverAddress1.setCounty("test1");
        receiverAddress1.setProvince("test1");
        DeliveryUserDTO receiverUser1 = new DeliveryUserDTO();
        receiverUser1.setName("test1");
        receiverUser1.setPhone("1223334455");
        receiverUser1.setUserId(3334445);
        DeliveryInfoDTO deliveryInfoDTO1 = new DeliveryInfoDTO();
        ExpressMerchantDTO expressMerchantDTO1 = new ExpressMerchantDTO();
        expressMerchantDTO1.setExpressNo("test-222333444");
        expressMerchantDTO1.setExpressName("东风快递");
        deliveryInfoDTO1.setDeliveryType(1);
        deliveryInfoDTO1.setExpressMerchantDTO(expressMerchantDTO1);
        deliveryInfoDTO1.setReceiverAddress(receiverAddress1);
        deliveryInfoDTO1.setReceiverUser(receiverUser1);
        DeliveryItemDTO deliveryItemDTO1 = new DeliveryItemDTO();
        deliveryItemDTO1.setCityProductId(333445L);
        deliveryItemDTO1.setProductNo("3334445");
        deliveryItemDTO1.setNum(5);

        List<DistOrderInfo> distOrderInfoList = new ArrayList<>();
        DistOrderInfo distOrderInfo = new DistOrderInfo();
        distOrderInfo.setDeliveryInfoDTO(deliveryInfoDTO);
        distOrderInfo.setDeliveryItemDTO(deliveryItemDTO);

        DistOrderInfo distOrderInfo1 = new DistOrderInfo();
        distOrderInfo1.setDeliveryInfoDTO(deliveryInfoDTO1);
        distOrderInfo1.setDeliveryItemDTO(deliveryItemDTO1);

        distOrderInfoList.add(distOrderInfo);
        distOrderInfoList.add(distOrderInfo1);

        DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO = new DeliveryOrderMultiPkgDTO();
        deliveryOrderMultiPkgDTO.setCityId(11);
        deliveryOrderMultiPkgDTO.setOrderNo("1024809699320304892");
        deliveryOrderMultiPkgDTO.setStoreId(0L);
        deliveryOrderMultiPkgDTO.setDistOrderInfoList(distOrderInfoList);
        deliveryOrderService.orderMultiPkgSend(deliveryOrderMultiPkgDTO);
    }

    @Test
    public void modifyMultiPkgExpressTest() {
        //1
        DeliveryAddressDTO receiverAddress = new DeliveryAddressDTO();
        receiverAddress.setAddressDetail("test");
        receiverAddress.setCity("test");
        receiverAddress.setCounty("test");
        receiverAddress.setProvince("test");
        DeliveryUserDTO receiverUser = new DeliveryUserDTO();
        receiverUser.setName("test");
        receiverUser.setPhone("12233344");
        receiverUser.setUserId(333444);
        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        ExpressMerchantDTO expressMerchantDTO = new ExpressMerchantDTO();
        expressMerchantDTO.setExpressNo("test-222333");
        expressMerchantDTO.setExpressName("东风快递");
        deliveryInfoDTO.setDeliveryType(1);
        deliveryInfoDTO.setExpressMerchantDTO(expressMerchantDTO);
        deliveryInfoDTO.setReceiverAddress(receiverAddress);
        deliveryInfoDTO.setReceiverUser(receiverUser);
        DeliveryItemDTO deliveryItemDTO = new DeliveryItemDTO();
        deliveryItemDTO.setCityProductId(33344L);
        deliveryItemDTO.setProductNo("333444");
        deliveryItemDTO.setNum(2);

        //2
        DeliveryAddressDTO receiverAddress1 = new DeliveryAddressDTO();
        receiverAddress1.setAddressDetail("test1");
        receiverAddress1.setCity("test1");
        receiverAddress1.setCounty("test1");
        receiverAddress1.setProvince("test1");
        DeliveryUserDTO receiverUser1 = new DeliveryUserDTO();
        receiverUser1.setName("test1");
        receiverUser1.setPhone("1223334455");
        receiverUser1.setUserId(3334445);
        DeliveryInfoDTO deliveryInfoDTO1 = new DeliveryInfoDTO();
        ExpressMerchantDTO expressMerchantDTO1 = new ExpressMerchantDTO();
        expressMerchantDTO1.setExpressNo("test-222333444");
        expressMerchantDTO1.setExpressName("东风快递");
        deliveryInfoDTO1.setDeliveryType(1);
        deliveryInfoDTO1.setExpressMerchantDTO(expressMerchantDTO1);
        deliveryInfoDTO1.setReceiverAddress(receiverAddress1);
        deliveryInfoDTO1.setReceiverUser(receiverUser1);
        DeliveryItemDTO deliveryItemDTO1 = new DeliveryItemDTO();
        deliveryItemDTO1.setCityProductId(333445L);
        deliveryItemDTO1.setProductNo("3334445");
        deliveryItemDTO1.setNum(5);

        //3
        DeliveryAddressDTO receiverAddress2 = new DeliveryAddressDTO();
        receiverAddress2.setAddressDetail("test2");
        receiverAddress2.setCity("test2");
        receiverAddress2.setCounty("test2");
        receiverAddress2.setProvince("test2");
        DeliveryUserDTO receiverUser2 = new DeliveryUserDTO();
        receiverUser2.setName("test2");
        receiverUser2.setPhone("1223334466");
        receiverUser2.setUserId(333645);
        DeliveryInfoDTO deliveryInfoDTO2 = new DeliveryInfoDTO();
        ExpressMerchantDTO expressMerchantDTO2 = new ExpressMerchantDTO();
        expressMerchantDTO2.setExpressNo("test-333444555");
        expressMerchantDTO2.setExpressName("东风快递");
        deliveryInfoDTO2.setDeliveryType(1);
        deliveryInfoDTO2.setExpressMerchantDTO(expressMerchantDTO2);
        deliveryInfoDTO2.setReceiverAddress(receiverAddress2);
        deliveryInfoDTO2.setReceiverUser(receiverUser2);
        DeliveryItemDTO deliveryItemDTO2 = new DeliveryItemDTO();
        deliveryItemDTO2.setCityProductId(333465L);
        deliveryItemDTO2.setProductNo("333446");
        deliveryItemDTO2.setNum(5);

        List<DistOrderInfo> distOrderInfoList = new ArrayList<>();
        DistOrderInfo distOrderInfo = new DistOrderInfo();
        distOrderInfo.setDeliveryInfoDTO(deliveryInfoDTO);
        distOrderInfo.setDeliveryItemDTO(deliveryItemDTO);

        DistOrderInfo distOrderInfo1 = new DistOrderInfo();
        distOrderInfo1.setDeliveryInfoDTO(deliveryInfoDTO1);
        distOrderInfo1.setDeliveryItemDTO(deliveryItemDTO1);

        DistOrderInfo distOrderInfo2 = new DistOrderInfo();
        distOrderInfo2.setDeliveryInfoDTO(deliveryInfoDTO2);
        distOrderInfo2.setDeliveryItemDTO(deliveryItemDTO2);

        distOrderInfoList.add(distOrderInfo);
        distOrderInfoList.add(distOrderInfo1);
        distOrderInfoList.add(distOrderInfo2);

        MultiPkgModifyExpressDTO multiPkgModifyExpressDTO = new MultiPkgModifyExpressDTO();
        multiPkgModifyExpressDTO.setOrderNo("1024809699320304992");
        multiPkgModifyExpressDTO.setDistOrderInfoList(distOrderInfoList);
        deliveryOrderOperateServiceImpl.modifyMultiPkgExpress(multiPkgModifyExpressDTO);
    }

    @Test
    public void orderConfirmReceiveTest(){
        OrderConfirmReceiveDTO orderConfirmReceiveDTO=new OrderConfirmReceiveDTO();
        orderConfirmReceiveDTO.setCityId(11);
        orderConfirmReceiveDTO.setOrderNo("1024809699320304882");
        orderConfirmReceiveDTO.setUserId(333444);
        PlainResult<Boolean> result=deliveryOrderOperateServiceImpl.orderConfirmReceive(orderConfirmReceiveDTO);
        Assert.assertTrue(result.isSuccess());
    }
}
