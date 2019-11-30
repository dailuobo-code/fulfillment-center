package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderItemDOMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderItemDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-11-16 17:02:05
 */
@Slf4j
@Repository
public class ProduceOrderItemDao {

    @Autowired
    private ProduceOrderItemDOMapper produceOrderItemDOMapper;

    public int batchInsertSelective(List<ProduceOrderItemDO> produceOrderItemDOS) {
        return produceOrderItemDOMapper.batchInsertSelective(produceOrderItemDOS);
    }
}
