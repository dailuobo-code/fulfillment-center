package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.PeriodBuyRecord;
import com.mallcai.fulfillment.dc.valueobject.dc.PeriodBuyRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PeriodBuyRecordMapper {
    int countByExample(PeriodBuyRecordCriteria example);

    int deleteByExample(PeriodBuyRecordCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PeriodBuyRecord record);

    int insertSelective(PeriodBuyRecord record);

    List<PeriodBuyRecord> selectByExampleWithRowbounds(PeriodBuyRecordCriteria example, RowBounds rowBounds);

    List<PeriodBuyRecord> selectByExample(PeriodBuyRecordCriteria example);

    PeriodBuyRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PeriodBuyRecord record, @Param("example") PeriodBuyRecordCriteria example);

    int updateByExample(@Param("record") PeriodBuyRecord record, @Param("example") PeriodBuyRecordCriteria example);

    int updateByPrimaryKeySelective(PeriodBuyRecord record);

    int updateByPrimaryKey(PeriodBuyRecord record);
}