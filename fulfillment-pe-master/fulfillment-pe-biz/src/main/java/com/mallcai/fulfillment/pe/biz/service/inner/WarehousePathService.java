package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.api.service.operate.dto.req.WareHouseInfo;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;

import java.util.List;
import java.util.Map;

/**
 * @description: 仓库路线业务接口
 * @author: chentao
 * @create: 2019-09-16 23:51:24
 */
public interface WarehousePathService {

    List<WareHouseInfo> buildWarehousePath(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2StoreInfoMap);
}
