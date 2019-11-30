package com.mallcai.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.framework.redis.plugin.sequence.RedisIdFactory;
import com.mallcai.fulfillment.dc.Application;
import com.mallcai.fulfillment.dc.api.service.dc.enums.CallbackStatusEnum;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.BoxDetailRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.BoxTravelRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.ErpCallbackRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.PurchasePredictRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.inner.ErpCallbackInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceiveOrderInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.dc.biz.service.inner.RedisService;
import com.mallcai.fulfillment.dc.common.constants.Constants;
import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.dao.bigData.StoreSkuForecastDAO;
import com.mallcai.fulfillment.dc.dao.dc.OrderItemInfoDAO;
import com.mallcai.scm.utils.DateUtils;
import com.mallcai.trade.common.model.SOAOrderDetailVO;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/8/27 11:23 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCase {
	@Resource
	private ReceiveOrderInnerService receiveOrderInnerService;

	@Resource
	private ErpCallbackInnerService erpCallbackInnerService;

	@Resource
	private RedisService redisService;

	@Resource
	private ReceivePurchaseForecastInnerService receivePurchaseForecastInnerService;

	@Resource
	private OrderItemInfoDAO orderItemInfoDAO;

	@Resource
	private StoreSkuForecastDAO storeSkuForecastDAO;

	@Resource
	private RedisIdFactory redisIdFactory;
	@Test
	public void testOrderItemInfoDelete(){
		orderItemInfoDAO.deleteByCondition(30, 2, "2019-10-18");
	}

	@Test
	public void testCountAll(){
		String str=Constants.BIG_PREFIX+ DateUtils.dateFormat(new Date(),"yyyyMMdd")+String.format("%018d",redisIdFactory.getIdByDay(Constants.BIG_PREFIX));
		System.out.println("=============="+str+"=========================");
		int forecastResultNum = storeSkuForecastDAO.countAll();
		System.out.println("total sum="+forecastResultNum);
	}
	@Test
	public void testPurchaseImport(){
		PurchasePredictRequestDTO purchasePredictRequestDTO=new PurchasePredictRequestDTO();
		purchasePredictRequestDTO.setCityId(30);
		purchasePredictRequestDTO.setWarehouseId(5);
		purchasePredictRequestDTO.setVersion(0);
		purchasePredictRequestDTO.setForecastDate("2019-10-17");
		purchasePredictRequestDTO.setCategoryType(1);
		boolean result= receivePurchaseForecastInnerService.receiveMsg(purchasePredictRequestDTO,
				Constants.PREDICT_T_2);
	}

	@Test
	public void DateTransfer(){
		Date date=new Date();
		Date date1= DateUtil.addDays(date,1);
		String date2=DateUtil.formatDate(date1);
		System.out.println(date);
		System.out.println(date1);
		System.out.println(date2);
	}



	@Test
	public void testErp() {
		String str = "{\"asynPayStatus\":\"2\",\"balance\":1263,\"change\":0,\"changeAfterComment\":0,\"cityId\":30,\"closeTime\":\"\",\"completeUserId\":0,\"couponDesc\":\"\",\"couponId\":-1,\"couponValue\":0.0,\"createTime\":\"2019-11-05 21:50:50\",\"createUserId\":0,\"deliveryMode\":1,\"evaluateContent\":\"\",\"extras\":\"{\\\"parent_order_no\\\":\\\"106753850690030629\\\"}\",\"freight\":0,\"generateTime\":\"2019-11-05 21:50:50\",\"groupId\":0,\"isDel\":\"0\",\"isWilling\":0,\"limitFee\":0,\"memo\":\"\",\"orderDetailLst\":[{\"changeFlag\":2,\"cityId\":30,\"cityProductId\":75,\"couponPrice\":0,\"createTime\":\"2019-11-05 21:50:50.0\",\"isGift\":0,\"isPay\":\"Y\",\"maxNum\":1,\"maxWeight\":700.0,\"merchantId\":3333,\"minNum\":1,\"minWeight\":700.0,\"numUnit\":\"袋\",\"orderId\":\"106753852760030370\",\"originalPrice\":600,\"packageMaxWeight\":700,\"payTime\":\"2019-11-05 21:50:50.0\",\"pointPrice\":0,\"price\":600,\"prodIcon\":\"http://img1.dailuobo.com/2019/5/20/1558341036789.jpg\",\"prodShowName\":\"【非标品】小娃娃菜测试商品 3棵/袋\",\"productInfo\":\"{\\\"fresh_wid\\\":\\\"11\\\",\\\"is_frozen\\\":\\\"0\\\",\\\"specName\\\":\\\"[]\\\",\\\"std_wid\\\":\\\"4\\\",\\\"product_no\\\":\\\"11118\\\",\\\"is_standard\\\":\\\"0\\\"}\",\"productNum\":1,\"remark1\":\"{\\\"category\\\":\\\"蔬菜菌菇\\\",\\\"categoryId\\\":437,\\\"productCode\\\":\\\"11118\\\",\\\"rate\\\":\\\"0.0000\\\",\\\"taxCode\\\":\\\"10101120700\\\"}\",\"specId\":54642441,\"specName\":\"\",\"starNum\":0,\"storeId\":3,\"totalDiscount\":0,\"unitType\":2,\"updateTime\":\"2019-11-05 21:50:52.0\",\"userId\":1080680,\"vipPrice\":0,\"weightUnit\":\"克\"},{\"changeFlag\":1,\"cityId\":30,\"cityProductId\":480,\"couponPrice\":0,\"createTime\":\"2019-11-05 21:50:50.0\",\"isGift\":0,\"isPay\":\"Y\",\"maxNum\":1,\"maxWeight\":450.0,\"merchantId\":0,\"minNum\":1,\"minWeight\":450.0,\"numUnit\":\"份\",\"orderId\":\"106753852760030370\",\"originalPrice\":663,\"packageMaxWeight\":450,\"payTime\":\"2019-11-05 21:50:50.0\",\"pointPrice\":0,\"price\":663,\"prodIcon\":\"http://img1.dailuobo.com/2019/6/4/1559632503147.jpg\",\"prodShowName\":\"【货品】菠菜 450g 测试\",\"productInfo\":\"{\\\"fresh_wid\\\":\\\"11\\\",\\\"is_frozen\\\":\\\"0\\\",\\\"specName\\\":\\\"[]\\\",\\\"std_wid\\\":\\\"4\\\",\\\"product_no\\\":\\\"11127\\\",\\\"is_standard\\\":\\\"0\\\"}\",\"productNum\":1,\"remark1\":\"{\\\"category\\\":\\\"蔬菜菌菇\\\",\\\"categoryId\\\":437,\\\"productCode\\\":\\\"11127\\\",\\\"rate\\\":\\\"0.0000\\\",\\\"taxCode\\\":\\\"10101020100\\\"}\",\"specId\":53239976,\"specName\":\"\",\"starNum\":0,\"storeId\":3,\"totalDiscount\":0,\"unitType\":1,\"updateTime\":\"2019-11-05 21:50:52.0\",\"userId\":1080680,\"vipPrice\":0,\"weightUnit\":\"克\"}],\"orderId\":\"106753852760030370\",\"orderName\":\"【货品】菠菜 450g 测试,【非标品】小娃娃菜测试商品 3棵/袋,\",\"orderPic\":\"http://img1.dailuobo.com/2019/6/4/1559632503147.jpg,http://img1.dailuobo.com/2019/5/20/1558341036789.jpg,\",\"orderPrice\":0,\"orderTag\":0,\"orderType\":1,\"overTimeMills\":0,\"payTime\":\"2019-11-05 21:50:52\",\"payType\":0,\"pickupCode\":\"\",\"pickupEndTime\":\"20:30\",\"pickupStartTime\":\"07:00\",\"pickupTime\":\"2019-11-06\",\"pickupTimeId\":41220,\"pickupTypeId\":0,\"refundStatus\":0,\"replyStatus\":0,\"residenceId\":0,\"splitType\":2,\"status\":2,\"storeId\":3,\"totalFull\":0.0,\"totalPointPrice\":0,\"totalPrice\":1263,\"updateTime\":\"2019-11-05 21:50:52\",\"updateUserId\":0,\"userId\":1080680,\"version\":1,\"warehouseId\":11,\"weight\":1150.0}";
		SOAOrderDetailVO detailVO = JSON.parseObject(str, new TypeReference<SOAOrderDetailVO>() {
		});
		System.out.println(detailVO);
		receiveOrderInnerService.receiveOrder(detailVO);
    }

	@Test
	public void testErpCallback() {
		ErpCallbackRequestDTO requestDTO = new ErpCallbackRequestDTO();
		requestDTO.setStatus(CallbackStatusEnum.WMS_OUT_STOCK.getStatus());
		Map<BoxTravelRequestDTO,List<BoxDetailRequestDTO>> boxRequestDTOS = new HashMap<>();
		List<BoxDetailRequestDTO> boxDetailRequestDTOS = new ArrayList<>();
		BoxDetailRequestDTO boxDetailRequestDTO = new BoxDetailRequestDTO();
		boxDetailRequestDTO.setCityId(0);
		boxDetailRequestDTO.setProduceOrderNo("test-001");
		boxDetailRequestDTO.setProductNo("22222");
		boxDetailRequestDTO.setProductNum(10);
		boxDetailRequestDTOS.add(boxDetailRequestDTO);
		boxDetailRequestDTO = new BoxDetailRequestDTO();
		boxDetailRequestDTO.setCityId(0);
		boxDetailRequestDTO.setProduceOrderNo("test-002");
		boxDetailRequestDTO.setProductNo("22222");
		boxDetailRequestDTO.setProductNum(10);
		boxDetailRequestDTOS.add(boxDetailRequestDTO);
		BoxTravelRequestDTO boxTravelRequestDTO = new BoxTravelRequestDTO();
		boxTravelRequestDTO.setBoxNo("box-test-004");
		boxTravelRequestDTO.setTravelNodeTime("2019-01-01");
		boxRequestDTOS.put(boxTravelRequestDTO, boxDetailRequestDTOS);
        requestDTO.setBoxRequestDTOS(boxRequestDTOS);
		erpCallbackInnerService.callBack(requestDTO);
	}

	@Test
	public void testRedis(){
		System.out.println("0----------------");
//		JedisProxy instance = JedisProxy.getInstance();
//		instance.set("123","123");

		redisService.set("fullfilment_dc_redis_test","1",3600);
	}


}
