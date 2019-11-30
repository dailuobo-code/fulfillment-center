package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrder;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OmsStandardOrderMapper {
    int countByExample(OmsStandardOrderCriteria example);

    int deleteByExample(OmsStandardOrderCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsStandardOrder record);

    int insertSelective(OmsStandardOrder record);

    List<OmsStandardOrder> selectByExampleWithRowbounds(OmsStandardOrderCriteria example, RowBounds rowBounds);

    List<OmsStandardOrder> selectByExample(OmsStandardOrderCriteria example);

    OmsStandardOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsStandardOrder record, @Param("example") OmsStandardOrderCriteria example);

    int updateByExample(@Param("record") OmsStandardOrder record, @Param("example") OmsStandardOrderCriteria example);

    int updateByPrimaryKeySelective(OmsStandardOrder record);

    int updateByPrimaryKey(OmsStandardOrder record);
}