package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.enums.DeliveryTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveryAddressDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryInfoDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryItemDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryUserDTO;
import com.mallcai.fulfillment.dp.api.request.ExpressMerchantDTO;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author: Liu Yang
 * @description 发货接口参数校验器
 * @date: 2019-09-06 17:02
 */
@Component
@Slf4j
public class DeliveryOrderServiceParamChecker {

  public PlainResult<?> checkDeliveryOrderQuery(DeliveryOrderDTO deliveryOrderDTO) {
    if (Objects.isNull(deliveryOrderDTO)) {
      log.warn("【发货接口】参数为空");
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
          Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    //简单字段信息校验
    boolean flag = Objects.isNull(deliveryOrderDTO.getOrderNo()) ||
        Objects.isNull(deliveryOrderDTO.getStoreId()) || Objects
        .isNull(deliveryOrderDTO.getCityId());
    if (flag) {
      log.warn("【发货接口】缺少字段信息，orderConfirmReceiveDTO:{}", JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "orderNo,storeId,cityId不能为空");
    }
    //配送信息校验
    PlainResult deliveryInfoCheckPlainResult=handleDeliveryInfo(deliveryOrderDTO);
    if(!deliveryInfoCheckPlainResult.isSuccess()){
      return deliveryInfoCheckPlainResult;
    }
    //发货明细校验
    PlainResult deliveryItemListCheckPlainResult=handleDeliveryItemDTOList(deliveryOrderDTO);
    if(!deliveryItemListCheckPlainResult.isSuccess()){
      return deliveryItemListCheckPlainResult;
    }
    return PlainResultBuilder.success(null);
  }

  public PlainResult<?> handleDeliveryInfo(DeliveryOrderDTO deliveryOrderDTO) {
    DeliveryInfoDTO deliveryInfoDTO=deliveryOrderDTO.getDeliveryInfoDTO();
    if (Objects.isNull(deliveryInfoDTO)) {
      log.warn("【发货接口】配送信息参数为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
          Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    //检查发货方式是否为快递配送
    if(Objects.isNull(deliveryInfoDTO.getDeliveryType())){
      log.warn("【发货接口】缺少发货方式字段信息，orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "发货方式字段信息不能为空");
    }else{
      if(DeliveryTypeEnum.EXPRESS_MERCHANT_CALL.getCode()==deliveryInfoDTO.getDeliveryType()){
        PlainResult deliveryInfoDetailCheckPlainResult=handleDeliveryInfoDetail(deliveryInfoDTO,deliveryOrderDTO);
        if(!deliveryInfoDetailCheckPlainResult.isSuccess()){
          return deliveryInfoDetailCheckPlainResult;
        }
      }else{
        log.warn("【发货接口】发货方式不是快递-商家呼叫快递,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
        return PlainResultBuilder
            .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "发货方式错误");
      }
    }
    return PlainResultBuilder.success(null);
  }

  public PlainResult<?> handleDeliveryInfoDetail(DeliveryInfoDTO deliveryInfoDTO,DeliveryOrderDTO deliveryOrderDTO) {
    //物流信息校验
    ExpressMerchantDTO expressMerchantDTO=deliveryInfoDTO.getExpressMerchantDTO();
    if (Objects.isNull(expressMerchantDTO)) {
      log.warn("【发货接口】物流信息参数为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "物流信息参数为空");
    }else if(StringUtils.isBlank(expressMerchantDTO.getExpressNo())||StringUtils.isBlank(expressMerchantDTO.getExpressName())){
      log.warn("【发货接口】物流信息快递单号或承运公司为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "物流信息快递单号或承运公司为空");
    }
    //收货人地址校验
    DeliveryAddressDTO deliveryAddressDTO=deliveryInfoDTO.getReceiverAddress();
    if (Objects.isNull(deliveryAddressDTO)) {
      log.warn("【发货接口】收货人地址为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人地址为空");
    }else if(StringUtils.isBlank(deliveryAddressDTO.getProvince())
        ||StringUtils.isBlank(deliveryAddressDTO.getCity())
        ||StringUtils.isBlank(deliveryAddressDTO.getCounty())
        ||StringUtils.isBlank(deliveryAddressDTO.getAddressDetail())){
      log.warn("【发货接口】收货人地址详细参数为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人地址详细参数为空");
    }
    //收货人信息校验
    DeliveryUserDTO deliveryUserDTO=deliveryInfoDTO.getReceiverUser();
    if (Objects.isNull(deliveryUserDTO)) {
      log.warn("【发货接口】收货人为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人为空");
    }else if(StringUtils.isBlank(deliveryUserDTO.getName())
        ||StringUtils.isBlank(deliveryUserDTO.getPhone()) || Objects.isNull(deliveryUserDTO.getUserId())){
      log.warn("【发货接口】收货人详细参数为空,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人详细参数为空");
    }
    return PlainResultBuilder.success(null);
  }

  public PlainResult<?> handleDeliveryItemDTOList(DeliveryOrderDTO deliveryOrderDTO) {
    List<DeliveryItemDTO> deliverItemList=deliveryOrderDTO.getDeliveryItemDTOList();
    if (CollectionUtils.isEmpty(deliverItemList)) {
      log.warn("【发货接口】发货明细,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "发货明细数据为空");
    } else {
      for (DeliveryItemDTO deliveryItemDTO : deliverItemList) {
        if (Objects.isNull(deliveryItemDTO.getCityProductId())
            || Objects.isNull(deliveryItemDTO.getNum())
            || Objects.isNull(deliveryItemDTO.getProductNo())) {
          log.warn(
              "【发货接口】存在数据不存在的发货明细,orderNo:{},deliveryOrderDTO:{}", deliveryOrderDTO.getOrderNo(),JSONObject.toJSONString(deliveryOrderDTO));
          return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在数据不存在的发货明细");
        }
      }
      return PlainResultBuilder.success(null);
    }
  }



}
