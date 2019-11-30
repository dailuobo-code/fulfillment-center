package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.TimeRuleStoreDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeRuleStoreMapper {

    void batchInsert(List<TimeRuleStoreDO> timeRuleStores);

    int deleteByStoreId(@Param("id") Long id,@Param("deleteId") Integer deleteId);

    List<Integer> getAllStoreIds();

    List<Integer> getStoreIdsByTimeRuleId(long timeRuleId);

    Integer getMaxDelete(Integer storeId);

    TimeRuleStoreDO getByStoreId(Integer storeId);
}
