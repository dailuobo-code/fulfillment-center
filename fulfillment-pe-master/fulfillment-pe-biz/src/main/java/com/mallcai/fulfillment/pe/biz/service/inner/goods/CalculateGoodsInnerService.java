package com.mallcai.fulfillment.pe.biz.service.inner.goods;

import com.mallcai.fulfillment.pe.biz.service.bo.GoodsCalculateBO;
import com.mallcai.fulfillment.pe.biz.service.bo.GoodsItemInfoBO;
import com.mallcai.fulfillment.pe.biz.service.bo.ItemGoodsCalculateBO;
import com.mallcai.fulfillment.pe.biz.service.bo.ItemInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.IcProductGoodsServiceFacade;
import com.mallcai.fulfillment.pe.dependency.facade.bo.ItemGoodsInfoBO;
import com.mallcai.fulfillment.pe.dependency.facade.bo.QueryGoodsRelBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 货品计算
 * @author bh.zhong
 * @date 2019/10/12 1:48 PM
 */
@Slf4j
@Component
public class CalculateGoodsInnerService {

    @Resource
    private IcProductGoodsServiceFacade icProductGoodsServiceFacade;


    public List<GoodsCalculateBO> calculateGoodsAmountByGoodsItems(List<GoodsItemInfoBO> goodsItemInfoBOList ) {
        //转换货品编号
        Map<String, List<GoodsItemInfoBO>> groupBy = goodsItemInfoBOList.stream().collect(Collectors.groupingBy(GoodsItemInfoBO::getGoodsId));
        //
        List<GoodsCalculateBO> goodsCalculateBOList = new ArrayList<>();

        groupBy.forEach((k,v)->{
            GoodsCalculateBO goodsCalculateBO = new GoodsCalculateBO();
            BigDecimal goodsAmount = new BigDecimal(0);
            List<Long> ids = new ArrayList<>();
            for (GoodsItemInfoBO goodsItemInfoBO:v) {
                ids.add(goodsItemInfoBO.getId());
                goodsAmount = goodsAmount.add(goodsItemInfoBO.getRelNum().multiply(new BigDecimal(goodsItemInfoBO.getItemNum())));
            }
            goodsCalculateBO.setGoodsId(v.get(0).getGoodsUnit());
            goodsCalculateBO.setGoodsId(k);
            goodsCalculateBO.setGoodsUnit(v.get(0).getGoodsUnit());
            goodsCalculateBO.setGoodsTotalAmount(goodsAmount);
            goodsCalculateBO.setIds(ids);
            goodsCalculateBOList.add(goodsCalculateBO);
        });
        return goodsCalculateBOList;
    }

    /**
     * 计算货品数量
     */
    public List<ItemGoodsCalculateBO> calculateGoodsAmountByItems(List<ItemInfoBO> itemInfos) {
        List<ItemGoodsInfoBO> itemGoodsInfoBOS = icProductGoodsServiceFacade.searchGoodsRel(bulidQueryGoods(itemInfos));
        Map<Integer,ItemGoodsInfoBO> itemGoodsMap = itemGoodsInfoBOS.stream().collect(Collectors.toMap(ItemGoodsInfoBO::getCityProductId, itemGoodsInfo->itemGoodsInfo));
        List<ItemGoodsCalculateBO> itemGoodsList = new ArrayList<>();
        //计算货品数量
        itemInfos.forEach(itemInfo->{
            if (itemGoodsMap.containsKey(itemInfo.getCityProductId())) {
                ItemGoodsInfoBO itemGoodsInfoBO = itemGoodsMap.get(itemInfo.getCityProductId());
                BigDecimal goodsTotalAmount = itemGoodsInfoBO.getRelNum().multiply(new BigDecimal(itemInfo.getItemNum()));
                ItemGoodsCalculateBO itemGoodsCalculateBO = new ItemGoodsCalculateBO();
                itemGoodsCalculateBO.setGoodsTotalAmount(goodsTotalAmount);
                itemGoodsCalculateBO.setGoodsId(itemGoodsInfoBO.getGoodsId());
                itemGoodsCalculateBO.setCityId(itemInfo.getCityId());
                itemGoodsCalculateBO.setGoodsUnit(itemGoodsInfoBO.getGoodsUnit());
                itemGoodsCalculateBO.setCityProductId(itemGoodsInfoBO.getCityProductId());
                itemGoodsList.add(itemGoodsCalculateBO);
            }
        });
        return itemGoodsList;
    }


    private List<QueryGoodsRelBO> bulidQueryGoods(List<ItemInfoBO> itemInfoBOS) {
        List<QueryGoodsRelBO> goodsRelBOS = new ArrayList<>();
        itemInfoBOS.forEach(itemInfoBO -> {
            QueryGoodsRelBO queryGoodsRelBO = new QueryGoodsRelBO();
            queryGoodsRelBO.setCityId(itemInfoBO.getCityId());
            queryGoodsRelBO.setCityProductId(itemInfoBO.getCityProductId());
            goodsRelBOS.add(queryGoodsRelBO);
        });
        return goodsRelBOS;
    }


}
