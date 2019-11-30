package com.mallcai.fulfillment.pe.infrastructure.mapper;

import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderItemDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderItemDOCriteria;
import java.util.List;

public interface ProduceOrderItemDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProduceOrderItemDO record);

    int insertSelective(ProduceOrderItemDO record);

    List<ProduceOrderItemDO> selectByExample(ProduceOrderItemDOCriteria oredCriteria);

    ProduceOrderItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProduceOrderItemDO record);

    int updateByPrimaryKey(ProduceOrderItemDO record);

    int batchInsertSelective(List<ProduceOrderItemDO> produceOrderItemDOS);
}