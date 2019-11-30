package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.mapper.TransOrderDOMapper;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.TransOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**z
 * @description:
 * @author: chentao
 * @create: 2019-08-23 19:10:39
 */
@Slf4j
@Repository
public class TransOrderAndPeOrderDao {

    @Autowired
    private TransOrderDOMapper transOrderDOMapper;

    @Autowired
    private OrderDOMapperExtend orderDOMapperExtend;

    @Transactional(value = "fpeTransactionManager", rollbackFor = Exception.class)
    public void insertTransOrderAndPeOrder(TransOrderDO transOrder, List<OrderDO> orders){

        if (transOrder == null || CollectionUtils.isEmpty(orders)){

            throw new BizException("交易订单,商品订单为空");
        }

        try {
            transOrderDOMapper.insertSelective(transOrder);
            orderDOMapperExtend.batchInsertSelective(orders);
        } catch (Exception e) {
            log.error("插入订单、交易订单数据异常", e);
            throw new BizException();
        }
    }
}
