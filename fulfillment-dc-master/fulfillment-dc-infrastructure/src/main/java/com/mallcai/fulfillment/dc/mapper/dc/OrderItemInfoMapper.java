package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderItemInfoMapper {
    int countByExample(OrderItemInfoCriteria example);

    int deleteByExample(OrderItemInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderItemInfoDO record);

    int insertSelective(OrderItemInfoDO record);

    List<OrderItemInfoDO> selectByExampleWithRowbounds(OrderItemInfoCriteria example,
        RowBounds rowBounds);

    List<OrderItemInfoDO> selectByExample(OrderItemInfoCriteria example);

    OrderItemInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderItemInfoDO record,
        @Param("example") OrderItemInfoCriteria example);

    int updateByExample(@Param("record") OrderItemInfoDO record,
        @Param("example") OrderItemInfoCriteria example);

    int updateByPrimaryKeySelective(OrderItemInfoDO record);

    int updateByPrimaryKey(OrderItemInfoDO record);
}