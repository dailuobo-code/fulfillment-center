package com.mallcai.fulfillment.dp.biz.service.impl.verify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.enums.DeliveryTypeEnum;
import com.mallcai.fulfillment.dp.api.request.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author gaoguoming
 * @date 2019-10-29 21:15:13
 */
@Slf4j
public class MultiPkgServiceChecker {
    private static final int MAX_DIST_ORDER_NUM = 9;
    private static final int MAX_BATCH_SIZE = 100;

    public static PlainResult<?> checkMultiPkgExpressModifyReqInfo(MultiPkgModifyExpressDTO multiPkgModifyExpressDTO) {
        if (Objects.isNull(multiPkgModifyExpressDTO)) {
            log.warn("【单品多包裹物流信息修改】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR);
        }

        if (Objects.isNull(multiPkgModifyExpressDTO.getOrderNo())) {
            log.warn("【单品多包裹物流信息修改】缺少字段信息，multiPkgModifyExpressDTO:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "orderNo不能为空");
        }

        List<DistOrderInfo> distOrderInfoList = multiPkgModifyExpressDTO.getDistOrderInfoList();
        if (CollectionUtils.isEmpty(distOrderInfoList)) {
            log.warn("【单品多包裹物流信息修改】缺少配送单信息，multiPkgModifyExpressDTO:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少配送物流信息");
        }

        if (distOrderInfoList.size() > MAX_DIST_ORDER_NUM) {
            log.warn("【单品多包裹物流信息修改】超过最大配送单数，multiPkgModifyExpressDTO:{}", JSONObject.toJSONString(multiPkgModifyExpressDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "不能超过9个配送单");
        }
        String[] expressNames = new String[1];
        Set<String> expressNoSet = new HashSet<>();
        for (DistOrderInfo distOrderInfo : distOrderInfoList) {
            PlainResult plainResult = checkDistOrderInfo(distOrderInfo, multiPkgModifyExpressDTO.getOrderNo(), multiPkgModifyExpressDTO, expressNames, expressNoSet);
            if (!plainResult.isSuccess()) {
                return plainResult;
            }
        }

        return PlainResultBuilder.success(null);
    }

    private static PlainResult checkDistOrderInfo(DistOrderInfo distOrderInfo, String orderNo, Object reqInfo, String[] expressNames, Set<String> expressNoSet) {
        if (Objects.isNull(distOrderInfo)) {
            log.warn("【单品多包裹配送信息】配送单参数为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "配送单参数为空");
        }
        DeliveryItemDTO deliveryItemDTO = distOrderInfo.getDeliveryItemDTO();
        if (Objects.isNull(deliveryItemDTO)) {
            log.warn("【单品多包裹配送信息】配送商品明细为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "配送商品明细为空");
        }
        if (Objects.isNull(deliveryItemDTO.getCityProductId())
                || Objects.isNull(deliveryItemDTO.getNum())
                || Objects.isNull(deliveryItemDTO.getProductNo())) {
            log.warn("【单品多包裹配送信息】存在数据不存在的发货明细, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "存在数据不存在的发货明细");
        }
        DeliveryInfoDTO deliveryInfoDTO = distOrderInfo.getDeliveryInfoDTO();
        if (Objects.isNull(deliveryInfoDTO)) {
            log.warn("【单品多包裹配送信息】配送信息为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "配送信息为空");
        }
        //配送信息校验
        PlainResult deliveryInfoCheckPlainResult = handleDeliveryInfo(deliveryInfoDTO, orderNo, reqInfo, expressNames, expressNoSet);
        if(!deliveryInfoCheckPlainResult.isSuccess()){
            return deliveryInfoCheckPlainResult;
        }

        return PlainResultBuilder.success(null);
    }

    private static PlainResult handleDeliveryInfo(DeliveryInfoDTO deliveryInfoDTO, String orderNo, Object reqInfo, String[] expressNames, Set<String> expressNoSet) {
        if (Objects.isNull(deliveryInfoDTO)) {
            log.warn("【单品多包裹配送信息】配送信息参数为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    "配送信息参数为空");
        }
        //检查发货方式是否为快递配送
        if(Objects.isNull(deliveryInfoDTO.getDeliveryType())){
            log.warn("【单品多包裹配送信息】缺少发货方式字段信息，orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "发货方式字段信息不能为空");
        }else{
            if(DeliveryTypeEnum.EXPRESS_MERCHANT_CALL.getCode() == deliveryInfoDTO.getDeliveryType()){
                PlainResult deliveryInfoDetailCheckPlainResult = handleDeliveryInfoDetail(deliveryInfoDTO, orderNo, reqInfo, expressNames, expressNoSet);
                if(!deliveryInfoDetailCheckPlainResult.isSuccess()){
                    return deliveryInfoDetailCheckPlainResult;
                }
            }else{
                log.warn("【单品多包裹配送信息】发货方式不是快递-商家呼叫快递, orderNo:{}, reqInfo:{}", orderNo,
                        JSON.toJSON(reqInfo));
                return PlainResultBuilder
                        .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "发货方式错误");
            }
        }
        return PlainResultBuilder.success(null);
    }

    private static PlainResult handleDeliveryInfoDetail(DeliveryInfoDTO deliveryInfoDTO, String orderNo, Object reqInfo, String[] expressNames, Set<String> expressNoSet) {
        //物流信息校验
        ExpressMerchantDTO expressMerchantDTO=deliveryInfoDTO.getExpressMerchantDTO();
        if (Objects.isNull(expressMerchantDTO)) {
            log.warn("【单品多包裹配送信息】物流信息参数为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "物流信息参数为空");
        } else if (StringUtils.isBlank(expressMerchantDTO.getExpressNo()) ||
                StringUtils.isBlank(expressMerchantDTO.getExpressName())){
            log.warn("【单品多包裹配送信息】物流信息快递单号或承运公司为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "物流信息快递单号或承运公司为空");
        }
        if (StringUtils.isBlank(expressNames[0])) {
            expressNames[0] = expressMerchantDTO.getExpressName();
        } else if (!expressNames[0].equals(expressMerchantDTO.getExpressName())) {
            log.warn("【单品多包裹配送信息】快递公司超过限制, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "只能指定同一个快递公司");
        }

        if (expressNoSet.contains(expressMerchantDTO.getExpressNo())) {
            log.warn("【单品多包裹配送信息】快递单号重复, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "快递单号重复");
        }
        expressNoSet.add(expressMerchantDTO.getExpressNo());

        if (expressMerchantDTO.getExpressName().length() > 32) {
            log.warn("【单品多包裹配送信息】快递公司名称超过长度限制, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "快递公司名称超过长度限制");
        }

        if (expressMerchantDTO.getExpressNo().length() > 50) {
            log.warn("【单品多包裹配送信息】快递单号超过长度限制, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "快递单号超过长度限制");
        }
        //收货人地址校验
        DeliveryAddressDTO deliveryAddressDTO = deliveryInfoDTO.getReceiverAddress();
        if (Objects.isNull(deliveryAddressDTO)) {
            log.warn("【单品多包裹配送信息】收货人地址为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人地址为空");
        }else if(StringUtils.isBlank(deliveryAddressDTO.getProvince())
                ||StringUtils.isBlank(deliveryAddressDTO.getCity())
                ||StringUtils.isBlank(deliveryAddressDTO.getCounty())
                ||StringUtils.isBlank(deliveryAddressDTO.getAddressDetail())){
            log.warn("【单品多包裹配送信息】收货人地址详细参数为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人地址详细参数为空");
        }
        //收货人信息校验
        DeliveryUserDTO deliveryUserDTO = deliveryInfoDTO.getReceiverUser();
        if (Objects.isNull(deliveryUserDTO)) {
            log.warn("【单品多包裹配送信息】收货人为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人为空");
        }else if(StringUtils.isBlank(deliveryUserDTO.getName())
                ||StringUtils.isBlank(deliveryUserDTO.getPhone()) || Objects.isNull(deliveryUserDTO.getUserId())){
            log.warn("【单品多包裹配送信息】收货人详细参数为空, orderNo:{}, reqInfo:{}", orderNo,
                    JSON.toJSON(reqInfo));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "收货人详细参数为空");
        }
        return PlainResultBuilder.success(null);
    }

    public static PlainResult checkDeliveryOrderMultiPkgInfo(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO) {
        if (Objects.isNull(deliveryOrderMultiPkgDTO)) {
            log.warn("【单品多包裹发货】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR);
        }

        //简单字段信息校验
        boolean flag = Objects.isNull(deliveryOrderMultiPkgDTO.getOrderNo()) ||
                Objects.isNull(deliveryOrderMultiPkgDTO.getStoreId()) || Objects
                .isNull(deliveryOrderMultiPkgDTO.getCityId());
        if (flag) {
            log.warn("【单品多包裹发货】缺少字段信息，deliveryOrderMultiPkgDTO:{}", JSONObject.toJSONString(deliveryOrderMultiPkgDTO));
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "orderNo,storeId,cityId不能为空");
        }

        List<DistOrderInfo> distOrderInfoList = deliveryOrderMultiPkgDTO.getDistOrderInfoList();
        if (CollectionUtils.isEmpty(distOrderInfoList)) {
            log.warn("【单品多包裹发货】缺少配送单信息，deliveryOrderMultiPkgDTO:{}", JSONObject.toJSONString(deliveryOrderMultiPkgDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "缺少配送物流信息");
        }

        if (distOrderInfoList.size() > MAX_DIST_ORDER_NUM) {
            log.warn("【单品多包裹发货】超过最大配送单数，deliveryOrderMultiPkgDTO:{}", JSONObject.toJSONString(deliveryOrderMultiPkgDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "不能超过9个配送单");
        }

        String[] expressNames = new String[1];
        Set<String> expressNoSet = new HashSet<>();
        for (DistOrderInfo distOrderInfo : distOrderInfoList) {
            PlainResult plainResult = checkDistOrderInfo(distOrderInfo, deliveryOrderMultiPkgDTO.getOrderNo(),
                    deliveryOrderMultiPkgDTO, expressNames, expressNoSet);
            if (!plainResult.isSuccess()) {
                return plainResult;
            }
        }
        return PlainResultBuilder.success(null);
    }

    public static PlainResult checkBatchQueryInfo(BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO) {
        if (Objects.isNull(batchDeliveryOrderQueryDTO)) {
            log.warn("【批量查询物流接口】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR);
        }
        List<String> orderNos = batchDeliveryOrderQueryDTO.getOrderNos();
        if (CollectionUtils.isEmpty(orderNos)) {
            log.warn("【批量查询物流接口】查询的订单列表为空，batchDeliveryOrderQueryDTO:{}", JSONObject.toJSONString(batchDeliveryOrderQueryDTO));
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(), "查询的订单列表为空");
        }

        if (orderNos.size() > MAX_BATCH_SIZE) {
            log.warn("【批量查询物流接口】超过最大批次限制");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    "超过最大批次限制");
        }
        return PlainResultBuilder.success(null);
    }
}
