package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mallcai.fulfillment.biz.loader.BigDecimalLoader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.infrastructure.mapper.erp.OmsStandardOrderDetailMapper;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderDetail;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderDetailCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;

/**
 * @author: Liu Yang
 * @description 履约的生产单明细数据加载器
 * @date: 2019-11-18 16:59
 */
@Component("ProduceErpRightLoader")
@Slf4j
@Service
public class ProduceErpRightLoader implements BigDecimalLoader {
    @Resource
    private OmsStandardOrderDetailMapper omsStandardOrderDetailMapper;

    @Override
    public Map<String, BigDecimal> loadResource(CheckContext checkContext) {
        log.info("ProduceErpRightLoader LEFT-LOADER-->get index:{}", JSON.toJSONString(checkContext.getIndex()));
        Map<String, BigDecimal> result = Maps.newHashMap();
        List<String> indexList=checkContext.getIndex();
        long beginTime=System.currentTimeMillis();
        OmsStandardOrderDetailCriteria omsStandardOrderDetailCriteria=new OmsStandardOrderDetailCriteria();
        omsStandardOrderDetailCriteria.createCriteria().andInvoiceNoIn(indexList);
        List<OmsStandardOrderDetail> omsStandardOrderDetailList=omsStandardOrderDetailMapper.selectByExample(omsStandardOrderDetailCriteria);
        int costTime = (int) ((System.currentTimeMillis() - beginTime) /1000);
        log.info("本次ERP生产单明细明细查询耗时:{}s",costTime);
        if(costTime>MAX_QUERY_TIME){
            log.warn("索引查询时间超出最大时长!");
        }
        if(CollectionUtils.isNotEmpty(omsStandardOrderDetailList)){
            for(OmsStandardOrderDetail omsStandardOrderDetail:omsStandardOrderDetailList){
                String key=omsStandardOrderDetail.getInvoiceNo()+"_"+omsStandardOrderDetail.getProductNo();
                if(!result.containsKey(key)){
                    result.put(key,omsStandardOrderDetail.getGoodsQuantity());
                }else{
                    result.put(key,result.get(key).add(omsStandardOrderDetail.getGoodsQuantity()));
                }
            }
        }
        return result;
    }

}
