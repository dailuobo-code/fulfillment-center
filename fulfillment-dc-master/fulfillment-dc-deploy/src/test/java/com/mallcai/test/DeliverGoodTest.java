package com.mallcai.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.backend.common.utils.DateUtils;
import com.mallcai.fulfillment.dc.Application;
import com.mallcai.fulfillment.dc.api.service.dc.enums.CallbackStatusEnum;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.*;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareAreaInfo;
import com.mallcai.fulfillment.dc.api.service.dc.operate.warearea.WareListReq;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.DeliverGoodsServiceImpl;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.PeriodBuyService;
import com.mallcai.fulfillment.dc.biz.service.impl.operate.WareStoreServiceImpl;
import com.mallcai.fulfillment.dc.biz.service.inner.ErpCallbackInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.RedisService;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.open.api.model.gm.DeliveryOrderInfo;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author bh.zhong
 * @date 2019/8/27 11:23 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DeliverGoodTest {


	@Autowired
	DeliverGoodsServiceImpl deliverGoodsService;


	@Autowired
	PeriodBuyService periodBuyService;

	@Autowired
	WareStoreServiceImpl wareStoreService;


	@Test
	public void testWareStore(){

		WareAreaInfo info=new WareAreaInfo();
		info.setWarehouseId(3);
		info.setWarehouseName("南京一仓");
		info.setAreas(ImmutableList.of(30,31,33,34,38,39));
		info.setEffectTime(DateUtils.parseDate("2019-10-27 20:00:00", DateUtils.DEFAULT_DATE_TIME_PATTERN));
		info.setGpsLongitude("123.213232");
		info.setGpsDimension("24.298712");
		info.setCreateBy("33241125");
		info.setCreateTime(new Date());


		wareStoreService.addOrUpdateWareArea(info);
	}

	@Test
	public void queryWareStore(){

		WareListReq req = WareListReq.builder()
				.page(2)
				.size(2)
				.build();


		com.mallcai.framework.common.util.plugin.api.response.PlainResult<List<WareAreaInfo>> listPlainResult = wareStoreService.queryWareAreaList(req);

		System.out.println(listPlainResult.getResult().size());
	}



	@Test
	public void testDeliverGood(){
		String temp="{\"cityId\":30,\"importTime\":\"2019-10-15T16:34:51.952\",\"orderType\":1,\"productNo\":\"13513\",\"productNum\":100,\"storeId\":1362,\"warehouseId\":2,\"cityProductId\":222}";

		DeliveryGoodsItemRequestDTO dto = JSON.parseObject(temp, DeliveryGoodsItemRequestDTO.class);

		DeliveryGoodsDTO req=new DeliveryGoodsDTO();
		req.setDeliveryGoodsItemRequestDTOList(Lists.newArrayList(dto));

		PlainResult<Boolean> booleanPlainResult = deliverGoodsService.importGoodsCsv(req);


	}

	@Test
	public void testDeliveryRecord(){
		DeliveryOrderInfo info=new DeliveryOrderInfo();
		info.setOrderId("order123123");
		info.setSerialNo("seria123123");
		info.setDeliveryDate(new Date());
		info.setDeliveryMan("zhangsan");
		info.setDeliveryManPhone("18112341234");
		info.setItem(1);
		info.setPdtId("123123");
		info.setPrice(new BigDecimal(3.2));
		info.setAmount(2);

		periodBuyService.deliveryPeriodBuy(info);
	}


	// {"cityId":30,"importTime":"2019-10-15T16:34:51.952","orderType":1,"productNo":"13513","productNum":100,"storeId":1362,"warehouseId":2}
}