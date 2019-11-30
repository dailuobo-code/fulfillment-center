package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderDOMapper {
    int countByExample(OrderDOCriteria example);

    int deleteByExample(OrderDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExampleWithRowbounds(OrderDOCriteria example, RowBounds rowBounds);

    List<OrderDO> selectByExample(OrderDOCriteria example);

    OrderDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDO record,
        @Param("example") OrderDOCriteria example);

    int updateByExample(@Param("record") OrderDO record, @Param("example") OrderDOCriteria example);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}