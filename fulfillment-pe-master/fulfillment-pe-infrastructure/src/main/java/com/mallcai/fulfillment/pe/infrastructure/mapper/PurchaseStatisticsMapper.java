package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseStatisticsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PurchaseStatisticsMapper {
    int countByExample(PurchaseStatisticsCriteria example);

    int deleteByExample(PurchaseStatisticsCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseStatisticsDO record);

    int insertSelective(PurchaseStatisticsDO record);

    List<PurchaseStatisticsDO> selectByExampleWithRowbounds(PurchaseStatisticsCriteria example, RowBounds rowBounds);

    List<PurchaseStatisticsDO> selectByExample(PurchaseStatisticsCriteria example);

    PurchaseStatisticsDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseStatisticsDO record, @Param("example") PurchaseStatisticsCriteria example);

    int updateByExample(@Param("record") PurchaseStatisticsDO record, @Param("example") PurchaseStatisticsCriteria example);

    int updateByPrimaryKeySelective(PurchaseStatisticsDO record);

    int updateByPrimaryKey(PurchaseStatisticsDO record);
}