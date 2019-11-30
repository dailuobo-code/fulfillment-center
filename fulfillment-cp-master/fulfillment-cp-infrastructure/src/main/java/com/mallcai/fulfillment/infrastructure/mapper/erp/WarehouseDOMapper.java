package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.WarehouseDO;
import com.mallcai.fulfillment.infrastructure.object.erp.WarehouseDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WarehouseDOMapper {
    int countByExample(WarehouseDOCriteria example);

    int deleteByExample(WarehouseDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseDO record);

    int insertSelective(WarehouseDO record);

    List<WarehouseDO> selectByExampleWithBLOBsWithRowbounds(WarehouseDOCriteria example, RowBounds rowBounds);

    List<WarehouseDO> selectByExampleWithBLOBs(WarehouseDOCriteria example);

    List<WarehouseDO> selectByExampleWithRowbounds(WarehouseDOCriteria example, RowBounds rowBounds);

    List<WarehouseDO> selectByExample(WarehouseDOCriteria example);

    WarehouseDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WarehouseDO record, @Param("example") WarehouseDOCriteria example);

    int updateByExampleWithBLOBs(@Param("record") WarehouseDO record, @Param("example") WarehouseDOCriteria example);

    int updateByExample(@Param("record") WarehouseDO record, @Param("example") WarehouseDOCriteria example);

    int updateByPrimaryKeySelective(WarehouseDO record);

    int updateByPrimaryKeyWithBLOBs(WarehouseDO record);

    int updateByPrimaryKey(WarehouseDO record);

    List<WarehouseDO> selectByExampleMainInfo(WarehouseDOCriteria warehouseDOCriteria);
}