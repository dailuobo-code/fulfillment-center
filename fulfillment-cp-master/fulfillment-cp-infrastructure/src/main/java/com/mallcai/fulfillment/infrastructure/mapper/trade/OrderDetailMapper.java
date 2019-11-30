package com.mallcai.fulfillment.infrastructure.mapper.trade;

import com.mallcai.fulfillment.infrastructure.object.trade.OrderDetail;
import com.mallcai.fulfillment.infrastructure.object.trade.OrderDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderDetailMapper {
    int countByExample(OrderDetailCriteria example);

    int deleteByExample(OrderDetailCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExampleWithRowbounds(OrderDetailCriteria example, RowBounds rowBounds);

    List<OrderDetail> selectByExample(OrderDetailCriteria example);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailCriteria example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailCriteria example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}