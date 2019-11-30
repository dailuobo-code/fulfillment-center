package com.mallcai.fulfillment.infrastructure.mapper.dc;

import com.mallcai.fulfillment.infrastructure.object.dc.OrderItemInfo;
import com.mallcai.fulfillment.infrastructure.object.dc.OrderItemInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderItemInfoMapper {
    int countByExample(OrderItemInfoCriteria example);

    int deleteByExample(OrderItemInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderItemInfo record);

    int insertSelective(OrderItemInfo record);

    List<OrderItemInfo> selectByExampleWithRowbounds(OrderItemInfoCriteria example, RowBounds rowBounds);

    List<OrderItemInfo> selectByExample(OrderItemInfoCriteria example);

    OrderItemInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderItemInfo record, @Param("example") OrderItemInfoCriteria example);

    int updateByExample(@Param("record") OrderItemInfo record, @Param("example") OrderItemInfoCriteria example);

    int updateByPrimaryKeySelective(OrderItemInfo record);

    int updateByPrimaryKey(OrderItemInfo record);
}