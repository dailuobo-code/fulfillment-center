package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.biz.service.inner.ProductService;
import com.mallcai.fulfillment.pe.dependency.client.CityGlobalServiceClient;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.service.vo.ic.common.SOAStoreProduct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-28 17:35:06
 */
@Service
public class ProductNoServiceImpl implements ProductService {

    @Resource
    private CityGlobalServiceClient cityGlobalServiceClient;

    @Override
    public Map<Integer, SOAStoreProduct> getCityProductId2ProductMap(List<OrderDO> orders) {

        Map<Integer, SOAStoreProduct> cityProductId2ProductNoMap = new HashMap<>();

        Map<Integer, List<OrderDO>> cityProductIdGroup = orders.stream().collect(Collectors.groupingBy(OrderDO::getCityId));

        for (Map.Entry map : cityProductIdGroup.entrySet()){

            List<Integer> cityProductIds =((List<OrderDO>) map.getValue()).stream().map(OrderDO::getCityProductId).collect(Collectors.toList());

            Map<Integer, SOAStoreProduct> cityProductId2ProductMap = cityGlobalServiceClient.getOnlyCityProductByCity((Integer) map.getKey(), cityProductIds);

            cityProductId2ProductNoMap.putAll(cityProductId2ProductMap);
        }

        return cityProductId2ProductNoMap;
    }
}
