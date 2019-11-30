package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.condition.SearchTimeRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleDO;

import java.util.List;

public interface TimeRuleMapper {

    List<TimeRuleDO> searchRules(SearchTimeRuleCondition condition);

    TimeRuleDO getById(long id);

    int insert(TimeRuleDO ruleToAdd);

    int update(TimeRuleDO timeRuleToUpdate);
}
