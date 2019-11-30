package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.deploy.test.BaseTestCase;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapper;
import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 17:46:23
 */
@Slf4j
public class ProduceOrderServiceTest extends BaseTestCase {

    @Autowired
    private ProduceOrderService invoiceService;

    @Autowired
    private ProduceOrderMapper invoiceDOMapper;

    @Autowired
    private ConfigurationProcessor configurationPorcessor;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Test
    public void insertOrders() throws InterruptedException {

        for (int i=0 ; i<10; i++){

            Thread thread = new Thread(new ProduceOrderServiceTest.MyRunnable());
            thread.start();

        }

        Thread.sleep(1000000);

    }



    @Test
    public void testCreateInvoice(){

        Date todayStart = DateUtil.parseDate("2019-10-14");
        OrderQueryDto orderQueryDto = new OrderQueryDto();

        orderQueryDto.setStartTime(todayStart);
        orderQueryDto.setEndTime(DateUtil.addDays(todayStart, 1));
        orderQueryDto.setGroupValue("3");

        Integer orderHoldMins = configurationPorcessor.getOrderHoldMins(orderQueryDto);
        orderQueryDto.setOrderHoldMins(orderHoldMins);


        invoiceService.createProduceOrder(new DataBo(orderQueryDto, null));
    }

    @Test
    public void testCreateFrozenProduceOrder(){

        Date todayStart = DateUtil.parseDate("2019-09-19");
        OrderQueryDto orderQueryDto = new OrderQueryDto();

        orderQueryDto.setStartTime(todayStart);
        orderQueryDto.setEndTime(DateUtil.addDays(todayStart, 1));
        orderQueryDto.setOrderHoldMins(0);
        orderQueryDto.setGroupValue("2");

        invoiceService.createProduceOrder(new DataBo(orderQueryDto, null));
    }

    @Test
    public void testInvoice(){

        ProduceOrderCriteria invoiceDOCriteria = new ProduceOrderCriteria();

        invoiceDOCriteria.createCriteria().andIdEqualTo(1L);

        //ProduceOrder invoiceDO = invoiceDOMapper.selectByPrimaryKey(1L);
        System.out.println();
    }

    public class MyRunnable implements Runnable {

        @Override
        public void run() {

            OrderDO orderDO = new OrderDO();

            orderDO.setOrderType(10);
            orderDO.setCityProductId(3);
            orderDO.setStoreId(3);
            orderDO.setUserId(0);
            orderDO.setCityId(30);
            orderDO.setProductNo("");
            orderDO.setOrderSource((byte) 1);
            orderDO.setGroupType((byte) 1);
            orderDO.setGroupValue("1");
            orderDO.setWarehouseId(11);
            orderDO.setCommodityInfoExt("{\\\"goodsId\\\":\\\"13201\\\",\\\"relNum\\\":2.1000,\\\"goodsUnit\\\":\\\"KG\\\",\\\"productNum\\\":3}");
            orderDO.setExpectPushTime(DateUtil.parseDateTime("2019-10-20 00:00:00"));
            orderDO.setPickupTime(DateUtil.parseDateTime("2019-10-20 00:00:00"));


            for (int i =90000; i<120000; i++){

                String transOrderIdPrefix = "data_%s20191020";

                String transOrderid = String.format(transOrderIdPrefix, Thread.currentThread().getId()) + i;

                orderDO.setTransOrderId(transOrderid);

                orderDOMapper.insertSelective(orderDO);
                log.info("插入成功，threadId:{},计数:{}" ,Thread.currentThread().getId(), i);
            }


        }}
}
