package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.infrastructure.mapper.TimeRuleStoreMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleStoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 时间规则-门店对应
 */
@Repository
public class TimeRuleStoreDao {

    @Autowired
    private TimeRuleStoreMapper timeRuleStoreMapper;

    public void insertTimeRuleStores(List<TimeRuleStoreDO> timeRuleStores) {
        timeRuleStoreMapper.batchInsert(timeRuleStores);
    }

    public int deleteByStoreId(Long id,Integer deleteId){
        return timeRuleStoreMapper.deleteByStoreId(id,deleteId);
    }

    public List<Integer> getAllStoreIds() {
        return timeRuleStoreMapper.getAllStoreIds();
    }

    public List<Integer> getStoreIdsByTimeRuleId(long timeRuleId){
        return timeRuleStoreMapper.getStoreIdsByTimeRuleId(timeRuleId);
    }

    public Integer getMaxDeleteId(Integer storeId){
        return timeRuleStoreMapper.getMaxDelete(storeId);
    }

    public TimeRuleStoreDO getByStoreId(Integer storeId){
        return timeRuleStoreMapper.getByStoreId(storeId);
    }
}
