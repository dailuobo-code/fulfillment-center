package com.mallcai.fulfillment.pe.biz.service.inner.purchase.impl;

import com.alibaba.fastjson.JSON;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoListDTO;
import com.mallcai.fulfillment.pe.api.msg.PurchaseForecastInfoQueryDTO;
import com.mallcai.fulfillment.pe.api.service.operate.PurchaseOperateService;
import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseDAO;
import com.mallcai.fulfillment.pe.infrastructure.dao.PurchaseStatisticsDao;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseDO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-10-11 23:08
 */
@Service("purchaseOperateService")
@Slf4j
public class PurchaseOperateServiceImpl implements PurchaseOperateService {
    @Resource
    private PurchaseDAO purchaseDAO;
    @Resource
    private PurchaseStatisticsDao purchaseStatisticsDao;

    @Override
    public PlainResult<?> getPurchaseStatusByCondition(
        PurchaseForecastInfoQueryDTO purchaseForecastInfoQueryDTO) {
        PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
        purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseForecastInfoQueryDTO.getCityId()).
            andWarehouseIdEqualTo(purchaseForecastInfoQueryDTO.getWarehouseId())
            .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoQueryDTO.getForecastDate()))
            .andForecastTypeEqualTo(purchaseForecastInfoQueryDTO.getForecastType())
            .andCategoryTypeEqualTo(purchaseForecastInfoQueryDTO.getCategoryType()).
            andVersionEqualTo(purchaseForecastInfoQueryDTO.getVersion()).
            andStatusEqualTo((byte)0).
            andStatusEqualTo(Constants.NOT_DELETED);
        int count=purchaseDAO.countSpecialByExample(purchaseCriteria);
        if(count!=0){
            return PlainResult.fail("上一版本数据仍有未处理的情况");
        }
        return PlainResultBuilder.success(true);
    }

    @Override
    public PlainResult<?> cancelPurchase(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
        List<PurchaseDO> existPurchaseList = getExistPurchaseList(purchaseForecastInfoListDTO);
        if (CollectionUtils
                .isNotEmpty(existPurchaseList)) {
            //作废指定城市和仓库的数据
            List<Long> purchaseIdList = existPurchaseList.stream().map(PurchaseDO::getId)
                    .collect(Collectors.toList());
            try {
                purchaseDAO.cancelPurchase(purchaseIdList);
                purchaseStatisticsDao.deleteByExample(getDeletePurchaseStatisticsCriteria(purchaseForecastInfoListDTO));
            } catch (Exception e) {
                log.error("删除已有采购单异常,purchaseIdList:{}", JSON.toJSONString(purchaseIdList), e);
                return PlainResult.fail("删除已有采购单异常");
            }
        }
        return PlainResultBuilder.success(true);
    }

    public List<PurchaseDO> getExistPurchaseList(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
        //需要根据采购还是补采，查询已有的数据
        List<PurchaseDO> result = new ArrayList<>();
        PurchaseCriteria purchaseCriteria = new PurchaseCriteria();
        if (Constants.PREDICT_T_2.equals(purchaseForecastInfoListDTO.getForecastType())) {
            //采购预测
            purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseForecastInfoListDTO.getCityId()).
                    andWarehouseIdEqualTo(purchaseForecastInfoListDTO.getWarehouseId())
                    .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()))
                    .andForecastTypeEqualTo(purchaseForecastInfoListDTO.getForecastType())
                    .andCategoryTypeEqualTo(purchaseForecastInfoListDTO.getCategoryType()).
                    andVersionLessThan(purchaseForecastInfoListDTO.getVersion()).
                    andIsDeletedEqualTo(Constants.NOT_DELETED);
        } else if (Constants.PREDICT_T.equals(purchaseForecastInfoListDTO.getForecastType())) {
            //补采预测
            purchaseCriteria.createCriteria().andCityIdEqualTo(purchaseForecastInfoListDTO.getCityId()).
                    andWarehouseIdEqualTo(purchaseForecastInfoListDTO.getWarehouseId())
                    .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()))
                    .andForecastTypeEqualTo(purchaseForecastInfoListDTO.getForecastType()).
                andVersionLessThan(purchaseForecastInfoListDTO.getVersion()).
                andIsDeletedEqualTo(Constants.NOT_DELETED);
        }
        result = purchaseDAO.selectByExample(purchaseCriteria);
        return result;
    }

    private PurchaseStatisticsCriteria getDeletePurchaseStatisticsCriteria(PurchaseForecastInfoListDTO purchaseForecastInfoListDTO) {
        PurchaseStatisticsCriteria purchaseStatisticsCriteria = new PurchaseStatisticsCriteria();
        purchaseStatisticsCriteria.createCriteria().andWarehouseIdEqualTo(purchaseForecastInfoListDTO.getWarehouseId())
                .andForecastTypeEqualTo(purchaseForecastInfoListDTO.getForecastType())
                .andForecastDateEqualTo(DateUtil.parseDate(purchaseForecastInfoListDTO.getForecastDate()))
                .andCategoryTypeEqualTo(purchaseForecastInfoListDTO.getCategoryType());
        return purchaseStatisticsCriteria;
    }
}
