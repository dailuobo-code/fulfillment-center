package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.StoreDO;
import com.mallcai.fulfillment.infrastructure.object.erp.StoreDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StoreDOMapper {
    int countByExample(StoreDOCriteria example);

    int deleteByExample(StoreDOCriteria example);

    int deleteByPrimaryKey(Integer storeId);

    int insert(StoreDO record);

    int insertSelective(StoreDO record);

    List<StoreDO> selectByExampleWithRowbounds(StoreDOCriteria example, RowBounds rowBounds);

    List<StoreDO> selectByExample(StoreDOCriteria example);

    StoreDO selectByPrimaryKey(Integer storeId);

    int updateByExampleSelective(@Param("record") StoreDO record, @Param("example") StoreDOCriteria example);

    int updateByExample(@Param("record") StoreDO record, @Param("example") StoreDOCriteria example);

    int updateByPrimaryKeySelective(StoreDO record);

    int updateByPrimaryKey(StoreDO record);

    List<StoreDO> selectByExampleMainInfo(StoreDOCriteria storeDOCriteria);
}