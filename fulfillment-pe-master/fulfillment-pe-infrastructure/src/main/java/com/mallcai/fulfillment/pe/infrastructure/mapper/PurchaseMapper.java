package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.PurchaseCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PurchaseMapper {
    int countByExample(PurchaseCriteria example);

    int deleteByExample(PurchaseCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseDO record);

    int insertSelective(PurchaseDO record);

    List<PurchaseDO> selectByExampleWithRowbounds(PurchaseCriteria example, RowBounds rowBounds);

    List<PurchaseDO> selectByExample(PurchaseCriteria example);

    PurchaseDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseDO record,
        @Param("example") PurchaseCriteria example);

    int updateByExample(@Param("record") PurchaseDO record,
        @Param("example") PurchaseCriteria example);

    int updateByPrimaryKeySelective(PurchaseDO record);

    int updateByPrimaryKey(PurchaseDO record);
}