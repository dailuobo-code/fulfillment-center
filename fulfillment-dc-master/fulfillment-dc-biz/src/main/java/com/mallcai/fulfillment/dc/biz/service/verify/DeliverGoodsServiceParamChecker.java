package com.mallcai.fulfillment.dc.biz.service.verify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DeliveryGoodsDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.DeliveryGoodsItemRequestDTO;
import com.mallcai.fulfillment.dc.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.dc.biz.service.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dc.common.exception.Errors;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @author: Liu Yang
 * @description MGR数据校验
 * @date: 2019-10-10 20:11
 */
@Component
@Slf4j
public class DeliverGoodsServiceParamChecker implements Serializable {

  @Resource
  private GoodsGrayRouterService goodsGrayRouterService;
  private static final long serialVersionUID = -4250157036590098862L;
  public PlainResult<?> checkDeliveryGoodsItem(DeliveryGoodsDTO deliveryGoodsDTO){
    if (Objects.isNull(deliveryGoodsDTO)|| CollectionUtils.isEmpty(deliveryGoodsDTO.getDeliveryGoodsItemRequestDTOList())) {
      log.warn("【物流信息查询】参数为空");
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
    }
    for(DeliveryGoodsItemRequestDTO deliveryGoodsItemRequestDTO:deliveryGoodsDTO.getDeliveryGoodsItemRequestDTOList()){
      PlainResult result=checkDeliveryGoodsItemRequestDTO(deliveryGoodsItemRequestDTO);
      if(!result.isSuccess()){
        return result;
      }
    }
    return PlainResultBuilder.success(null);
  }
  public PlainResult<?> checkDeliveryGoodsItemRequestDTO(DeliveryGoodsItemRequestDTO deliveryGoodsItemRequestDTO){
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getOrderType())){
      log.warn("【MGR接口】缺少订单类型信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少订单类型信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getProductNo())){
      log.warn("【MGR接口】缺少总部商品ID信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少总部商品ID信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getCityProductId())){
      log.warn("【MGR接口】缺少城市商品ID信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少城市商品ID信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getProductNum())){
      log.warn("【MGR接口】缺少商品数量信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少商品数量信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getStoreId())){
      log.warn("【MGR接口】缺少门店信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少门店信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getWarehouseId())){
      log.warn("【MGR接口】缺少仓库ID信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少仓库ID信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getCityId())){
      log.warn("【MGR接口】缺少城市ID信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少城市ID信息");
    }
    if(Objects.isNull(deliveryGoodsItemRequestDTO.getImportTime())){
      log.warn("【MGR接口】缺少导入时间信息，deliveryGoodsItemRequestDTO:{}", JSONObject.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResultBuilder
          .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少导入时间信息");
    }
    if(!goodsGrayRouterService.isNeedRoute(deliveryGoodsItemRequestDTO.getCityId())){
      log.warn("本城市没有开通货品开关，无法进行数据导入,sortingRequestDTO:{}",JSON.toJSONString(deliveryGoodsItemRequestDTO));
      return PlainResult.fail("运营导入数据失校验失败，本城市没有开通货品开关!cityId="+deliveryGoodsItemRequestDTO.getCityId());
    }
    return PlainResultBuilder.success(null);
  }
}
