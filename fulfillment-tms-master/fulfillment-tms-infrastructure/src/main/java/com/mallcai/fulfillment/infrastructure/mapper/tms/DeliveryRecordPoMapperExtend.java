package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPo;
import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPoCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Date;
import java.util.List;

public interface DeliveryRecordPoMapperExtend extends DeliveryRecordPoMapper{

    int updateTransPlanByWaveNo(@Param("date") Date date,@Param("waveNos") List<String> waveNos);

    int updateTransCarByWaveNo(@Param("date") Date date,@Param("waveNos") List<String> waveNos);

    int updateTransResByWaveNo(@Param("date") Date date,@Param("waveNos") List<String> waveNos);

}