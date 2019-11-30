package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.ExpressMerchantDTO;
import com.mallcai.fulfillment.dp.api.request.ModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.response.MilkResultDTO;
import java.io.Serializable;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author: Liu Yang
 * @description 物流相关服务接口校验器
 * @date: 2019-09-17 19:40
 */
@Component
@Slf4j
public class DeliveryOrderQueryServiceParamChecker implements Serializable {

  private static final long serialVersionUID = 5993962677421262655L;

  public PlainResult<?> checkExpressInfoQuery(DeliveryOrderQueryDTO deliveryOrderQueryDTO){
    if (Objects.isNull(deliveryOrderQueryDTO)) {
      log.warn("【物流信息查询】参数为空");
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    boolean flag = Objects.isNull(deliveryOrderQueryDTO.getOrderNo());
    if (flag) {
      log.warn("【物流信息查询】缺少订单编号信息，deliveryOrderQueryDTO:{}", JSONObject.toJSONString(deliveryOrderQueryDTO));
      return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "订单编号为空");
    }
    return PlainResultBuilder.success(null);
  }

}
