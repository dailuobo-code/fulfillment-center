package com.mallcai.fulfillment.biz.loader.index;

import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.ProduceOrderStatusEnum;
import com.mallcai.fulfillment.infrastructure.mapper.pe.ProduceOrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrder;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Liu Yang
 * @description 履约侧生产单索引查询
 * @date: 2019-11-18 14:02
 */
@Component("FulfillmentProduceDataIndex")
@Slf4j
public class FulfillmentProduceDataIndex implements DataIndex {
    @Resource
    private ProduceOrderMapperExtend produceOrderMapperExtend;
    @Override
    public List<String> findIndex(CheckContext context, int start, int pageSize) {
        String cities=(String)context.getBizParam().get(Constants.PARAM_CITY);
        String groupValue=(String)context.getBizParam().get(Constants.PARAM_GROUP_VALUE);
        List<Integer> cityIdList= StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities,Constants.ALL_CITY)?null: Arrays.asList(cities.split(","))
                .stream().map(w->Integer.valueOf(w)).collect(Collectors.toList()));
        List<ProduceOrder> produceOrderList= produceOrderMapperExtend.getByCondition(context.getBeginTime(),context.getEndTime(), groupValue
                ,cityIdList, ProduceOrderStatusEnum.COMPLETE.getStatus(),start,pageSize);
        List<String> indexList=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(produceOrderList)){
            indexList=produceOrderList.stream().map(ProduceOrder::getProduceOrderNo).collect(Collectors.toList());
        }
        return indexList;
    }

    @Override
    public int getTotalCount(CheckContext context) {
        String cities=(String)context.getBizParam().get(Constants.PARAM_CITY);
        String groupValue=(String)context.getBizParam().get(Constants.PARAM_GROUP_VALUE);
        List<Integer> cityIdList= StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities,Constants.ALL_CITY)?null: Arrays.asList(cities.split(",")).stream().map(w->Integer.valueOf(w)).collect(Collectors.toList()));
        return produceOrderMapperExtend.countByCondition(context.getBeginTime(),context.getEndTime(), groupValue,cityIdList, ProduceOrderStatusEnum.COMPLETE.getStatus());
    }
}
