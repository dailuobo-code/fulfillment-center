package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.infrastructure.mapper.TransOrderDOMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDOCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author wangjingcheng
 */
@Repository
public class TransOrderDao {

    @Autowired
    private TransOrderDOMapper transOrderDOMapper;

    public List<TransOrderDO> selectByTransOrderId(String transOrderId){

        TransOrderDOCriteria transOrderDOCriteria = new TransOrderDOCriteria();

        transOrderDOCriteria.createCriteria().andTransOrderIdEqualTo(transOrderId);

        return transOrderDOMapper.selectByExample(transOrderDOCriteria);
    }
}
