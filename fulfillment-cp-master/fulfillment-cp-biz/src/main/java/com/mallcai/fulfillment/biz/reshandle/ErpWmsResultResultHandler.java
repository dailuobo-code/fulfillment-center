package com.mallcai.fulfillment.biz.reshandle;

import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.BizTagEnum;
import com.mallcai.fulfillment.biz.object.enums.OrderTypeEnum;
import com.mallcai.fulfillment.biz.service.ErpWmsLoaderService;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component("erpWmsResultResultHandler")
public class ErpWmsResultResultHandler implements ResultHandler {
    @Override
    public void handleResult(CheckContext checkContext) {
        String bizTag = checkContext.getBizTag();
        BizTagEnum enuFromKey = BizTagEnum.getEnuFromKey(bizTag);
        log.info("即将开始进行{} 总数校验...checkNo={}", enuFromKey.getDesc(), checkContext.getCheckNo());
        checkTotalNum(checkContext);
        log.info("即将开始进行{}出错数据反查...checkNo={}", enuFromKey.getDesc(), checkContext.getCheckNo());
        List<Object> errorKeyList = checkContext.getErrorKeyList();
        if(CollectionUtils.isNotEmpty(errorKeyList)){
            StringBuffer errorKeyBuffer = new StringBuffer();
            errorKeyList.parallelStream().forEach(item -> errorKeyBuffer.append(String.valueOf(item) + ","));
            log.error("{}，以下goodsId存在货品数量不一致问题:goodsIdList:{}", enuFromKey.getDesc(), errorKeyBuffer.toString());
        }
        log.info("出错数据反查结束...checkNo={}",checkContext.getCheckNo());
    }

    private void checkTotalNum(CheckContext checkContext) {

        Integer orderType = Integer.parseInt((String) checkContext.getBizParam().get(Constants.PARAM_ORDER_TYPE));
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getEnuFromKey(orderType);
        Table<String, String, Object> data = checkContext.getResult();
        Set<String> keys= data.rowKeySet();

        Map<String, BigDecimal> leftTotalCount = Maps.newHashMap();
        Map<String, BigDecimal> rightTotalCount = Maps.newHashMap();
        Map<String, BigDecimal> leftStoreTotalCount = Maps.newHashMap();
        Map<String, BigDecimal> rightStoreTotalCount = Maps.newHashMap();

        Map<String, BigDecimal> leftRecord = Maps.newHashMap();
        Map<String, BigDecimal> rightRecord = Maps.newHashMap();
        //放入对比关系

        for (String key : keys) {
            BigDecimal left = (BigDecimal) data.get(key, Constants.TBL_LEFT_V);
            BigDecimal right = (BigDecimal) data.get(key, Constants.TBL_RIGHT_V);

            String[] strs = key.split(ErpWmsLoaderService.KEY_JOINER);
            String warehouseKey = strs[0] + "," + strs[1];
            String warehouseStoreKey = strs[0] + "," + strs[1] + "," + strs[2];
            if (left != null) {
                //仓 + 总量
                BigDecimal bigDecimal = leftTotalCount.get(warehouseKey);
                leftTotalCount.put(warehouseKey, bigDecimal == null ? left : bigDecimal.add(left));
                //仓 + 店 + 总量
                BigDecimal storeVal = leftStoreTotalCount.get(warehouseStoreKey);
                leftStoreTotalCount.put(warehouseStoreKey, storeVal == null ? left : storeVal.add(left));

                leftRecord.put(key, left);
            }

            if (right != null) {
                //仓 + 总量
                BigDecimal bigDecimal = rightTotalCount.get(warehouseKey);
                rightTotalCount.put(warehouseKey,  bigDecimal == null ? right : bigDecimal.add(right));

                //仓 + 店 + 总量
                BigDecimal storeVal = rightStoreTotalCount.get(warehouseStoreKey);
                rightStoreTotalCount.put(warehouseStoreKey, storeVal == null ? right : storeVal.add(right));
                rightRecord.put(key, right);
            }

        }

        boolean checkResult = true;
        StringBuilder builder = new StringBuilder("供应链全链路对账");
        if (MapUtils.isNotEmpty(leftRecord)) {
            if (MapUtils.isEmpty(rightRecord)) {
                builder.append("\n检测到WMS侧" + orderTypeEnum.getDesc() + "数据尚未落库,无法对账,本次对账凭证ID:").append(checkContext.getCheckNo());
                log.warn(builder.toString());
                return;
            }

            //仓 + 总记录数
            Map<String, Long> warehouseLeftRecord = leftRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1];
            }, Collectors.counting()));
            Map<String, Long> warehouseRightRecord = rightRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1];
            }, Collectors.counting()));
            //仓总量
            builder.append("\n对账内容:【ERP、WMS " + orderTypeEnum.getDesc() + " 对账】\n【城市,仓库,ERP总量,ERP总记录,WMS总量,WMS总记录】\n");

            for (Map.Entry<String, BigDecimal> entry : leftTotalCount.entrySet()) {
                String key = entry.getKey();
                builder.append(key).append(",").append(entry.getValue()).append(",").append(warehouseLeftRecord.get(key));
                BigDecimal rightCityTotal = rightTotalCount.get(key);
                Long rightCount = warehouseRightRecord.get(key);
                if (rightCityTotal == null || !rightCityTotal.equals(entry.getValue())) {
                    checkResult = false;
                    if (rightCityTotal == null) {
                        rightCityTotal = BigDecimal.ZERO;
                    }

                }

                builder.append(",").append(rightCityTotal);
                if (rightCount == null || !rightCount.equals(warehouseLeftRecord.get(key))) {
                    checkResult = false;
                    if (rightCount == null) {
                        rightCount = 0l;
                    }

                }
                builder.append(",").append(rightCount).append("\n");

            }

            leftTotalCount.clear();
            rightTotalCount.clear();
            warehouseLeftRecord.clear();
            warehouseRightRecord.clear();

            //仓 + 店数
            Map<String, List<String>> warehouseLeftStoreCount = leftRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1];
            }));
            Map<String, List<String>> warehouseRightStoreCount = rightRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1];
            }));
            builder.append("\n对账内容:【ERP、WMS " + orderTypeEnum.getDesc() + " 对账】\n【城市,仓库, ERP门店数, WMS门店数】\n");

            for (Map.Entry<String, List<String>> entry : warehouseLeftStoreCount.entrySet()) {
                String key = entry.getKey();
                int leftSize = entry.getValue().stream().map(s -> {
                    return s.split(ErpWmsLoaderService.KEY_JOINER)[2];
                }).collect(Collectors.toSet()).size();
                builder.append(key).append(",").append(leftSize);
                int rightSize = warehouseRightStoreCount.get(key) == null ? 0 : warehouseRightStoreCount.get(key).stream().map(s -> {
                    return s.split(ErpWmsLoaderService.KEY_JOINER)[2];
                }).collect(Collectors.toSet()).size();
                if (leftSize != rightSize) {
                    checkResult = false;
                }
                builder.append(",").append(rightSize).append("\n");
            }

            //仓 + 店总量
            Map<String, Long> warehouseStoreLeftRecord = leftRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1] + "," + split[2];
            }, Collectors.counting()));
            Map<String, Long> warehouseStoreRightRecord = rightRecord.keySet().stream().collect(Collectors.groupingBy(key -> {
                String[] split = key.split(ErpWmsLoaderService.KEY_JOINER);
                return split[0] + "," + split[1] + "," + split[2];
            }, Collectors.counting()));

            builder.append("\n对账内容:【ERP、WMS " + orderTypeEnum.getDesc() + " 对账】\n【城市,仓库,门店,ERP总量,ERP总记录,WMS总量,WMS总记录数】\n");
            for (Map.Entry<String, BigDecimal> entry : leftStoreTotalCount.entrySet()) {
                String key = entry.getKey();
                builder.append(key).append(",").append(entry.getValue()).append(",").append(warehouseStoreLeftRecord.get(key));
                BigDecimal rightCityTotal = rightStoreTotalCount.get(key);
                Long rightCount = warehouseStoreRightRecord.get(key);
                if (rightCityTotal == null || !rightCityTotal.equals(entry.getValue())) {
                    checkResult = false;
                    if (rightCityTotal == null) {
                        rightCityTotal = BigDecimal.ZERO;
                    }

                }
                builder.append(",").append(rightCityTotal);

                if (rightCount == null || !rightCount.equals(warehouseStoreLeftRecord.get(key))) {
                    checkResult = false;
                    if (rightCount == null) {
                        rightCount = 0l;
                    }

                }
                builder.append(",").append(rightCount).append("\n");

            }

        } else {
            builder.append("\n检测到ERP侧" + orderTypeEnum.getDesc() + "数据尚未落库,无法对账,本次对账凭证ID:").append(checkContext.getCheckNo());
            log.warn(builder.toString());
            return;
        }

        if (checkResult) {
            builder.append("\n对账结果: 一致 ,本次对账凭证ID:").append(checkContext.getCheckNo());
        } else {
            builder.append("\n对账结果: 不一致 ,本次对账凭证ID:").append(checkContext.getCheckNo());
        }

        log.warn(builder.toString());
    }
}
