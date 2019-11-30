package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchTimeRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.mapper.TimeRuleMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRuleDao {

    @Autowired
    private TimeRuleMapper timeRuleMapper;

    public TimeRuleDO getById(long id) {
        return timeRuleMapper.getById(id);
    }

    public int insertRule(TimeRuleDO ruleToAdd) {
        return timeRuleMapper.insert(ruleToAdd);
    }

    public int updateRule(TimeRuleDO timeRuleToUpdate) {
        return timeRuleMapper.update(timeRuleToUpdate);
    }

    public PagedSearch<TimeRuleDO> searchTimeRules(SearchTimeRuleCondition condition) {
        // 分页插件-设置分页参数
        PageHelper.startPage(condition.getPage(), condition.getPageSize());

        List<TimeRuleDO> rules = timeRuleMapper.searchRules(condition);

        PageInfo<TimeRuleDO> pageInfo = new PageInfo<>(rules);
        return new PagedSearch<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
