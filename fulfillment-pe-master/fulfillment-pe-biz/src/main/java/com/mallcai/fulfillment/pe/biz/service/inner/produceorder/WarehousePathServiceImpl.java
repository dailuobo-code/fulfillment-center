package com.mallcai.fulfillment.pe.biz.service.inner.produceorder;

import com.mallcai.fulfillment.pe.api.enums.WarehouseOrderEnum;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.WareHouseInfo;
import com.mallcai.fulfillment.pe.biz.service.inner.WarehousePathService;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.dependency.enums.WareHouseTypeEnum;
import com.mallcai.fulfillment.pe.infrastructure.enums.GroupValueEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.shop.api.service.response.WareHouseRespDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 仓库路线实现
 * @author: chentao
 * @create: 2019-09-16 23:54:26
 */
@Service
public class WarehousePathServiceImpl implements WarehousePathService {

    @Override
    public List<WareHouseInfo> buildWarehousePath(ProduceOrder produceOrder, List<OrderDO> orders, Map<Integer, Map<Integer, WareHouseRespDTO>> storeId2StoreInfoMap) {

        // 暂时先认为一个生产单里的订单都是同一种分组维度

        List<WareHouseInfo> wareHouseInfos = new ArrayList<>();

        GroupValueEnum groupValueEnum = GroupValueEnum.fromValue(orders.get(0).getGroupValue());
        Integer freshWarehouseId = null;
        Integer standardWarehouseId = null;

        switch (groupValueEnum){

            case FRESH_PRODUCT:
            case STORE_FROZEN_PRODUCT:

                freshWarehouseId = storeId2StoreInfoMap.get(produceOrder.getStoreId()).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId();

                WareHouseInfo wareHouseInfo = new WareHouseInfo(freshWarehouseId, WareHouseTypeEnum.WARE_HOUSE_FRESH.getType(), WarehouseOrderEnum.ORDER_1.getOrder());

                wareHouseInfos.add(wareHouseInfo);
                break;

            case ORDER_TYPE_STANDARD:

                freshWarehouseId = storeId2StoreInfoMap.get(produceOrder.getStoreId()).get(WareHouseTypeEnum.WARE_HOUSE_FRESH.getType()).getId();
                standardWarehouseId = storeId2StoreInfoMap.get(produceOrder.getStoreId()).get(WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType()).getId();

                WareHouseInfo wareHouseInfoOrder1 = new WareHouseInfo(standardWarehouseId, WareHouseTypeEnum.WARE_HOUSE_STANDARD.getType(), WarehouseOrderEnum.ORDER_1.getOrder());
                WareHouseInfo wareHouseInfoOrder2 = new WareHouseInfo(freshWarehouseId, WareHouseTypeEnum.WARE_HOUSE_FRESH.getType(), WarehouseOrderEnum.ORDER_2.getOrder());

                wareHouseInfos.add(wareHouseInfoOrder1);
                wareHouseInfos.add(wareHouseInfoOrder2);

                break;

            default:

                throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }

        return wareHouseInfos;
    }
}
