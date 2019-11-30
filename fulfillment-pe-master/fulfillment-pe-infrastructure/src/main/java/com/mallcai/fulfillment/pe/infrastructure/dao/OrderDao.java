package com.mallcai.fulfillment.pe.infrastructure.dao;

import com.mallcai.fulfillment.pe.common.constants.Constants;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.utils.AssertUtils;
import com.mallcai.fulfillment.pe.common.utils.DateUtil;
import com.mallcai.fulfillment.pe.common.utils.DateUtils;
import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import com.mallcai.fulfillment.pe.infrastructure.enums.OrderStatusEnum;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapper;
import com.mallcai.fulfillment.pe.infrastructure.mapper.OrderDOMapperExtend;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDO;
import com.mallcai.fulfillment.pe.infrastructure.valueobject.OrderDOCriteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author wangjingcheng
 * 订单dao
 */
@Slf4j
@Repository
public class OrderDao {

    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private OrderDOMapperExtend orderDOMapperExtend;

    /**
     * 批量插入pe_order
     * @param orders
     * @return
     */
    public int batchInsertSelective(List<OrderDO> orders){
        return orderDOMapperExtend.batchInsertSelective(orders);
    }



    /**
     * 根据交易订单查所有商品订单
     * @param transOrderId : 交易订单ID
     * @return
     */
    public List<OrderDO> selectByTransOrderId(String transOrderId){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria()
                .andTransOrderIdEqualTo(transOrderId).andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus());

        return orderDOMapper.selectByExample(orderDOCriteria);
    }

    /**
     * 根据预期推单时间查询商品订单，时间参数左闭右开
     * @param startTime
     * @param endTime
     * @return
     */
    public List<OrderDO> selectByExpectPushTime(Date startTime, Date endTime){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria().andExpectPushTimeGreaterThanOrEqualTo(startTime)
                .andExpectPushTimeLessThan(endTime).andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus());

        return orderDOMapper.selectByExample(orderDOCriteria);
    }

    /**
     * 根据预期推单时间、订单暂存时间查询需要聚合的商品订单，时间参数左闭右开
     * @param startTime
     * @param endTime
     * @param orderHoldMins （可选） 订单暂存时间，订单在生产保留多久后再积单、推单。
     *             根据order表的createTime计算，单位分钟
     * @param storeId （可选）
     * @return
     */
    public List<OrderDO> selectNeedAggregate(OrderQueryDto orderQueryDto) {

        AssertUtils.assertNotNull(orderQueryDto.getStartTime(), orderQueryDto.getEndTime());

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        OrderDOCriteria.Criteria criteria = orderDOCriteria.createCriteria();
        criteria.andExpectPushTimeGreaterThanOrEqualTo(orderQueryDto.getStartTime()).
                andExpectPushTimeLessThan(orderQueryDto.getEndTime()).
                andStatusEqualTo(OrderStatusEnum.INIT.getStatus());

        if (orderQueryDto.getOrderHoldMins() != null) {

            criteria.andCreateTimeLessThan(DateUtil.addMinutes(new Date(), -orderQueryDto.getOrderHoldMins()));
        }

        if (orderQueryDto.getStoreId() != null) {

            criteria.andStoreIdEqualTo(orderQueryDto.getStoreId());
        }

        if (orderQueryDto.getCityId() != null){

            criteria.andCityIdEqualTo(orderQueryDto.getCityId());
        }

        if (orderQueryDto.getGroupType() != null){

            criteria.andGroupTypeEqualTo(orderQueryDto.getGroupType());
        }

        if (orderQueryDto.getGroupValue() != null){

            criteria.andGroupValueEqualTo(orderQueryDto.getGroupValue());
        }

        if (orderQueryDto.getProduceWarehouseId() != null){

            criteria.andWarehouseIdEqualTo(orderQueryDto.getProduceWarehouseId());
        }

        if (orderQueryDto.getProductNo() != null){

            criteria.andProductNoEqualTo(orderQueryDto.getProductNo());
        }

        return orderDOMapper.selectByExample(orderDOCriteria);
    }

//    public List<OrderDO> selectNeedAggregateByPushTime(Date startTime, Date endTime){
//
//        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
//
//        orderDOCriteria.createCriteria().andExpectPushTimeGreaterThanOrEqualTo(startTime).
//                andExpectPushTimeLessThan(endTime).
//                andStatusEqualTo(OrderStatusEnum.INIT.getStatus());
//
//        return orderDOMapper.selectByExample(orderDOCriteria);
//    }



    public List<OrderDO> selectByIds(List<Long> ids){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria().andIdIn(ids).andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus());

        return orderDOMapper.selectByExample(orderDOCriteria);
    }

    public List<OrderDO> selectByIdsAndStatus(List<Long> ids, Byte status){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria().andIdIn(ids).andStatusEqualTo(status).andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus());

        return orderDOMapper.selectByExample(orderDOCriteria);
    }


    /**
     * 根据订单 推单时间 查询订单列表
     *
     * @return
     */
    public  List<OrderDO> selectByExample() {
        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
        OrderDOCriteria.Criteria  criteria = orderDOCriteria.createCriteria();
        criteria.andExpectPushTimeBetween(DateUtils.getCurrentDayEarlyMorning(), DateUtils.getCurrentDayLastTime()).andStatusNotEqualTo(OrderStatusEnum.DELETE.getStatus());
        orderDOCriteria.or(criteria);
        return orderDOMapper.selectByExample(orderDOCriteria);
    }

    /**
     * 根据主键ID及初始状态更新目标状态
     * @param id
     * @param targetStatus
     * @param underStatus
     * @return
     */
    public boolean updateStatusByIdUnderStatus(Long id, Byte targetStatus, Byte underStatus){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria().andIdEqualTo(id)
                .andStatusEqualTo(underStatus);

        OrderDO orderPO = new OrderDO();

        orderPO.setStatus(targetStatus);

        int updateCount = orderDOMapper.updateByExampleSelective(orderPO, orderDOCriteria);

        if (updateCount < 1){

            log.error("订单状态更新异常");
            return false;
        }

        return true;
    }

    /**
     * 根据主键ID及初始状态批量更新目标状态
     * @param ids
     * @param targetStatus
     * @param underStatus
     * @return
     */
    @Transactional(value = "fpeTransactionManager", rollbackFor = Exception.class)
    public void updateStatusByIdUnderStatus(List<Long> ids, Byte targetStatus, Byte underStatus){

        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();

        orderDOCriteria.createCriteria().andIdIn(ids)
                .andStatusEqualTo(underStatus);

        OrderDO orderPO = new OrderDO();

        orderPO.setStatus(targetStatus);

        int updateCount = 0;
        try {
            updateCount = orderDOMapper.updateByExampleSelective(orderPO, orderDOCriteria);
        } catch (Exception e) {

            log.error("更新订单状态异常,ids:{},targetStatus:{},underStatus:{}", StringUtils.join(ids, Constants.SYMBOL_COMMA), targetStatus, underStatus);
            throw new BizException();
        }

        if (updateCount != ids.size()){

            log.error("订单状态更新异常，应更新数:{},实际更新数:{}", ids.size(), updateCount);
            throw new BizException();
        }
    }

    public int countSpecialByExample(OrderDOCriteria orderDOCriteria){
        return orderDOMapper.countByExample(orderDOCriteria);
    }

    public void insert(OrderDO orderDO){
        orderDOMapper.insert(orderDO);
    }

    public List<OrderDO> selectSpecialByExample(OrderDOCriteria example){
        return orderDOMapper.selectByExample(example);
    }

    public int updateByExampleSelective(OrderDO record,OrderDOCriteria example){
      return orderDOMapper.updateByExampleSelective(record,example);
    }
}
