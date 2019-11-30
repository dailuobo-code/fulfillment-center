package com.mallcai.fulfillment.pe.biz.service.inner.purchase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.msg.PurchaseGoodsInfoMsg;
import com.mallcai.fulfillment.pe.api.msg.PurchaseInfoMsg;
import com.mallcai.fulfillment.pe.biz.service.bo.GoodsCalculateBO;
import com.mallcai.fulfillment.pe.biz.service.bo.GoodsItemInfoBO;
import com.mallcai.fulfillment.pe.biz.service.bo.PurchaseBO;
import com.mallcai.fulfillment.pe.biz.service.enums.CommodityInfoExtEnum;
import com.mallcai.fulfillment.pe.biz.service.inner.goods.CalculateGoodsInnerService;
import com.mallcai.fulfillment.pe.biz.service.mq.MqEventMsg;
import com.mallcai.fulfillment.pe.common.utils.ListUtlis;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseDAO;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseStatisticsDao;
import com.mallcai.fulfillment.pe.infrastructure.enums.PurchaseStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购inner方法
 *
 * @author bh.zhong
 * @date 2019/10/11 4:23 PM
 */
@Slf4j
@Service
public class PurchaseInnerService {

    @Autowired
    private PurchaseDAO purchaseDAO;
    @Autowired
    private PurchaseStatisticsDao purchaseStatisticsDao;
    @Resource
    private CalculateGoodsInnerService calculateGoodsInnerService;
    @Autowired
    @Qualifier("fpeTransactionTemplate")
    private TransactionTemplate fpeTransactionTemplate;

    @Resource
    private MqEventMsg mqEventMsg;

    /**
     * 处理采购信息
     *
     * @param purchaseBO
     */
    public void purchaseExecute(PurchaseBO purchaseBO,Boolean compensate) {
        log.info("接收采购执行数据,purchaseBO:{}",purchaseBO);
        if (purchaseBO.getWarehouseId() == null || purchaseBO.getForecastDate() == null || purchaseBO.getForecastType() == null || purchaseBO.getVersion() == null) {
            log.error("参数异常，purchaseBO:{}",purchaseBO);
            return;
        }
        try {
            PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
            //数据组装
            PurchaseCriteria.Criteria criteria = purchaseCriteria.createCriteria();
            if (purchaseBO.getCategoryType() != null) {
                criteria.andCategoryTypeEqualTo(purchaseBO.getCategoryType());
            }
            criteria.andWarehouseIdEqualTo(purchaseBO.getWarehouseId())
                    .andIsDeletedEqualTo((byte) 0).andForecastTypeEqualTo(purchaseBO.getForecastType())
                    .andForecastDateEqualTo(purchaseBO.getForecastDate())
                    .andStatusEqualTo(PurchaseStatusEnum.INIT.getStatus()).andVersionEqualTo(purchaseBO.getVersion());
            List<PurchaseDO> purchaseDOList = purchaseDAO.selectByExample(purchaseCriteria);
            if (CollectionUtils.isEmpty(purchaseDOList)) {
                log.error("采购数据不存在，purchaseBO:{}",purchaseBO);
                return;
            }

            if (purchaseDOList.size() != purchaseBO.getCount() && !compensate) {
                log.error("采购数据数量不一致，purchaseBO:{},size:{}",purchaseBO,purchaseDOList.size());
                return;
            }
            //按照categorytype进行分组
            Map<Byte, List<PurchaseDO>> purchaseDOMap = purchaseDOList.stream().collect(Collectors.groupingBy(PurchaseDO::getCategoryType));
            purchaseDOMap.forEach((k,v) -> executePurchaseSplit(k,purchaseBO,v));
        } catch (Exception e) {
            log.warn("采购业务处理异常，purchaseBO:{}",purchaseBO,e);
            int maxRetryTimes = 10;
            if (purchaseBO.getRetryTimes() == null) {
                purchaseBO.setRetryTimes(0);
                mqEventMsg.sendPurchaseCompensateMsg(purchaseBO);
            } else if (purchaseBO.getRetryTimes() < maxRetryTimes) {
                purchaseBO.setRetryTimes(purchaseBO.getRetryTimes()+1);
                mqEventMsg.sendPurchaseCompensateMsg(purchaseBO);
            } else {
                log.error("采购业务处理异常,重试次数:{}，purchaseBO:{}",maxRetryTimes,purchaseBO,e);
            }

        }
    }


    private void executePurchaseSplit(Byte categoryType,PurchaseBO purchaseBO,List<PurchaseDO> purchaseDOList) {
        //计算货品信息
        List<GoodsCalculateBO> goodsCalculateBOList = calculateGoodsInnerService.calculateGoodsAmountByGoodsItems(transToGoodsItemInfoBO(purchaseDOList));
        if (CollectionUtils.isEmpty(goodsCalculateBOList)) {
            log.error("计算货品数据不存在，purchaseBO:{},purchaseDOList:{}",purchaseBO, JSON.toJSONString(purchaseDOList));
            return;
        }
        Integer totalCount;
        //写入统计数据
        List<PurchaseStatisticsDO> list = purchaseStatisticsDao.select(buildPurchaseStatisticsCriteria(purchaseBO));
        if (CollectionUtils.isEmpty(list)) {
            totalCount = goodsCalculateBOList.size();
            purchaseStatisticsDao.insert(buildPurchaseStatisticsDO(purchaseBO, goodsCalculateBOList.size()));
        } else {
            totalCount = list.get(0).getCount();
        }
        List<List<GoodsCalculateBO>> goodsListSplit = ListUtlis.splitList(goodsCalculateBOList, 100);
        goodsListSplit.forEach(a-> executePurchase(categoryType,a,purchaseBO,totalCount));
    }

    private void executePurchase(Byte categoryType,List<GoodsCalculateBO> goodsCalculateBOS,PurchaseBO purchaseBO,Integer amount) {
        //调用ERP采购
        mqEventMsg.sendPurchaseMsg(buildPurchaseInfoMsg(categoryType,goodsCalculateBOS,purchaseBO,amount));
        //得到这次发送的IDs
        List<Long> ids = new ArrayList<>();
        goodsCalculateBOS.forEach(goodsCalculateBO -> ids.addAll(goodsCalculateBO.getIds()));
        purchaseDAO.updateStatusByIdUnderStatus(ids, PurchaseStatusEnum.PURCHASE_SUCCESS.getStatus(), PurchaseStatusEnum.INIT.getStatus());
    }

    private PurchaseInfoMsg buildPurchaseInfoMsg(Byte categoryType,List<GoodsCalculateBO> goodsCalculateBOS,PurchaseBO purchaseBO,Integer amount) {
        PurchaseInfoMsg purchaseInfoMsg = new PurchaseInfoMsg();
        List<PurchaseGoodsInfoMsg> purchaseGoodsInfoMsgs = new ArrayList<>();
        purchaseInfoMsg.setCategoryGoodsTotalAmount(amount);
        purchaseInfoMsg.setForecastType(purchaseBO.getForecastType());
        purchaseInfoMsg.setWarehouseId(purchaseBO.getWarehouseId());
        purchaseInfoMsg.setForecastDate(purchaseBO.getForecastDate());
        purchaseInfoMsg.setCategoryType(categoryType);
        purchaseInfoMsg.setVersion(purchaseBO.getVersion());
        goodsCalculateBOS.forEach(goodsCalculateBO -> {
            PurchaseGoodsInfoMsg purchaseGoodsInfoMsg = new PurchaseGoodsInfoMsg();
            purchaseGoodsInfoMsg.setGoodsTotalAmount(goodsCalculateBO.getGoodsTotalAmount());
            purchaseGoodsInfoMsg.setStockUnit(goodsCalculateBO.getGoodsUnit());
            purchaseGoodsInfoMsg.setGoodsId(goodsCalculateBO.getGoodsId());
            purchaseGoodsInfoMsgs.add(purchaseGoodsInfoMsg);
        });
        purchaseInfoMsg.setPurchaseGoodsInfoMsgs(purchaseGoodsInfoMsgs);
        return purchaseInfoMsg;
    }

    private List<GoodsItemInfoBO> transToGoodsItemInfoBO(List<PurchaseDO> purchaseDOList) {
        List<GoodsItemInfoBO> goodsItemInfoBOS = new ArrayList<>();
        purchaseDOList.forEach(purchaseDO -> {
            GoodsItemInfoBO goodsItemInfoBO = new GoodsItemInfoBO();
            JSONObject commodityInfoExt = JSONObject.parseObject(purchaseDO.getCommodityInfoExt());
            goodsItemInfoBO.setCityProductId(purchaseDO.getCityProductId());
            goodsItemInfoBO.setGoodsId(commodityInfoExt.getString(CommodityInfoExtEnum.GOODS_ID.getKey()));
            goodsItemInfoBO.setGoodsUnit(commodityInfoExt.getString(CommodityInfoExtEnum.GOODS_UNIT.getKey()));
            goodsItemInfoBO.setRelNum(commodityInfoExt.getBigDecimal(CommodityInfoExtEnum.REL_NUM.getKey()));
            goodsItemInfoBO.setItemNum(purchaseDO.getForecastNum());
            goodsItemInfoBO.setId(purchaseDO.getId());
            goodsItemInfoBOS.add(goodsItemInfoBO);
        });
        return goodsItemInfoBOS;
    }

    private PurchaseStatisticsDO buildPurchaseStatisticsDO(PurchaseBO purchaseBO,int count) {
        PurchaseStatisticsDO purchaseStatisticsDO = new PurchaseStatisticsDO();
        purchaseStatisticsDO.setIsDeleted((byte)0);
        purchaseStatisticsDO.setCategoryType(purchaseBO.getCategoryType());
        purchaseStatisticsDO.setCount(count);
        purchaseStatisticsDO.setForecastDate(purchaseBO.getForecastDate());
        purchaseStatisticsDO.setForecastType(purchaseBO.getForecastType());
        purchaseStatisticsDO.setWarehouseId(purchaseBO.getWarehouseId());
        purchaseStatisticsDO.setVersion(purchaseBO.getVersion());
        return purchaseStatisticsDO;
    }

    private PurchaseStatisticsCriteria buildPurchaseStatisticsCriteria(PurchaseBO purchaseBO) {
        PurchaseStatisticsCriteria purchaseStatisticsDO = new PurchaseStatisticsCriteria();
        purchaseStatisticsDO.createCriteria().andWarehouseIdEqualTo(purchaseBO.getWarehouseId())
                .andForecastTypeEqualTo(purchaseBO.getForecastType())
                .andCategoryTypeEqualTo(purchaseBO.getCategoryType())
                .andForecastDateEqualTo(purchaseBO.getForecastDate())
                .andVersionEqualTo(purchaseBO.getVersion());
        return purchaseStatisticsDO;
    }

}
