package com.mallcai.fulfillment.pe.dependency.client;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.service.api.ICityGlobalService;
import com.mallcai.service.vo.ic.common.SOAStoreProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-28 11:26:11
 */
@Slf4j
@Service
public class CityGlobalServiceClientImpWithoutXml implements CityGlobalServiceClient {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

//    @ConfigValue(key = "/zookeeper/27/zk.properties")
//    private Properties bongbuZkProperties;
//
//    @ConfigValue(key = "/zookeeper/32/zk.properties")
//    private Properties wuhuZkProperties;
//
//    @ConfigValue(key = "/zookeeper/0/zk.properties")
//    private Properties otherProperties;

    /**
     * 城市zk配置中心路径
     */
    public static final String CITY_ZK_PATH_FORMAT = "/zookeeper/%d/zk";

    private Map<String, ICityGlobalService> clientCache = new ConcurrentHashMap<>();

//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//            ICityGlobalService bbICityGlobalService = buildServiceClient("bongbuZkProperties", bongbuZkProperties);
//            ICityGlobalService whICityGlobalService = buildServiceClient("wuhuZkProperties", wuhuZkProperties);
//            ICityGlobalService otherICityGlobalService = buildServiceClient("otherProperties", otherProperties);
//
//            clientCache.putIfAbsent("/zookeeper/27/zk", bbICityGlobalService);
//            clientCache.putIfAbsent("/zookeeper/32/zk", whICityGlobalService);
//            clientCache.putIfAbsent("/zookeeper/0/zk", otherICityGlobalService);
//    }


    public Map<Integer, SOAStoreProduct> getOnlyCityProductByCity(Integer cityId, List<Integer> cityProductIds) {

        String cityProductIdsStr = StringUtils.join(cityProductIds, Constants.SYMBOL_COMMA);

        Map<Integer, SOAStoreProduct> map = null;
        try {

            log.info("通过cityProductId查询商品信息开始,cityId:{},cityProductIds:{}", cityId, cityProductIdsStr);
            ICityGlobalService iCityGlobalService;
            if (Lists.newArrayList(27, 32).contains(cityId)){

                iCityGlobalService = clientCache.get(String.format(CITY_ZK_PATH_FORMAT, cityId));
            }else {

                iCityGlobalService = clientCache.get(String.format(CITY_ZK_PATH_FORMAT, 0));

            }

            map = iCityGlobalService.getOnlyCityProduct(cityId, cityProductIds.toArray(new Integer[cityProductIds.size()]));
            log.info("通过cityProductId查询商品信息结束,result:{}", JSONObject.toJSONString(map));
        } catch (Exception e) {
            log.error("通过cityProductId查询商品信息异常，cityProductIds:{}", cityProductIdsStr, e);
            throw new BizException();
        }

        if (map == null || map.size() == 0){

            log.error("通过cityProductId查询商品信息失败，cityId:{},cityProductIds:{}", cityId, cityProductIdsStr);
            throw new BizException();
        }

        return map;
    }

    private ICityGlobalService buildServiceClient(String key, Properties properties){

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress((String) properties.get("dubbo.city.registry.address"));
        registry.setProtocol("zookeeper");

        ReferenceConfig<ICityGlobalService> reference = new ReferenceConfig();
        reference.setApplication((ApplicationConfig)listableBeanFactory.getBeansOfType(ApplicationConfig.class).values().toArray()[0]);
        reference.setRegistry(registry);
        reference.setTimeout(2000);
        reference.setInterface("com.mallcai.service.api.ICityGlobalService");

        // todo 缓存
        ReferenceConfigCache cache = ReferenceConfigCache.getCache(key);

        // todo 缓存
        ICityGlobalService iCityGlobalService = cache.get(reference);

        return iCityGlobalService;
    }
}
