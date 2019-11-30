package com.mallcai.fulfillment.infrastructure.mapper.trade;

import com.mallcai.fulfillment.infrastructure.object.trade.Order;
import com.mallcai.fulfillment.infrastructure.object.trade.OrderCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    int countByExample(OrderCriteria example);

    int deleteByExample(OrderCriteria example);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExampleWithRowbounds(OrderCriteria example, RowBounds rowBounds);

    List<Order> selectByExample(OrderCriteria example);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderCriteria example);
}