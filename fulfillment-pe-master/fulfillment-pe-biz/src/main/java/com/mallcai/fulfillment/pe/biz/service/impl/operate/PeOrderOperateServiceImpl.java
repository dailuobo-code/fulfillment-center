package com.mallcai.fulfillment.pe.biz.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.service.operate.PeOrderOperateService;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CancleProduceRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderItemInfoDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderItemInfoListDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.SortingOrderRequestDTO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.DataReloadAndAggregateOrderLockRequesterEnum;
import com.mallcai.fulfillment.pe.biz.service.enums.PeOrderSourceEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.PeOrderOperateServiceInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.lock.DataReloadAndAggregateOrderLockService;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.pe.common.utils.RedisDistributeLockUtil;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.infrastructure.dao.OrderDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.ProduceOrderDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-10 22:30
 */
@Slf4j
@Service("peOrderOperateService")
public class PeOrderOperateServiceImpl implements PeOrderOperateService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;

    @Autowired
    private RedisDistributeLockUtil redisDistributeLockUtil;

    @Resource
    private PeOrderOperateServiceInnerService peOrderOperateServiceInnerService;

    @Autowired
    private ProduceOrderDao produceOrderDao;

    @Autowired
    private DataReloadAndAggregateOrderLockService dataReloadAndAggregateOrderLockService;

    /**
     * 锁超时时间，3分钟
     */
    private static final Integer LOCK_EXPIRE_TIME = 60 * 3;

    /**
     * 锁请求数据重入方
     */
    private static final String LOCK_REQUEST_DATA_RELOAD = "data_reload";

    @Override
    public PlainResult<?> acceptOrderItem(OrderItemInfoListDTO orderItemInfoListDTO) {
        log.info("即将开始进行MGR数据操作!");
        List<OrderDO> orderDOList = new ArrayList<>();
        List<OrderItemInfoDTO> orderItemInfoDTOList = orderItemInfoListDTO.getOrderItemInfoDOList();
        List<ItemGoodsInfoBO> itemGoodsInfoBOList = getGoodsList(orderItemInfoDTOList);
        convertOrderItemInfoDTO(orderItemInfoListDTO, orderDOList, itemGoodsInfoBOList);
        StringBuffer modifyCountBuffer = new StringBuffer();
        boolean result = executeSavePeOrderList(orderDOList, modifyCountBuffer);
        if(!result){
            log.error("本次批量插入分拣预测数据失败！");
            return PlainResult.fail("本次批量插入分拣预测数据失败");
        }
        int count = Integer.parseInt(modifyCountBuffer.toString());
        log.info("MGR本次批量插入有效数据量为:{}", count);
        return PlainResultBuilder.success(true);
    }

    public boolean executeSavePeOrderList(List<OrderDO> orderDOList, StringBuffer modifyCount) {
        boolean flag = true;
        int count = 0;
        for (OrderDO orderDO : orderDOList) {
            if (checkNotExistOrderDo(orderDO)) {
                try {
                    orderDao.insert(orderDO);
                    count++;
                } catch (Exception e) {
                    log.error("插入分拣预测数据失败,orderDO:{}", JSON.toJSONString(orderDO), e);
                    flag = false;
                }
            }
        }
        modifyCount.append(count);
        return flag;
    }


    public boolean checkNotExistOrderDo(OrderDO orderDO) {
        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
        orderDOCriteria.createCriteria().andCityIdEqualTo(orderDO.getCityId())
            .andWarehouseIdEqualTo(orderDO.getWarehouseId()).
            andStoreIdEqualTo(orderDO.getStoreId()).andCityProductIdEqualTo(orderDO.getCityProductId()).
            andExpectPushTimeGreaterThanOrEqualTo(orderDO.getExpectPushTime())
            .andExpectPushTimeLessThan(DateUtil.addDays(orderDO.getExpectPushTime(), 1))
            .andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus())
            .andOrderSourceEqualTo(PeOrderSourceEnum.MGR.getValue())
            .andVersionEqualTo(orderDO.getVersion());
        int count = orderDao.countSpecialByExample(orderDOCriteria);
        if (count == 0) {
            return true;
        }
        return false;
    }


    @Override
    public PlainResult<?> cancelOrder(SortingOrderRequestDTO sortingOrderRequestDTO) {
        try {
            log.info("即将作废pe_order的低版本数据");
            Date expectPushTime = DateUtil.parseDate(sortingOrderRequestDTO.getForecastDate());
            OrderDOCriteria bigOrderDOCriteria = new OrderDOCriteria();
            bigOrderDOCriteria.createCriteria().andCityIdEqualTo(sortingOrderRequestDTO.getCityId()).andWarehouseIdEqualTo(sortingOrderRequestDTO.getWarehouseId()).
                andExpectPushTimeGreaterThanOrEqualTo(DateUtil.dayStart(expectPushTime)).andExpectPushTimeLessThan(DateUtil.addDays(DateUtil.dayStart(expectPushTime), 1))
                .andOrderSourceEqualTo(PeOrderSourceEnum.BIG.getValue());
            OrderDO bigOrderDO = new OrderDO();
            bigOrderDO.setStatus(OrderStatusEnum.DELETE.getStatus());
            //置废大数据的数据
            int orderDeleteCount=orderDao.updateByExampleSelective(bigOrderDO, bigOrderDOCriteria);
            log.info("本次删除pe_order低版本大数据数量为:{}",orderDeleteCount);
            //重置MGR和预售数据
            OrderDO mgrOrderDo=new OrderDO();
            OrderDOCriteria mgrOrderDOCriteria = new OrderDOCriteria();
            mgrOrderDo.setStatus(OrderStatusEnum.INIT.getStatus());
            mgrOrderDOCriteria.createCriteria().andCityIdEqualTo(sortingOrderRequestDTO.getCityId()).andWarehouseIdEqualTo(sortingOrderRequestDTO.getWarehouseId()).
                andExpectPushTimeGreaterThanOrEqualTo(DateUtil.dayStart(expectPushTime)).andExpectPushTimeLessThan(DateUtil.addDays(DateUtil.dayStart(expectPushTime), 1))
                .andOrderSourceEqualTo(PeOrderSourceEnum.MGR.getValue());
            int mgrOrderModifyCount=orderDao.updateByExampleSelective(mgrOrderDo, mgrOrderDOCriteria);
            log.info("本次更新pe_order的MGR版本数量为:{}",mgrOrderModifyCount);
            //重置预售数据
            OrderDO bookOrderDo=new OrderDO();
            OrderDOCriteria bookOrderDOCriteria = new OrderDOCriteria();
            bookOrderDo.setStatus(OrderStatusEnum.INIT.getStatus());
            bookOrderDOCriteria.createCriteria().andCityIdEqualTo(sortingOrderRequestDTO.getCityId()).andWarehouseIdEqualTo(sortingOrderRequestDTO.getWarehouseId()).
                andExpectPushTimeGreaterThanOrEqualTo(DateUtil.dayStart(expectPushTime)).andExpectPushTimeLessThan(DateUtil.addDays(DateUtil.dayStart(expectPushTime), 1))
                .andOrderSourceEqualTo(PeOrderSourceEnum.CUSTOMER.getValue()).andGroupValueEqualTo(GroupValueEnum.FRESH_PRODUCT.getGroupValue());
            int bookOrderModifyCount=orderDao.updateByExampleSelective(bookOrderDo, bookOrderDOCriteria);
            log.info("本次更新pe_order的预售数量为:{}",bookOrderModifyCount);
            //置废生产单
            int deleteProduceOrderCount=produceOrderDao.cancelProduceOrderBywareHouseIdAndCityId(GroupValueEnum.FRESH_PRODUCT, sortingOrderRequestDTO.getWarehouseId(), sortingOrderRequestDTO.getCityId(), sortingOrderRequestDTO.getForecastDate());
            log.info("本次作废生产单数量为:{}",deleteProduceOrderCount);
        } catch (Exception e) {
            log.error("作废生产订单表失败，sortingOrderRequestDTO:{}", sortingOrderRequestDTO);
            return PlainResult.fail("作废生产订单表失败");
        }finally {
            dataReloadAndAggregateOrderLockService.releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.DATA_RELOAD, sortingOrderRequestDTO.getWarehouseId());
        }
        return PlainResultBuilder.success(true);
    }

    @Override
    public PlainResult<?> cancelProduceOrder(CancleProduceRequestDTO dto) {
        log.info("即将进行订单作废操作,cityId:{},warehouseId,forecastDate:{}",dto.getCityId(),dto.getWarehouseId(),dto.getForecastDate());
        boolean result=dataReloadAndAggregateOrderLockService.getDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.DATA_RELOAD, dto.getWarehouseId());
        if(result){
            if (peOrderOperateServiceInnerService.cancelProduceOrder(dto)) {
                return PlainResultBuilder.success(true);
            } else {
                dataReloadAndAggregateOrderLockService.releaseDataReloadAndAggregateOrderLock(DataReloadAndAggregateOrderLockRequesterEnum.DATA_RELOAD, dto.getWarehouseId());
                return PlainResult.fail("即将通知下游作废生产单操作失败");
            }
        }else{
            return PlainResult.fail("获取数据重入锁失败");
        }
    }

    public void convertOrderItemInfoDTO(OrderItemInfoListDTO orderItemInfoListDTO, List<OrderDO> orderDOList, List<ItemGoodsInfoBO> itemGoodsInfoBoList) {
        List<OrderItemInfoDTO> orderItemInfoDTOList = orderItemInfoListDTO.getOrderItemInfoDOList();
        Map<Integer, ItemGoodsInfoBO> goodsInfo = itemGoodsInfoBoList.stream().collect(Collectors.toMap(p -> p.getCityProductId(), p -> p));
        if (CollectionUtils.isNotEmpty(orderItemInfoDTOList)) {
            for (OrderItemInfoDTO orderItemInfoDTO : orderItemInfoDTOList) {
                OrderDO orderDO = new OrderDO();
                orderDO.setTransOrderId(orderItemInfoDTO.getOrderNo());
                orderDO.setStatus(OrderStatusEnum.INIT.getStatus());
                orderDO.setOrderType(orderItemInfoDTO.getOrderType().intValue());
                orderDO.setOrderSource(PeOrderSourceEnum.MGR.getValue());
                orderDO.setCityProductId(orderItemInfoDTO.getCityProductId());
                orderDO.setStoreId(orderItemInfoDTO.getStoreId());
                orderDO.setUserId(0);
                orderDO.setCityId(orderItemInfoDTO.getCityId());
                orderDO.setProductNo(orderItemInfoDTO.getProductNo());
                orderDO.setGroupType(GroupTypeEnum.CATEGORY.getGroupType());
                orderDO.setGroupValue(GroupValueEnum.FRESH_PRODUCT.getGroupValue());
                orderDO.setWarehouseId(orderItemInfoDTO.getWarehouseId());
                orderDO.setVersion(0);
                // 商品信息扩展字段  货品信息 + 数按量
                if (!goodsInfo.containsKey(orderDO.getCityProductId())) {
                    throw new BizException("没有查询到对应的货品信息:" + orderDO.getCityProductId());
                }
                ItemGoodsInfoBO good = goodsInfo.get(orderDO.getCityProductId());
                Map<String, Object> infos = ImmutableMap.of(CommodityInfoExtEnum.GOODS_ID.getKey(), good.getGoodsId(),
                        CommodityInfoExtEnum.GOODS_UNIT.getKey(), good.getGoodsUnit(),
                        CommodityInfoExtEnum.REL_NUM.getKey(), good.getRelNum(),
                        CommodityInfoExtEnum.PRODUCT_NUM.getKey(), orderItemInfoDTO.getProductNum());
                orderDO.setCommodityInfoExt(JSON.toJSONString(infos));
                orderDO.setExpectPushTime(DateUtil.dayStart(orderItemInfoDTO.getCreateTime()));
                orderDO.setCreateTime(new Date());
                orderDO.setUpdateTime(new Date());
                Date pickupTime = DateUtil.parseDate(orderItemInfoDTO.getPickupTime());
                orderDO.setPickupTime(pickupTime);
                orderDOList.add(orderDO);
            }
        }
    }

    public List<ItemGoodsInfoBO> getGoodsList(List<OrderItemInfoDTO> orderItemInfoDTOList) {
        log.info("即将开始进行MGR数据格式转换！");
        List<ItemGoodsInfoBO> result = new ArrayList<>();
        List<QueryGoodsRelBO> queryGoodsRelBOList = new ArrayList<>();
        for (OrderItemInfoDTO orderItemInfoDTO : orderItemInfoDTOList) {
            if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
                boolean flag = false;
                for (QueryGoodsRelBO queryGoodsRelBO : queryGoodsRelBOList) {
                    if (queryGoodsRelBO.getCityId().equals(orderItemInfoDTO.getCityId()) && queryGoodsRelBO
                            .getCityProductId().equals(orderItemInfoDTO.getCityProductId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                    queryGoodsRelBO.setCityId(orderItemInfoDTO.getCityId());
                    queryGoodsRelBO.setCityProductId(orderItemInfoDTO.getCityProductId());
                    queryGoodsRelBOList.add(queryGoodsRelBO);
                }
            } else {
                QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
                queryGoodsRelBO.setCityId(orderItemInfoDTO.getCityId());
                queryGoodsRelBO.setCityProductId(orderItemInfoDTO.getCityProductId());
                queryGoodsRelBOList.add(queryGoodsRelBO);
            }
        }
        if (CollectionUtils.isNotEmpty(queryGoodsRelBOList)) {
            log.info("即将开始进行MGR数据货品转换！");
            result = icProductGoodsServiceFacade.searchGoodsRel(queryGoodsRelBOList);
        }
        return result;
    }
}
