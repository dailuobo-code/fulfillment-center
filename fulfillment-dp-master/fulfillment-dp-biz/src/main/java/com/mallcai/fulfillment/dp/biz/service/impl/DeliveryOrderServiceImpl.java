package com.mallcai.fulfillment.dp.biz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.common.exception.BizException;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.DeliveryOrderService;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDeliveryResultDTO;
import com.mallcai.fulfillment.dp.api.response.BathDeliveryOrderDTO;
import com.mallcai.fulfillment.dp.api.response.ErrorOrderInfo;
import com.mallcai.fulfillment.dp.biz.service.impl.inner.DeliveryOrderInnerService;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.DeliveryOrderServiceParamChecker;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.MultiPkgServiceChecker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Liu Yang
 * @date: 2019-09-06 16:54
 */
@Service
@Slf4j
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    public static final int BATCH_ORDER_MAX_NUM = 100;

    @Resource
    private DeliveryOrderServiceParamChecker deliveryOrderServiceParamChecker;

    @Resource
    private DeliveryOrderInnerService deliveryOrderInnerService;

    @Override
    public PlainResult<Boolean> orderSend(DeliveryOrderDTO deliveryOrderDTO) {
        PlainResult plainResult = deliveryOrderServiceParamChecker.checkDeliveryOrderQuery(deliveryOrderDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        return deliveryOrderInnerService.orderSend(deliveryOrderDTO);
    }

    @Override
    public PlainResult<BathDeliveryOrderDTO> batchOrderSend(BatchDeliveryOrderDTO batchDeliveryOrderDTO) {
        PlainResult<BathDeliveryOrderDTO> result = new PlainResult<>();
        BathDeliveryOrderDTO bathDeliveryOrderDTO = new BathDeliveryOrderDTO();
        List<String> errorOrderNo = new ArrayList<>();
        List<DeliveryOrderDTO> deliveryOrderDTOList = batchDeliveryOrderDTO.getDeliveryOrderDTOList();
        if (CollectionUtils.isEmpty(deliveryOrderDTOList)) {
            log.warn("【批量发货接口】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        if (deliveryOrderDTOList.size() > BATCH_ORDER_MAX_NUM) {
            log.warn("【批量发货接口】超过最大批次限制");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    Errors.FULFILLMENT_PARAMS_ERROR.getMessage());
        }
        deliveryOrderDTOList.forEach(deliveryOrderDTO -> {
            try {
                deliveryOrderServiceParamChecker.checkDeliveryOrderQuery(deliveryOrderDTO);
                PlainResult<Boolean> orderResult = deliveryOrderInnerService.orderSend(deliveryOrderDTO);
                if (!orderResult.isSuccess() || !orderResult.getData()) {
                    errorOrderNo.add(deliveryOrderDTO.getOrderNo());
                }
            } catch (BizException e) {
                log.error("业务异常，发货失败数据,deliveryOrderDTO{}", JSON.toJSONString(deliveryOrderDTO), e);
                errorOrderNo.add(deliveryOrderDTO.getOrderNo());
                //发送补偿
            } catch (Exception e) {
                log.error("未知异常，发货失败数据,deliveryOrderDTO{}", JSON.toJSONString(deliveryOrderDTO), e);
                errorOrderNo.add(deliveryOrderDTO.getOrderNo());
            }
        });

        if (CollectionUtils.isEmpty(errorOrderNo)) {
            bathDeliveryOrderDTO.setAllSuccess(true);
        } else {
            bathDeliveryOrderDTO.setAllSuccess(false);
            bathDeliveryOrderDTO.setErrorOrderNo(errorOrderNo);
        }
        //返回失败数量
        return PlainResultBuilder.success(bathDeliveryOrderDTO);
    }

    @Override
    public PlainResult<Boolean> orderMultiPkgSend(DeliveryOrderMultiPkgDTO deliveryOrderMultiPkgDTO) {
        PlainResult plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }

        return deliveryOrderInnerService.orderMultiPkgSend(deliveryOrderMultiPkgDTO);
    }

    @Override
    public PlainResult<BatchDeliveryResultDTO> batchOrderMultiPkgSend(BatchDeliveryOrderMultiPkgDTO batchDeliveryOrderMultiPkgDTO) {
        BatchDeliveryResultDTO batchDeliveryResultDTO = new BatchDeliveryResultDTO();
        List<ErrorOrderInfo> errorOrderInfos = new ArrayList<>();
        List<DeliveryOrderMultiPkgDTO> deliveryOrderMultiPkgDTOList = batchDeliveryOrderMultiPkgDTO.getDeliveryOrderMultiPkgDTOList();
        if (CollectionUtils.isEmpty(deliveryOrderMultiPkgDTOList)) {
            log.warn("【批量发货接口】参数为空");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    "批量发货信息列表为空");
        }
        if (deliveryOrderMultiPkgDTOList.size() > BATCH_ORDER_MAX_NUM) {
            log.warn("【批量发货接口】超过最大批次限制");
            return PlainResultBuilder.fail(Errors.FULFILLMENT_PARAMS_ERROR.getCode(),
                    "超过最大批次限制, 单次只允许100个订单");
        }
        deliveryOrderMultiPkgDTOList.forEach(deliveryOrderMultiPkgDTO -> {
            try {
                PlainResult plainResult = MultiPkgServiceChecker.checkDeliveryOrderMultiPkgInfo(deliveryOrderMultiPkgDTO);
                if (!plainResult.isSuccess()) {
                    errorOrderInfos.add(new ErrorOrderInfo(deliveryOrderMultiPkgDTO.getOrderNo(), plainResult.getMessage()));
                    return;
                }
                PlainResult<Boolean> orderResult = deliveryOrderInnerService.orderMultiPkgSend(deliveryOrderMultiPkgDTO);
                if (!orderResult.isSuccess() || !orderResult.getData()) {
                    String message = Errors.ORDERID_NOT_EXIST_EXCEPTION.getCode() == orderResult.getCode() ? String.valueOf(orderResult.getCode()) : plainResult.getMessage();
                    errorOrderInfos.add(new ErrorOrderInfo(deliveryOrderMultiPkgDTO.getOrderNo(), message));
                }
            } catch (BizException e) {
                log.error("业务异常，发货失败数据,deliveryOrderDTO{}", JSON.toJSONString(deliveryOrderMultiPkgDTO), e);
                errorOrderInfos.add(new ErrorOrderInfo(deliveryOrderMultiPkgDTO.getOrderNo(), e.getMessage()));
                //发送补偿
            } catch (Exception e) {
                log.error("未知异常，发货失败数据,deliveryOrderDTO{}", JSON.toJSONString(deliveryOrderMultiPkgDTO), e);
                errorOrderInfos.add(new ErrorOrderInfo(deliveryOrderMultiPkgDTO.getOrderNo(), "未知异常"));
            }
        });

        if (CollectionUtils.isEmpty(errorOrderInfos)) {
            batchDeliveryResultDTO.setAllSuccess(true);
        } else {
            batchDeliveryResultDTO.setAllSuccess(false);
            batchDeliveryResultDTO.setErrorOrderInfos(errorOrderInfos);
        }
        //返回失败数量
        return PlainResultBuilder.success(batchDeliveryResultDTO);
    }
}
