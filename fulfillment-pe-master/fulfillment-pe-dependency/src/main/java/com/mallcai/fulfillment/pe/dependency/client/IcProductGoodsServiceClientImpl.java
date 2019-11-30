package com.mallcai.fulfillment.pe.dependency.client;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.ic.api.request.product.CityProductQueryRequest;
import com.dailuobo.itemcenter.api.service.product.ItemCenterRouterService;
import com.google.common.collect.Maps;
import com.mallcai.backend.common.api.Paging;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.backend.common.api.Response;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.utils.ListUtlis;
import com.mallcai.router.client.RouterReference;
import com.mallcai.service.request.ProductGoodsRelQueryRequest;
import com.mallcai.service.vo.Product4MgrVO;
import com.mallcai.service.vo.ProductGoodsRelVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品中心查下接口
 * @author bh.zhong
 * @date 2019/10/10 1:48 PM
 */
@Slf4j
@Service
public class IcProductGoodsServiceClientImpl implements IcProductGoodsServiceClient{
    /**
     * 商品中心接口
     */
    @RouterReference
    private ItemCenterRouterService itemCenterRouterService;

    @Override
    public List<ProductGoodsRelVO> searchGoodsRel(Map<Integer,Set<Integer>> cityItems) {
        Map<Integer, ProductGoodsRelQueryRequest> queryRequestMap = new HashMap<>(20);
        cityItems.forEach((k,v)->{
            List<Integer> cityProduceList = new ArrayList<>(v);
            ProductGoodsRelQueryRequest productGoodsRelQueryRequest = new ProductGoodsRelQueryRequest();
            productGoodsRelQueryRequest.setCityId(k);
            productGoodsRelQueryRequest.setCityProductIds(cityProduceList);
            queryRequestMap.put(k,productGoodsRelQueryRequest);
        });

        Map<Integer, Response<List<ProductGoodsRelVO>>> result;
        try {
            log.info("查询商品货品信息开始，queryRequestMap:{}", JSON.toJSONString(queryRequestMap));
            result = itemCenterRouterService.queryByCityProductIds(queryRequestMap);
            log.info("查询商品货品信息结束，result:{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("获取商品信息异常，queryRequestMap:{}", JSON.toJSONString(queryRequestMap), e);
            throw new BizException(Errors.DEPENDENCY_SERVICE_EXCEPTION);
        }

        return mergeCityGoods(result,cityItems);
    }

    private List<ProductGoodsRelVO> mergeCityGoods(Map<Integer, Response<List<ProductGoodsRelVO>>> result,Map<Integer,Set<Integer>> cityItems) {
        List<ProductGoodsRelVO> productGoodsRelVOS = new ArrayList<>();
        result.forEach((k,v)->{
            if (!v.isSuccess()){
                log.error("获取商品货品信息失败，code:{}; msg:{}; queryRequestMap:{}", v.getCode(), v.getMessage(), JSON.toJSONString(result));
                throw new BizException(Errors.DEPENDENCY_SERVICE_EXCEPTION);
            }
            if (CollectionUtils.isEmpty(v.getData())){
                log.error("查询商品货品结果为空,queryRequestMap:{}", JSON.toJSONString(result));
                throw new BizException(Errors.DEPENDENCY_DATA_ERROR);
            }
            if (CollectionUtils.isEmpty(cityItems.get(k))) {
                log.error("不匹配的货品信息,productGoodsRelVOS:{},result:{},cityItems:{}", productGoodsRelVOS,result,cityItems);
            }
            checkAgreement(v.getData(),cityItems.get(k));
            productGoodsRelVOS.addAll(v.getData());
        });
        return productGoodsRelVOS;
    }

    private void checkAgreement(List<ProductGoodsRelVO> productGoodsRelVOS,Set<Integer> cityProductIds) {
        if (productGoodsRelVOS.size() != cityProductIds.size()) {
            List<Integer> itemCityProductIds = new ArrayList<>();
            productGoodsRelVOS.forEach(productGoodsRelVO -> itemCityProductIds.add(productGoodsRelVO.getCityProductId()));
            cityProductIds.forEach(cityProductId -> {
                if (!itemCityProductIds.contains(cityProductId)) {
                    log.error("不存在的货品信息,cityProductId:{}", cityProductId);
                }
            });
        }
    }

    @Override
    public Map<Integer,CityProduct> searchProductInfo(Map<Integer, List<Integer>> cityItems) {

        Map<Integer,CityProduct> result = Maps.newHashMap();
        for (Map.Entry<Integer, List<Integer>> entry : cityItems.entrySet()) {
            CityProductQueryRequest request= new CityProductQueryRequest();
            request.setCityProductIds(entry.getValue());
            request.setCityId(entry.getKey());
            for (int i = 0; i*50 <=entry.getValue().size() ; i++) {
                request.setOffset(i*50);
                request.setLimit(50);
                Map<Integer, CityProductQueryRequest> params= Maps.newHashMap();
                params.put(entry.getKey(),request);
                PlainResult<Paging<CityProduct>> pageResult = itemCenterRouterService.productListByCity(params);
                if(pageResult.isSuccess()){
                    Map<Integer, CityProduct> resMap = pageResult.getData().getData().stream().collect(Collectors.toMap(CityProduct::getCityProductId, p -> p, (oldValue, newValue) -> newValue));
                    result.putAll(resMap);
                }

            }


//            List<List<Integer>> splitList = ListUtlis.splitList(entry.getValue(), 30);
//            for (List<Integer> citys : splitList) {
//                Map<Integer, List<Integer>> params= Maps.newHashMap();
//                params.put(entry.getKey(),citys);
//                CityProductQueryRequest
//                Response<List<Product4MgrVO>> listResponse = itemCenterRouterService.productListByCity(params);
//                if(listResponse.isSuccess()){
//                    Map<Integer, Product4MgrVO> infos = listResponse.getData().stream().collect(Collectors.toMap(Product4MgrVO::getCityProductId, p -> p,(oldValue, newValue) -> newValue));
//                    result.putAll(infos);
//                }
//            }
        }
        return result;
    }


}
