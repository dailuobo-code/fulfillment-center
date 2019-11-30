package com.mallcai.fulfillment.dc.biz.service.build;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.api.service.dc.enums.OrderStatus;
import com.mallcai.fulfillment.dc.api.service.dc.msg.OrderItemInfoMsg;
import com.mallcai.fulfillment.dc.api.service.dc.msg.RealTimeOrderInfoMsg;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoExtEnum;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderInfoDO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建消费者支付消息体
 * @author bh.zhong
 * @date 2019/11/5 5:16 PM
 */
public class RealTimeOrderInfoMsgBuild {

    public static RealTimeOrderInfoMsg buildRealTimeOrderInfoMsg(List<OrderItemInfoDO> orderItemInfoDOList,OrderInfoDO orderInfoDO) {
        RealTimeOrderInfoMsg realTimeOrderInfoMsg = new RealTimeOrderInfoMsg();
        realTimeOrderInfoMsg.setCityId(orderInfoDO.getCityId());
        realTimeOrderInfoMsg.setOrderNo(orderInfoDO.getOrderNo());
        realTimeOrderInfoMsg.setPayStatus(OrderStatus.getStatus(orderInfoDO.getStatus()));
        realTimeOrderInfoMsg.setPickupTime(orderInfoDO.getPickupTime());
        realTimeOrderInfoMsg.setStoreId(orderInfoDO.getStoreId());
        List<OrderItemInfoMsg>  orderItemInfoList = new ArrayList<>();
        orderItemInfoDOList.forEach(orderItemInfoDO -> {
            OrderItemInfoMsg orderItemInfoMsg = new OrderItemInfoMsg();
            orderItemInfoMsg.setCityProductId(orderItemInfoDO.getCityProductId());
            orderItemInfoMsg.setProductNo(orderItemInfoDO.getProductNo());
            orderItemInfoMsg.setProductNum(orderItemInfoDO.getProductNum());
            JSONObject itemExt = JSONObject.parseObject(orderItemInfoDO.getItemExt());
            orderItemInfoMsg.setGoodsId(itemExt.getString(OrderItemInfoExtEnum.GOODS_ID.getKey()));
            orderItemInfoMsg.setGoodsUnit(itemExt.getString(OrderItemInfoExtEnum.GOODS_UNIT.getKey()));
            orderItemInfoMsg.setGoodsNum(itemExt.getBigDecimal(OrderItemInfoExtEnum.REL_NUM.getKey()));
            orderItemInfoMsg.setGroupValue(itemExt.getInteger(OrderItemInfoExtEnum.GROUP_VALUE.getKey()));
            orderItemInfoMsg.setWarehouseId(orderItemInfoDO.getWarehouseId());
            orderItemInfoList.add(orderItemInfoMsg);
        });
        realTimeOrderInfoMsg.setOrderItemInfoList(orderItemInfoList);
        return realTimeOrderInfoMsg;

    }

}
