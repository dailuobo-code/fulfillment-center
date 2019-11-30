package com.mallcai.fulfillment.dc.dependency.facade;

import com.mallcai.fulfillment.dc.dependency.client.IcProductGoodsServiceClient;
import com.mallcai.fulfillment.dc.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.dc.dependency.facade.bo.QueryGoodsRelBO;
import com.mallcai.fulfillment.pe.common.utils.ListUtlis;
import com.mallcai.service.vo.ProductGoodsRelVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 商品货品防腐层
 *
 * @author bh.zhong
 * @date 2019/10/10 10:51 PM
 */
@Slf4j
@Service
public class IcProductGoodsServiceFacade {

    @Autowired
    private IcProductGoodsServiceClient icProductGoodsServiceClient;

    public List<ItemGoodsInfoBO> searchGoodsRel(List<QueryGoodsRelBO> goodsRelBOS) {
        if (CollectionUtils.isEmpty(goodsRelBOS)) {
            return null;
        }
        List<List<QueryGoodsRelBO>> itemInfoList = ListUtlis.splitList(goodsRelBOS, 100);
        List<ItemGoodsInfoBO> itemGoodsInfoBOS = new ArrayList<>();
        itemInfoList.forEach(itemInfoBOS -> {
            if (CollectionUtils.isNotEmpty(limitSearchGoodsRel(itemInfoBOS))) {
                itemGoodsInfoBOS.addAll(limitSearchGoodsRel(itemInfoBOS));
            } else {
                log.error("查询商品信息返回空值，request itemInfoBOS:{}",itemInfoBOS);
            }
        });
        return itemGoodsInfoBOS;
    }

    private List<ItemGoodsInfoBO> limitSearchGoodsRel(List<QueryGoodsRelBO> goodsRelBOS) {
        //localKvCacheHandle.cleanLocalKv();
        Map<Integer, Set<Integer>> cityItems = new HashMap<>(20);
        goodsRelBOS.forEach(goodsRelBO -> {
            if (cityItems.containsKey(goodsRelBO.getCityId())) {
                cityItems.get(goodsRelBO.getCityId()).add(goodsRelBO.getCityProductId());
            } else {
                HashSet<Integer> itemSet = new HashSet<>();
                itemSet.add(goodsRelBO.getCityProductId());
                cityItems.put(goodsRelBO.getCityId(), itemSet);
            }
        });
        List<ProductGoodsRelVO> itemGoods = icProductGoodsServiceClient.searchGoodsRel(cityItems);

        if (CollectionUtils.isEmpty(itemGoods)) {
            return null;
        }
        return transforToItemGoodsInfo(itemGoods);
    }

    private List<ItemGoodsInfoBO> transforToItemGoodsInfo(List<ProductGoodsRelVO> itemGoods) {
        List<ItemGoodsInfoBO> goodsInfoBOS = new ArrayList<>();
        itemGoods.forEach(itemGood -> {
            ItemGoodsInfoBO itemGoodsInfoBO = new ItemGoodsInfoBO();
            itemGoodsInfoBO.setCityId(itemGood.getCityId());
            itemGoodsInfoBO.setCityProductId(itemGood.getCityProductId());
            itemGoodsInfoBO.setGoodsId(itemGood.getGoodsId());
            itemGoodsInfoBO.setGoodsUnit(itemGood.getGoodsUnit());
            itemGoodsInfoBO.setRelNum(itemGood.getRelNum());
            itemGoodsInfoBO.setCategoryType(itemGood.getIsStandard() == 1 ? 1 : 2);
            itemGoodsInfoBO.setAttributeType(itemGood.getKeepType() == 3 ? 1 : null);
            goodsInfoBOS.add(itemGoodsInfoBO);
        });
        return goodsInfoBOS;
    }


}
