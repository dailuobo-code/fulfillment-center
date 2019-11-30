package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.infrastructure.mapper.pe.ProduceOrderItemMapper;
import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrderItem;
import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrderItemCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author: Liu Yang
 * @description 履约的生产单明细数据加载器
 * @date: 2019-11-18 16:59
 */
@Component("ProduceFulLeftLoader")
@Slf4j
@Service
public class ProduceFulLeftLoader implements BigDecimalLoader {
    @Resource
    private ProduceOrderItemMapper produceOrderItemMapper;

    @Override
    public Map<String, BigDecimal> loadResource(CheckContext checkContext) {
        log.info("ProduceFulLeftLoader LEFT-LOADER-->get index:{}", JSON.toJSONString(checkContext));
        Map<String, BigDecimal> result = Maps.newHashMap();
        List<String> indexList=checkContext.getIndex();
        long beginTime=System.currentTimeMillis();
        ProduceOrderItemCriteria produceOrderItemCriteria=new ProduceOrderItemCriteria();
        produceOrderItemCriteria.createCriteria().andProduceOrderNoIn(indexList);
        List<ProduceOrderItem> produceOrderItemList=produceOrderItemMapper.selectByExample(produceOrderItemCriteria);
        int costTime = (int) ((System.currentTimeMillis() - beginTime) /1000);
        log.info("本次商品明细查询耗时:{}s",costTime);
        if(costTime>MAX_QUERY_TIME){
            log.warn("索引查询时间超出最大时长!");
        }
        if(CollectionUtils.isNotEmpty(produceOrderItemList)){
            for(ProduceOrderItem produceOrderItem:produceOrderItemList){
                String key=produceOrderItem.getProduceOrderNo()+"_"+produceOrderItem.getGoodId();
                if(!result.containsKey(key)){
                    result.put(key,produceOrderItem.getTotalNum().setScale(3,ROUND_HALF_UP));
                }else{
                    result.put(key,result.get(key).add(produceOrderItem.getTotalNum().setScale(3,ROUND_HALF_UP)));
                }
            }
        }
        return result;
    }
}
