package com.mallcai.fulfillment.dc.dependency.client;

import com.mallcai.service.vo.ProductGoodsRelVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品中心查下接口
 * @author bh.zhong
 * @date 2019/10/10 1:48 PM
 */
public interface IcProductGoodsServiceClient {

    /**
     *
     * @param cityItems
     * @return
     */
    List<ProductGoodsRelVO> searchGoodsRel(Map<Integer, Set<Integer>> cityItems);

}
