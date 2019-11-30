package com.mallcai.fulfillment.infrastructure.mapper.tms;

import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPo;
import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeliveryRecordPoMapper {
    long countByExample(DeliveryRecordPoCriteria example);

    int deleteByExample(DeliveryRecordPoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(DeliveryRecordPo record);

    int insertSelective(DeliveryRecordPo record);

    List<DeliveryRecordPo> selectByExampleWithRowbounds(DeliveryRecordPoCriteria example, RowBounds rowBounds);

    List<DeliveryRecordPo> selectByExample(DeliveryRecordPoCriteria example);

    DeliveryRecordPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeliveryRecordPo record, @Param("example") DeliveryRecordPoCriteria example);

    int updateByExample(@Param("record") DeliveryRecordPo record, @Param("example") DeliveryRecordPoCriteria example);

    int updateByPrimaryKeySelective(DeliveryRecordPo record);

    int updateByPrimaryKey(DeliveryRecordPo record);
}