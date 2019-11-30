package com.mallcai.fulfillment.pe.biz.service.impl.transfor;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.ChangeRuleStatusRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperateProductionRuleDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchProductionRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.ProductionRuleDTO;
import com.mallcai.fulfillment.pe.domain.entity.ProductionLimitRule;
import com.mallcai.fulfillment.pe.domain.entity.ProductionRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchProductionRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProductionRuleDO;

import java.util.ArrayList;
import java.util.List;

/**
 * bean转化
 * @author zhanghao
 * @date 2019年08月13日23:40:11
 */
public class BeanConverter {

    public static BeanConverter INSTANCE = new BeanConverter();

    public List<ProductionRule> toProductionRuleList(List<ProductionRuleDO> source){
        if(null == source){
            return new ArrayList<>();
        }

        List<ProductionRule> target = new ArrayList<>(source.size());
        source.forEach(ele -> target.add(this.toProductionRule(ele)));

        return target;
    }

    public List<ProductionRuleDTO> toProductionRuleListDTO(List<ProductionRule> productionRules){
        if(null == productionRules){
            return null;
        }

        List<ProductionRuleDTO> target = new ArrayList<>(productionRules.size());
        productionRules.forEach(ele -> target.add(toProductionRuleDTO(ele)));

        return target;
    }

    public ProductionRule toProductionRule(ProductionRuleDO source) {
        if(null == source){
            return null;
        }
        ProductionRule target = new ProductionRule();
        target.setId(source.getId());
        target.setCityId(source.getCityId());
        target.setCreateTime(source.getCreateTime());
        target.setCreateUserId(source.getCreateUserId());
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setStoreGroupId(source.getStoreGroupId());
        target.setUpdateTime(source.getUpdateTime());
        target.setUpdateUserId(source.getUpdateUserId());
        target.setWarehouseId(source.getWarehouseId());
        target.setProductionLimitRule(JSON.parseObject(source.getProductionLimitRule(), ProductionLimitRule.class));
        return target;
    }

    public ProductionRuleDO toProductionRule(ProductionRule source){
        if(null == source){
            return null;
        }
        ProductionRuleDO target = new ProductionRuleDO();
        target.setId(source.getId());
        target.setCityId(source.getCityId());
        target.setCreateTime(source.getCreateTime());
        target.setCreateUserId(source.getCreateUserId());
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setStoreGroupId(source.getStoreGroupId());
        target.setUpdateTime(source.getUpdateTime());
        target.setUpdateUserId(source.getUpdateUserId());
        target.setWarehouseId(source.getWarehouseId());
        target.setProductionLimitRule(null == source.getProductionLimitRule() ? "" : JSON.toJSONString(source.getProductionLimitRule()));
        return target;
    }


    public SearchProductionRuleCondition toSearchProductionRuleCondition(SearchProductionRuleRequestDTO request){
        if(null == request){
            return null;
        }

        SearchProductionRuleCondition condition = new SearchProductionRuleCondition();
        condition.setName(request.getName());
        condition.setStoreGroupId(request.getStoreGroupId());
        condition.setWarehouseId(request.getWarehouseId());
        condition.setCreateTimeBegin(request.getCreateTimeBegin());
        condition.setCreateTimeEnd(request.getCreateTimeEnd());
        condition.setPage(request.getPage(), 1);
        condition.setPageSize(request.getPageSize(), 20);
        condition.setCityId(request.getCityId());

        return condition;
    }

    public ProductionRuleDTO toProductionRuleDTO(ProductionRule productionRule){
        if(null == productionRule){
            return null;
        }

        ProductionRuleDTO target = new ProductionRuleDTO();
        target.setId(productionRule.getId());
        target.setCityId(productionRule.getCityId());
        target.setAmountLimit(productionRule.getProductionLimitRule().getAmountLimit());
        target.setCreateTime(productionRule.getCreateTime());
        target.setCreateUserId(productionRule.getCreateUserId());
        target.setDayLimit(productionRule.getProductionLimitRule().getDayLimit());
        target.setName(productionRule.getName());
        target.setStatus(productionRule.getStatus());
        target.setStoreGroupId(productionRule.getStoreGroupId());
        target.setUpdateTime(productionRule.getUpdateTime());
        target.setUpdateUserId(productionRule.getUpdateUserId());
        target.setWarehouseId(productionRule.getWarehouseId());

        return target;
    }

    public ProductionRule toProductionRule(OperateProductionRuleDTO source){
        if(null == source){
            return null;
        }

        ProductionRule target = new ProductionRule();
        target.setId(source.getId());
        target.setCityId(source.getCityId());
        target.setName(source.getName());
        target.setStoreGroupId(source.getStoreGroupId());
        target.setWarehouseId(source.getWarehouseId());

        ProductionLimitRule limitRule = new ProductionLimitRule();
        limitRule.setDayLimit(source.getDayLimit());
        limitRule.setAmountLimit(source.getAmountLimit());
        target.setProductionLimitRule(limitRule);

        return target;
    }

    public ProductionRule toProductionRule(ChangeRuleStatusRequestDTO source){
        if(null == source){
            return null;
        }

        ProductionRule target = new ProductionRule();
        target.setId(source.getId());
        target.setUpdateUserId(source.getUserId());
        target.setStatus(source.getStatus());

        return target;
    }




}
