package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.common.utils.ListUtils;
import com.mallcai.fulfillment.pe.domain.PickingRuleConverter;
import com.mallcai.fulfillment.pe.domain.entity.PickingRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchPickingRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.dao.PickingRuleDao;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PickingRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickingRuleInnerService {

    @Autowired
    private PickingRuleDao pickingRuleDao;

    private PickingRuleConverter pickingRuleConverter = PickingRuleConverter.INSTANCE;

    public boolean addRules(List<PickingRule> pickingRules) {
        List<String> productNos = ListUtils.toList(pickingRules, PickingRule::getProductNo);
        List<PickingRuleDO> existsList = pickingRuleDao.selectByProductNoList(productNos);
        if(existsList.size() > 0){
            throw new BizException("规则中已经配置了商品编号为: " + ListUtils.toList(existsList, PickingRuleDO::getProductNo) + " 的总部商品, 请检查");
        }

        List<PickingRuleDO> rules = pickingRuleConverter.toPickingRuleDOList(pickingRules);

        pickingRuleDao.batchInsert(rules);

        return true;
    }

    public boolean updateRule(PickingRule pickingRule) {
        List<PickingRuleDO> existsList = pickingRuleDao.selectByProductNoList(ListUtils.asList(pickingRule.getProductNo()));
        // 检查商品编号
        if(existsList.size() > 0 && !pickingRule.getProductNo().equals(existsList.get(0).getProductNo())){
            throw new BizException("规则中已经配置了商品编号为: " + pickingRule.getProductNo() + " 的总部商品, 请检查");
        }

        PickingRuleDO ruleToUpdate = pickingRuleConverter.toPickingRuleDO(pickingRule);

        return pickingRuleDao.updateRule(ruleToUpdate) > 0;
    }

    public boolean deleteRule(Long id, Integer userId) {
        return pickingRuleDao.deleteRule(id, userId) > 0;
    }

    public PickingRule getById(Long id) {
        PickingRuleDO ruleFromDb = pickingRuleDao.getById(id);
        if(null ==ruleFromDb){
            return null;
        }

        return pickingRuleConverter.toPickingRule(ruleFromDb);
    }

    public PickingRule loadById(Long id) {
        PickingRule fromDb = this.getById(id);

        if(null == fromDb){
            throw new BizException("对应的数据不存在");
        }
        return fromDb;
    }

    public PagedSearch<PickingRule> searchRules(SearchPickingRuleCondition condition) {
        PagedSearch<PickingRuleDO> timeRules = pickingRuleDao.searchRules(condition);

        List<PickingRule> mappedResult = pickingRuleConverter.toPickingRuleList(timeRules.getData());

        return new PagedSearch<>(timeRules.getTotal(), mappedResult);
    }

    /**
     * 获取所有已经配置了规则的商品编号列表
     * @return
     */
    public List<String> getAllProductNos(){
        return pickingRuleDao.getAllProductNos();
    }
}
