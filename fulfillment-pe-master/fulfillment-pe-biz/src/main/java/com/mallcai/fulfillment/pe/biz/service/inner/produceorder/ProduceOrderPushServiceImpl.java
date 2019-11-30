package com.mallcai.fulfillment.pe.biz.service.inner.produceorder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.pe.api.msg.*;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CommodityInfo;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.GoodsInfo;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.gray.GoodsGrayRouterService;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderPushService;
import com.mallcai.fulfillment.pe.biz.service.inner.WarehousePathService;
import com.mallcai.fulfillment.pe.biz.service.mq.SendErpMsg;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.ProduceOrderGoodsMsgProducer;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.dependency.client.StoreServiceClient;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.dao.*;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.ProduceOrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderItemDO;
import com.mallcai.shop.api.service.response.StoreRespDTO;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 生产单发送业务类
 * @author: chentao
 * @create: 2019-08-24 20:00:51
 */
@Slf4j
@Service
public class ProduceOrderPushServiceImpl implements ProduceOrderPushService {

    @Autowired
    private ProduceOrderDao produceOrderDao;

    @Autowired
    private ProduceOrderDetailDao produceOrderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TransOrderDao transOrderDao;

    @Autowired
    private ProduceOrderItemDao produceOrderItemDao;

    @Autowired
    private ProduceOrderAndOrderDao produceOrderAndOrderDao;

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Autowired
    private SendErpMsg sendErpMsg;

    @Autowired
    private ProduceOrderGoodsMsgProducer produceOrderGoodsMsgProducer;

    @Autowired
    private WarehousePathService warehousePathService;

    @Autowired
    private GoodsGrayRouterService goodsGrayRouterService;

    @Autowired
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;

    @Override
    public void pushProduceOrder(Date startTime, Date endTime) {
            List<ProduceOrder> produceOrders = produceOrderDao.selectByExpectPushTime(startTime, endTime);
            if (produceOrders.isEmpty()) {
                log.warn("没有需要推送的生产单，startTime:{},endTime:{}", DateUtil.formatDate(startTime), DateUtil.formatDate(endTime));
                return;
            }
            pushProduceOrders(produceOrders);
    }

    private void pushProduceOrders(List<ProduceOrder> produceOrders){

        // step 构建发货单参数
        Map<String, List<OrderDO>> produceOrderNo2OrdersMap = new HashMap<>();
        Set<Integer> storeIds = produceOrders.stream().map(ProduceOrder::getStoreId).collect(Collectors.toSet());
        // 门店ID到仓库信息映射
        Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap = new HashMap<>();
        // 门店ID到门店信息映射
        Map<Integer, StoreRespDTO> storeId2StoreInfoMap = new HashMap<>();
        // 构建映射信息
        buildStoreId2StoreInfoMap(storeIds, storeId2WareHouseInfoMap, storeId2StoreInfoMap);
        // 构建单仓生产单数量
        Map<String,Map<Integer,WarehouseCount>> warehouseCount = buildWareHouseProduceTotalCount(produceOrders,storeId2WareHouseInfoMap);
        for (ProduceOrder produceOrder : produceOrders) {
            try {
                // 更新推单状态为处理中
                if (!produceOrderDao.updateStatusByIdUnderStatus(produceOrder.getId(), ProduceOrderStatusEnum.PROCESS.getStatus(), ProduceOrderStatusEnum.INIT.getStatus())) {
                    continue;
                }
                try {
                    buildAndSendProduceOrderMsgWithRouter(produceOrder, storeId2WareHouseInfoMap, storeId2StoreInfoMap, produceOrderNo2OrdersMap,warehouseCount);
                } catch (Exception e) {
                    log.error("发货单消息发送异常,produceOrderId:{}", produceOrder.getId(), e);
                    if (!produceOrderDao.updateStatusByIdUnderStatus(produceOrder.getId(), ProduceOrderStatusEnum.INIT.getStatus(), ProduceOrderStatusEnum.PROCESS.getStatus())) {
                        log.error("生产单回置为初始状态异常，produceOrderId:{}", produceOrder.getId());
                    }
                    continue;
                }
                List<Long> orderIds = produceOrderNo2OrdersMap.get(produceOrder.getProduceOrderNo()).stream().map(OrderDO::getId).collect(Collectors.toList());
                ProduceOrder updateProduceOrderPo = new ProduceOrder();
                updateProduceOrderPo.setStatus(ProduceOrderStatusEnum.COMPLETE.getStatus());
                updateProduceOrderPo.setSucTime(new Date());
                try {
                    produceOrderAndOrderDao.updateProduceOrderAndOrderStatusUnderStatus(produceOrder.getId(), orderIds, updateProduceOrderPo, OrderStatusEnum.PUSH_COMPLETE.getStatus(),
                            ProduceOrderStatusEnum.PROCESS.getStatus(), OrderStatusEnum.AGGREGATE_COMPLETE.getStatus());
                } catch (Exception e) {
                    log.error("更新生产单推送完成状态异常,produceOrderId:{}", produceOrder.getId(), e);
                    continue;
                }
            } catch (Exception e) {
                log.error("发送生产单异常,produceOrderId:{}",produceOrder.getId(), e);
                continue;
            }
        }
    }

    private Map<String,Map<Integer,WarehouseCount>> buildWareHouseProduceTotalCount(List<ProduceOrder> produceOrders,Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap) {
        Map<String, List<ProduceOrder>> groupProduceOrders = produceOrders.stream().collect(Collectors.groupingBy(ProduceOrder::getGroupValue));
        //冻品
        List<ProduceOrder> frozenProduce = groupProduceOrders.get(GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue());
        //生鲜
        List<ProduceOrder> freshProduce = groupProduceOrders.get(GroupValueEnum.FRESH_PRODUCT.getGroupValue());
        Map<String,Map<Integer,WarehouseCount>> groupWarehouseCounts = new HashMap<>();
        if (CollectionUtils.isNotEmpty(frozenProduce)) {
            groupWarehouseCounts.putAll(buildProduceTotalCount(frozenProduce,storeId2WareHouseInfoMap,GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue()));
        }
        if (CollectionUtils.isNotEmpty(freshProduce)) {
            groupWarehouseCounts.putAll(buildProduceTotalCount(freshProduce,storeId2WareHouseInfoMap,GroupValueEnum.FRESH_PRODUCT.getGroupValue()));
        }
        return groupWarehouseCounts;
    }

    private Map<String, Map<Integer, WarehouseCount>> buildProduceTotalCount(List<ProduceOrder> produceOrders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap, String groupValue) {
        List<Integer> storeIds = produceOrders.stream().map(u -> u.getStoreId()).collect(Collectors.toList());
        Map<Integer, List<Integer>> map = new HashMap<>(20);
        storeIds.forEach(storeId -> {
            if (map.containsKey(storeId2WareHouseInfoMap.get(storeId).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId())) {
                map.get(storeId2WareHouseInfoMap.get(storeId).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId()).add(storeId);
            } else {
                List<Integer> ids = new ArrayList<>();
                ids.add(storeId);
                map.put(storeId2WareHouseInfoMap.get(storeId).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId(), ids);
            }
        });
        List<Integer> repeatStore = produceOrders.stream()
                .collect(Collectors.groupingBy(ProduceOrder::getStoreId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(repeatStore)) {
            log.error("冻品或生鲜下发数据存在多条生产单,重复门店数据:{},groupValue:{}", repeatStore, groupValue);
        }
        Map<Integer, WarehouseCount> warehouseCountMap = new HashMap<>(20);
        map.forEach((k, v) ->{
                    WarehouseCount count = new WarehouseCount();
                    count.setCount(v.size());
                    count.setOrderType(Integer.valueOf(groupValue));
                    count.setWarehouseId(k);
                    warehouseCountMap.put(k,count);
                }
                );
        Map<String, Map<Integer, WarehouseCount>> groupWarehouseCount = new HashMap<>();
        groupWarehouseCount.put(groupValue,warehouseCountMap);
        return groupWarehouseCount;
    }

    private ProduceOrderInfoMsg buildProduceOrdersProductParam(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WarehouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap, Map<String, List<OrderDO>> produceOrderNo2OrdersMap, List<ProduceOrderItemDO> produceOrderItemDOS) {

        ProduceOrderInfoMsg produceOrderInfoMsg = new ProduceOrderInfoMsg();

        Map<Integer, List<OrderDO>> productId2ProductNumMap = new HashMap<>(); // 将商品cityProductId与订单的映射， 用于按cityProductId维度聚合订单

        for (OrderDO orderDO : orders) {

            // 将商品按cityProductId存储映射关系
            if (productId2ProductNumMap.containsKey(orderDO.getCityProductId())) {

                productId2ProductNumMap.get(orderDO.getCityProductId()).add(orderDO);
            } else {

                productId2ProductNumMap.put(orderDO.getCityProductId(), Lists.newArrayList(orderDO));
            }

            // 存一份produceOrderNO 与order的映射
            if (produceOrderNo2OrdersMap.containsKey(produceOrder.getProduceOrderNo())) {

                produceOrderNo2OrdersMap.get(produceOrder.getProduceOrderNo()).add(orderDO);
            } else {

                produceOrderNo2OrdersMap.put(produceOrder.getProduceOrderNo(), Lists.newArrayList(orderDO));
            }

        }

        produceOrderInfoMsg.setStoreId(produceOrder.getStoreId());
        produceOrderInfoMsg.setProduceOrderNo(produceOrder.getProduceOrderNo());
        produceOrderInfoMsg.setCityId(orders.get(0).getCityId());
        produceOrderInfoMsg.setPickupTime(DateUtil.formatDate(orders.get(0).getPickupTime()));

        // 仓库路线
        produceOrderInfoMsg.setWareHouseInfos(warehousePathService.buildWarehousePath(produceOrder, orders, storeId2WarehouseInfoMap));
        produceOrderInfoMsg.setOrderType(Integer.valueOf(produceOrder.getGroupValue()));

        // 是否是团购订单,目前同一个生产单要不全部都是团购商品,要不就不是
        String groupBuying = JSONObject.parseObject(orders.get(0).getCommodityInfoExt()).getString(CommodityInfoExtEnum.IS_GROUP_BUYING.getKey());
        produceOrderInfoMsg.buildOrderExt(Integer.valueOf(produceOrder.getGroupValue()), groupBuying);

        List<CommodityInfo> commodities = new ArrayList<>();

        // 按cityProductId 维度聚合订单信息
        for (Map.Entry<Integer, List<OrderDO>> productIdOrderEntry : productId2ProductNumMap.entrySet()) {

            CommodityInfo commodityInfo = new CommodityInfo();

            Integer productNum = 0;
            for (OrderDO orderDOItem : productIdOrderEntry.getValue()) {

                // 商品扩展信息
                JSONObject commodityInfoExt = JSONObject.parseObject(orderDOItem.getCommodityInfoExt());
                Integer packageQuantity = commodityInfoExt.getInteger(CommodityInfoExtEnum.PACKAGE_QUANTITY.getKey());
                AssertUtils.assertNotNull(packageQuantity);
                productNum = productNum + (commodityInfoExt.getInteger(CommodityInfoExtEnum.PRODUCT_NUM.getKey()) * packageQuantity) ;
            }

            commodityInfo.setCityProductId(productIdOrderEntry.getKey());
            commodityInfo.setProductNum(productNum);

            commodities.add(commodityInfo);

            produceOrderItemDOS.add(buildProduceOrderItemWithProductInfo(produceOrderInfoMsg, commodityInfo));
        }

        produceOrderInfoMsg.setCommodities(commodities);

        return produceOrderInfoMsg;
    }

    private ProduceOrderItemDO buildProduceOrderItemWithProductInfo(ProduceOrderInfoMsg produceOrderInfoMsg, CommodityInfo commodityInfo) {
        ProduceOrderItemDO produceOrderItemDO = new ProduceOrderItemDO();
        produceOrderItemDO.setProduceOrderNo(produceOrderInfoMsg.getProduceOrderNo());
        produceOrderItemDO.setCityId(produceOrderInfoMsg.getCityId());
        produceOrderItemDO.setStoreId(produceOrderInfoMsg.getStoreId());
        produceOrderItemDO.setWarehouseId(produceOrderInfoMsg.getWareHouseInfos().get(0).getWareHouseId());
        produceOrderItemDO.setCityProductId(commodityInfo.getCityProductId());
        produceOrderItemDO.setTotalNum(BigDecimal.valueOf(commodityInfo.getProductNum()));
        return produceOrderItemDO;
    }

    private ProduceOrderGoodsInfoMsg buildProduceOrdersGoodsParam(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WarehouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap, Map<String, List<OrderDO>> produceOrderNo2OrdersMap,Map<String,Map<Integer,WarehouseCount>> warehouseCount, List<ProduceOrderItemDO> produceOrderItemDOS) {

        ProduceOrderGoodsInfoMsg produceOrderGoodsInfoMsg = new ProduceOrderGoodsInfoMsg();

        // 将goodsId与订单的映射， 用于按goodsId维度聚合订单
        Map<String, List<OrderDO>> goodsId2OrdersMap = new HashMap<>();

        //发送生产单前在检查下是否转了货品
        List<OrderDO> filterOrder = orders.stream().filter(p -> !p.getCommodityInfoExt().contains(CommodityInfoExtEnum.GOODS_ID.getKey()))
                .collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(filterOrder)){
            List<QueryGoodsRelBO> queryList = filterOrder.stream().map(p-> { QueryGoodsRelBO query=new QueryGoodsRelBO();
                            query.setCityId(p.getCityId());
                            query.setCityProductId(p.getCityProductId());
                            return query;}).collect(Collectors.toList());
            //如果商品中心没有货品信息,函数会抛异常
            Map<Integer, ItemGoodsInfoBO> goodInfo = icProductGoodsServiceFacade.searchGoodsRel(queryList).stream().collect(Collectors.toMap(p -> p.getCityProductId(), p -> p));
            for (OrderDO orderDO : filterOrder) {
                log.info("推单时发现生产单[{}]没有货品信息,补充货品信息{}.", orderDO.getCityProductId(), goodInfo.get(orderDO.getCityProductId()));
                if(goodInfo.containsKey(orderDO.getCityProductId())){
                    Map infos = JSON.parseObject(orderDO.getCommodityInfoExt(), Map.class);
                    if(infos==null){
                        infos= Maps.newHashMap();
                    }
                    infos.put(CommodityInfoExtEnum.GOODS_ID.getKey(),goodInfo.get(orderDO.getCityProductId()).getGoodsId());
                    infos.put(CommodityInfoExtEnum.GOODS_UNIT.getKey(),goodInfo.get(orderDO.getCityProductId()).getGoodsUnit());
                    infos.put(CommodityInfoExtEnum.REL_NUM.getKey(),goodInfo.get(orderDO.getCityProductId()).getRelNum());
                    orderDO.setCommodityInfoExt(JSON.toJSONString(infos));
                }else {

                    log.error("发送生产单丢失货品信息,produceOrderNo:{},cityProductId:{}", produceOrder.getProduceOrderNo(), orderDO.getCityProductId());
                    throw new BizException(Errors.PRODUCE_ORDER_GOODS_INFO_ERROR);
                }
            }
        }

        for (OrderDO orderDO : orders) {

            JSONObject commodityInfoExt = JSONObject.parseObject(orderDO.getCommodityInfoExt());
            String goodsId = commodityInfoExt.getString(CommodityInfoExtEnum.GOODS_ID.getKey());

            // 将商品按goodId存储映射关系
            if (goodsId2OrdersMap.containsKey(goodsId)) {

                goodsId2OrdersMap.get(goodsId).add(orderDO);
            } else {

                goodsId2OrdersMap.put(goodsId, Lists.newArrayList(orderDO));
            }

            // 存一份produceOrderNO 与order的映射
            if (produceOrderNo2OrdersMap.containsKey(produceOrder.getProduceOrderNo())) {

                produceOrderNo2OrdersMap.get(produceOrder.getProduceOrderNo()).add(orderDO);
            } else {

                produceOrderNo2OrdersMap.put(produceOrder.getProduceOrderNo(), Lists.newArrayList(orderDO));
            }

        }

        produceOrderGoodsInfoMsg.setStoreId(produceOrder.getStoreId());
        produceOrderGoodsInfoMsg.setProduceOrderNo(produceOrder.getProduceOrderNo());
        produceOrderGoodsInfoMsg.setCityId(orders.get(0).getCityId());
        produceOrderGoodsInfoMsg.setPickupTime(DateUtil.formatDate(orders.get(0).getPickupTime()));

        // 仓库路线
        produceOrderGoodsInfoMsg.setWareHouseInfos(warehousePathService.buildWarehousePath(produceOrder, orders, storeId2WarehouseInfoMap));
        produceOrderGoodsInfoMsg.setOrderType(Integer.valueOf(produceOrder.getGroupValue()));

        // 是否是团购订单,目前同一个生产单要不全部都是团购商品,要不就不是
        String groupBuying = JSONObject.parseObject(orders.get(0).getCommodityInfoExt()).getString(CommodityInfoExtEnum.IS_GROUP_BUYING.getKey());
        produceOrderGoodsInfoMsg.buildOrderExt(Integer.valueOf(produceOrder.getGroupValue()), groupBuying);

        List<GoodsInfo> goodsInfos = new ArrayList<>();

        // 按goodId 维度聚合订单信息
        for (Map.Entry<String, List<OrderDO>> goodIdOrderEntry : goodsId2OrdersMap.entrySet()) {

            GoodsInfo goodsInfo = new GoodsInfo();

            BigDecimal goodsTotalNum = BigDecimal.ZERO;
            JSONObject commodityInfoExt = null;
            for (OrderDO orderDOItem : goodIdOrderEntry.getValue()) {

                // 商品扩展信息
                commodityInfoExt = JSONObject.parseObject(orderDOItem.getCommodityInfoExt());
                BigDecimal relNum = commodityInfoExt.getBigDecimal(CommodityInfoExtEnum.REL_NUM.getKey());
                BigDecimal number = commodityInfoExt.getBigDecimal(CommodityInfoExtEnum.PRODUCT_NUM.getKey());
                goodsTotalNum = goodsTotalNum.add(relNum.multiply(number));

            }
            goodsInfo.setGoodsId(goodIdOrderEntry.getKey());
            goodsInfo.setGoodsNum(goodsTotalNum.setScale(3,BigDecimal.ROUND_HALF_DOWN));
            goodsInfo.setGoodsUnit(commodityInfoExt.getString(CommodityInfoExtEnum.GOODS_UNIT.getKey()));
            goodsInfos.add(goodsInfo);
            produceOrderItemDOS.add(buildProduceOrderItemDOWithGoodInfo(produceOrderGoodsInfoMsg, goodsInfo,goodsTotalNum));
        }

        if (warehouseCount.get(produceOrder.getGroupValue()) != null) {
            produceOrderGoodsInfoMsg.setWarehouseCount(warehouseCount.get(produceOrder.getGroupValue()).get(storeId2WarehouseInfoMap.get(produceOrder.getStoreId()).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId()));
        }

        produceOrderGoodsInfoMsg.setGoodsInfos(goodsInfos);

        return produceOrderGoodsInfoMsg;
    }

    private ProduceOrderItemDO buildProduceOrderItemDOWithGoodInfo(ProduceOrderGoodsInfoMsg produceOrderGoodsInfoMsg, GoodsInfo goodsInfo,BigDecimal goodsTotalNum) {
        ProduceOrderItemDO produceOrderItemDO = new ProduceOrderItemDO();
        produceOrderItemDO.setProduceOrderNo(produceOrderGoodsInfoMsg.getProduceOrderNo());
        produceOrderItemDO.setCityId(produceOrderGoodsInfoMsg.getCityId());
        produceOrderItemDO.setStoreId(produceOrderGoodsInfoMsg.getStoreId());
        produceOrderItemDO.setWarehouseId(produceOrderGoodsInfoMsg.getWareHouseInfos().get(0).getWareHouseId());
        produceOrderItemDO.setGoodId(goodsInfo.getGoodsId());
        produceOrderItemDO.setTotalNum(goodsTotalNum);
        produceOrderItemDO.setGoodUnit(goodsInfo.getGoodsUnit());
        return produceOrderItemDO;
    }

    /**
     *  构建并发送生产单消息，路由
     * @param produceOrder
     * @param storeId2WarehouseInfoMap
     * @param storId2StoreInfoMap
     * @param produceOrderNo2OrdersMap
     */
    private void buildAndSendProduceOrderMsgWithRouter(ProduceOrder produceOrder, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WarehouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap, Map<String, List<OrderDO>> produceOrderNo2OrdersMap,Map<String,Map<Integer,WarehouseCount>> warehouseCount){
        List<ProduceOrderDetail> produceOrderDetailDos = produceOrderDetailDao.selectByProduceOrderId(produceOrder.getId());

        List<Long> orderIds = produceOrderDetailDos.stream().map(ProduceOrderDetail::getOrderId).collect(Collectors.toList());

        List<OrderDO> orders = orderDao.selectByIds(orderIds);

        if (goodsGrayRouterService.isNeedRoute(orders.get(0).getCityId())){
            buildAndSendProduceOrderGoodsMsg(produceOrder, orders, storeId2WarehouseInfoMap, storId2StoreInfoMap, produceOrderNo2OrdersMap,warehouseCount);
        }else {
            buildAndSendProduceOrderProductMsg(produceOrder, orders, storeId2WarehouseInfoMap, storId2StoreInfoMap, produceOrderNo2OrdersMap);
        }
    }

    /**
     *
     * @param produceOrder
     * @param storeId2WarehouseInfoMap
     * @param storId2StoreInfoMap
     * @param produceOrderNo2OrdersMap
     */
    private void buildAndSendProduceOrderProductMsg(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WarehouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap, Map<String, List<OrderDO>> produceOrderNo2OrdersMap){

        List<ProduceOrderItemDO> produceOrderItemDOS = new ArrayList<>();

        ProduceOrderInfoMsg produceOrderInfoMsg = buildProduceOrdersProductParam(produceOrder, orders, storeId2WarehouseInfoMap, storId2StoreInfoMap, produceOrderNo2OrdersMap, produceOrderItemDOS);

        //保存明细
        try {
            produceOrderItemDao.batchInsertSelective(produceOrderItemDOS);
        } catch (Exception e) {
            log.error("插入生产单货品明细失败,produceOrderId:{}, produceOrderNo:{}", produceOrder.getId(), produceOrder.getProduceOrderNo(), e);
        }

        sendErpMsg.sendBatchTradeMsg(new ProduceOrderInfoListMsg(Lists.newArrayList(produceOrderInfoMsg)));
    }

    /**
     * 构建并发送生产单货品信息
     * @param produceOrder
     * @param storeId2WarehouseInfoMap
     * @param storId2StoreInfoMap
     * @param produceOrderNo2OrdersMap
     */
    private void buildAndSendProduceOrderGoodsMsg(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WarehouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap, Map<String, List<OrderDO>> produceOrderNo2OrdersMap,Map<String,Map<Integer,WarehouseCount>> warehouseCount){

        List<ProduceOrderItemDO> produceOrderItemDOS = new ArrayList<>();

        ProduceOrderGoodsInfoMsg produceOrderGoodsInfoMsg = buildProduceOrdersGoodsParam(produceOrder, orders, storeId2WarehouseInfoMap, storId2StoreInfoMap, produceOrderNo2OrdersMap, warehouseCount, produceOrderItemDOS);

        //保存明细
        try {

            produceOrderItemDao.batchInsertSelective(produceOrderItemDOS);
        } catch (Exception e) {
            log.error("插入生产单货品明细失败,produceOrderId:{}, produceOrderNo:{}", produceOrder.getId(), produceOrder.getProduceOrderNo(), e);
        }

        produceOrderGoodsMsgProducer.sendProduceOrderGoodsMsg(new ProduceOrderGoodsInfoListMsg(Lists.newArrayList(produceOrderGoodsInfoMsg)));
    }

    private void buildStoreId2StoreInfoMap(Set<Integer> storeIds, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2WareHouseInfoMap, Map<Integer, StoreRespDTO> storId2StoreInfoMap) {

        List<StoreRespDTO> storeInfos = storeServiceClient.searchStoreByIds(storeIds);

        for (StoreRespDTO storeRespDTO : storeInfos) {

            Map<Integer, WareHouseRespDTO> storeType2WareHouseMap = new HashMap<>();

            for (WareHouseRespDTO wareHouseRespDTO : storeRespDTO.getWareHouseDTOS()) {

                if (WareHouseTypeEnum.WARE_HOUSE_FRESH.getType().equals(wareHouseRespDTO.getType())) {

                    storeType2WareHouseMap.put(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType(), wareHouseRespDTO);
                } else if (WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType().equals(wareHouseRespDTO.getType())) {

                    storeType2WareHouseMap.put(WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType(), wareHouseRespDTO);
                }
            }

            storeId2WareHouseInfoMap.put(storeRespDTO.getStoreId(), storeType2WareHouseMap);
            storId2StoreInfoMap.put(storeRespDTO.getStoreId(), storeRespDTO);
        }
    }
}
