package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil.ExpireType;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil.SetPremise;
import com.mallcai.fulfillment.dp.api.common.dto.*;
import com.mallcai.fulfillment.dp.api.enums.NumberTypeEnum;
import com.mallcai.fulfillment.dp.api.enums.PickUpSourceEnum;
import com.mallcai.fulfillment.dp.api.enums.TimeTypeEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.request.OrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.OrderRelationQueryDTO;
import com.mallcai.fulfillment.dp.api.response.PickUpResultDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOperateDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOrderAssemblyDTO;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.common.utils.RedisKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author yl
 * @description 订单参数校验器
 * @date 2019-07-16
 */
@Component
@Slf4j
public class OrderParamChecker {

    @Resource
    private JedisClientUtil jedisClientUtil;
    private static final String OK = "OK";

    /**
     * 关联订单查询参数校验
     */
    public PlainResult<List<TradeOrderAssemblyDTO>> checkOrderRelationQuery(
            OrderRelationQueryDTO orderRelationQuery) {

        if (Objects.isNull(orderRelationQuery)
                || StringUtils.isBlank(orderRelationQuery.getNumber())
                || Objects.isNull(orderRelationQuery.getNumberType())) {
            log.warn("【关联订单查询】参数为空");
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }

        if (!NumberTypeEnum.isContainType(orderRelationQuery.getNumberType())) {
            log.warn("【关联订单查询】 编号类型不匹配，numberType:{}", orderRelationQuery.getNumber());
            return PlainResultBuilder
                    .fail(Errors.NUMBER_TYPE_NOT_MATCHING.getCode(),
                            Errors.NUMBER_TYPE_NOT_MATCHING.getMessage());
        }

        boolean cityAndStoreFlag = Objects.isNull(orderRelationQuery.getCityId())
                || Objects.isNull(orderRelationQuery.getStoreId());
        if (cityAndStoreFlag) {
            log.warn("【关联订单查询】缺少城市和门店信息，orderRelationQuery:{}",
                    JSONObject.toJSONString(orderRelationQuery));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "cityId或storeId为空");
        }
        TimeRangeDTO timeRangeDTO = orderRelationQuery.getTimeRange();
        if (!TimeTypeEnum.isContainType(timeRangeDTO.getTimeType())) {
            log.warn("【关联订单查询】 TimeType不匹配，{}", JSONObject.toJSONString(orderRelationQuery));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "timeType不匹配");
        }
        if (Objects.isNull(timeRangeDTO.getAppointDay())) {
            if (Objects.isNull(timeRangeDTO.getBeginTime())
                    || Objects.isNull(timeRangeDTO.getEndTime())) {
                log.warn("【关联订单查询】 时间区间参数错误，{}", JSONObject.toJSONString(orderRelationQuery));
                return PlainResultBuilder
                        .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "时间区间参数错误");
            }

            if (timeRangeDTO.getBeginTime().getTime() > timeRangeDTO.getEndTime().getTime()) {
                log.warn("【关联订单查询】 开始时间大于结束时间，{}", JSONObject.toJSONString(orderRelationQuery));
                return PlainResultBuilder
                        .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "开始时间大于结束时间");
            }
        }

        if (!PickUpSourceEnum.isContainType(orderRelationQuery.getOperateSource())) {
            log.warn("【关联订单查询】 请求来源有误，{}", JSONObject.toJSONString(orderRelationQuery));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "请求来源有误");
        }
        return PlainResultBuilder.success(null);
    }

    public PlainResult<PickUpResultDTO> checkOrderBatchQuery(
            OrderQueryDTO orderQueryDTO) {
        if (Objects.isNull(orderQueryDTO)) {
            log.warn("【批量订单查询】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        OrderQuerySelector orderQuerySelector = orderQueryDTO.getOrderQuerySelector();
        if (Objects.isNull(orderQuerySelector)
                || Objects.isNull(orderQuerySelector.getCity())
                || Objects.isNull(orderQuerySelector.getStore())
                || Objects.isNull(orderQuerySelector.getTimeRange())) {
            log.warn("【批量订单查询】参数为空，{}", JSONObject.toJSONString(orderQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        CityDTO cityDTO = orderQuerySelector.getCity();
        StoreDTO storeDTO = orderQuerySelector.getStore();

        if (Objects.isNull(cityDTO.getCityId()) || Objects.isNull(storeDTO.getStoreId())) {
            log.warn("【批量订单查询】 cityId,或storeId为空，{}", JSONObject.toJSONString(orderQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "cityId或storeId为空");
        }

        TimeRangeDTO timeRangeDTO = orderQuerySelector.getTimeRange();
        if (!TimeTypeEnum.isContainType(timeRangeDTO.getTimeType())) {
            log.warn("【批量订单查询】 TimeType不匹配，{}", JSONObject.toJSONString(orderQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "timeType不匹配");
        }
        if (Objects.isNull(timeRangeDTO.getAppointDay())) {
            if (Objects.isNull(timeRangeDTO.getBeginTime()) || Objects.isNull(timeRangeDTO.getEndTime())) {
                log.warn("【批量订单查询】 时间区间参数错误，{}", JSONObject.toJSONString(orderQueryDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "时间区间参数错误");
            }

            if (timeRangeDTO.getBeginTime().getTime() > timeRangeDTO.getEndTime().getTime()) {
                log.warn("【批量订单查询】 开始时间大于结束时间，{}", JSONObject.toJSONString(orderQueryDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "开始时间大于结束时间");
            }
        }
        if (!PickUpSourceEnum.isContainType(orderQueryDTO.getOperateSource())) {
            log.warn("【关联订单查询】 请求来源有误，{}", JSONObject.toJSONString(orderQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "请求来源有误");
        }
        return PlainResultBuilder.success(null);
    }


    /**
     * 订单交付参数检查
     */
    public PlainResult<TradeOperateDTO> checkDeliverOrder(DeliveredDetailDTO deliveredDetailDTO) {
        if (Objects.isNull(deliveredDetailDTO)) {
            log.warn("【订单交付】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        if (Objects.isNull((deliveredDetailDTO.getStoreId()))
                || Objects.isNull(deliveredDetailDTO.getUserId())
                || Objects.isNull(deliveredDetailDTO.getCityId())
                || Objects.isNull(deliveredDetailDTO.getOperatorId())) {
            log.warn("【订单交付】基础参数为空：{}", JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "门店、用户、操作人id可能不存在");
        }
        if (!PickUpSourceEnum.isContainType(deliveredDetailDTO.getOperateSource())) {
            log.warn("【订单交付】 请求来源有误，{}", JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "请求来源有误");
        }
        List<DeliveredOrder> deliveredOrderList = deliveredDetailDTO.getDeliveredOrderList();
        if (CollectionUtils.isEmpty(deliveredOrderList)) {
            log.warn("【订单交付】已取货订单数据为空：userId:{},request:{}", deliveredDetailDTO.getUserId(),JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "已取货订单数据为空");
        }
        List<String> errorOrderIdList = Lists.newArrayList();
        List<String> successOrderIdList = Lists.newArrayList();

        Iterator<DeliveredOrder> iter = deliveredOrderList.iterator();
        while (iter.hasNext()) {
            DeliveredOrder deliveredOrder = iter.next();
            try {
                PlainResult plainResult = handleDeliveredOrder(deliveredOrder, deliveredDetailDTO);
                if (!plainResult.isSuccess()) {
                    errorOrderIdList.add(deliveredOrder.getOrderId());
                    iter.remove();
                } else {
                    successOrderIdList.add(deliveredOrder.getOrderId());
                }
            } catch (Exception e) {
                log.error("【交付】前置检查出错 orderId：" + deliveredOrder.getOrderId(), e);
                errorOrderIdList.add(deliveredOrder.getOrderId());
                iter.remove();
            }
        }
        TradeOperateDTO tradeOperateDTO = new TradeOperateDTO();
        tradeOperateDTO.setErrorOrderIdList(errorOrderIdList);
        tradeOperateDTO.setSuccessOrderIdList(successOrderIdList);
        return PlainResultBuilder.success(tradeOperateDTO);
    }


    /**
     * 订单交付参数检查
     */
    public PlainResult<TradeOperateDTO> checkDeliverOrderOneFailAllFail(DeliveredDetailDTO deliveredDetailDTO) {
        if (Objects.isNull(deliveredDetailDTO)) {
            log.warn("【订单交付】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        if (Objects.isNull((deliveredDetailDTO.getStoreId()))
                || Objects.isNull(deliveredDetailDTO.getUserId())
                || Objects.isNull(deliveredDetailDTO.getCityId())
                || Objects.isNull(deliveredDetailDTO.getOperatorId())) {
            log.warn("【订单交付】基础参数为空：{}", JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "门店、用户、操作人id可能不存在");
        }
        if (!PickUpSourceEnum.isContainType(deliveredDetailDTO.getOperateSource())) {
            log.warn("【订单交付】 请求来源有误，{}", JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "请求来源有误");
        }
        List<DeliveredOrder> deliveredOrderList = deliveredDetailDTO.getDeliveredOrderList();
        if (CollectionUtils.isEmpty(deliveredOrderList)) {
            log.warn("【订单交付】已取货订单数据为空：userId:{},request:{}", deliveredDetailDTO.getUserId(),JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "已取货订单数据为空");
        }
        List<String> errorOrderIdList = Lists.newArrayList();
        List<String> successOrderIdList = Lists.newArrayList();

        Iterator<DeliveredOrder> iter = deliveredOrderList.iterator();
        while (iter.hasNext()) {
            DeliveredOrder deliveredOrder = iter.next();
            try {
                PlainResult plainResult = handleDeliveredOrder(deliveredOrder, deliveredDetailDTO);
                if (!plainResult.isSuccess()) {
                    errorOrderIdList.add(deliveredOrder.getOrderId());
                    iter.remove();
                    return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), plainResult.getMessage());
                } else {
                    successOrderIdList.add(deliveredOrder.getOrderId());
                }
            } catch (Exception e) {
                log.error("【交付】前置检查出错 orderId：" + deliveredOrder.getOrderId(), e);
                errorOrderIdList.add(deliveredOrder.getOrderId());
                iter.remove();
            }
        }
        TradeOperateDTO tradeOperateDTO = new TradeOperateDTO();
        tradeOperateDTO.setErrorOrderIdList(errorOrderIdList);
        tradeOperateDTO.setSuccessOrderIdList(successOrderIdList);
        return PlainResultBuilder.success(tradeOperateDTO);
    }


    private PlainResult handleDeliveredOrder(DeliveredOrder deliveredOrder,
                                             DeliveredDetailDTO deliveredDetailDTO) {
        if (StringUtils.isBlank(deliveredOrder.getOrderId())) {
            log.warn("【订单交付】订单号不存在：userId:{},request:{}", deliveredDetailDTO.getUserId(),JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在订单号不存在的取货订单");
        }

        String lockResult = jedisClientUtil.set(RedisKeyGenerator.deliveredOrderLockKey() + deliveredOrder.getOrderId(), "",SetPremise.NX, ExpireType.Milliseconds, 5000L);
        if (!OK.equals(lockResult)) {
            log.warn("同用户同门店订单正在交付，稍后再试：userId：{}", deliveredDetailDTO.getUserId());
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "同用户同门店订单正在交付");
        }

        List<ProductDTO> productDtoList = deliveredOrder.getProductDtoList();

        if (CollectionUtils.isEmpty(productDtoList)) {
            log.warn("【订单交付】存在商品数据不存在的订单：userId:{},orderId:{},request:{}",deliveredDetailDTO.getUserId(), deliveredOrder.getOrderId(),JSONObject.toJSONString(deliveredDetailDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在商品数据不存在的订单");
        }
        for (ProductDTO productDTO : productDtoList) {
            if (StringUtils.isBlank(productDTO.getProductNo())
                    || Objects.isNull(productDTO.getCityProductId())
                    || Objects.isNull(productDTO.getNum())) {
                log.warn("【订单交付】存在商品数据不存在的订单：userId:{},orderId:{},request:{}",deliveredDetailDTO.getUserId(), deliveredOrder.getOrderId(),JSONObject.toJSONString(deliveredDetailDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在商品数据不存在的订单");
            }
            List<BarCodeDTO> barCodeDtoList = productDTO.getBarCodeDtoList();
            if (CollectionUtils.isEmpty(barCodeDtoList)) {
                log.warn("【订单交付】存在条码信息不存在的商品：userId:{},orderId:{},productId:{},request:{}",deliveredDetailDTO.getUserId(), deliveredOrder.getOrderId(),productDTO.getCityProductId(),JSONObject.toJSONString(deliveredDetailDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在条码信息不存在的商品");
            }
            if (!Objects.equals(productDTO.getNum(), barCodeDtoList.size())) {
                log.warn("【订单交付】存在商品数量和条码对不上的订单：userId:{},orderId:{},productId:{},request:{}",
                        deliveredDetailDTO.getUserId(), deliveredOrder.getOrderId(),
                        productDTO.getCityProductId(),
                        JSONObject.toJSONString(deliveredDetailDTO));
                return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在商品数量和条码对不上的订单");
            }
            for (BarCodeDTO barcodeDTO : barCodeDtoList) {
                if (StringUtils.isBlank(barcodeDTO.getBarCode())
                        || Objects.isNull(barcodeDTO.getStatus())) {
                    log.warn("【订单交付】条码数据部分缺失： userId:{},orderId:{},productId:{},request:{}",
                            deliveredDetailDTO.getUserId(), deliveredOrder.getOrderId(),
                            productDTO.getCityProductId(),
                            JSONObject.toJSONString(deliveredDetailDTO));
                    return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在条码信息不存在的商品");
                }
            }
        }
        return PlainResultBuilder.success(null);
    }

}
