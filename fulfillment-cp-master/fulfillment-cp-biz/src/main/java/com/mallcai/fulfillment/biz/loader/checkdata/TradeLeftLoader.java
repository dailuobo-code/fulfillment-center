package com.mallcai.fulfillment.biz.loader.checkdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mallcai.fulfillment.biz.loader.Loader;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.infrastructure.mapper.trade.OrderDetailMapper;
import com.mallcai.fulfillment.infrastructure.object.trade.OrderDetail;
import com.mallcai.fulfillment.infrastructure.object.trade.OrderDetailCriteria;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.service.order.constants.OrderItemProductInfoKeys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;

@Component("TradeLeftLoader")
@Slf4j
@Service
public class TradeLeftLoader implements Loader {
  @Resource
  private OrderDetailMapper orderDetailMapper;
  @Override
  public Map<String, Integer> loadResource(CheckContext checkContext) {
    List<String> indexs=checkContext.getIndex();
    log.info("TradeLeftLoader LEFT-LOADER-->get index:{}", JSON.toJSONString(indexs));
    Map<String,Integer> result= Maps.newHashMap();
    OrderDetailCriteria orderDetailCriteria=new OrderDetailCriteria();
    orderDetailCriteria.createCriteria().andOrderIdIn(indexs);
    long beginTime=System.currentTimeMillis();
    List<OrderDetail> orderDetailList=orderDetailMapper.selectByExample(orderDetailCriteria);
    int costTime = (int) ((System.currentTimeMillis() - beginTime) /1000);
    log.info("本次商品明细查询耗时:{}s",costTime);
    if(costTime>MAX_QUERY_TIME){
      log.warn("索引查询时间超出最大时长!");
    }
    Table<Object,String,List<String>> keyToSourceTable=checkContext.getKeyToSourceTable();
    if(CollectionUtils.isNotEmpty(orderDetailList)){
      for(OrderDetail orderDetail:orderDetailList){
        //过滤购物袋
        if (orderDetail.getProductInfo().indexOf(OrderItemProductInfoKeys.IS_SHOP_BAG)>0) {
          continue;
        }
        //过滤第三方订单
        if (orderDetail.getMerchantId() != null && orderDetail.getMerchantId() > 0 ) {
          continue;
        }
        if(!result.containsKey(orderDetail.getCityProductId()+"")){
          result.put(orderDetail.getCityProductId()+"",orderDetail.getProductNum());
        }else{
          int sumCount=Integer.valueOf(result.get(orderDetail.getCityProductId()+""))+orderDetail.getProductNum();
          result.put(orderDetail.getCityProductId()+"",sumCount);
        }
        processKeyToSourceTable(keyToSourceTable,orderDetail);
      }
    }
    return result;
  }
  public void processKeyToSourceTable(Table<Object,String,List<String>> keyToSourceTable,OrderDetail orderDetail){
    if(keyToSourceTable.isEmpty()||keyToSourceTable.get(orderDetail.getCityProductId(), Constants.TBL_LEFT_V)==null){
      List<String> orderIdList=new ArrayList<>();
      orderIdList.add(orderDetail.getOrderId());
      keyToSourceTable.put(orderDetail.getCityProductId(), Constants.TBL_LEFT_V,orderIdList);
    }else{
      if(!keyToSourceTable.get(orderDetail.getCityProductId(), Constants.TBL_LEFT_V).contains(orderDetail.getOrderId())){
        keyToSourceTable.get(orderDetail.getCityProductId(), Constants.TBL_LEFT_V).add(orderDetail.getOrderId());
      }
    }
  }
}
