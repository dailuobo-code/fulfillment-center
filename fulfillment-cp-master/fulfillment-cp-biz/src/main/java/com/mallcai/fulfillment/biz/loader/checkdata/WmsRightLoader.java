package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.service.ErpWmsLoaderService;
import com.mallcai.fulfillment.infrastructure.mapper.wms.OubDeliveryOrderDetailMapper;
import com.mallcai.fulfillment.infrastructure.mapper.wms.OubDeliveryOrderMapper;
import com.mallcai.fulfillment.infrastructure.object.erp.*;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryKey;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrder;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrderCriteria;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrderDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;

/**
 * 冻品数据加载器（履约）
 *
 * @author Liu Yang
 * @date 2019/9/27 2:09 PM
 */
@Component("wmsRightLoader")
@Slf4j
@Service
public class WmsRightLoader implements BigDecimalLoader {


    @Autowired
    private OubDeliveryOrderDetailMapper oubDeliveryOrderDetailMapper;

    @Autowired
    private OubDeliveryOrderMapper oubDeliveryOrderMapper;

    /**
     * 一次批量操作大小
     */
    private final static int MAX_PROCESS_NUM = 10000;

    @Override
    public Map<String, BigDecimal> loadResource(CheckContext checkContext) {
        log.info("wms Right-LOADER-->start,get index:{}", JSON.toJSONString(checkContext));
        Map<String, BigDecimal> result = Maps.newHashMap();

        List<String> indies = checkContext.getIndex();

        Map<String, String> keyCityMap = Maps.newHashMap();
        List<OubDeliveryKey> keys = parseKeys(indies, keyCityMap);
        long beginTime = System.currentTimeMillis();

        int count = oubDeliveryOrderDetailMapper.countByPickupDate(pickupDate());
        int executeNum = (count - 1) / MAX_PROCESS_NUM + 1;

        List<OubDeliveryOrderDetail> detailList = new ArrayList<>();
        for (int i = 0; i < executeNum; i++) {
            List<OubDeliveryOrderDetail> tmp = oubDeliveryOrderDetailMapper.selectByPage(pickupDate(), i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);

            if (CollectionUtils.isEmpty(tmp)) {
                log.warn("Error to get OmsStandardOrder infos from db by page:{}, pageSize:{}", i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
                continue;
            }

            detailList.addAll(tmp);
        }

        if (CollectionUtils.isEmpty(detailList)) {
            return result;
        }

        long costTime = (System.currentTimeMillis() - beginTime);
        log.info("本次WMS生产单明细明细查询耗时:{}ms",costTime);
        if(costTime > MAX_QUERY_TIME * 1000){
            log.warn("索引查询时间超出最大时长!");
        }
        detailList.forEach(detail -> {
            // 仓库的 门店的 商品
            String key = detail.getWarehouseCode() + ErpWmsLoaderService.KEY_JOINER +  detail.getStoreNo() +
                ErpWmsLoaderService.KEY_JOINER + detail.getSkuCode();

            if (!keyCityMap.keySet().contains(key)) {
                return;
            }

            key = keyCityMap.get(key) + ErpWmsLoaderService.KEY_JOINER + key;
            // 对应的 数量 OR  重量
            BigDecimal value = detail.getSoldQty();

            result.put(key, value);
        });

        log.info("wms Right-LOADER-->end:{}", result);

        return result;
    }

    //@Override
    public Map<String, BigDecimal> loadResource1(CheckContext checkContext) {
        log.info("wms Right-LOADER-->start,get index:{}", JSON.toJSONString(checkContext));
        Map<String, BigDecimal> result = Maps.newHashMap();

        List<String> indies = checkContext.getIndex();

        Map<String, String> keyCityMap = Maps.newHashMap();
        List<OubDeliveryKey> keys = parseKeys(indies, keyCityMap);

        List<OubDeliveryOrderDetail> deliveryOrderDetails = new ArrayList<>();
        //count
        int totalCount = oubDeliveryOrderDetailMapper.countByCondition(keys);
        int executeNum = (totalCount - 1) / MAX_PROCESS_NUM + 1;

        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < executeNum; i++) {
            List<OubDeliveryOrderDetail> tmp = oubDeliveryOrderDetailMapper.selectPageByCondition(keys, i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);

            if (CollectionUtils.isEmpty(tmp)) {
                log.warn("Error to get OmsStandardOrder infos from db by page:{}, pageSize:{}", i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
                continue;
            }

            deliveryOrderDetails.addAll(tmp);
        }

        /*int totalCount = getTotalCount(checkContext);

        int executeNum = (totalCount - 1) / MAX_PROCESS_NUM + 1;
        Map<String, BigDecimal> resultMap = Maps.newHashMap();
        List<OubDeliveryOrder> oubDeliveryOrders = new ArrayList<>();
        Map<String, List<OubDeliveryOrderDetail>> detailMap = Maps.newHashMap();
        for (int i = 0; i < executeNum; i++) {
            List<OubDeliveryOrder> tmp = getPage(checkContext, i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);

            *//*if (CollectionUtils.isEmpty(omsStandardOrders)) {
                log.warn("Error to get OmsStandardOrder infos from db by page:{}, pageSize:{}", i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
            }

            List<OubDeliveryKey> deliveryKeys = omsStandardOrders.stream().map(o -> new OubDeliveryKey(o.getWarehouseCode(), o.getDeliveryOrder())).collect(Collectors.toList());

            Map<String, List<OubDeliveryOrderDetail>> tmpMap = loadDetail(deliveryKeys);
            oubDeliveryOrders.addAll(tmp);
            detailMap.putAll(tmpMap);*//*
        }

        buildResultMap(resultMap, oubDeliveryOrders, detailMap);

        return resultMap;*/

        long costTime = (System.currentTimeMillis() - beginTime);
        log.info("本次WMS生产单明细明细查询耗时:{}ms",costTime);
        if(costTime > MAX_QUERY_TIME * 1000){
            log.warn("索引查询时间超出最大时长!");
        }
        if (CollectionUtils.isEmpty(deliveryOrderDetails)) {
            return result;
        }

        deliveryOrderDetails.forEach(detail -> {
            // 仓库的 门店的 商品
            String key = buildKey(detail.getWarehouseCode(), detail.getStoreNo(),
                    detail.getSkuCode(), keyCityMap);

            // 对应的 数量 OR  重量
            BigDecimal value = detail.getSoldQty();

            result.put(key, value);
        });

        log.info("wms Right-LOADER-->end:{}", result);

        return result;
    }

    private List<OubDeliveryKey> parseKeys(List<String> indies, Map<String, String> keyCityMap) {
        List<OubDeliveryKey> list = new ArrayList<>();
        indies.forEach(index -> {
            String[] strs = index.split(ErpWmsLoaderService.KEY_JOINER);
            keyCityMap.put(strs[1] + ErpWmsLoaderService.KEY_JOINER + strs[2]
                    + ErpWmsLoaderService.KEY_JOINER + strs[3], strs[0]);
            list.add(new OubDeliveryKey(strs[1], pickupDate(), Integer.parseInt(strs[2]), strs[3]));
        });
        return list;
    }

    private List<OubDeliveryOrder> getPage(CheckContext checkContext, int start, int pageSize) {
        OubDeliveryOrderCriteria criteria = createCriteria(checkContext);
        List<OubDeliveryOrder> oubDeliveryOrders = oubDeliveryOrderMapper.selectPageByExample(criteria, start, pageSize);
        return oubDeliveryOrders;
    }

    private int getTotalCount(CheckContext checkContext) {
        OubDeliveryOrderCriteria orderCriteria = createCriteria(checkContext);
        int count = oubDeliveryOrderMapper.countByExample(orderCriteria);
        return count;
    }

    private OubDeliveryOrderCriteria createCriteria(CheckContext checkContext) {
        String order_type = (String) checkContext.getBizParam().get(Constants.PARAM_ORDER_TYPE);
        String cities = (String) checkContext.getBizParam().get(Constants.PARAM_CITY);
        List<Integer> cityIdList = StringUtils.isEmpty(cities) ? null :
                (StringUtils.equals(cities, Constants.ALL_CITY) ? null :
                        Arrays.asList(cities.split(",")).stream()
                                .map(c->Integer.valueOf(c)).collect(Collectors.toList()));
        OubDeliveryOrderCriteria oubDeliveryOrderCriteria = new OubDeliveryOrderCriteria();
        OubDeliveryOrderCriteria.Criteria criteria = oubDeliveryOrderCriteria.createCriteria()
                .andCreatedTimeGreaterThanOrEqualTo(checkContext.getBeginTime())
                .andCreatedTimeLessThan(checkContext.getEndTime());

        if (CollectionUtils.isNotEmpty(cityIdList)) {
            criteria.andCityIdIn(cityIdList);
        }
        return oubDeliveryOrderCriteria;
    }


    private LocalDate pickupDate() {
        int hour = LocalDateTime.now().getHour();
        return hour > 6 ? LocalDate.now().plusDays(1) : LocalDate.now();
    }

    /**
     * *  key 格式是 warehouseCode + "@" + storeNo + "@" + productNo
     *
     * @param warehouseCode
     * @param storeNo
     * @param productNo
     * @param keyCityMap
     * @return
     */
    private String buildKey(String warehouseCode, Integer storeNo, String productNo, Map<String, String> keyCityMap) {
        String keySubfix = warehouseCode + ErpWmsLoaderService.KEY_JOINER + storeNo
                + ErpWmsLoaderService.KEY_JOINER + productNo;

        return keyCityMap.get(keySubfix) + ErpWmsLoaderService.KEY_JOINER + keySubfix;
    }


}
