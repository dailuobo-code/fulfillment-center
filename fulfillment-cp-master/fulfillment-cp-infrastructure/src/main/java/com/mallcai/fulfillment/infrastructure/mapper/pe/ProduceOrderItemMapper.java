package com.mallcai.fulfillment.infrastructure.mapper.pe;

import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrderItem;
import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrderItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProduceOrderItemMapper {
    int countByExample(ProduceOrderItemCriteria example);

    int deleteByExample(ProduceOrderItemCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ProduceOrderItem record);

    int insertSelective(ProduceOrderItem record);

    List<ProduceOrderItem> selectByExampleWithRowbounds(ProduceOrderItemCriteria example, RowBounds rowBounds);

    List<ProduceOrderItem> selectByExample(ProduceOrderItemCriteria example);

    ProduceOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProduceOrderItem record, @Param("example") ProduceOrderItemCriteria example);

    int updateByExample(@Param("record") ProduceOrderItem record, @Param("example") ProduceOrderItemCriteria example);

    int updateByPrimaryKeySelective(ProduceOrderItem record);

    int updateByPrimaryKey(ProduceOrderItem record);
}