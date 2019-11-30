package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-26 11:21:09
 */
@Repository
public class ProduceOrderAndOrderDao {

    @Autowired
    private ProduceOrderDao produceOrderDao;

    @Autowired
    private OrderDao orderDao;

    /**
     * 更新推单完成状态
     * @param produceOrderId
     * @param orderIds
     * @return
     */
    @Transactional(value = "fpeTransactionManager", rollbackFor = Exception.class)
    public void updateProduceOrderAndOrderStatusUnderStatus(Long produceOrderId, List<Long> orderIds, ProduceOrder produceOrder, Byte orderTargetStatus,
                                                            Byte produceOrderStatus, Byte orderUnderStatus){

        if (!produceOrderDao.updateByIdUnderStatus(produceOrderId, produceOrder, produceOrderStatus)){

            throw new BizException();
        }

        orderDao.updateStatusByIdUnderStatus(orderIds, orderTargetStatus, orderUnderStatus);
    }

}
