package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderDetailMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.mapper.ProduceOrderMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrder;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.ProduceOrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: chentao
 * @create: 2019-08-23 20:16:17
 */
@Slf4j
@Repository
public class ProduceOrderAndDetailDao {

    @Autowired
    private ProduceOrderMapper produceOrderMapper;

    @Autowired
    private ProduceOrderDetailMapperExtend produceOrderDetailMapperExtend;

    @Autowired
    private OrderDao orderDao;

    @Transactional(value = "fpeTransactionManager", rollbackFor = Exception.class)
    public void insertProduceOrderAndDetailAndUpdateOrder(ProduceOrder produceOrder, List<ProduceOrderDetail> produceOrderDetails, List<Long> orderIds){

        if (produceOrder == null || CollectionUtils.isEmpty(produceOrderDetails)){

            throw new BizException();
        }

        try {
            produceOrderMapper.insertSelective(produceOrder);
            for (ProduceOrderDetail produceOrderDetail : produceOrderDetails){

                produceOrderDetail.setProduceOrderId(produceOrder.getId());
            }

            produceOrderDetailMapperExtend.batchInsertSelective(produceOrderDetails);

            // 更新订单状态为积单完成
            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_COMPLETE.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
        } catch (Exception e) {

            log.error("生产单数据插入异常", e);
            throw new BizException();
        }
    }


    @Transactional(value = "fpeOrderManager", rollbackFor = Exception.class)
    public void insertOrder(ProduceOrder produceOrder, List<ProduceOrderDetail> produceOrderDetails){

        if (produceOrder == null || CollectionUtils.isEmpty(produceOrderDetails)){
          return;
        }
        produceOrderMapper.insertSelective(produceOrder);
        for (ProduceOrderDetail produceOrderDetail : produceOrderDetails){
            produceOrderDetail.setProduceOrderId(produceOrder.getId());
        }
        produceOrderDetailMapperExtend.batchInsertSelective(produceOrderDetails);

    }

//    @Transactional(value = "fpeTransactionManager", rollbackFor = Exception.class)
//    public void batchInsertProOrder(List<ProduceOrder> produceOrder){
//
//            produceOrderMapper.
//            for (ProduceOrderDetail produceOrderDetail : produceOrderDetails){
//
//                produceOrderDetail.setProduceOrderId(produceOrder.getId());
//            }
//
//            produceOrderDetailMapperExtend.batchInsertSelective(produceOrderDetails);
//
//            // 更新订单状态为积单完成
//            orderDao.updateStatusByIdUnderStatus(orderIds, OrderStatusEnum.AGGREGATE_COMPLETE.getStatus(), OrderStatusEnum.AGGREGATE_PROCESS.getStatus());
//
//    }
}
