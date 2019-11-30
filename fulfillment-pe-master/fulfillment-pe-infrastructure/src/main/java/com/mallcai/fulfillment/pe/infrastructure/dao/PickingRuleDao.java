package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchPickingRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.mapper.PickingRuleMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PickingRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PickingRuleDao {

    @Autowired
    private PickingRuleMapper pickingRuleMapper;

    public List<PickingRuleDO> selectByProductNoList(List<String> productNoList){
        if(null == productNoList || productNoList.size() == 0){
            return new ArrayList<>();
        }
        return pickingRuleMapper.selectByProductNoList(productNoList);
    }

    public int batchInsert(List<PickingRuleDO> rules) {
        return pickingRuleMapper.batchInsert(rules);
    }

    public int updateRule(PickingRuleDO ruleToUpdate) {
        return pickingRuleMapper.updateRule(ruleToUpdate);
    }

    public int deleteRule(Long id, Integer userId) {
        return pickingRuleMapper.deleteRule(id, userId);
    }

    public PickingRuleDO getById(Long id) {
        return pickingRuleMapper.getById(id);
    }

    public PagedSearch<PickingRuleDO> searchRules(SearchPickingRuleCondition condition) {
        // 分页插件-设置分页参数
        PageHelper.startPage(condition.getPage(), condition.getPageSize());

        List<PickingRuleDO> rules = pickingRuleMapper.searchRules(condition);

        PageInfo<PickingRuleDO> pageInfo = new PageInfo<>(rules);
        return new PagedSearch<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public List<String> getAllProductNos() {
        return pickingRuleMapper.getAllProductNos();
    }
}
