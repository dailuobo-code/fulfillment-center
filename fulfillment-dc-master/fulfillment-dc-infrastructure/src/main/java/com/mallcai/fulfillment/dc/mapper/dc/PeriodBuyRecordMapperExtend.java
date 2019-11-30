package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.PeriodBuyRecord;
import com.mallcai.fulfillment.dc.valueobject.dc.PeriodBuyRecordCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PeriodBuyRecordMapperExtend extends PeriodBuyRecordMapper{
    PeriodBuyRecord selectByOrderNo(String orderNo);
}