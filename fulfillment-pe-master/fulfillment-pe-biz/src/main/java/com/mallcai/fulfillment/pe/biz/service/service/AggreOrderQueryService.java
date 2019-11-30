package com.mallcai.fulfillment.pe.biz.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.pe.api.enums.OrderAggregationTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.bo.AggregateQueryConf;
import com.mallcai.fulfillment.pe.biz.service.bo.BatchDataTaskMessage;
import com.mallcai.fulfillment.pe.biz.service.bo.ProduceOrderAndDetailBO;
import com.mallcai.fulfillment.pe.biz.service.constants.BizConstant;
import com.mallcai.fulfillment.pe.biz.service.enums.BatchDataTypeEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderAggregationRuleService;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderFilterActuatorService;
import com.mallcai.fulfillment.pe.biz.service.inner.batchdata.enums.BatchDataTaskParamKeyEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.configuration.ConfigurationProcessor;
import com.mallcai.fulfillment.pe.biz.service.inner.lock.DataReloadAndAggregateOrderLockService;
import com.mallcai.fulfillment.pe.biz.service.mq.producer.BatchDataTaskMsgProducer;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.ProduceOrderAndDetailDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.ProduceOrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import com.mallcai.fulfillment.promise.configcenter.ConfigCenter;
import com.mallcai.fulfillment.promise.configcenter.ConfigReq;
import com.mallcai.fulfillment.promise.configcenter.model.FulfillPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AggreOrderQueryService {

    @Autowired
    private OrderDOMapperExtend orderDOMapperExtend;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ConfigurationProcessor configurationPorcessor;

    @Autowired
    private OrderFilterActuatorService orderFilterActuatorService;

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private ProduceOrderAndDetailDao produceOrderAndDetailDao;

    @Autowired
    private ConfigCenter configCenter;

    @Autowired
    private BatchDataTaskMsgProducer batchDataTaskMsgProducer;

    @Autowired
    private DataReloadAndAggregateOrderLockService dataReloadAndAggregateOrderLockService;

    public void aggregateOrder(AggregateQueryConf config){

        log.info("开始集单，aggregateQueryConf:{}", JSON.toJSONString(config));
        //分布式处理
        if(Boolean.TRUE.equals(config.getDistFlag())){

            BatchDataTaskMessage batchDataTaskMessage = new BatchDataTaskMessage();

            batchDataTaskMessage.setBatchTaskId(UUID.randomUUID().toString());
            batchDataTaskMessage.setBatchDataTypeEnum(BatchDataTypeEnum.AGGREGATE_ORDER);
            batchDataTaskMessage.setSplitCount(BizConstant.AGGREGATE_ORDER_BATCH_DATA_SPLIT_COUNT);

            String paramStr = JSON.toJSONString(config);
            JSONObject param = JSONObject.parseObject(paramStr);

            // 特殊参数字段处理，后续待业务清晰后修改
            param.put(BatchDataTaskParamKeyEnum.GROUP_VALUE.getKey(), config.getGroupValue());
            if (CollectionUtils.isNotEmpty(config.getWarehouses())){

                param.put(BatchDataTaskParamKeyEnum.WARE_HOUSE_ID.getKey(), config.getWarehouses().get(0));
            }

            batchDataTaskMessage.setParam(param.toJSONString());

            batchDataTaskMsgProducer.sendBatchDataTaskMsg(batchDataTaskMessage);

        }

        // 普通处理
        if(config.getDistFlag()==null || Boolean.FALSE.equals(config.getDistFlag())){
            // 查询数据
            try {
                List<OrderDO> orders = queryOrders(config);
                log.info("通过任务获取到待集单任务大小:{},任务列表:{}",orders.size(),orders.stream().map(p->String.valueOf(p.getId())).collect(Collectors.joining(Constants.SYMBOL_COMMA)));
                aggregateOrder(orders);
            } finally {

                dataReloadAndAggregateOrderLockService.releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.AGGREGATE_ORDER, config.getWarehouses().get(0));
            }
        }
    }


    public List<OrderDO> queryOrders(AggregateQueryConf conf){

        //每次取数都是一天的数据
        OrderDOCriteria orderCriteria =new  OrderDOCriteria();
        OrderDOCriteria.Criteria criteria = orderCriteria.createCriteria();
        criteria.andExpectPushTimeGreaterThanOrEqualTo(DateUtil.todayStart());
        criteria.andExpectPushTimeLessThanOrEqualTo(DateUtil.todayEnd());
        criteria.andStatusEqualTo(OrderStatusEnum.INIT.getStatus());

        if(conf.getGroupValue()!=null) {
            criteria.andGroupValueEqualTo(conf.getGroupValue());
        }
        if (conf.getGroupType()!=null) {
            criteria.andGroupTypeEqualTo(Byte.valueOf(conf.getGroupType()));
        }

        if (!CollectionUtils.isEmpty(conf.getStores())) {
            criteria.andStoreIdIn(conf.getStores());
        }
        if (!CollectionUtils.isEmpty(conf.getCities())) {
            criteria.andCityIdIn(conf.getCities());
        }
        if (!CollectionUtils.isEmpty(conf.getGoodes())) {
            criteria.andProductNoIn(conf.getGoodes());
        }
        if (!CollectionUtils.isEmpty(conf.getWarehouses())) {
            criteria.andWarehouseIdIn(conf.getWarehouses());
        }
        //判断 groupValue 的 hold单时间和类型
        String orderCancelExpireTime = configurationPorcessor.getOrderCancelExpireTime(conf.getGroupValue());
        if(orderCancelExpireTime!=null){
            Date nowTime=new Date();
            Date cancelTime = DateUtil.parseDateTime(DateUtil.formatDate(nowTime) + " " + orderCancelExpireTime.trim());
            if (nowTime.compareTo(cancelTime) <= 0) {
                Integer orderHoldMins = configurationPorcessor.getOrderHoldMins(conf.getGroupValue());
                criteria.andCreateTimeLessThanOrEqualTo(DateUtil.addMinutes(new Date(), -orderHoldMins));
            }
        }


        return orderDOMapperExtend.selectByExample(orderCriteria);
    }


    public List<Long> queryOrderIds(AggregateQueryConf conf){

        //每次取数都是一天的数据
        OrderDOCriteria orderCriteria =new  OrderDOCriteria();
        OrderDOCriteria.Criteria criteria = orderCriteria.createCriteria();
        criteria.andExpectPushTimeGreaterThanOrEqualTo(DateUtil.todayStart());
        criteria.andExpectPushTimeLessThanOrEqualTo(DateUtil.todayEnd());
        criteria.andStatusEqualTo(OrderStatusEnum.INIT.getStatus());
        criteria.andGroupValueEqualTo(conf.getGroupValue());

        if (conf.getGroupType()!=null) {
            criteria.andGroupTypeEqualTo(Byte.valueOf(conf.getGroupType()));
        }

        if (!CollectionUtils.isEmpty(conf.getStores())) {
            criteria.andStoreIdIn(conf.getStores());
        }
        if (!CollectionUtils.isEmpty(conf.getCities())) {
            criteria.andCityIdIn(conf.getCities());
        }
        if (!CollectionUtils.isEmpty(conf.getGoodes())) {
            criteria.andProductNoIn(conf.getGoodes());
        }
        if (!CollectionUtils.isEmpty(conf.getWarehouses())) {
            criteria.andWarehouseIdIn(conf.getWarehouses());
        }

        //判断 groupValue 的 hold单时间和类型
        String orderCancelExpireTime = configurationPorcessor.getOrderCancelExpireTime(conf.getGroupValue());
        if(orderCancelExpireTime!=null){
            Date nowTime=new Date();
            Date cancelTime = DateUtil.parseDateTime(DateUtil.formatDate(nowTime) + " " + orderCancelExpireTime.trim());
            if (nowTime.compareTo(cancelTime) <= 0) {
                Integer orderHoldMins = configurationPorcessor.getOrderHoldMins(conf.getGroupValue());
                criteria.andCreateTimeLessThan(DateUtil.addMinutes(new Date(), -orderHoldMins));
            }
        }

        return orderDOMapperExtend.selectIdListByExample(orderCriteria);
    }


    public void filterOrder(List<OrderDO> orders){
        orders=orderFilterActuatorService.filter(orders);
    }


    //集单
    public void aggregateOrder(List<OrderDO> orders){

        Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggreList = aggreSubOrder(orders);

        for (Map.Entry<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> entry : aggreList.entrySet()) {
            log.info("开始生成生产单，类型:{},数量:{}",entry.getKey(),entry.getValue().size());
            List<ProduceOrderAndDetailBO> result = entry.getValue().entrySet().stream().map(p -> buildProduce(p.getKey(), p.getValue(),entry.getKey()))
                    .collect(Collectors.toList());
            result.stream().forEach(p->createProduceOrder(p));
        }
    }

    //支持利用mq来拆分集单 的方法
    public  Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggreSubOrder(List<OrderDO> orders){
        log.info("--->接收到批处理集单任务:任务大小:{}",orders.size());
        //根据规则集单
        Map<String, OrderAggregationRuleService> beansMap = listableBeanFactory.getBeansOfType(OrderAggregationRuleService.class);

        Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> result =Maps.newHashMap();
        for (OrderDO order : orders) {
            //获取取单规则
            GroupValueEnum groupEnum = GroupValueEnum.fromValue(order.getGroupValue());
            ConfigReq req = ConfigReq.builder()
                    .attributeType(groupEnum.getAttributeType())
                    .categoryType(groupEnum.getCategoryType())
                    .cityId(order.getCityId())
                    .houseId(order.getWarehouseId())
                    .goodId(order.getProductNo())
                    .build();

            log.info("集单策略获取请求参数:{}", JSON.toJSONString(req));
            FulfillPolicy fulfillPolicy = configCenter.getFulfillPolicy(req);
            if(fulfillPolicy==null){
                log.error("订单[{}]没有匹配到对应的匹配规则,请联系管理员!!! orderInfo:{}",order.getId(),JSON.toJSONString(order));
                continue;
            }

            OrderAggregationTypeEnum aggreType=OrderAggregationTypeEnum.fromName(fulfillPolicy.getRuleName());
            OrderAggregationRuleService aggregaService = beansMap.get(aggreType.getProcessorBeanName());
            String key = aggregaService.aggregateOrder(order);
            if(StringUtils.isEmpty(key)){
                log.info("订单[{}]通过集单策略[{}]计算得到的key为null,不参与集单!!",order.getId(),aggreType.getName());
                continue;
            }

            //放入结果集
            if(!result.containsKey(aggreType)){
                result.put(aggreType,Maps.newHashMap());
            }
            if(!result.get(aggreType).containsKey(key)){
                result.get(aggreType).put(key, new ArrayList<>());
            }
            result.get(aggreType).get(key).add(order);
        }
        return result;
    }

    public Map<OrderAggregationTypeEnum, Map<String, List<OrderDO>>> aggregateById(List<Long> ids){

        List<OrderDO> orders = orderDOMapperExtend.selectByIds(ids);
        return aggreSubOrder(orders);
    }


    private ProduceOrderAndDetailBO buildProduce(String key,List<OrderDO> orders,OrderAggregationTypeEnum aggreType) {
        ProduceOrder produceOrder = new ProduceOrder();

        // 如果是按照交易订单维度集单的生产单号就填写交易单号
        String produceOrderNo = DateUtil.formatLDateTime(new Date()) + String.format("%018d", orders.get(0).getId());;
        if (OrderAggregationTypeEnum.TRANS_ORDER.equals(aggreType)) {
            produceOrderNo = orders.get(0).getTransOrderId();
        }
        produceOrder.setProduceOrderNo(produceOrderNo);
        produceOrder.setExpectPushTime(orders.get(0).getExpectPushTime());
        produceOrder.setOrderCount(orders.size());
        produceOrder.setStoreId(orders.get(0).getStoreId());
        produceOrder.setStatus(ProduceOrderStatusEnum.INIT.getStatus());
        produceOrder.setAggregateType(aggreType.getValue());
        produceOrder.setAggregateValue(key);
        produceOrder.setGroupType(orders.get(0).getGroupType());
        produceOrder.setGroupValue(orders.get(0).getGroupValue());
        produceOrder.setCityId(orders.get(0).getCityId());
        produceOrder.setWarehouseId(orders.get(0).getWarehouseId());

        log.info("生成生产单[{}],包含商品单数量:{},ids:[{}]",produceOrderNo, orders.size(),orders.stream().map(p->String.valueOf(p.getId())).collect(Collectors.joining(Constants.SYMBOL_COMMA)));

        return ProduceOrderAndDetailBO.builder().produceOrder(produceOrder)
                .produceOrderDetails(buildProduceOrderDetail(produceOrderNo,orders)).build();
    }

    private List<ProduceOrderDetail> buildProduceOrderDetail(String orderNo,List<OrderDO> orders) {
        return orders.stream().map(p->{
            ProduceOrderDetail detail =new ProduceOrderDetail();
            detail.setOrderId(p.getId());
            detail.setProduceOrderNo(orderNo);
            return detail;
        }).collect(Collectors.toList());
    }


    @Transactional(value = "fcpTransactionManager", rollbackFor = Exception.class)
    public void createProduceOrder(ProduceOrderAndDetailBO produceOrderAndDetailBO) {

        List<Long> orderIds = produceOrderAndDetailBO.getProduceOrderDetails().stream().map(ProduceOrderDetail::getOrderId).collect(Collectors.toList());

        try {
            //保证pe_order 的订单都没有被处理
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_PROCESS.getStatus(), OrderStatusEnum.INIT.getStatus());
            log.info("生产单[{}]状态正常,开始插入 produce_order ,更新 pe_order状态.",produceOrderAndDetailBO.getProduceOrder().getProduceOrderNo());
            produceOrderAndDetailDao.insertOrder(produceOrderAndDetailBO.getProduceOrder(), produceOrderAndDetailBO.getProduceOrderDetails());

            // 更新订单状态为积单完成
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_COMPLETE.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
        }catch (Exception e){
            log.error("createProduceOrder 发生异常,信息:{}", JSON.toJSONString(produceOrderAndDetailBO),e);
        }

    }
}
