package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.PurchaseDO;
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