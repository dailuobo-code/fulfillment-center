package com.mallcai.fulfillment.pe.dependency.client;

import com.mallcai.service.api.ICityGlobalService;
import com.mallcai.service.vo.ic.common.SOAStoreProduct;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-27 10:19:38
 */
public interface CityGlobalServiceClient {

    /**
     * 分城批量根据cityProductId获取productNo
     * @param
     * @param cityProductIds
     * @return
     */
    Map<Integer, SOAStoreProduct> getOnlyCityProductByCity(Integer cityId, List<Integer> cityProductIds);
}
