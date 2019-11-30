package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.service.vo.ic.common.SOAStoreProduct;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-28 17:34:56
 */
public interface ProductService {

    /**
     * 获取cityProductId 与productNo映射
     * @param orders
     * @return
     */
    Map<Integer, SOAStoreProduct> getCityProductId2ProductMap(List<OrderDO> orders);
}
