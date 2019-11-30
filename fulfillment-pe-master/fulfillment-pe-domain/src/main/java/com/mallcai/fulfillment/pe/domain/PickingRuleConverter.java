package com.mallcai.fulfillment.pe.domain;

import com.mallcai.fulfillment.pe.domain.entity.PickingRule;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PickingRuleDO;

import java.util.ArrayList;
import java.util.List;

/**
 * 领域层 拣货规则 转换器
 */
public class PickingRuleConverter {

    public static PickingRuleConverter INSTANCE = new PickingRuleConverter();

    public List<PickingRuleDO> toPickingRuleDOList(List<PickingRule> pickingRules) {
        if(null == pickingRules){
            return null;
        }

        List<PickingRuleDO> target = new ArrayList<>(pickingRules.size());
        pickingRules.forEach(ele -> target.add(this.toPickingRuleDO(ele)));

        return target;
    }

    public PickingRuleDO toPickingRuleDO(PickingRule pickingRule) {
        if(null == pickingRule){
            return null;
        }

        PickingRuleDO target = new PickingRuleDO();
        target.setId(pickingRule.getId());
        target.setProductNo(pickingRule.getProductNo());
        target.setWarehouseId(pickingRule.getWarehouseId());
        target.setCreateUserId(pickingRule.getCreateUserId());
        target.setUpdateUserId(pickingRule.getUpdateUserId());

        return target;
    }

    public PickingRule toPickingRule(PickingRuleDO pickingRuleDO) {
        if(null == pickingRuleDO){
            return null;
        }

        PickingRule target = new PickingRule();
        target.setId(pickingRuleDO.getId());
        target.setProductNo(pickingRuleDO.getProductNo());
        target.setWarehouseId(pickingRuleDO.getWarehouseId());
        target.setCreateUserId(pickingRuleDO.getCreateUserId());
        target.setUpdateUserId(pickingRuleDO.getUpdateUserId());
        target.setCreateTime(pickingRuleDO.getCreateTime());
        target.setUpdateTime(pickingRuleDO.getUpdateTime());

        return target;
    }

    public List<PickingRule> toPickingRuleList(List<PickingRuleDO> data) {
        if(null == data){
            return null;
        }

        List<PickingRule> target = new ArrayList<>(data.size());
        data.forEach(ele -> target.add(this.toPickingRule(ele)));

        return target;
    }
}
