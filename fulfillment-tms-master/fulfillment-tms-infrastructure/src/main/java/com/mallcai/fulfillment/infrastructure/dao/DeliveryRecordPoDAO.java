package com.mallcai.fulfillment.infrastructure.dao;

import com.mallcai.fulfillment.infrastructure.mapper.tms.DeliveryRecordPoMapper;
import com.mallcai.fulfillment.infrastructure.object.tms.DeliveryRecordPo;
import com.mallcai.manager.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: chentao
 * @create: 2019-11-19 00:02:31
 */
@Slf4j
@Repository
public class DeliveryRecordPoDAO {

    @Autowired
    private DeliveryRecordPoMapper deliveryRecordPoMapper;

    public void createDeliveryRecord(DeliveryRecordPo deliveryRecordPo){

        int count = deliveryRecordPoMapper.insertSelective(deliveryRecordPo);

        if (count < 1){

            throw new BizException();
        }
    }
}
