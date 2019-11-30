package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.api.service.operate.OrderAcceptService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderDetailDTO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.OrderPushDateEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.PeOrderSourceEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.PeOrderTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.RedisService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.order.OrderType;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.DateUtils;
import com.mallcai.fulfillment.pe.dependency.client.StoreServiceClient;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.TransOrderAndPeOrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.TransOrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDO;
import com.mallcai.fulfillment.promise.api.req.CheckTimeCorrectRequest;
import com.mallcai.fulfillment.promise.configcenter.ConfigCenter;
import com.mallcai.fulfillment.promise.configcenter.ConfigReq;
import com.mallcai.fulfillment.promise.impl.PickUpTimeServiceImpl;
import com.mallcai.fulfillment.promise.util.DateTimeUtils;
import com.mallcai.fulfillment.promise.util.DistriCacheUtil;
import com.mallcai.service.order.constants.OrderExtraKeys;
import com.mallcai.service.order.constants.OrderItemProductInfoKeys;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 生产收单
 * @author: chentao
 * @create: 2019-08-23 17:25:24
 */
@Slf4j
@Service("orderAcceptService")
public class OrderAcceptServiceImpl implements OrderAcceptService {

    @ConfigValue(key = "/app_fulfillment/common/order.push.date")
    private String orderPushDateKey;

    @Autowired
    private TransOrderDao transOrderDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TransOrderAndPeOrderDao transOrderAndPeOrderDao;
    @Autowired
    private OrderInnerService orderInnerService;
    @Autowired
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;
    @Autowired
    private ConfigCenter configCenter;
    @Autowired
    private GoodsGrayRouterService goodsGrayRouterService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StoreServiceClient storeServiceClient;


    /**
     * 订单请求时间到创建时间会有时间差，而取货日期是按照订单请求时间计算的。目前 只能拿到订单创建时间，所以给一个buffer时间
     */
    private Integer ORDER_DELAY_BUFFER_MINS = 10;

    /**
     * 交易订单中productNo参数key
     */
    private final String PRODUCT_NO_KEY = "product_no";

    /**
     * 交易订单中冻品标识
     */
    private final String IS_FROZEN = "1";

    @Override
    public PlainResult<?> acceptOrder(OrderAcceptRequestDTO oriTransOrder) {

        log.info("收单，入参:{}", JSON.toJSONString(oriTransOrder));

        Date payTime;
        try {
            payTime = DateUtil.parseDate(oriTransOrder.getPayTime());
        } catch (Exception e) {
            log.error("交易订单支付时间格式有误，transOrderId:{},payTime:{}", oriTransOrder.getOrderId(),
                    oriTransOrder.getPayTime());
            throw new BizException(Errors.FULFILLMENT_PARAMS_ERROR);
        }

        if (payTime.compareTo(DateUtil.todayStart()) < 0) {

            log.error("交易订单跨日,transOrderId:{},payTime:{}", oriTransOrder.getOrderId(),
                    oriTransOrder.getPayTime());
        }

        List<TransOrderDO> transOrders = transOrderDao.selectByTransOrderId(oriTransOrder.getOrderId());

        if (!transOrders.isEmpty()) {

            log.error("交易订单已存在,transOrderId:{}", oriTransOrder.getOrderId());
            throw new BizException(Errors.ORDER_EXISTED);
        }
        List<OrderDO> orders = orderDao.selectByTransOrderId(oriTransOrder.getOrderId());
        if (!orders.isEmpty()) {

            log.error("商品订单已存在,transOrderId:{}", oriTransOrder.getOrderId());
            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }
        //查询本订单内的商品明细，对应的货品信息
        //如果开了商品转货品开关，则返回货品列表，如果没有开，则返回空列表
        List<ItemGoodsInfoBO> itemGoodsInfoBoList = getGoodsList(oriTransOrder.getOrderDetailLst(), oriTransOrder.getCityId());
        savePeOrder(convertTransOrder(oriTransOrder), buildOrders(oriTransOrder, itemGoodsInfoBoList));
        return PlainResult.ok();
    }


    private void savePeOrder(TransOrderDO transOrderPo, List<OrderDO> ordersPo) {
        transOrderAndPeOrderDao.insertTransOrderAndPeOrder(transOrderPo, ordersPo);
        redisCountInc(ordersPo);
    }

    private void redisCountInc(List<OrderDO> ordersPo) {
        for (OrderDO order : ordersPo) {
            GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(order.getGroupValue());
            if (GroupValueEnum.ORDER_TYPE_STANDARD.equals(groupValueEnum)) {
                redisService.incrBy(DistriCacheUtil.getCapacityKey(order.getWarehouseId(),groupValueEnum.getCategoryType(),groupValueEnum.getAttributeType(),new Date()), 1);
                break;
            }
        }
    }


    public List<ItemGoodsInfoBO> getGoodsList(List<OrderDetailDTO> orderDetailLst, Integer cityId) {
        List<ItemGoodsInfoBO> result = new ArrayList<>();
        if (!goodsGrayRouterService.isNeedRoute(cityId)) {
            return result;
        }
        List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDetailLst) {
            if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
                boolean flag = false;
                for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
                    if (queryGoodsRelBO.getCityId().equals(orderDetailDTO.getCityId()) && queryGoodsRelBO
                            .getCityProductId().equals(orderDetailDTO.getCityProductId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                    queryGoodsRelBO.setCityId(orderDetailDTO.getCityId());
                    queryGoodsRelBO.setCityProductId(orderDetailDTO.getCityProductId());
                    queryGoodsRelBOList.add(queryGoodsRelBO);
                }
            } else {
                QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                queryGoodsRelBO.setCityId(orderDetailDTO.getCityId());
                queryGoodsRelBO.setCityProductId(orderDetailDTO.getCityProductId());
                queryGoodsRelBOList.add(queryGoodsRelBO);
            }
        }
        if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
            result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
        }
        return result;
    }

    private TransOrderDO convertTransOrder(OrderAcceptRequestDTO transOrder) {

        AssertUtils
                .assertNotNull(transOrder.getStoreId(), transOrder.getUserId(), transOrder.getCityId(),
                        transOrder.getPickupTime(), transOrder.getOrderId());

        TransOrderDO transOrderDO = new TransOrderDO();

        transOrderDO.setTransOrderId(transOrder.getOrderId());
        transOrderDO.setStoreId(transOrder.getStoreId());
        transOrderDO.setUserId(transOrder.getUserId());
        transOrderDO.setCityId(transOrder.getCityId());
        try {
            transOrderDO.setPickupTime(DateUtil.parseDate(transOrder.getPickupTime()));
        } catch (Exception e) {
            log.error("订单取货时间格式有误,transOrderId:{}, pickupTime:{}", transOrder.getOrderId(),
                    transOrder.getPickupTime());
            throw new BizException(Errors.FULFILLMENT_PARAMS_ERROR);

        }

        return transOrderDO;
    }

    private List<OrderDO> buildOrders(OrderAcceptRequestDTO oriTransOrder,
                                      List<ItemGoodsInfoBO> itemGoodsInfoBoList) {

        AssertUtils.assertNotNull(oriTransOrder.getOrderType());

        List<OrderDO> orders = new ArrayList<>();

        Integer orderType = oriTransOrder.getOrderType();

        for (OrderDetailDTO orderDetail : oriTransOrder.getOrderDetailLst()) {

            OrderDO order = new OrderDO();

            AssertUtils.assertNotNull(orderDetail.getProductInfo(), orderDetail.getCityProductId());

            JSONObject productInfoJson = JSON.parseObject(orderDetail.getProductInfo());
            String productNo = productInfoJson.getString(PRODUCT_NO_KEY);
            AssertUtils.assertNotNull(productNo);


            order.setTransOrderId(oriTransOrder.getOrderId());
            order.setStatus(OrderStatusEnum.INIT.getStatus());
            order.setOrderSource(PeOrderSourceEnum.CUSTOMER.getValue());
            order.setOrderType(oriTransOrder.getOrderType());
            order.setCityProductId(orderDetail.getCityProductId());
            order.setStoreId(oriTransOrder.getStoreId());
            order.setUserId(oriTransOrder.getUserId());
            order.setCityId(oriTransOrder.getCityId());
            order.setVersion(0);
            order.setProductNo(productNo);
            // 先划分好维度，后面会利用这些字段
            groupOrder(oriTransOrder, order, orderType, itemGoodsInfoBoList);

            // 商品信息扩展字段
            orderInnerService.buildOrderCommodityInfo(oriTransOrder, orderDetail, order, itemGoodsInfoBoList);

            order.setPickupTime(DateUtil.parseDate(oriTransOrder.getPickupTime()));
            Integer warehouseId = getWarehouseId(orderDetail,
                    GroupValueEnum.fromValue(order.getGroupValue()));

            if (warehouseId == null) {
                warehouseId = getWarehouseIdBak(orderDetail,
                        GroupValueEnum.fromValue(order.getGroupValue()));
            }

            AssertUtils.assertNotNull(warehouseId);
            order.setWarehouseId(warehouseId);
            order.setExpectPushTime(calculateExpectPushTime(oriTransOrder, order));
            orders.add(order);
        }

        return orders;
    }


    /**
     * 对订单进行分组
     *
     * @param oriTransOrder
     * @param order
     */
    private void groupOrder(OrderAcceptRequestDTO oriTransOrder, OrderDO order, Integer orderType,
                            List<ItemGoodsInfoBO> itemGoodsInfoBoList) {

        JSONObject extraJson = JSON.parseObject(oriTransOrder.getExtras());
        String isFrozen = extraJson.getString(OrderExtraKeys.IS_FROZEN_ORDER);
        if (goodsGrayRouterService.isNeedRoute(oriTransOrder.getCityId()) && orderType != null && orderType.equals(PeOrderTypeEnum.BOOKING.getType())) {
            Integer categoryType = 0;
            Integer attributeType = 0;
            if (CollectionUtils.isNotEmpty(itemGoodsInfoBoList)) {
                //通过获得品类和属性来设置预售商品的分组
                for (ItemGoodsInfoBO itemGoodsInfoBO : itemGoodsInfoBoList) {
                    if (order.getCityId().equals(itemGoodsInfoBO.getCityId()) && order.getCityProductId().equals(itemGoodsInfoBO.getCityProductId())) {
                        categoryType = itemGoodsInfoBO.getCategoryType();
                        attributeType = itemGoodsInfoBO.getAttributeType();
                    }
                }
            }
            GroupValueEnum groupValueEnum = GroupValueEnum.fromCategoryTypeAndAttributeType(categoryType, attributeType);
            if (null != groupValueEnum) {
                if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.FRESH_PRODUCT.getGroupValue())) {
                    //预售订单的生鲜
                    order.setGroupType(GroupTypeEnum.CATEGORY.getGroupType());
                    order.setGroupValue(GroupValueEnum.FRESH_PRODUCT.getGroupValue());
                } else if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue())) {
                    //预售订单的标品
                    order.setGroupType(GroupTypeEnum.ORDER_TYPE.getGroupType());
                    order.setGroupValue(GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue());
                } else if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue())) {
                    //预售订单的冻品
                    order.setGroupType(GroupTypeEnum.STORE.getGroupType());
                    order.setGroupValue(GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue());
                }
            }
        } else if (isFrozen != null && IS_FROZEN.equals(isFrozen)) {
            order.setGroupType(GroupTypeEnum.STORE.getGroupType());
            order.setGroupValue(GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue());
        } else {
            order.setGroupType(GroupTypeEnum.ORDER_TYPE.getGroupType());
            order.setGroupValue(GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue());
        }
    }

    private Date calculateExpectPushTime(OrderAcceptRequestDTO oriTransOrder, OrderDO order) {

        Date pickupTime = DateUtil.parseDate(oriTransOrder.getPickupTime());
        Date expectPushTime;

        GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(order.getGroupValue());

        if (groupValueEnum == null) {

            log.error("数据异常");
            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }

        //  预售订单全部走履约统一配置中心处理
        if (goodsGrayRouterService.isNeedRoute(order.getCityId()) && PeOrderTypeEnum.BOOKING.getType().equals(order.getOrderType())) {
            ConfigReq config = ConfigReq.builder().attributeType(groupValueEnum.getAttributeType()).categoryType(groupValueEnum.getCategoryType())
                    .cityId(order.getCityId()).houseId(order.getWarehouseId()).build();
            Integer interval = configCenter.getPushTimeLine(config);
            return DateUtil.addDays(DateUtil.dayStart(pickupTime), -interval);
        }

        // 非预售订单走老处理处理
        switch (groupValueEnum) {
            case STORE_FROZEN_PRODUCT:
                // 判断取货时间是否有误
                boolean isPickupTimeCorrect = true;
                try {
                    isPickupTimeCorrect = validatePickupTime(oriTransOrder, order);
                } catch (Exception e) {
                    log.error("判断取货时间是否正确异常", e);
                }
                // 取货时间有误做告警处理，推单时间全部处理为取货日期前一天，防止取货时间误接口有问题导致一些正常的订单取货时间计算出错
                if (!isPickupTimeCorrect) {

                    log.error("取货日期有误，transOrderId:{},创建时间:{}, 取货日期:{}", oriTransOrder.getOrderId(), oriTransOrder.getCreateTime(),
                            oriTransOrder.getPickupTime());
                }
                // 取货日期前一天
                expectPushTime = DateUtil.addDays(DateUtil.dayStart(pickupTime), -1);

                break;
            case ORDER_TYPE_STANDARD:
                expectPushTime = OrderPushDateEnum.getOrderPushDate(orderPushDateKey.trim(), DateUtil.parseDate(oriTransOrder.getPayTime()));
                break;

            default:
                throw new BizException(Errors.ORDER_GROUP_VALUE_ERROR);

        }

        return expectPushTime;
    }

    /**
     * 兜底逻辑
     * @param orderDetail
     * @param groupValueEnum
     * @return
     */
    private Integer getWarehouseIdBak(OrderDetailDTO orderDetail, GroupValueEnum groupValueEnum) {
        log.warn("交易订单无仓信息，兜底查询门店获取,orderDetail:{}",orderDetail);
        StoreRespDTO storeRespDTO = storeServiceClient.searchStoreByIds(Sets.newHashSet(orderDetail.getStoreId()))
                .get(0);

        // 生产仓ID
        Integer produceWarehouseId = null;

        Integer freshWarehouseId = null;
        Integer standardWarehouseId = null;

        for (WareHouseRespDTO wareHouseRespDTO : storeRespDTO.getWareHouseDTOS()) {

            if (WareHouseTypeEnum.WARE_HOUSE_FRESH.getType().equals(wareHouseRespDTO.getType())) {

                freshWarehouseId = wareHouseRespDTO.getId();
            } else if (WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType()
                    .equals(wareHouseRespDTO.getType())) {

                standardWarehouseId = wareHouseRespDTO.getId();
            }
        }

        switch (groupValueEnum) {

            case STORE_FROZEN_PRODUCT:
                produceWarehouseId = freshWarehouseId;
                break;
            case ORDER_TYPE_STANDARD:
                produceWarehouseId = standardWarehouseId;
                break;
            case FRESH_PRODUCT:
                produceWarehouseId = freshWarehouseId;
                break;

            default:
                break;
        }

        return produceWarehouseId;

    }

    private Integer getWarehouseId(OrderDetailDTO orderDetail, GroupValueEnum groupValueEnum) {

        // 生产仓ID
        String produceWarehouseId = null;
        String freshWarehouseId = orderDetail.getProductInfoAsMap().get(OrderItemProductInfoKeys.FRESH_WID);
        String standardWarehouseId = orderDetail.getProductInfoAsMap().get(OrderItemProductInfoKeys.STD_WID);
        switch (groupValueEnum) {
            case STORE_FROZEN_PRODUCT:
                produceWarehouseId = freshWarehouseId;
                break;
            case ORDER_TYPE_STANDARD:
                produceWarehouseId = standardWarehouseId;
                break;
            case FRESH_PRODUCT:
                produceWarehouseId = freshWarehouseId;
                break;
            default:
                break;
        }
        return StringUtils.isNotEmpty(produceWarehouseId) ? Integer.valueOf(produceWarehouseId) : null;
    }

    private boolean validatePickupTime(OrderAcceptRequestDTO oriTransOrder, OrderDO orderDO) {

        CheckTimeCorrectRequest checkTimeCorrectRequest = new CheckTimeCorrectRequest();
        GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(orderDO.getGroupValue());

        checkTimeCorrectRequest
                .setOrderCreateTime(DateUtil.parseDateTime(oriTransOrder.getCreateTime()));
        checkTimeCorrectRequest.setBufferTime(ORDER_DELAY_BUFFER_MINS);
        checkTimeCorrectRequest.setAttributeType(groupValueEnum.getAttributeType());
        checkTimeCorrectRequest.setCategoryType(groupValueEnum.getCategoryType());
        checkTimeCorrectRequest.setSkuId(groupValueEnum.getSku());
        checkTimeCorrectRequest.setCityId(orderDO.getCityId());
        checkTimeCorrectRequest.setPickUpTime(DateUtil.parseDate(oriTransOrder.getPickupTime()));

        return PickUpTimeServiceImpl.getInstance().checkTimeCorrect(checkTimeCorrectRequest);
    }
}