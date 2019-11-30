package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.api.msg.OrderItemInfoDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderAcceptRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OrderDetailDTO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import java.util.List;

/**
 * @description: 订单内部业务接口
 * @author: chentao
 * @create: 2019-09-23 17:00:54
 */
public interface OrderInnerService {

    /**
     * 构建商品扩展信息
     * @param transOrder
     * @param orderDetail
     * @param order
     * @param itemGoodsInfoBoList
     */
    void buildOrderCommodityInfo(OrderAcceptRequestDTO transOrder, OrderDetailDTO orderDetail, OrderDO order,
        List<ItemGoodsInfoBO> itemGoodsInfoBoList);

    /**
     * 构建分拣商品对应的货品信息
     * @param order
     * @param itemGoodsInfoBoList
     */
    void buildSortingOrderCommodityInfo(OrderDO order,
        List<ItemGoodsInfoBO> itemGoodsInfoBoList, OrderItemInfoDTO orderItemInfoDTO);
}
