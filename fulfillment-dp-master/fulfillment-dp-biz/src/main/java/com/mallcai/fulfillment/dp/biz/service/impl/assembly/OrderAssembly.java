package com.mallcai.fulfillment.dp.biz.service.impl.assembly;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.backend.common.utils.DatetimeUtil;
import com.mallcai.fulfillment.dp.biz.service.impl.dto.ActualOrderDetailDTO;
import com.mallcai.fulfillment.dp.biz.service.impl.execute.OrderExecuteService;
import com.mallcai.fulfillment.dp.biz.service.impl.execute.SettlementService;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderDetailDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao.OrderPackageDetailDAO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderPackageDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderSortingLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.OrderQueryParamDTO;
import com.mallcai.fulfillment.dp.api.common.dto.*;
import com.mallcai.fulfillment.dp.api.enums.NumberTypeEnum;
import com.mallcai.fulfillment.common.constants.SymbolConstants;
import com.mallcai.fulfillment.common.utils.DateTimeUtils;
import com.mallcai.fulfillment.dp.api.enums.OrderStatusEnum;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.fulfillment.dp.api.request.OrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.OrderRelationQueryDTO;
import com.mallcai.fulfillment.dp.api.response.TradeOrderAssemblyDTO;
import com.mallcai.service.api.IGlobalService;
import com.mallcai.service.user.api.IUserService;
import com.mallcai.service.user.vo.user.AppUser;
import com.mallcai.service.user.vo.user.AppUserInfo;
import com.mallcai.service.user.vo.user.AppUserInfoDTO;
import com.mallcai.service.vo.ic.common.SOACityProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yl
 * @description 数据组装
 * @date 2019-07-16
 */
@Component
@Slf4j
public class OrderAssembly {

    @Resource
    private OrderDAO orderDAO;
    @Resource
    private IUserService iUserService;
    @Resource
    private OrderDetailDAO orderDetailDAO;
    @Resource
    private SettlementService settlementService;
    @Resource
    private OrderPackageDetailDAO orderPackageDetailDAO;
    @Resource
    private IGlobalService iGlobalService;
    @Resource
    private OrderExecuteService orderExecuteService;

    private static final Integer LIMIT = 50;

    private static final Integer DEFAULT_QUERY_LIMIT = 20;

    private static final Integer SHORTING_SIZE = 6;

    /**
     * 根据类型查询userId
     */
    public Integer getUserId(Integer numberType, String number) {
        Integer userId = null;
        if (NumberTypeEnum.PHONE.getType().equals(numberType)) {
            AppUser userByPhone = iUserService.getUserByPhone(number);
            userId = Objects.isNull(userByPhone) ? null : userByPhone.getUserId();
        } else if (NumberTypeEnum.CARD_ID.getType().equals(numberType)) {
            PlainResult<AppUser> userPlainResult = iUserService.getUserByRfidNo(number);
            AppUser userByRfidNo = userPlainResult.getData();
            userId = Objects.isNull(userByRfidNo) ? null : userByRfidNo.getUserId();
        } else if (NumberTypeEnum.ORDER_ID.getType().equals(numberType)) {
            OrderDO orderDO = orderDAO.selectByOrderId(number);
            userId = Objects.isNull(orderDO) ? null : orderDO.getUserId();
        }
        return userId;
    }

    /**
     * 查询参数组装
     */
    public OrderQueryParamDTO handleQueryParamAssembly(OrderRelationQueryDTO orderRelationQuery,
                                                       Integer userId) {
        OrderQueryParamDTO orderQueryParamDTO = new OrderQueryParamDTO();
        BeanUtils.copyProperties(orderRelationQuery, orderQueryParamDTO);
        orderQueryParamDTO.setUserId(Long.valueOf(userId));
        orderQueryParamDTO.setLimit(LIMIT);

        TimeRangeDTO timeRangeDTO = orderRelationQuery.getTimeRange();
        orderQueryParamDTO.setCityId(orderRelationQuery.getCityId());
        orderQueryParamDTO.setStoreId(orderRelationQuery.getStoreId());
        orderQueryParamDTO.setTimeType(timeRangeDTO.getTimeType());
        if (!Objects.isNull(timeRangeDTO.getAppointDay())) {
            Date startDate = DateTimeUtils.addDate(new Date(), 1 - timeRangeDTO.getAppointDay());
            orderQueryParamDTO.setQueryStartDate(DateTimeUtils.formatDateStart(startDate));
            orderQueryParamDTO.setQueryEndDate(DateTimeUtils.formatDateEnd(new Date()));
        } else {
            orderQueryParamDTO
                    .setQueryStartDate(DateTimeUtils.formatDateStart(timeRangeDTO.getBeginTime()));
            orderQueryParamDTO
                    .setQueryEndDate(DateTimeUtils.formatDateEnd(timeRangeDTO.getEndTime()));
        }
        orderQueryParamDTO.setStatusList(orderRelationQuery.getOrderStatusList());
        return orderQueryParamDTO;
    }


    /**
     * 结果处理
     */
    public List<TradeOrderAssemblyDTO> handleOrderAssembly(List<OrderDO> orderDoList, OrderAggregationOption orderAggregationOption) {
        if (CollectionUtils.isEmpty(orderDoList)
                || Objects.isNull(orderAggregationOption)) {
            return null;
        }
        List<TradeOrderAssemblyDTO> tradeOrderAssemblyDtoList = Lists.newArrayList();

        List<Integer> userIds = orderDoList.stream().map(OrderDO::getUserId).collect(Collectors.toList());
        PlainResult<List<AppUserInfoDTO>> userResult = iUserService.getUserByUserIdList(userIds);
        List<AppUserInfoDTO> appUserList = userResult.getData();
        Map<Integer, AppUserInfoDTO> map = Maps.newHashMap();
        appUserList.forEach(appUserInfo -> map.put(appUserInfo.getUserId(), appUserInfo));
        orderDoList.forEach(orderDO -> {
            try {
                TradeOrderAssemblyDTO orderResponseDTO = new TradeOrderAssemblyDTO();
                orderResponseDTO.setPhone(Objects.isNull(map.get(orderDO.getUserId())) ? null : map.get(orderDO.getUserId()).getPhone());
                if (orderAggregationOption.isIncludeOrderBase()) {
                    TradeOrderDTO tradeOrderDTO = new TradeOrderDTO();
                    BeanUtils.copyProperties(orderDO, tradeOrderDTO);
                    tradeOrderDTO.setOrderType(orderDO.getOrderType().byteValue());
                    tradeOrderDTO.setInnerCardId(Objects.isNull(map.get(orderDO.getUserId())) ? null : map.get(orderDO.getUserId()).getRfidInnerCardNo());
                    tradeOrderDTO.setOuterCardId(Objects.isNull(map.get(orderDO.getUserId())) ? null : map.get(orderDO.getUserId()).getRfidOuterCardNo());
                    orderResponseDTO.setOrderDO(tradeOrderDTO);
                    if (orderAggregationOption.isIncludeOrderItem()) {
                        //detail
                        List<OrderDetailDO> orderDetailDoList = orderDetailDAO.selectByOrderId(orderDO.getOrderId());
                        List<TradeOrderDetailDTO> tradeOrderDetailDtoList = Lists.newArrayList();

                        List<Integer> productIdList = orderDetailDoList.stream().map(OrderDetailDO::getCityProductId).collect(Collectors.toList());

                        Response<Map<Integer, SOACityProduct>> cityProductsByIds = iGlobalService.getCityProductsByIds(productIdList);
                        Map<Integer, SOACityProduct> cityProduct = cityProductsByIds.getData();
                        Map<String, SOACityProduct> productNoMap = Maps.newHashMap();
                        cityProduct.forEach((key, value) -> productNoMap.put(value.getProductNo(), value));
                        orderDetailDoList.forEach(orderDetailDO -> {
                            SOACityProduct product = cityProduct.get(orderDetailDO.getCityProductId());
                            TradeOrderDetailDTO tradeOrderDetailDTO = new TradeOrderDetailDTO();
                            BeanUtils.copyProperties(orderDetailDO, tradeOrderDetailDTO);
                            tradeOrderDetailDTO.setProductNo(product.getProductNo());
                            tradeOrderDetailDTO.setBarcode(product.getBarCode());
                            tradeOrderDetailDTO.setPickupNum(orderExecuteService.getDetailJsNum(orderDetailDO.getCityProductId(), orderDO));
                            tradeOrderDetailDtoList.add(tradeOrderDetailDTO);
                        });
                        orderResponseDTO.setOrderDetailDoList(tradeOrderDetailDtoList);
                        //package
                        List<OrderPackageDetailDO> orderPackageDetailDoList = orderPackageDetailDAO.selectByOrderId(orderDO.getOrderId());
                        List<TradePackageDetailDTO> tradePackageDetailDtoList = Lists.newArrayList();
                        if (CollectionUtils.isNotEmpty(orderPackageDetailDoList)) {
                            orderPackageDetailDoList.forEach(orderPackageDetailDO -> {
                                SOACityProduct soaCityProduct = productNoMap.get(String.valueOf(orderPackageDetailDO.getProductNo()));
                                TradePackageDetailDTO packageDetailDTO = new TradePackageDetailDTO();
                                BeanUtils.copyProperties(orderPackageDetailDO, packageDetailDTO);
                                packageDetailDTO.setProductShowName(Objects.isNull(soaCityProduct) ? "-" : soaCityProduct.getShowName());
                                tradePackageDetailDtoList.add(packageDetailDTO);
                            });
                        }
                        orderResponseDTO.setPackageDetailDTO(tradePackageDetailDtoList);
                    }
                }
                tradeOrderAssemblyDtoList.add(orderResponseDTO);
            } catch (Exception e) {
                log.error("查询部分数据出错：orderId：" + orderDO.getOrderId(), e);
            }
        });
        return tradeOrderAssemblyDtoList;
    }

    /**
     * 批量查询  查询参数组装
     */
    public OrderQueryParamDTO handleBatchOrderParam(OrderQueryDTO orderQueryDTO) {
        OrderQueryParamDTO orderQueryParamDTO = new OrderQueryParamDTO();
        OrderQuerySelector selector = orderQueryDTO.getOrderQuerySelector();

        CityDTO cityDTO = selector.getCity();
        StoreDTO storeDTO = selector.getStore();

        TimeRangeDTO timeRangeDTO = selector.getTimeRange();
        orderQueryParamDTO.setCityId(cityDTO.getCityId());
        orderQueryParamDTO.setTimeType(timeRangeDTO.getTimeType());
        orderQueryParamDTO.setStoreId(storeDTO.getStoreId());

        if (!Objects.isNull(timeRangeDTO.getAppointDay())) {
            Date startDate = DateTimeUtils.addDate(new Date(), 1 - timeRangeDTO.getAppointDay());
            orderQueryParamDTO.setQueryEndDate(DateTimeUtils.formatDateEnd(new Date()));
            orderQueryParamDTO.setQueryStartDate(DateTimeUtils.formatDateStart(startDate));
        } else {
            orderQueryParamDTO.setQueryEndDate(DateTimeUtils.formatDateEnd(timeRangeDTO.getEndTime()));
            orderQueryParamDTO.setQueryStartDate(DateTimeUtils.formatDateStart(timeRangeDTO.getBeginTime()));
        }
        Integer limit = DEFAULT_QUERY_LIMIT;
        Integer offset = null;
        List<Integer> statusList = Collections.singletonList(OrderStatusEnum.PAID.getType());
        if (CollectionUtils.isNotEmpty(selector.getOrderStatusList())) {
            statusList = selector.getOrderStatusList();
        }
        orderQueryParamDTO.setStatusList(statusList);
        PagingDTO pagingDTO = selector.getPaging();
        if (Objects.nonNull(pagingDTO)) {
            limit = pagingDTO.getLimit();
            offset = pagingDTO.getOffset();
        }
        orderQueryParamDTO.setOffset(offset);
        orderQueryParamDTO.setLimit(limit);
        return orderQueryParamDTO;
    }

    /**
     * 交付数据组装
     */
    public void handleDeliveredAssembly(List<ActualOrderDetailDTO> detailList,
                                        List<OrderSortingLogDO> sortingList, DeliveredDetailDTO deliveredDetailDTO) {

        DeliveredOrder deliveredOrder = deliveredDetailDTO.getDeliveredOrderList().get(0);
        String orderId = deliveredOrder.getOrderId();
        List<ProductDTO> productDtoList = deliveredOrder.getProductDtoList();

        productDtoList.forEach(productDTO -> {
            List<Integer> weightList = Lists.newArrayList();
            List<Integer> statusList = Lists.newArrayList();
            List<String> sortingStrList = Lists.newArrayList();
            Integer cityProductId = productDTO.getCityProductId();
            List<BarCodeDTO> barCodeDtoList = productDTO.getBarCodeDtoList();

            barCodeDtoList.forEach(barCodeDTO -> {
                String barCode = barCodeDTO.getBarCode();
                String sorting = barCodeDTO.getSorting();
                Integer status = barCodeDTO.getStatus();
                Integer weight = Integer.parseInt(barCode.substring(7, 12));
                weightList.add(weight);
                statusList.add(status);
                sortingStrList.add(sorting);
                if (StringUtils.isBlank(sorting) || !sorting.contains(SymbolConstants.OBLIQUE_LINE)) {
                    return;
                }
                String[] sortingArray = sorting.split(SymbolConstants.OBLIQUE_LINE);
                OrderSortingLogDO sortingLog = new OrderSortingLogDO();

                if (sortingArray.length == SHORTING_SIZE) {
                    sortingLog.setCityId(deliveredDetailDTO.getCityId());
                    sortingLog.setOrderId(orderId);
                    sortingLog.setCityProductId(cityProductId);
                    sortingLog.setScaleType(sortingArray[1]);
                    sortingLog.setScaleNo(Integer.valueOf(sortingArray[3]));
                    Date sortingDate = DatetimeUtil
                            .convertStringToDate(sortingArray[4], "yyyy-MM-dd HH:mm:ss");
                    sortingLog.setSortingDate(sortingDate);
                    sortingLog.setSortingUserId(Integer.valueOf(sortingArray[5]));
                    sortingLog.setSortingStr(sorting);
                    sortingList.add(sortingLog);
                }
            });
            ActualOrderDetailDTO detail = new ActualOrderDetailDTO();
            detail.setOrderId(orderId);
            detail.setOperUserId(deliveredDetailDTO.getOperatorId());
            detail.setCityProductId(cityProductId);
            detail.setActualWeight(weightList);
            detail.setIsOutStock(statusList);
            detail.setSortingStr(sortingStrList);
            detailList.add(detail);
        });
        deliveredDetailDTO.setSettlementLog(settlementService.initConfirmSettlement(orderId, deliveredDetailDTO));
    }
}
