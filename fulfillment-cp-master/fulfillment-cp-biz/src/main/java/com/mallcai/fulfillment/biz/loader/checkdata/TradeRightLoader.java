package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.loader.Loader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.infrastructure.mapper.pe.PeOrderMapper;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrder;
import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderCriteria;
import com.mallcai.manager.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;

@Component("TradeRightLoader")
@Slf4j
@Service
public class TradeRightLoader implements Loader {

  @Resource
  private PeOrderMapper peOrderMapper;

  @Override
  public Map<String, Integer> loadResource(CheckContext checkContext) {
    List<String> indexs=checkContext.getIndex();
    log.info("TradeRightLoader RIGHT-LOADER-->get index:{}", JSON.toJSONString(indexs));
    Map<String, Integer> result = Maps.newHashMap();
    long beginTime=System.currentTimeMillis();
    PeOrderCriteria peOrderCriteria=new PeOrderCriteria();
    peOrderCriteria.createCriteria().andTransOrderIdIn(indexs);
    List<PeOrder> peOrderList=peOrderMapper.selectByExample(peOrderCriteria);
    int costTime = (int) ((System.currentTimeMillis() - beginTime) /1000);
    log.info("本次商品明细查询耗时:{}s",costTime);
    if(costTime>MAX_QUERY_TIME){
      log.warn("索引查询时间超出最大时长!");
    }
    Table<Object,String,List<String>> keyToSourceTable=checkContext.getKeyToSourceTable();
    if(CollectionUtils.isNotEmpty(peOrderList)){
      for(PeOrder peOrder:peOrderList){
        String productNumStr = JSONObject
                .parseObject(peOrder.getCommodityInfoExt()).getString(CommodityInfoExtEnum.PRODUCT_NUM.getKey());
        int productNum= StringUtils.isEmpty(productNumStr)?0:Integer.valueOf(productNumStr);
        if(!result.containsKey(peOrder.getCityProductId()+"")){
          result.put(peOrder.getCityProductId()+"",productNum);
        }else{
          int sumCount=Integer.valueOf(result.get(peOrder.getCityProductId()+""))+productNum;
          result.put(peOrder.getCityProductId()+"",sumCount);
        }
        processKeyToSourceTable(keyToSourceTable,peOrder);
      }
    }
    return result;
  }

  public void processKeyToSourceTable(Table<Object,String,List<String>> keyToSourceTable, PeOrder peOrder){
    if(keyToSourceTable.isEmpty()||keyToSourceTable.get(peOrder.getCityProductId(), Constants.TBL_RIGHT_V)==null){
      List<String> orderIdList=new ArrayList<>();
      orderIdList.add(peOrder.getTransOrderId());
      keyToSourceTable.put(peOrder.getCityProductId(), Constants.TBL_RIGHT_V,orderIdList);
    }else{
      if(!keyToSourceTable.get(peOrder.getCityProductId(), Constants.TBL_RIGHT_V).contains(peOrder.getTransOrderId())){
        keyToSourceTable.get(peOrder.getCityProductId(), Constants.TBL_RIGHT_V).add(peOrder.getTransOrderId());
      }
    }
  }
}
