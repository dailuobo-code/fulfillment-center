package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderPushService;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.dependency.client.IcProductGoodsServiceClient;
import com.mallcai.fulfillment.pe.dependency.client.StoreServiceClient;
import com.mallcai.fulfillment.pe.deploy.test.BaseTestCase;
import com.mallcai.router.client.RouterReference;
import com.mallcai.service.vo.Product4MgrVO;
import com.mallcai.service.vo.ProductGoodsRelVO;
import com.mallcai.shop.api.service.IStoreService;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 21:12:01
 */
public class ProduceOrderPushServiceTest extends BaseTestCase {

//    @InjectMocks
    @Autowired
    private ProduceOrderPushService invoicePushService;

    @Autowired
    private IcProductGoodsServiceClient icProductGoodsServiceClient;

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Test
    public void testPushInvoice(){

        StoreRespDTO storeRespDTO = new StoreRespDTO();
        storeRespDTO.setStoreName("芜湖呆萝卜");
        storeRespDTO.setStoreId(122344);
        storeRespDTO.setTelephone("15555555555");

        WareHouseRespDTO wareHouseRespDTO = new WareHouseRespDTO();
        WareHouseRespDTO wareHouseRespDTO2 = new WareHouseRespDTO();

        wareHouseRespDTO.setType(1);
        wareHouseRespDTO.setId(666);
        wareHouseRespDTO2.setType(2);
        wareHouseRespDTO2.setId(777);

        storeRespDTO.setWareHouseDTOS(Lists.newArrayList(wareHouseRespDTO, wareHouseRespDTO2));


//        when(iStoreService.searchStoreByIds(any())).thenReturn( PlainResult.ok(Lists.newArrayList(storeRespDTO)));


        Date todayStart = DateUtil.parseDate("2019-10-14");

        invoicePushService.pushProduceOrder(todayStart, DateUtil.addDays(todayStart, 1));
    }


    @Test
    public void testIcProductGoodsService(){
        Map<Integer, Set<Integer>> cityItems = new HashMap<>(20);
        Set<Integer> set = new HashSet<>();
        set.add(4990);
        cityItems.put(220,set);
        List<ProductGoodsRelVO> productGoodsRelVOS = icProductGoodsServiceClient.searchGoodsRel(cityItems);
        System.out.println(productGoodsRelVOS);
    }

    @Test
    public void testStoreServiceClient(){
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        storeServiceClient.searchStoreByIds(ids);
    }

    @RouterReference
    private ItemCenterRouterService itemCenterRouterService;

    @Test
    public void testProductTrans(){
        Map<Integer, List<Integer>> maps= Maps.newHashMap();
        maps.put(30, ImmutableList.of(18241));

        Response<List<Product4MgrVO>> listResponse = itemCenterRouterService.listStoreProduct(maps);

        System.out.println(JSON.toJSONString(listResponse.getData()));
    }
}
