package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardWarehouse;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardWarehouseCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OmsStandardWarehouseMapper {
    int countByExample(OmsStandardWarehouseCriteria example);

    int deleteByExample(OmsStandardWarehouseCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OmsStandardWarehouse record);

    int insertSelective(OmsStandardWarehouse record);

    List<OmsStandardWarehouse> selectByExampleWithRowbounds(OmsStandardWarehouseCriteria example, RowBounds rowBounds);

    List<OmsStandardWarehouse> selectByExample(OmsStandardWarehouseCriteria example);

    OmsStandardWarehouse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OmsStandardWarehouse record, @Param("example") OmsStandardWarehouseCriteria example);

    int updateByExample(@Param("record") OmsStandardWarehouse record, @Param("example") OmsStandardWarehouseCriteria example);

    int updateByPrimaryKeySelective(OmsStandardWarehouse record);

    int updateByPrimaryKey(OmsStandardWarehouse record);

    List<OmsStandardWarehouse> selectByInvoiceNos(@Param("invoiceNos") List<String> invoiceNos);
}