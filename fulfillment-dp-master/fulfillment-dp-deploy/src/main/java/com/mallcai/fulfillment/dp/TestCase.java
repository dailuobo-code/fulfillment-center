package com.mallcai.fulfillment.dp;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.DeliveryOrderQueryService;
import com.mallcai.fulfillment.dp.api.request.*;
import com.mallcai.fulfillment.dp.api.response.DistOrderDTO;
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
 * @author bh.zhong
 * @date 2019/8/27 11:23 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCase {
	@Resource
	private DeliveryOrderServiceImpl deliveryOrderService;
	@Resource
	private ConfirmDeliveryTask confirmDeliveryTask;
	@Resource
	private DeliveryOrderQueryServiceImpl deliveryOrderQueryServiceImpl;
	@Resource
	private DeliveryOrderOperateServiceImpl deliveryOrderOperateServiceImpl;

	@Test
	public void QueryByOrderNoTest(){
		DeliveryOrderQueryDTO deliveryOrderQueryDTO=new DeliveryOrderQueryDTO();
		deliveryOrderQueryDTO.setOrderNo("1024809699320304872");
		PlainResult<DistOrderDTO> result=deliveryOrderQueryServiceImpl.queryByOrderNo(deliveryOrderQueryDTO);
		Assert.assertEquals("不相等",result.getData().getOrderNo(),"1024809699320304872");
	}
	@Test
	public void ModifyExpressTest(){
		ModifyExpressDTO modifyExpressDTO=new ModifyExpressDTO();
		modifyExpressDTO.setExpressName("2222");
		modifyExpressDTO.setExpressNo("33353");
		modifyExpressDTO.setOrderNo("1024341613030303605");
		PlainResult<?> result=deliveryOrderOperateServiceImpl.modifyExpress(modifyExpressDTO);
		Assert.assertTrue(result.isSuccess());
	}

	@Test
	public void orderConfirmReceiveTest(){
		OrderConfirmReceiveDTO orderConfirmReceiveDTO=new OrderConfirmReceiveDTO();
		orderConfirmReceiveDTO.setCityId(1);
		orderConfirmReceiveDTO.setOrderNo("102484257065030462");
		orderConfirmReceiveDTO.setUserId(886956);
		PlainResult<Boolean> result=deliveryOrderOperateServiceImpl.orderConfirmReceive(orderConfirmReceiveDTO);
		Assert.assertTrue(result.isSuccess());
	}

	@Test
	public void testErp() {
		DeliveryAddressDTO receiverAddress = new DeliveryAddressDTO();
		receiverAddress.setAddressDetail("test");
		receiverAddress.setCity("test");
		receiverAddress.setCounty("test");
		receiverAddress.setProvince("test");
		DeliveryUserDTO receiverUser = new DeliveryUserDTO();
		receiverUser.setName("test");
		receiverUser.setPhone("12233344");
		receiverUser.setUserId(333444);
		DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();
		DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
		ExpressMerchantDTO expressMerchantDTO = new ExpressMerchantDTO();
		expressMerchantDTO.setExpressNo("test-222333");
		expressMerchantDTO.setExpressName("东风快递");
		deliveryOrderDTO.setCityId(11);
		deliveryOrderDTO.setOrderNo("1024809699320304872");
		deliveryOrderDTO.setStoreId(0L);
		deliveryOrderDTO.setDeliveryInfoDTO(deliveryInfoDTO);
		deliveryInfoDTO.setDeliveryType(1);
		deliveryInfoDTO.setExpressMerchantDTO(expressMerchantDTO);
		deliveryInfoDTO.setReceiverAddress(receiverAddress);
		deliveryInfoDTO.setReceiverUser(receiverUser);
		List<DeliveryItemDTO> deliveryItemDTOList = new ArrayList<>();
		DeliveryItemDTO deliveryItemDTO = new DeliveryItemDTO();
		deliveryItemDTOList.add(deliveryItemDTO);
		deliveryItemDTO.setCityProductId(33344L);
		deliveryItemDTO.setProductNo("333444");
		deliveryItemDTO.setNum(2);
		deliveryOrderDTO.setDeliveryItemDTOList(deliveryItemDTOList);
		deliveryOrderService.orderSend(deliveryOrderDTO);
    }

    @Test
	public void test() {
		confirmDeliveryTask.executeConfirmDelivery();
	}
}
