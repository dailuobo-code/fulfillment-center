package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.service.ErpWmsLoaderService;
import com.mallcai.fulfillment.infrastructure.mapper.erp.*;
import com.mallcai.fulfillment.infrastructure.object.erp.*;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;

@Component("erpLeftLoader")
@Slf4j
@Service
public class ErpLeftLoader implements BigDecimalLoader {

    @Resource
    private OmsStandardOrderDetailMapper omsStandardOrderDetailMapper;

    @Resource
    private OmsStandardOrderMapper omsStandardOrderMapper;

    @Resource
    private OmsStandardOrderMapperExtend omsStandardOrderMapperExtend;

    @Resource
    private OmsStandardWarehouseMapper omsStandardWarehouseMapper;

    @Resource
    private StoreDOMapper storeDOMapper;

    @Resource
    private WarehouseDOMapper warehouseDOMapper;

    /**
     * 一次批量操作大小
     */
    private final static int MAX_PROCESS_NUM = 10000;

    @Override
    public Map<String, BigDecimal> loadResource(CheckContext context) {
        log.info("erp_wms Left-LOADER-->start,get index:{}", JSON.toJSONString(context));
        //erp取得totalCount
        int totalCount = getTotalCount(context);

        int executeNum = (totalCount - 1) / MAX_PROCESS_NUM + 1;
        Map<String, BigDecimal> resultMap = Maps.newHashMap();
        List<OmsStandardOrder> omsStandardOrders = new ArrayList<>();
        Map<String, List<OmsStandardOrderDetail>> detailMap = Maps.newHashMap();
        Map<String, OmsStandardWarehouse> warehouseMap = Maps.newHashMap();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < executeNum; i++) {
            List<OmsStandardOrder> tmp = getPage(context, i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
            if (CollectionUtils.isEmpty(tmp)) {
                log.warn("Error to get OmsStandardOrder infos from db by page:{}, pageSize:{}", i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
                continue;
            }

            List<String> invoiceNos = tmp.stream().map(OmsStandardOrder::getInvoiceNo).collect(Collectors.toList());

            /*OmsStandardWarehouseCriteria omsStandardWarehouseCriteria = new OmsStandardWarehouseCriteria();
            omsStandardWarehouseCriteria.createCriteria().andInvoiceNoIn(invoiceNos);*/
            List<OmsStandardWarehouse> omsStandardWarehouses = omsStandardWarehouseMapper.selectByInvoiceNos(invoiceNos);

            warehouseMap.putAll(omsStandardWarehouses.stream().collect(Collectors.toMap(OmsStandardWarehouse::getInvoiceNo,
                    omsStandardWarehouse -> omsStandardWarehouse)));

            Map<String, List<OmsStandardOrderDetail>> tmpMap = loadDetail(invoiceNos);
            omsStandardOrders.addAll(tmp);
            detailMap.putAll(tmpMap);
        }

        Set<Integer> storeSet = omsStandardOrders.stream().map(OmsStandardOrder::getStoreId).collect(Collectors.toSet());
        Set<Integer> wareHouseSet = warehouseMap.values().stream().map(OmsStandardWarehouse::getWarehouseId).collect(Collectors.toSet());

        Map<Integer, Integer> storeReflect = Maps.newHashMap();
        Map<Integer, String> warehouseReflect = Maps.newHashMap();
        //仓id -> 仓编号, 门店id ->门店编号
        transToNo(storeSet, storeReflect, wareHouseSet, warehouseReflect);

        long costTime = (System.currentTimeMillis() - beginTime);
        log.info("本次ERP生产单明细明细查询耗时:{}ms",costTime);
        if(costTime > MAX_QUERY_TIME * 1000){
            log.warn("索引查询时间超出最大时长!");
        }
        buildResultMap(resultMap, omsStandardOrders, warehouseMap, detailMap, storeReflect, warehouseReflect);

        return resultMap;
    }

    private void transToNo(Set<Integer> storeSet, Map<Integer, Integer> storeReflect, Set<Integer> wareHouseSet, Map<Integer, String> warehouseReflect) {
        StoreDOCriteria storeDOCriteria = new StoreDOCriteria();
        storeDOCriteria.createCriteria().andStoreIdIn(new ArrayList<>(storeSet));
        List<StoreDO> storeDOS = storeDOMapper.selectByExampleMainInfo(storeDOCriteria);
        storeReflect.putAll(storeDOS.stream().collect(Collectors.toMap(StoreDO::getStoreId, StoreDO::getStoreNo)));

        WarehouseDOCriteria warehouseDOCriteria = new WarehouseDOCriteria();
        warehouseDOCriteria.createCriteria().andIdIn(new ArrayList<>(wareHouseSet));
        List<WarehouseDO> warehouseDOS = warehouseDOMapper.selectByExampleMainInfo(warehouseDOCriteria);
        warehouseReflect.putAll(warehouseDOS.stream().collect(Collectors.toMap(WarehouseDO::getId, WarehouseDO::getCode)));
    }

    private void buildResultMap(Map<String, BigDecimal> resultMap, List<OmsStandardOrder> omsStandardOrders, Map<String, OmsStandardWarehouse> warehouseMap, Map<String, List<OmsStandardOrderDetail>> detailMap,
                                Map<Integer, Integer> storeReflect, Map<Integer, String> warehouseReflect) {
        omsStandardOrders.forEach(omsStandardOrder -> {
            String invoiceNo = omsStandardOrder.getInvoiceNo();
            List<OmsStandardOrderDetail> omsStandardOrderDetails = detailMap.get(invoiceNo);
            List<OmsStandardOrderDetail> goodDetails = omsStandardOrderDetails.stream().filter(omsStandardOrderDetail -> {
                BigDecimal goodsQuantity = omsStandardOrderDetail.getGoodsQuantity();

                return goodsQuantity != null && goodsQuantity.compareTo(BigDecimal.ZERO) > 0;
            }).collect(Collectors.toList());

            List<OmsStandardOrderDetail> cityProductIdDetails = omsStandardOrderDetails.stream().filter(omsStandardOrderDetail -> {
                Integer productNum = omsStandardOrderDetail.getProductNum();
                return productNum != null && productNum > 0;
            }).collect(Collectors.toList());

            goodDetails.forEach(omsStandardOrderDetail -> {
                String key = buildKey(omsStandardOrder.getCityId(), warehouseReflect.get(warehouseMap.get(invoiceNo).getWarehouseId()), storeReflect.get(omsStandardOrder.getStoreId()),
                        omsStandardOrderDetail.getProductNo());

                resultMap.put(key, omsStandardOrderDetail.getGoodsQuantity());
            });

            cityProductIdDetails.forEach(omsStandardOrderDetail -> {
                String key = buildKey(omsStandardOrder.getCityId(), warehouseReflect.get(warehouseMap.get(invoiceNo).getWarehouseId()), storeReflect.get(omsStandardOrder.getStoreId()),
                        omsStandardOrderDetail.getProductNo() != null ? omsStandardOrderDetail.getProductNo() : omsStandardOrderDetail.getCityProductId());

                resultMap.put(key, BigDecimal.valueOf(omsStandardOrderDetail.getProductNum()));
            });
        });
    }

    private String buildKey(Integer cityId, String warehouseCode, Integer storeId, Integer productNo) {
        return cityId + ErpWmsLoaderService.KEY_JOINER +
                warehouseCode + ErpWmsLoaderService.KEY_JOINER +
                storeId + ErpWmsLoaderService.KEY_JOINER + productNo;
    }

    private Map<String, List<OmsStandardOrderDetail>> loadDetail(List<String> invoiceNos) {
        OmsStandardOrderDetailCriteria orderDetailCriteria = new OmsStandardOrderDetailCriteria();
        orderDetailCriteria.createCriteria().andInvoiceNoIn(invoiceNos);
        int count = omsStandardOrderDetailMapper.countByExample(orderDetailCriteria);

        int executeNum = (count - 1) / MAX_PROCESS_NUM + 1;

        Map<String, List<OmsStandardOrderDetail>> detailMap = Maps.newHashMap();

        for (int i = 0; i < executeNum; i++) {
            List<OmsStandardOrderDetail> omsStandardOrderDetails = omsStandardOrderDetailMapper.selectByPage(invoiceNos, i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);

            if (CollectionUtils.isEmpty(omsStandardOrderDetails)) {
                log.warn("Error to get OmsStandardOrderDetail infos from db by page:{}, pageSize:{}", i * MAX_PROCESS_NUM, MAX_PROCESS_NUM);
                continue;
            }

            omsStandardOrderDetails.stream().forEach(omsStandardOrderDetail -> {
                detailMap.computeIfAbsent(omsStandardOrderDetail.getInvoiceNo(), k -> new ArrayList<>()).add(omsStandardOrderDetail);
            });
        }


        return detailMap;
    }

    private List<OmsStandardOrder> getPage(CheckContext context, int start, int pageSize) {
        OmsStandardOrderCriteria orderCriteria = createCriteria(context);
        List<OmsStandardOrder> omsStandardOrders = omsStandardOrderMapperExtend.selectPageByExample(orderCriteria, start, pageSize);
        return omsStandardOrders;
    }

    private int getTotalCount(CheckContext context) {
        OmsStandardOrderCriteria orderCriteria = createCriteria(context);
        int count = omsStandardOrderMapper.countByExample(orderCriteria);
        return count;
    }

    private OmsStandardOrderCriteria createCriteria(CheckContext context) {
        String order_type = (String) context.getBizParam().get(Constants.PARAM_ORDER_TYPE);
        String cities = (String)context.getBizParam().get(Constants.PARAM_CITY);
        List<Integer> cityIdList = StringUtils.isEmpty(cities) ? null :
                (StringUtils.equals(cities, Constants.ALL_CITY) ? null :
                        Arrays.asList(cities.split(",")).stream()
                                .map(c->Integer.valueOf(c)).collect(Collectors.toList()));
        OmsStandardOrderCriteria orderCriteria = new OmsStandardOrderCriteria();
        OmsStandardOrderCriteria.Criteria criteria = orderCriteria.createCriteria().andGmtCreateGreaterThanOrEqualTo(context.getBeginTime())
                .andGmtCreateLessThan(context.getEndTime())
                .andOrderTypeEqualTo(Integer.parseInt(order_type));
        if (CollectionUtils.isNotEmpty(cityIdList)) {
            criteria.andCityIdIn(cityIdList);
        }

        return orderCriteria;
    }
}
