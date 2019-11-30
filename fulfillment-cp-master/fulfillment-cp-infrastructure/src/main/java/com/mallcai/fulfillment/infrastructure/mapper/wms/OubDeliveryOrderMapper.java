package com.mallcai.fulfillment.infrastructure.mapper.wms;

import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrder;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderCriteria;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrder;
import com.mallcai.fulfillment.infrastructure.object.wms.OubDeliveryOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OubDeliveryOrderMapper {
    int countByExample(OubDeliveryOrderCriteria example);

    int deleteByExample(OubDeliveryOrderCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OubDeliveryOrder record);

    int insertSelective(OubDeliveryOrder record);

    List<OubDeliveryOrder> selectByExampleWithRowbounds(OubDeliveryOrderCriteria example, RowBounds rowBounds);

    List<OubDeliveryOrder> selectByExample(OubDeliveryOrderCriteria example);

    OubDeliveryOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OubDeliveryOrder record, @Param("example") OubDeliveryOrderCriteria example);

    int updateByExample(@Param("record") OubDeliveryOrder record, @Param("example") OubDeliveryOrderCriteria example);

    int updateByPrimaryKeySelective(OubDeliveryOrder record);

    int updateByPrimaryKey(OubDeliveryOrder record);

    List<OubDeliveryOrder> selectPageByExample(@Param("oredCriteria") OubDeliveryOrderCriteria oredCriteria,
                                               @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}