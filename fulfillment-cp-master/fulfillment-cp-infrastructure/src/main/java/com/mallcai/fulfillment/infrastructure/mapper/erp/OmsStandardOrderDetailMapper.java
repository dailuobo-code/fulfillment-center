package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrder;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderCriteria;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderDetail;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderDetailCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OmsStandardOrderDetailMapper {
    int countByExample(OmsStandardOrderDetailCriteria example);

    int deleteByExample(OmsStandardOrderDetailCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsStandardOrderDetail record);

    int insertSelective(OmsStandardOrderDetail record);

    List<OmsStandardOrderDetail> selectByExampleWithRowbounds(OmsStandardOrderDetailCriteria example, RowBounds rowBounds);

    List<OmsStandardOrderDetail> selectByExample(OmsStandardOrderDetailCriteria example);

    OmsStandardOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsStandardOrderDetail record, @Param("example") OmsStandardOrderDetailCriteria example);

    int updateByExample(@Param("record") OmsStandardOrderDetail record, @Param("example") OmsStandardOrderDetailCriteria example);

    int updateByPrimaryKeySelective(OmsStandardOrderDetail record);

    int updateByPrimaryKey(OmsStandardOrderDetail record);

    List<OmsStandardOrderDetail> selectByPage(@Param("invoiceNos") List<String> invoiceNos,
                                               @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}