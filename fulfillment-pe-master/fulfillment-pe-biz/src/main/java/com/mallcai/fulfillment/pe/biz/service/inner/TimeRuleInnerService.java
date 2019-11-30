package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.biz.service.handle.StoreWhiteHandle;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import com.mallcai.fulfillment.pe.common.page.PagedSearch;
import com.mallcai.fulfillment.pe.domain.TimeRuleConverter;
import com.mallcai.fulfillment.pe.domain.entity.TimeRule;
import com.mallcai.fulfillment.pe.infrastructure.condition.SearchTimeRuleCondition;
import com.mallcai.fulfillment.pe.infrastructure.dao.TimeRuleDao;
import com.mallcai.fulfillment.pe.infrastructure.dao.TimeRuleStoreDao;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleStoreDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeRuleInnerService {

    @Autowired
    private TimeRuleDao timeRuleDao;

    @Autowired
    private TimeRuleStoreDao timeRuleStoreDao;

    @Autowired
    @Qualifier("fpeTransactionTemplate")
    private TransactionTemplate fpeTransactionTemplate;

    @Resource
    private StoreWhiteHandle storeWhiteHandle;

    private TimeRuleConverter timeRuleConverter = TimeRuleConverter.INSTANCE;

    public boolean addRule(TimeRule timeRule) {
        List<Integer> allStoreIds = this.getAllStoreIds();
        for(Integer storeIdToAdd : timeRule.getStoreIds()){
            if(null != allStoreIds && allStoreIds.contains(storeIdToAdd)){
                throw new BizException("门店id" + storeIdToAdd + "已经配置在现有规则中了");
            }
        }

        TimeRuleDO ruleToAdd = timeRuleConverter.toTimeRuleDO(timeRule);

        // 根据数据库返回的影响的行数, 判断是否成功
        boolean flag = fpeTransactionTemplate.execute(status -> {
            try {
                boolean success = timeRuleDao.insertRule(ruleToAdd) > 0;
                if(success){
                    // id 透传
                    timeRule.setId(ruleToAdd.getId());
                    List<TimeRuleStoreDO> timeRuleStores = createTimeRuleStores(ruleToAdd.getId(), timeRule.getStoreIds(), timeRule.getCreateUserId());
                    timeRuleStoreDao.insertTimeRuleStores(timeRuleStores);
                }
            } catch (DuplicateKeyException e) {
                throw new BizException(Errors.RULE_ALREADY_EXISTS);
            }
            return true;
        });
        return flag;
    }

    private List<TimeRuleStoreDO> createTimeRuleStores(long timeRuleId, List<Integer> storeIds, Integer userId){
        List<TimeRuleStoreDO> stores = new ArrayList<>(storeIds.size());
        storeIds.forEach(storeId -> {
            TimeRuleStoreDO timeRuleStore = new TimeRuleStoreDO();
            timeRuleStore.setTimeRuleId(timeRuleId);
            timeRuleStore.setStoreId(storeId);
            timeRuleStore.setCreateUserId(userId);
            timeRuleStore.setUpdateUserId(userId);
            stores.add(timeRuleStore);
        });

        return stores;
    }

    public boolean updateRule(TimeRule timeRule) {
        TimeRule oldTimeRule = this.loadById(timeRule.getId());

        TimeRuleDO timeRuleToUpdate = timeRuleConverter.toTimeRuleDO(timeRule);
        List<Integer> existStoreRules = timeRuleStoreDao.getStoreIdsByTimeRuleId(timeRule.getId());
        List<Integer> needInsertRules = new ArrayList<>();
        List<Integer> needDeleteRules = new ArrayList<>();
        //获取需要增加的门店
        timeRule.getStoreIds().forEach(storeId->{
            if (!existStoreRules.contains(storeId)) {
                needInsertRules.add(storeId);
            }
        });
        //获取需要删除的门店
        existStoreRules.forEach(existStoreRule -> {
            if (!timeRule.getStoreIds().contains(existStoreRule)) {
                needDeleteRules.add(existStoreRule);
            }
        });
        Boolean flag = fpeTransactionTemplate.execute(status -> {
            try {
                timeRuleDao.updateRule(timeRuleToUpdate);
                //更新与门店的对应关系
                if(storeChanged(oldTimeRule, timeRule)){
                    // 如果有变的话删除老的
                    if (CollectionUtils.isNotEmpty(needDeleteRules)) {
                        needDeleteRules.forEach(deleteRule -> {
                            TimeRuleStoreDO storeDO = timeRuleStoreDao.getByStoreId(deleteRule);
                            if (storeDO == null) {
                                throw new BizException(Errors.UPDATE_RULE_ERROR);
                            }
                            Integer maxDeleteId = timeRuleStoreDao.getMaxDeleteId(deleteRule);
                            timeRuleStoreDao.deleteByStoreId(storeDO.getId(), maxDeleteId + 1);
                        });
                    }
                    // 插入新的对应关系
                    if (CollectionUtils.isNotEmpty(needInsertRules)) {
                        List<TimeRuleStoreDO> timeRuleStores = createTimeRuleStores(timeRule.getId(), needInsertRules, timeRule.getUpdateUserId());
                        timeRuleStoreDao.insertTimeRuleStores(timeRuleStores);
                    }
                }
            } catch (Exception e) {
                throw new BizException(Errors.UPDATE_RULE_ERROR);
            }
            return true;
        });
        return flag;
    }

    /**
     * 判断规则对应的门店信息是否有变化
     */
    private boolean storeChanged(TimeRule oldOne, TimeRule newValue){
        if(newValue.getStoreIds() == null || oldOne.getStoreIds() == null){
            return false;
        }

        if(newValue.getStoreIds().size() != oldOne.getStoreIds().size()){
            return true;
        }

        for(Integer storeId : newValue.getStoreIds()){
            if(!oldOne.getStoreIds().contains(storeId)){
                return true;
            }
        }

        return false;
    }

    public List<Integer> getAllStoreIds() {
        return timeRuleStoreDao.getAllStoreIds();
    }

    /**
     * 根据id查询,如果不存在返回 null
     * @param id
     * @return
     */
    public TimeRule getById(long id){
        TimeRuleDO ruleFromDb = timeRuleDao.getById(id);
        if(null == ruleFromDb){
            return null;
        }

        List<Integer> storeIds = timeRuleStoreDao.getStoreIdsByTimeRuleId(id);

        TimeRule timeRule = timeRuleConverter.toTimeRule(ruleFromDb);
        timeRule.setStoreIds(storeIds);

        return timeRule;
    }

    /**
     * 根据id查询, 如果不存在抛出异常
     * @param id
     * @return
     * @throws BizException 如果查询数据不存在
     */
    public TimeRule loadById(long id) {
        TimeRule fromDb = this.getById(id);

        if(null == fromDb){
            throw new BizException(Errors.RULE_NOT_EXISTS);
        }

        return fromDb;
    }

    public PagedSearch<TimeRule> searchRules(SearchTimeRuleCondition condition) {
        PagedSearch<TimeRuleDO> timeRules = timeRuleDao.searchTimeRules(condition);

        List<TimeRule> mappedResult = timeRuleConverter.toTimeRuleList(timeRules.getData());

        // 设置门店信息
        mappedResult.forEach(timeRule -> timeRule.setStoreIds(timeRuleStoreDao.getStoreIdsByTimeRuleId(timeRule.getId())));

        return new PagedSearch<>(timeRules.getTotal(), mappedResult);
    }

    public TimeRule loadByStoreId(Integer storeId) {

        if (storeWhiteHandle.openWhite()) {
            if (!storeWhiteHandle.getWhiteStores().contains(storeId)) {
                return null;
            }
        }

        TimeRuleStoreDO timeRuleStore = timeRuleStoreDao.getByStoreId(storeId);
        if(null == timeRuleStore || null == timeRuleStore.getTimeRuleId()){
            return null;
        }

        TimeRuleDO timeRule = timeRuleDao.getById(timeRuleStore.getTimeRuleId());

        if(null == timeRule){
            return null;
        }

        return timeRuleConverter.toTimeRule(timeRule);
    }
}
