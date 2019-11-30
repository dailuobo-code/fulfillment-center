package com.mallcai.fulfillment.dp.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.common.exception.Errors;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.dp.api.enums.OrderStatusEnum;
import com.mallcai.fulfillment.dp.biz.service.impl.assembly.OrderAssembly;
import com.mallcai.fulfillment.dp.biz.service.impl.execute.OrderExecuteService;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailJsDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.OrderQueryParamDTO;
import com.mallcai.fulfillment.dp.api.DpOrderQueryService;
import com.mallcai.fulfillment.dp.api.common.dto.OrderAggregationOption;
import com.mallcai.fulfillment.dp.api.common.dto.ProductDTO;
import com.mallcai.fulfillment.dp.api.request.OrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.OrderRelationQueryDTO;
import com.mallcai.fulfillment.dp.api.response.PickUpResultDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOrderAssemblyDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.verify.OrderParamChecker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yl
 * @description 订单查询服务实现
 * @date 2019-07-15
 */
@Service
@Slf4j
public class DpOrderQueryServiceImpl implements DpOrderQueryService {

    public static final int LIMIT = 500;
    @Resource
    private OrderParamChecker orderParamChecker;
    @Resource
    private OrderAssembly orderAssembly;
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private OrderDetailJsDAO orderDetailJsDAO;
    @Resource
    private OrderDetailDAO orderDetailDAO;
    @Resource
    private OrderExecuteService orderExecuteService;

    private static final List<Integer> ORDER_STATUS_LIST = Arrays
            .asList(OrderStatusEnum.WAIT_EVALUATE.getType(), OrderStatusEnum.REFUND.getType(),
                    OrderStatusEnum.FINISH.getType(),
                    OrderStatusEnum.AFTER_SALE_PROCESSING.getType(),
                    OrderStatusEnum.AFTER_SALE_DONE.getType());


    @Override
    public PlainResult<List<TradeOrderAssemblyDTO>> queryRelationOrderList(
            OrderRelationQueryDTO orderRelationQuery) {
        try {
            PlainResult<List<TradeOrderAssemblyDTO>> plainResult = orderParamChecker
                    .checkOrderRelationQuery(orderRelationQuery);
            if (!plainResult.isSuccess()) {
                return plainResult;
            }
            Integer userId = orderAssembly
                    .getUserId(orderRelationQuery.getNumberType(), orderRelationQuery.getNumber());
            if (Objects.isNull(userId)) {
                log.warn("【关联订单查询】useId 不存在：request:{}", JSONObject.toJSONString(orderRelationQuery));
                return PlainResultBuilder.success(null);
            }

            OrderQueryParamDTO orderQueryParamDTO = orderAssembly
                    .handleQueryParamAssembly(orderRelationQuery, userId);

            List<OrderDO> orderDoList = orderDAO
                    .selectByOrderQueryParam(orderQueryParamDTO);

            OrderAggregationOption option = new OrderAggregationOption();
            option.setIncludeOrderItem(true);
            option.setIncludeOrderBase(true);

            List<TradeOrderAssemblyDTO> tradeOrderAssemblyDtoList = orderAssembly
                    .handleOrderAssembly(orderDoList, option);
            return PlainResultBuilder.success(tradeOrderAssemblyDtoList);
        } catch (Exception e) {
            log.error("【关联订单查询】", e);
            return PlainResultBuilder
                    .fail(Errors.FULFILLMENT_SYSTEM_ERROR.getCode(), Errors.FULFILLMENT_SYSTEM_ERROR.getMessage());
        }
    }

    @Override
    public PlainResult<List<TradeOrderAssemblyDTO>> queryOrderAndLock(
            OrderRelationQueryDTO orderRelationQuery) {

        PlainResult<List<TradeOrderAssemblyDTO>> listPlainResult = queryRelationOrderList(
                orderRelationQuery);
        orderExecuteService.pickupLock(listPlainResult);
        return listPlainResult;
    }


    @Override
    public PlainResult<PickUpResultDTO> queryBatchOrderList(OrderQueryDTO orderQueryDTO) {
        //参数校验
        PlainResult<PickUpResultDTO> plainResult = orderParamChecker.checkOrderBatchQuery(orderQueryDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        //参数组装
        OrderQueryParamDTO orderQueryParamDTO = orderAssembly.handleBatchOrderParam(orderQueryDTO);
        List<OrderDO> orderDoList = orderDAO.selectByOrderQueryParam(orderQueryParamDTO);

        Integer count = orderDAO.countByOrderQueryParam(orderQueryParamDTO);
        count = Objects.isNull(count) ? 0 : count;
        OrderAggregationOption option = orderQueryDTO.getOrderAggregationOption();
        if (Objects.isNull(option)) {
            option = new OrderAggregationOption();
            option.setIncludeOrderBase(true);
            option.setIncludeOrderItem(true);
        }
        List<TradeOrderAssemblyDTO> tradeOrderAssemblyDtoList = orderAssembly.handleOrderAssembly(orderDoList, option);
        PickUpResultDTO pickUpResultDTO = new PickUpResultDTO();
        pickUpResultDTO.setTradeOrderAssemblyList(tradeOrderAssemblyDtoList);
        pickUpResultDTO.setOffset(orderQueryParamDTO.getOffset() + orderDoList.size());
        pickUpResultDTO.setCount(count);
        Integer offset = orderQueryParamDTO.getOffset();
        if (Objects.isNull(offset)) {
            offset = 0;
        }
        pickUpResultDTO.setHasNext((offset + orderDoList.size()) < count);
        return PlainResultBuilder.success(pickUpResultDTO);
    }

    /**
     * 查询强制结算订单列表.
     *
     * @param orderQueryDTO 订单查询dto
     * @return PlainResult
     */
    @Override
    public PlainResult<PickUpResultDTO> queryForcedSettlemenOrderList(OrderQueryDTO orderQueryDTO) {
        //参数校验
        PlainResult<PickUpResultDTO> plainResult = orderParamChecker.checkOrderBatchQuery(orderQueryDTO);
        if (!plainResult.isSuccess()) {
            return plainResult;
        }
        //参数组装
        OrderQueryParamDTO orderQueryParamDTO = orderAssembly.handleBatchOrderParam(orderQueryDTO);
        orderQueryParamDTO.setStatusList(ORDER_STATUS_LIST);
        List<OrderDO> orderDoList = new ArrayList<>();
        boolean flag = true;
        Integer offset = 0;
        orderQueryParamDTO.setLimit(LIMIT);
        // 分页查询 今天订单
        while (flag) {
            orderQueryParamDTO.setOffset(offset);
            List<OrderDO> orderList = orderDAO.selectByOrderQueryParam(orderQueryParamDTO);
            if (CollectionUtils.isEmpty(orderList)) {
                flag = false;
            }
            offset = offset + orderList.size();
            List<String> orderIdList = orderList.stream().map(OrderDO::getOrderId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderIdList)) {
                // 查询订单中存在强结的订单
                List<String> jsOrderIdList = orderDetailJsDAO.queryForcedSettlemenOrderList(orderIdList);
                if (CollectionUtils.isEmpty(jsOrderIdList)) {
                    continue;
                }
                orderList.forEach(order -> {
                    if (jsOrderIdList.contains(order.getOrderId())) {
                        orderDoList.add(order);
                    }
                });
            }
        }
        if (CollectionUtils.isEmpty(orderDoList)) {
            PickUpResultDTO pickUpResultDTO = new PickUpResultDTO();
            pickUpResultDTO.setTradeOrderAssemblyList(null);
            return PlainResultBuilder.success(pickUpResultDTO);
        }
        OrderAggregationOption option = orderQueryDTO.getOrderAggregationOption();
        if (Objects.isNull(option)) {
            option = new OrderAggregationOption();
            option.setIncludeOrderBase(true);
            option.setIncludeOrderItem(true);
        }
        List<TradeOrderAssemblyDTO> tradeOrderAssemblyDtoList = orderAssembly.handleOrderAssembly(orderDoList, option);
        PickUpResultDTO pickUpResultDTO = new PickUpResultDTO();
        pickUpResultDTO.setTradeOrderAssemblyList(tradeOrderAssemblyDtoList);
        return PlainResultBuilder.success(pickUpResultDTO);
    }

    /**
     * 查询门店交付库存商品列表.
     *
     * @param orderQueryDTO 订单查询dto
     * @return PlainResult
     */
    @Override
    public PlainResult<List<ProductDTO>> queryOrderProductNumList(OrderQueryDTO orderQueryDTO) {
        //参数校验
        PlainResult<PickUpResultDTO> plainResult = orderParamChecker.checkOrderBatchQuery(orderQueryDTO);
        if (!plainResult.isSuccess()) {
            return PlainResultBuilder.fail(plainResult.getCode(), plainResult.getMessage());
        }
        //参数组装
        OrderQueryParamDTO orderQueryParamDTO = orderAssembly.handleBatchOrderParam(orderQueryDTO);
        List<ProductDTO> productList = new ArrayList<>();
        Map<Integer, Integer> productNumMap = Maps.newHashMap();
        boolean flag = true;
        Integer offset = 0;
        orderQueryParamDTO.setLimit(LIMIT);
        // 分页查询 今天订单
        while (flag) {
            orderQueryParamDTO.setOffset(offset);
            List<OrderDO> orderList = orderDAO.selectByOrderQueryParam(orderQueryParamDTO);
            if (CollectionUtils.isEmpty(orderList)) {
                flag = false;
            }
            offset = offset + orderList.size();
            List<String> orderIdList = orderList.stream().map(OrderDO::getOrderId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderIdList)) {
                // 查询订单详情
                List<OrderDetailDO> orderDetailDoList = orderDetailDAO.selectDetailByOrderIdList(orderIdList);
                if (CollectionUtils.isEmpty(orderDetailDoList)) {
                    continue;
                }
                ProductDTO productDTO = null;
                for (OrderDetailDO orderDetail : orderDetailDoList) {
                    Integer productNum = productNumMap.get(orderDetail.getCityProductId());
                    if (productNum == null) {
                        productDTO = new ProductDTO();
                        productDTO.setCityProductId(orderDetail.getCityProductId());
                        productDTO.setProductName(orderDetail.getProdShowName());
                        productList.add(productDTO);
                        productDTO = null;
                        productNum = 0;
                    }
                    productNumMap.put(orderDetail.getCityProductId(), productNum + orderDetail.getProductNum());
                }
            }
        }
        if (CollectionUtils.isEmpty(productList)) {
            return PlainResultBuilder.success(productList);
        }
        productList.forEach(productDTO -> productDTO.setNum(productNumMap.get(productDTO.getCityProductId())));
        return PlainResultBuilder.success(productList);
    }

}
