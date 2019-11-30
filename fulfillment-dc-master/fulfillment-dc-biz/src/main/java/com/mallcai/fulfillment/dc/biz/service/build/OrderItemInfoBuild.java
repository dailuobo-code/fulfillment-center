package com.mallcai.fulfillment.dc.biz.service.build;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.dc.biz.service.enums.GroupValueEnum;
import com.mallcai.fulfillment.dc.biz.service.enums.OrderItemInfoExtEnum;
import com.mallcai.fulfillment.dc.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 构建订单商品扩展信息
 * @author bh.zhong
 * @date 2019/11/5 2:59 PM
 */
@Slf4j
public class OrderItemInfoBuild {


    public static void buildOrderItemInfoExt(List<ItemGoodsInfoBO> itemGoodsInfoBOS,List<OrderItemInfoDO> orderItemInfoDOList) {

        Map<Integer, ItemGoodsInfoBO> integerItemGoodsInfoBOMap = itemGoodsInfoBOS.stream().collect(Collectors.toMap(ItemGoodsInfoBO::getCityProductId, account -> account));

        orderItemInfoDOList.forEach(orderItemInfoDO -> {
            if (integerItemGoodsInfoBOMap.containsKey(orderItemInfoDO.getCityProductId())) {
                ItemGoodsInfoBO itemGoodsInfoBO = integerItemGoodsInfoBOMap.get(orderItemInfoDO.getCityProductId());
                JSONObject orderItemInfoExt = new JSONObject();
                orderItemInfoExt.put(OrderItemInfoExtEnum.GOODS_ID.getKey(),itemGoodsInfoBO.getGoodsId());
                orderItemInfoExt.put(OrderItemInfoExtEnum.GOODS_UNIT.getKey(),itemGoodsInfoBO.getGoodsUnit());
                orderItemInfoExt.put(OrderItemInfoExtEnum.REL_NUM.getKey(),itemGoodsInfoBO.getRelNum());
                orderItemInfoExt.put(OrderItemInfoExtEnum.GROUP_VALUE.getKey(),getOrderGroupValue(itemGoodsInfoBO));
                orderItemInfoDO.setItemExt(orderItemInfoExt.toJSONString());
                log.info("构建订单条目，商品扩展信息,orderItemInfoExt:{}", orderItemInfoExt.toJSONString());
            } else {
               log.error("货品信息不存在，orderItemInfoDO:{},integerItemGoodsInfoBOMap:{}",orderItemInfoDO,integerItemGoodsInfoBOMap);
            }
        });
    }

    public static String getOrderGroupValue(ItemGoodsInfoBO itemGoodsInfoBO) {

        Integer categoryType = itemGoodsInfoBO.getCategoryType();
        Integer attributeType = itemGoodsInfoBO.getAttributeType();
        GroupValueEnum groupValueEnum = GroupValueEnum.fromCategoryTypeAndAttributeType(categoryType, attributeType);
        if (null != groupValueEnum) {
            if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.FRESH_PRODUCT.getGroupValue())) {
                //预售订单的生鲜
                return GroupValueEnum.FRESH_PRODUCT.getGroupValue();
            } else if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue())) {
                //预售订单的标品
                return GroupValueEnum.ORDER_TYPE_STANDARD.getGroupValue();
            } else if (StringUtils.equals(groupValueEnum.getGroupValue(), GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue())) {
                //预售订单的冻品
                return GroupValueEnum.STORE_FROZEN_PRODUCT.getGroupValue();
            }
        }
        return null;
    }

}
