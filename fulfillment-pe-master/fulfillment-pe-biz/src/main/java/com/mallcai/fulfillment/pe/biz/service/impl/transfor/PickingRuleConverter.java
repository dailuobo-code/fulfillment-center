package com.mallcai.fulfillment.pe.biz.service.impl.transfor;

import com.mallcai.fulfillment.pe.api.service.operate.dto.req.AddPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.OperatePickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.req.SearchPickingRuleRequestDTO;
import com.mallcai.fulfillment.pe.api.service.query.dto.rsp.PickingRuleDTO;
import com.mallcai.fulfillment.pe.domain.entity.PickingRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchPickingRuleCondition;

import java.util.ArrayList;
import java.util.List;

public class PickingRuleConverter {

    public static PickingRuleConverter INSTANCE = new PickingRuleConverter();

    public List<PickingRule> toPickingRules(AddPickingRuleRequestDTO param) {
        List<PickingRule> pickingRules = new ArrayList<>(param.getProductNos().size());
        // 前端选择多个商品信息，为每一个总部商品都生成一个规则
        param.getProductNos().forEach(productNo -> {
            PickingRule pickingRule = new PickingRule();
            pickingRule.setProductNo(productNo);
            pickingRule.setWarehouseId(param.getWarehouseId());
            pickingRule.setCreateUserId(param.getUserId());
            pickingRule.setUpdateUserId(param.getUserId());
            pickingRules.add(pickingRule);
        });
        return pickingRules;
    }

    public PickingRule toPickingRule(OperatePickingRuleRequestDTO param) {
        if (null == param) {
            return null;
        }
        PickingRule pickingRule = new PickingRule();
        pickingRule.setId(param.getId());
        pickingRule.setProductNo(param.getProductNo());
        pickingRule.setWarehouseId(param.getWarehouseId());
        return pickingRule;
    }

    public PickingRuleDTO toPickingRuleDTO(PickingRule pickingRule) {
        if (null == pickingRule) {
            return null;
        }
        PickingRuleDTO target = new PickingRuleDTO();
        target.setId(pickingRule.getId());
        target.setProductNo(pickingRule.getProductNo());
        target.setWarehouseId(pickingRule.getWarehouseId());
        target.setCreateTime(pickingRule.getCreateTime());
        target.setCreateUserId(pickingRule.getCreateUserId());
        target.setUpdateTime(pickingRule.getUpdateTime());
        target.setUpdateUserId(pickingRule.getUpdateUserId());
        return target;
    }

    public SearchPickingRuleCondition toSearchPickingRuleCondition(SearchPickingRuleRequestDTO param) {
        if (null == param) {
            return null;
        }
        SearchPickingRuleCondition condition = new SearchPickingRuleCondition();
        condition.setPage(param.getPage(), 1);
        condition.setPageSize(param.getPageSize(), 20);
        return condition;
    }

    public List<PickingRuleDTO> toPickingRuleDTOList(List<PickingRule> data) {
        if (null == data) {
            return null;
        }
        List<PickingRuleDTO> target = new ArrayList<>(data.size());
        data.forEach(ele -> target.add(this.toPickingRuleDTO(ele)));
        return target;
    }
}
