package com.mallcai.fulfillment.biz.loader.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.biz.loader.DataIndex;
import com.mallcai.fulfillment.biz.object.context.CheckContext;
import com.mallcai.fulfillment.biz.object.enums.AsyncPayStatus;
import com.mallcai.fulfillment.biz.service.GoodsGrayRouterService;
import com.mallcai.fulfillment.infrastructure.mapper.trade.OrderMapper;
import com.mallcai.fulfillment.infrastructure.mapper.trade.OrderMapperExtend;
import com.mallcai.fulfillment.infrastructure.object.trade.Order;
import com.mallcai.manager.common.constant.Constants;
import com.mallcai.service.order.constants.DeliveryModeEnum;
import com.mallcai.service.order.constants.OrderTypeEnum;
import com.mallcai.service.order.vo.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mallcai.manager.common.constant.Constants.MAX_QUERY_TIME;
import static com.mallcai.service.order.constants.OrderItemProductInfoKeys.IS_FROZEN;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-11-05 10:59
 */
@Component("TradeDataIndex")
@Slf4j
@Service
public class TradeDataIndex implements DataIndex {

  @Resource
  private OrderMapper orderMapper;
  @Resource
  private OrderMapperExtend orderMapperExtend;
  @Resource
  private GoodsGrayRouterService goodsGrayRouterService;



  @Override
  public List<String> findIndex(CheckContext context,int start,int pageSize) {
    log.info("INDEX--->get params:{}", JSON.toJSONString(context.getBizParam()));
    long beginTime=System.currentTimeMillis();
    List<String> result=new ArrayList<>();
    List<Order> orderList=getOrderListByCondition(context,start,pageSize);
    int costTime = (int) ((System.currentTimeMillis() - beginTime) /1000);
    log.info("本次索引查询耗时:{}s",costTime);
    if(costTime>MAX_QUERY_TIME){
      log.warn("索引查询时间超出最大时长!");
    }
    if(CollectionUtils.isNotEmpty(orderList)){
      orderList=processOrder(orderList);
      result=orderList.stream().map(Order::getOrderId).collect(Collectors.toList()).stream().map(w->w.toString()).collect(Collectors.toList());
    }
    return result;
  }

  @Override
  public int getTotalCount(CheckContext context) {
    List<Byte> orderTypeList=new ArrayList<>();
    orderTypeList.add((byte)OrderTypeEnum.PRESELL.getValue());
    orderTypeList.add((byte)OrderTypeEnum.STANDARD_PRODUCT.getValue());
    String cities=(String)context.getBizParam().get(Constants.PARAM_CITY);
    Integer status=OrderStatusEnum.PAID.getValue();
    String asyncPayStatus= AsyncPayStatus.SUCCESS.getValue();
    List<Integer> cityIdList=StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities, Constants.ALL_CITY)?null:Arrays.asList(cities.split(",")).stream().map(w->Integer.valueOf(w)).collect(Collectors.toList()));
    return orderMapperExtend.countByCondition(context.getBeginTime(),context.getEndTime(),(byte)DeliveryModeEnum.PICK_UP.getValue(),orderTypeList,"%"+IS_FROZEN+"%",cityIdList,status,asyncPayStatus);
  }

  public List<Order> getOrderListByCondition(CheckContext context,int start,int pageSize){
    String cities=(String)context.getBizParam().get(Constants.PARAM_CITY);
    List<Integer> cityIdList=StringUtils.isEmpty(cities)?null:(StringUtils.equals(cities,Constants.ALL_CITY)?null:Arrays.asList(cities.split(",")).stream().map(w->Integer.valueOf(w)).collect(Collectors.toList()));
    List<Byte> orderTypeList=new ArrayList<>();
    orderTypeList.add((byte)OrderTypeEnum.PRESELL.getValue());
    orderTypeList.add((byte)OrderTypeEnum.STANDARD_PRODUCT.getValue());
    Integer status=OrderStatusEnum.PAID.getValue();
    String asyncPayStatus= AsyncPayStatus.SUCCESS.getValue();
    return orderMapperExtend.getByCondition(context.getBeginTime(),context.getEndTime(),(byte)DeliveryModeEnum.PICK_UP.getValue(),orderTypeList,"%"+IS_FROZEN+"%",cityIdList,status,asyncPayStatus,start,pageSize);
  }

  /**
   * 过滤掉不需要的订单
   * @return
   */
  public List<Order> processOrder(List<Order> orderList){
    List<Order> result=new ArrayList<>();
    for(Order orderDetailVO:orderList){
      JSONObject extraJson = JSON.parseObject(orderDetailVO.getExtras());

      //周期购订购业务
      if(extraJson.containsKey("is_period_buy") && StringUtils
          .equals(extraJson.getString("is_period_buy"),"1")){
        //周期购不沉淀order_info 数据,暂时不处理
        continue;
      }

      //灰度未开的城市,预售订单会过滤掉
      boolean isBooking=orderDetailVO.getOrderType()==2?true:false;
      if(!goodsGrayRouterService.isNeedRoute(orderDetailVO.getCityId())&&isBooking){
          continue;
      }
      result.add(orderDetailVO);
    }
    return result;
  }
}
