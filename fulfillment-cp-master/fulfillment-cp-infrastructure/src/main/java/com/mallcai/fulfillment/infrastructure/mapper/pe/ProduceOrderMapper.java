package com.mallcai.fulfillment.infrastructure.mapper.pe;

import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrder;
import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProduceOrderMapper {
    int countByExample(ProduceOrderCriteria example);

    int deleteByExample(ProduceOrderCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ProduceOrder record);

    int insertSelective(ProduceOrder record);

    List<ProduceOrder> selectByExampleWithRowbounds(ProduceOrderCriteria example, RowBounds rowBounds);

    List<ProduceOrder> selectByExample(ProduceOrderCriteria example);

    ProduceOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProduceOrder record, @Param("example") ProduceOrderCriteria example);

    int updateByExample(@Param("record") ProduceOrder record, @Param("example") ProduceOrderCriteria example);

    int updateByPrimaryKeySelective(ProduceOrder record);

    int updateByPrimaryKey(ProduceOrder record);
}