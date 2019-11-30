package com.mallcai.fulfillment.pe.deploy.test;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.dp.api.request.*;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.MultiPkgServiceChecker;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-10-30 21:35:31
 */
public class VerifyTest {

    @Test
    public void checkBatchQueryInfoTest() {
        //null
        PlainResult plainResult = MultiPkgServiceChecker.checkBatchQueryInfo(null);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "参数异常");

        //orderNos == null
        BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO = new BatchDeliveryOrderQueryDTO();
        plainResult = MultiPkgServiceChecker.checkBatchQueryInfo(batchDeliveryOrderQueryDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "查询的订单列表为空");

        List<String> orderNos = new ArrayList<>(101);
        for (int i = 0; i < 102; i++) {
            orderNos.add(String.valueOf(i));
        }

        batchDeliveryOrderQueryDTO.setOrderNos(orderNos);
        plainResult = MultiPkgServiceChecker.checkBatchQueryInfo(batchDeliveryOrderQueryDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "超过最大批次限制");

        orderNos.remove(101);
        orderNos.remove(100);
        plainResult = MultiPkgServiceChecker.checkBatchQueryInfo(batchDeliveryOrderQueryDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), true);
    }

    @Test
    public void checkMultiPkgExpressModifyReqInfoTest() {
        //null
        PlainResult plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(null);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "参数异常");

        //orderNo == null
        MultiPkgModifyExpressDTO multiPkgModifyExpressDTO = new MultiPkgModifyExpressDTO();
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "orderNo不能为空");

        multiPkgModifyExpressDTO.setOrderNo("11111");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "缺少配送物流信息");

        List<DistOrderInfo> distOrderInfos = new ArrayList<>();
        DistOrderInfo distOrderInfo = new DistOrderInfo();
        multiPkgModifyExpressDTO.setDistOrderInfoList(distOrderInfos);
        distOrderInfos.add(distOrderInfo);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送商品明细为空");

        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());

        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "不能超过9个配送单");

        distOrderInfos.clear();
        distOrderInfos.add(distOrderInfo);
        DeliveryItemDTO deliveryItemDTO = new DeliveryItemDTO();
        distOrderInfo.setDeliveryItemDTO(deliveryItemDTO);

        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setCityProductId(1111L);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setNum(2);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setProductNo("12121212");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送信息为空");

        deliveryItemDTO.setWeight(11L);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送信息为空");

        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        distOrderInfo.setDeliveryInfoDTO(deliveryInfoDTO);

        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "发货方式字段信息不能为空");

        deliveryInfoDTO.setDeliveryType(0);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "发货方式错误");

        deliveryInfoDTO.setDeliveryType(1);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息参数为空");

        ExpressMerchantDTO expressMerchantDTO = new ExpressMerchantDTO();
        deliveryInfoDTO.setExpressMerchantDTO(expressMerchantDTO);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName("顺丰快递");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName(null);
        expressMerchantDTO.setExpressNo("111111111");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName("顺丰快递");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址为空");

        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryInfoDTO.setReceiverAddress(deliveryAddressDTO);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setProvince("zj");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setCity("hz");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setCounty("cn");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setAddressDetail("zjhzxxby");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人为空");

        DeliveryUserDTO deliveryUserDTO = new DeliveryUserDTO();
        deliveryInfoDTO.setReceiverUser(deliveryUserDTO);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setName("test");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setPhone("11111111111");
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setUserId(111);
        plainResult = MultiPkgServiceChecker.checkMultiPkgExpressModifyReqInfo(multiPkgModifyExpressDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), true);
    }

    @Test
    public void checkDeliveryOrderMultiPkgInfoTest() {
        PlainResult plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(null);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "参数异常");

        DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO = new DeliveryOrderMultiPkgDTO();
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "orderNo,storeId,cityId不能为空");

        deliveryOrderMultiPkgDTO.setOrderNo("11111");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "orderNo,storeId,cityId不能为空");

        deliveryOrderMultiPkgDTO.setCityId(111);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "orderNo,storeId,cityId不能为空");

        deliveryOrderMultiPkgDTO.setStoreId(11111L);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "缺少配送物流信息");

        List<DistOrderInfo> distOrderInfos = new ArrayList<>();
        DistOrderInfo distOrderInfo = new DistOrderInfo();
        deliveryOrderMultiPkgDTO.setDistOrderInfoList(distOrderInfos);
        distOrderInfos.add(distOrderInfo);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送商品明细为空");

        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());
        distOrderInfos.add(new DistOrderInfo());

        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "不能超过9个配送单");

        distOrderInfos.clear();
        distOrderInfos.add(distOrderInfo);
        DeliveryItemDTO deliveryItemDTO = new DeliveryItemDTO();
        distOrderInfo.setDeliveryItemDTO(deliveryItemDTO);

        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setCityProductId(1111L);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setNum(2);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "存在数据不存在的发货明细");

        deliveryItemDTO.setProductNo("12121212");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送信息为空");

        deliveryItemDTO.setWeight(11L);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "配送信息为空");

        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        distOrderInfo.setDeliveryInfoDTO(deliveryInfoDTO);

        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "发货方式字段信息不能为空");

        deliveryInfoDTO.setDeliveryType(0);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "发货方式错误");

        deliveryInfoDTO.setDeliveryType(1);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息参数为空");

        ExpressMerchantDTO expressMerchantDTO = new ExpressMerchantDTO();
        deliveryInfoDTO.setExpressMerchantDTO(expressMerchantDTO);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName("顺丰快递");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName(null);
        expressMerchantDTO.setExpressNo("111111111");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "物流信息快递单号或承运公司为空");

        expressMerchantDTO.setExpressName("顺丰快递");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址为空");

        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryInfoDTO.setReceiverAddress(deliveryAddressDTO);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setProvince("zj");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setCity("hz");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setCounty("cn");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人地址详细参数为空");

        deliveryAddressDTO.setAddressDetail("zjhzxxby");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人为空");

        DeliveryUserDTO deliveryUserDTO = new DeliveryUserDTO();
        deliveryInfoDTO.setReceiverUser(deliveryUserDTO);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setName("test");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setPhone("11111111111");
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), false);
        Assert.assertEquals("不相等", plainResult.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getCode());
        Assert.assertEquals("不相等", plainResult.getMessage(), "收货人详细参数为空");

        deliveryUserDTO.setUserId(111);
        plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        Assert.assertEquals("不相等", plainResult.isSuccess(), true);
    }
}
