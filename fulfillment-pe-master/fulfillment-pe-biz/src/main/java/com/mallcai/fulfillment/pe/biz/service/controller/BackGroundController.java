package com.mallcai.fulfillment.pe.biz.service.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.redis.plugin.ms.JedisClientUtil;
import com.mallcai.fulfillment.pe.api.msg.ProduceOrderInfoListMsg;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.pe.biz.service.bo.AnalysisAggregate;
import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;
import com.mallcai.fulfillment.pe.biz.service.bo.MessageRetryBo;
import com.mallcai.fulfillment.pe.biz.service.bo.PurchaseBO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderPushService;
import com.mallcai.fulfillment.pe.biz.service.inner.ProduceOrderService;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.PurchaseInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.ReceivePurchaseForecastInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.purchase.impl.PurchaseOperateServiceImpl;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderFileService;
import com.mallcai.fulfillment.pe.biz.service.service.AggreOrderQueryService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.mq.ErpMqProducer;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 后门口子，用于触发一些任务之类的
 * @author: chentao
 * @create: 2019-08-28 21:28:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/fulfillment/background/")
public class BackGroundController {

    @Autowired
    private ProduceOrderService produceOrderService;

    @Autowired
    private ProduceOrderPushService produceOrderPushService;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Autowired
    private ErpMqProducer erpMqProducer;

    @Autowired
    private Environment environment;

    @Autowired
    private OrderDOMapperExtend orderDOMapperExtend;

    @Autowired
    private AggreOrderQueryService aggreOrderQueryService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PurchaseOperateServiceImpl purchaseOperateService;

    @Autowired
    private PurchaseInnerService purchaseInnerService;

    @Autowired
    private JedisClientUtil jedisClientUtil;

    @Autowired
    private ReceivePurchaseForecastInnerService receivePurchaseForecastInnerService;

    @Resource
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;

    @Autowired
    AggreOrderFileService aggreOrderFileService;

    private final Long LOCK_EXPIRE_TIME = 10 * 60L;

    /**
     * beta环境测试门店ID
     */
    private final List<Integer> BETA_ENVIRONMENT_STOREIDS = Lists.newArrayList(1329,1354,1355);

    /**
     * 生产环境tag标识
     */
    private final String PRODUCT_ENVIRONMENT_TAG = "product_vpc";


    /**
     * 手工触发生成备灾文件
     * curl -i -X POST -H "Content-Type:application/json" -d '{"groupValue":"2","pushTime":"2019-11-08 00:00:00"}' 'http://localhost:6002/fulfillment/background/genaratorXls'
     * @return
     */
    @RequestMapping(value = "genaratorXls",method = RequestMethod.POST)
    public void genaratorXls(@RequestBody AnalysisAggregate info){
        log.info("back exec genaratorXls,params:{}",JSON.toJSONString(info));
        aggreOrderFileService.AggreXlsFile(info);
    }



    /**
     * 知道怎么改货品字段可以调用这个接口
     * @param commonInfoExtStr
     * @param orderIds
     * @return
     */
    @RequestMapping(value = "order/modify")
    public String modifyOrders(@RequestParam("commonInfoExtStr") String commonInfoExtStr, @RequestParam("orderIds") String orderIds){
        log.info("后门触发修改pe_order口子,commonInfoExtStr:{},orderIds:{}", commonInfoExtStr, orderIds);
        if(StringUtils.isEmpty(orderIds)||StringUtils.isEmpty(commonInfoExtStr)){
            return "存在参数为空!";
        }
        List<Long> idList=new ArrayList<>();
        if(orderIds.indexOf(",")<0){
            idList.add(Long.parseLong(orderIds));
        }else{
            String[] idStrArray=orderIds.split(",");
            for(String str:idStrArray){
                idList.add(Long.parseLong(str));
            }
        }
        if(CollectionUtils.isNotEmpty(idList)){
            OrderDO orderDO=new OrderDO();
            orderDO.setCommodityInfoExt(commonInfoExtStr);
            OrderDOCriteria orderDOCriteria=new OrderDOCriteria();
            orderDOCriteria.createCriteria().andIdIn(idList);
            return orderDao.updateByExampleSelective(orderDO,orderDOCriteria)+"";
        }
        return "0";
    }

    /**
     * 根据id直接去查货品关联
     * @param orderIds
     * @return
     */
    @RequestMapping(value = "order/modifyBySearchGoods")
    public String modifyBySearchGoods(@RequestParam("orderIds") String orderIds){
        log.info("后门触发修改pe_order口子,orderIds:{}",  orderIds);
        if(StringUtils.isEmpty(orderIds)){
            return "存在参数为空!";
        }
        List<Long> idList=new ArrayList<>();
        if(orderIds.indexOf(",")<0){
            idList.add(Long.parseLong(orderIds));
        }else{
            String[] idStrArray=orderIds.split(",");
            for(String str:idStrArray){
                idList.add(Long.parseLong(str));
            }
        }
        if(CollectionUtils.isNotEmpty(idList)){
            return executeDataRepair(idList);
        }
        return "0";
    }

    /**
     * 根据id去查货品信息
     * @param idList
     * @return
     */
    public String executeDataRepair(List<Long> idList){
        OrderDOCriteria orderDOCriteria=new OrderDOCriteria();
        orderDOCriteria.createCriteria().andIdIn(idList);
        List<OrderDO> orderDOList=orderDao.selectSpecialByExample(orderDOCriteria);
        List<ItemGoodsInfoBO> itemGoodsInfoBOList = getGoodsList(orderDOList);
        int count=0;
        if(CollectionUtils.isNotEmpty(orderDOList)){
          for (OrderDO orderDO:orderDOList){
            OrderDO target=new OrderDO();
            BigDecimal productNum = JSONObject
                    .parseObject(orderDO.getCommodityInfoExt()).getBigDecimal(CommodityInfoExtEnum.PRODUCT_NUM.getKey());
              buildSortingOrderCommodityInfo(orderDO,itemGoodsInfoBOList,productNum);
              target.setCommodityInfoExt(orderDO.getCommodityInfoExt());
              OrderDOCriteria orderDOCriteria1=new OrderDOCriteria();
              orderDOCriteria1.createCriteria().andIdEqualTo(orderDO.getId());
              count+=orderDao.updateByExampleSelective(target,orderDOCriteria1);
            }
        }
        return count+"";
    }

  public void buildSortingOrderCommodityInfo(OrderDO order,
      List<ItemGoodsInfoBO> itemGoodsInfoBoList,BigDecimal productNum) {
    // 商品信息扩展字段
    JSONObject commodityInfoExt = new JSONObject();
    if(CollectionUtils.isNotEmpty(itemGoodsInfoBoList)){
      for(ItemGoodsInfoBO itemGoodsInfoBO:itemGoodsInfoBoList){
        if(itemGoodsInfoBO.getCityId().equals(order.getCityId())&&itemGoodsInfoBO.getCityProductId().equals(order.getCityProductId())){
          commodityInfoExt.put(CommodityInfoExtEnum.GOODS_ID.getKey(),itemGoodsInfoBO.getGoodsId());
          commodityInfoExt.put(CommodityInfoExtEnum.GOODS_UNIT.getKey(),itemGoodsInfoBO.getGoodsUnit());
          commodityInfoExt.put(CommodityInfoExtEnum.REL_NUM.getKey(),itemGoodsInfoBO.getRelNum());
        }
      }
    }
    commodityInfoExt.put(CommodityInfoExtEnum.PRODUCT_NUM.getKey(),productNum);
    order.setCommodityInfoExt(commodityInfoExt.toJSONString());
  }

    public List<ItemGoodsInfoBO> getGoodsList(List<OrderDO> orderDOList) {
        log.info("即将开始进行PE_ORDER数据格式转换！");
        List<ItemGoodsInfoBO> result = new ArrayList<>();
        List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
        for (OrderDO orderDO : orderDOList) {
            if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
                boolean flag = false;
                for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
                    if (queryGoodsRelBO.getCityId().equals(orderDO.getCityId()) && queryGoodsRelBO
                        .getCityProductId().equals(orderDO.getCityProductId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                    queryGoodsRelBO.setCityId(orderDO.getCityId());
                    queryGoodsRelBO.setCityProductId(orderDO.getCityProductId());
                    queryGoodsRelBOList.add(queryGoodsRelBO);
                }
            } else {
                QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                queryGoodsRelBO.setCityId(orderDO.getCityId());
                queryGoodsRelBO.setCityProductId(orderDO.getCityProductId());
                queryGoodsRelBOList.add(queryGoodsRelBO);
            }
        }
        if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
            log.info("即将开始进行MGR数据货品转换！");
            result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
        }
        return result;
    }

    @RequestMapping(value = "aggregate/task")
    public String aggregateOrderTask(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                     @RequestParam("groupValue") String groupValue,
                                     @RequestParam("orderHoldMins") Integer orderHoldMins) {

        log.info("当前环境:{}", environment.getActiveProfiles()[0]);
        if (!environment.getActiveProfiles()[0].equals(PRODUCT_ENVIRONMENT_TAG)) {

            return "illegal";
        }

        OrderQueryDto orderQueryDto = new OrderQueryDto();

        orderQueryDto.setStartTime(DateUtil.parseLDate(startDate));
        orderQueryDto.setEndTime(DateUtil.parseLDate(endDate));
        orderQueryDto.setGroupValue(groupValue);
        orderQueryDto.setOrderHoldMins(orderHoldMins);

        return triggerAggregateTask(orderQueryDto);
    }

    @RequestMapping(value = "purchase/dataRepair")
    public PlainResult dataRepair(@RequestBody PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
        if (Objects.isNull(purchaseForecastInfoListDTO)||Objects.isNull(purchaseForecastInfoListDTO.getCityId())||Objects.isNull(purchaseForecastInfoListDTO.getWarehouseId())
            ||Objects.isNull(purchaseForecastInfoListDTO.getForecastType())||Objects.isNull(purchaseForecastInfoListDTO.getCategoryType())||
            Objects.isNull(purchaseForecastInfoListDTO.getForecastDate())||Objects.isNull(purchaseForecastInfoListDTO.getVersion())) {

            return PlainResult.fail("参数不能为空!");
        }
        return receivePurchaseForecastInnerService.executeDateRepair(purchaseForecastInfoListDTO);
    }

    @RequestMapping(value = "purchase/dataRePush")
    public PlainResult dataRePush(@RequestBody PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
        if (Objects.isNull(purchaseForecastInfoListDTO)||Objects.isNull(purchaseForecastInfoListDTO.getCityId())||Objects.isNull(purchaseForecastInfoListDTO.getWarehouseId())
            ||Objects.isNull(purchaseForecastInfoListDTO.getForecastType())||Objects.isNull(purchaseForecastInfoListDTO.getCategoryType())||
            Objects.isNull(purchaseForecastInfoListDTO.getForecastDate())||Objects.isNull(purchaseForecastInfoListDTO.getVersion())) {

            return PlainResult.fail("参数不能为空!");
        }
        return receivePurchaseForecastInnerService.executeDateRePush(purchaseForecastInfoListDTO);
    }


    @RequestMapping(value = "orderpush/task")
    public String orderPushTask(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {

        if (!environment.getActiveProfiles()[0].equals(PRODUCT_ENVIRONMENT_TAG)) {

            return "illegal";
        }

        return triggerOrderPushTask(startDate, endDate);
    }

    @RequestMapping(value = "aggregate/task/test")
    public String aggregateOrderTaskByTest(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                           @RequestParam("orderHoldMins") Integer orderHoldMins, @RequestParam("groupValue") String groupValue,
                                           @RequestParam("storeId") Integer storeId) {

        AssertUtils.assertNotNull(storeId);

        if (environment.getActiveProfiles()[0].contains("beta")) {

            if (!BETA_ENVIRONMENT_STOREIDS.contains(storeId)) {

                return "illegal";
            }
        } else if (environment.getActiveProfiles()[0].contains("product")) {

            return "illegal";
        }

        OrderQueryDto orderQueryDto = new OrderQueryDto();

        orderQueryDto.setStartTime(DateUtil.parseLDate(startDate));
        orderQueryDto.setEndTime(DateUtil.parseLDate(endDate));
        orderQueryDto.setOrderHoldMins(orderHoldMins);
        orderQueryDto.setGroupValue(groupValue);
        orderQueryDto.setStoreId(storeId);

        return triggerAggregateTask(orderQueryDto);
    }

    @RequestMapping(value = "retry/message")
    public String mqMessageRetry(@RequestBody MessageRetryBo messageRetryBo) {

        log.info("后门触发消息，topic:{},messages:{}", messageRetryBo.getTopic(), messageRetryBo.getMessage());

        log.info("后门发送消息开始,key:{},内容:{}", messageRetryBo.getKey(), messageRetryBo.getMessage());
        boolean result = erpMqProducer.sendMq(messageRetryBo.getTopic(), messageRetryBo.getTag(), messageRetryBo.getKey(), messageRetryBo.getMessage());
        log.info("后门发送消息结束,key:{},结果:{}", messageRetryBo.getKey(), result);

        return "ok";
    }

    @RequestMapping(value = "aggregateOrder")
    public String aggregateOrderByOrderIds(@RequestParam("orderIds") String orderIdsStr){

        log.info("后门触发集单,orderIds:{}", orderIdsStr);
        List<Long> orderIds = Arrays.asList(orderIdsStr.split(Constants.SYMBOL_COMMA)).stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());

        List<OrderDO> orders = orderDOMapperExtend.selectByIds(orderIds);

        aggreOrderQueryService.aggregateOrder(orders);

        return "ok";
    }

    /**
     * 置废数据
     * @param
     * @return
     */
    @RequestMapping(value = "abolishPurchase")
    public String abolishPurchase(@RequestParam("forecastDate") String forecastDate,
                                  @RequestParam("categoryType") Integer categoryType,
                                  @RequestParam("cityId") Integer cityId,
                                  @RequestParam("forecastType") Integer forecastType,
                                  @RequestParam("version") Integer version,
                                  @RequestParam("warehouseId") Integer warehouseId){
        PurchaseForecastInfoListDTO forecastInfoListDTO = new PurchaseForecastInfoListDTO();
        forecastInfoListDTO.setCategoryType(Byte.valueOf(String.valueOf(categoryType)));
        forecastInfoListDTO.setCityId(cityId);
        forecastInfoListDTO.setForecastType(Byte.valueOf(String.valueOf(forecastType)));
        forecastInfoListDTO.setVersion(version);
        forecastInfoListDTO.setWarehouseId(warehouseId);
        forecastInfoListDTO.setForecastDate(forecastDate);
        log.info("后门置废采购数据,forecastInfoListDTO:{}", JSON.toJSON(forecastInfoListDTO));
        purchaseOperateService.cancelPurchase(forecastInfoListDTO);
        return "ok";
    }

    @RequestMapping(value = "back/executePurchaseOperation")
    public String backExecutePurchaseOperation(@RequestParam("forecastDate") String forecastDate,
                                               @RequestParam("categoryType") Integer categoryType,
                                               @RequestParam("cityId") Integer cityId,
                                               @RequestParam("forecastType") Integer forecastType,
                                               @RequestParam("version") Integer version,
                                               @RequestParam("warehouseId") Integer warehouseId,
                                               @RequestParam("count") Integer count) {
        PurchaseBO purchaseBO=new PurchaseBO();
        purchaseBO.setCityId(cityId);
        purchaseBO.setCategoryType(Byte.valueOf(String.valueOf(categoryType)));
        purchaseBO.setCount(count);
        purchaseBO.setForecastDate(DateUtil.parseDate(forecastDate));
        purchaseBO.setForecastType(Byte.valueOf(String.valueOf(forecastType)));
        purchaseBO.setWarehouseId(warehouseId);
        purchaseBO.setVersion(version);
        purchaseInnerService.purchaseExecute(purchaseBO,false);
        return "ok";
    }

    @RequestMapping(value = "redis/opt")
    public Object redisOperate(@RequestParam("opt") String opt, @RequestParam("key") String key,
                               @RequestParam(value = "value", required = false) String value,
                               @RequestParam(value = "expire", required = false) int expire,
                               @RequestParam(value = "fields", required = false) String field,
                               @RequestParam(value = "incrByValue", required = false) Long incrByValue,
                               @RequestParam(value = "decrByValue", required = false) Long decrByValue) {

        if ("get".equals(opt)) {

            return jedisClientUtil.get(key);
        } else if ("setex".equals(opt)) {

            return jedisClientUtil.setex(key, expire, value);
        } else if ("smember".equals(opt)) {
            return jedisClientUtil.smembers(key);

        } else if ("sadd".equals(opt)) {

            String[] values = value.split(Constants.SYMBOL_COMMA);
            return jedisClientUtil.sadd(key, values);
        } else if ("del".equals(opt)) {

            return jedisClientUtil.del(key);
        } else if ("hdel".equals(opt)) {

            String[] fields = field.split(Constants.SYMBOL_COMMA);
            return jedisClientUtil.hdel(key, fields);
        } else if ("hincrBy".equals(opt)) {

            return jedisClientUtil.hincrBy(key, field, incrByValue);
        } else if ("hgetAll".equals(opt)) {

            return jedisClientUtil.hgetAll(key);
        } else if ("decrBy".equals(opt)) {

            return jedisClientUtil.decrBy(key, decrByValue);
        }

        return "illegal opt";
    }


    @RequestMapping(value = "order/status")
    public boolean updateOrderStatus(@RequestParam("orderIds") String orderIdsStr, @RequestParam("targetStatus") String targetStatus,
                                    @RequestParam("underStatus") String underStatus){

        log.info("后门触发集单,orderIds:{},targetStatus:{},underStatus:{}", orderIdsStr, targetStatus, underStatus);
        List<Long> orderIds = Arrays.asList(orderIdsStr.split(Constants.SYMBOL_COMMA)).stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());

        int count = 0;
        for (Long id : orderIds){
            if (!orderDao.updateStatusByIdUnderStatus(id, Byte.valueOf(targetStatus), Byte.valueOf(underStatus))){

                log.error("后门触发更新订单状态失败,id:{}", id);
                continue;
            }

            count++;
        }

        return count == orderIds.size();
    }

    private String triggerAggregateTask(OrderQueryDto orderQueryDto) {

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_BACKGROUND_ORDER_AGGREGATE;

        boolean lockResult = false;
        try {

            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);

            if (lockResult == false) {

                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return "fail";
            }

            log.info("后台触发积单任务开始,开始时间:{},截止时间：{},orderHoldMins:{}", DateUtil.formatDate(orderQueryDto.getStartTime()), DateUtil.formatDate(orderQueryDto.getEndTime()), orderQueryDto.getOrderHoldMins());

            produceOrderService.createProduceOrder(new DataBo(orderQueryDto, null));

            log.info("后台触发积单任务结束");
        } catch (Exception e) {
            log.info("后台触发积单任务异常", e);

            throw e;
        } finally {

            if (lockResult == true) {

                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }

        return "ok";
    }

    private String triggerOrderPushTask(String startDate, String endDate) {

        String requestId = UUID.randomUUID().toString();
        String lockKey = Constants.REDIS_LOCK_PREFIX + Constants.REDIS_LOCK_KEY_ORDER_PUSH;

        boolean lockResult = false;

        try {

            lockResult = redisDistributeLockUtil.tryGetDistributedLock(lockKey, requestId, LOCK_EXPIRE_TIME);

            if (lockResult == false) {

                log.info("获取锁失败,lockKey:{},requestId:{}", lockKey, requestId);
                return "fail";
            }

            log.info("后台触发积单任务开始,开始时间:{},截止时间：{}", startDate, endDate);
            produceOrderPushService.pushProduceOrder(DateUtil.parseLDate(startDate), DateUtil.parseLDate(endDate));
            log.info("后台触发积单任务结束");

        } catch (Exception e) {
            log.error("后台触发积单任务异常", e);

            throw e;
        } finally {

            if (lockResult == true) {

                redisDistributeLockUtil.releaseDistributedLock(lockKey, requestId);
            }
        }

        return "ok";
    }


}
