package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.request.ModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.OrderConfirmReceiveDTO;
import java.io.Serializable;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Liu Yang
 * @description 确认收货接口参数校验器
 * @date: 2019-09-06 17:02
 */
@Component
@Slf4j
public class DeliveryOrderOperateServiceParamChecker implements Serializable {

  private static final long serialVersionUID = -8565784347824600783L;


  public PlainResult<?> checkOrderConfirmQuery(
      OrderConfirmReceiveDTO orderConfirmReceiveDTO){
    if (Objects.isNull(orderConfirmReceiveDTO)) {
      log.warn("【确认收货】参数为空");
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    boolean flag = Objects.isNull(orderConfirmReceiveDTO.getCityId()) ||
        Objects.isNull(orderConfirmReceiveDTO.getUserId())  || Objects.isNull(orderConfirmReceiveDTO.getOrderNo());
    if (flag) {
      log.warn("【确认收货】缺少字段信息，orderConfirmReceiveDTO:{}", JSONObject.toJSONString(orderConfirmReceiveDTO));
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "orderNo,userId,cityId不能为空");
    }
    return PlainResultBuilder.success(null);
  }


  public PlainResult<?> checkEditExpressInfoQuery(ModifyExpressDTO modifyExpressDTO){
    if (Objects.isNull(modifyExpressDTO)) {
      log.warn("【物流信息查询】参数为空");
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    boolean flag = Objects.isNull(modifyExpressDTO.getExpressNo())||Objects.isNull(modifyExpressDTO.getExpressName())||Objects.isNull(modifyExpressDTO.getOrderNo());
    if (flag) {
      log.warn("【物流信息查询】缺少物流编号信息或承运公司名称或订单编号，modifyExpressDTO:{}", JSONObject.toJSONString(modifyExpressDTO));
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少物流编号信息或承运公司名称或订单编号");
    }
    return PlainResultBuilder.success(null);
  }

}
