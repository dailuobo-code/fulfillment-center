package com.mallcai.fulfillment.pe.biz.service.inner.order;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderDetailDTO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.OrderInnerService;
import com.mallcai.fulfillment.pe.biz.service.inner.transorder.TransOrderInfoHandleService;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;

import java.math.BigDecimal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 订单内部业务实现
 * @author: chentao
 * @create: 2019-09-23 17:03:28
 */

@Slf4j
@Service
public class OrderInnerServiceImpl implements OrderInnerService {

    @Autowired
    private TransOrderInfoHandleService transOrderInfoHandleService;

    @Override
    public void buildOrderCommodityInfo(OrderAcceptRequestDTO transOrder, OrderDetailDTO orderDetail, OrderDO order,
        List<ItemGoodsInfoBO> itemGoodsInfoBoList) {

        // 商品信息扩展字段
        JSONObject commodityInfoExt = new JSONObject();

        commodityInfoExt.put(CommodityInfoExtEnum.SPEC_ID.getKey(), orderDetail.getSpecId());
        commodityInfoExt.put(CommodityInfoExtEnum.PRODUCT_NUM.getKey(), orderDetail.getProductNum());
        commodityInfoExt.put(CommodityInfoExtEnum.PACKAGE_QUANTITY.getKey(), orderDetail.getMaxNum());
        commodityInfoExt.put(CommodityInfoExtEnum.IS_GROUP_BUYING.getKey(), transOrderInfoHandleService.isGroupBuyingOrder(transOrder) ? "1" : "0");
        log.info("构建订单商品信息,commodityInfoExt:{}", commodityInfoExt.toJSONString());
        //如果开了灰度开关，将进行商品转货品操作
        if(CollectionUtils.isNotEmpty(itemGoodsInfoBoList)){
            for(ItemGoodsInfoBO itemGoodsInfoBO:itemGoodsInfoBoList){
                if(itemGoodsInfoBO.getCityId().equals(order.getCityId())&&itemGoodsInfoBO.getCityProductId().equals(order.getCityProductId())){
                    commodityInfoExt.put(CommodityInfoExtEnum.GOODS_ID.getKey(),itemGoodsInfoBO.getGoodsId());
                    commodityInfoExt.put(CommodityInfoExtEnum.GOODS_UNIT.getKey(),itemGoodsInfoBO.getGoodsUnit());
                    commodityInfoExt.put(CommodityInfoExtEnum.REL_NUM.getKey(),itemGoodsInfoBO.getRelNum());
                }
            }
        }
        order.setCommodityInfoExt(commodityInfoExt.toJSONString());
    }

    @Override
    public void buildSortingOrderCommodityInfo(OrderDO order,
        List<ItemGoodsInfoBO> itemGoodsInfoBoList, OrderItemInfoDTO orderItemInfoDTO) {
        // 商品信息扩展字段
        JSONObject commodityInfoExt = new JSONObject();
        if(CollectionUtils.isNotEmpty(itemGoodsInfoBoList)){
            for(ItemGoodsInfoBO itemGoodsInfoBO:itemGoodsInfoBoList){
                if(itemGoodsInfoBO.getCityId().equals(order.getCityId())&&itemGoodsInfoBO.getCityProductId().equals(order.getCityProductId())){
                    commodityInfoExt.put(CommodityInfoExtEnum.GOODS_ID.getKey(),itemGoodsInfoBO.getGoodsId());
                    commodityInfoExt.put(CommodityInfoExtEnum.GOODS_UNIT.getKey(),itemGoodsInfoBO.getGoodsUnit());
                    commodityInfoExt.put(CommodityInfoExtEnum.REL_NUM.getKey(),itemGoodsInfoBO.getRelNum());
                }
            }
        }
        commodityInfoExt.put(CommodityInfoExtEnum.PRODUCT_NUM.getKey(),orderItemInfoDTO.getProductNum());
        order.setCommodityInfoExt(commodityInfoExt.toJSONString());
    }
}
