package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.condition.SearchPickingRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PickingRuleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PickingRuleMapper {

    List<PickingRuleDO> selectByProductNoList(List<String> productNoList);

    int batchInsert(List<PickingRuleDO> rules);

    int updateRule(PickingRuleDO ruleToUpdate);

    int deleteRule(@Param("id") Long id, @Param("userId")Integer userId);

    PickingRuleDO getById(Long id);

    List<PickingRuleDO> searchRules(SearchPickingRuleCondition condition);

    List<String> getAllProductNos();
}
