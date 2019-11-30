package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.infrastructure.mapper.PurchaseStatisticsMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsCriteria;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/10/14 12:34 PM
 */
@Slf4j
@Repository
public class PurchaseStatisticsDao {
    @Autowired
    private PurchaseStatisticsMapper purchaseStatisticsMapper;

    public int deleteByExample(PurchaseStatisticsCriteria example) {
        PurchaseStatisticsDO record = new PurchaseStatisticsDO();
        record.setIsDeleted((byte)1);
        return purchaseStatisticsMapper.updateByExampleSelective(record,example);
    }

    public int insert(PurchaseStatisticsDO record) {
        return purchaseStatisticsMapper.insert(record);
    }

    public List<PurchaseStatisticsDO> select(PurchaseStatisticsCriteria example) {
        return purchaseStatisticsMapper.selectByExample(example);
    }


}
