package com.mallcai.fulfillment.dc.dao.dc;

import com.mallcai.fulfillment.dc.common.utils.DateUtil;
import com.mallcai.fulfillment.dc.mapper.dc.OrderItemInfoMapper;
import com.mallcai.fulfillment.dc.mapper.dc.OrderItemInfoMapperExtend;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoCriteria;
import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 调度中心订单记录，用来流程管控
 *
 * @author bh.zhong
 * @date 2019/8/19 3:10 PM
 */
@Slf4j
@Repository
public class OrderItemInfoDAO {

  @Resource
  private OrderItemInfoMapper orderItemInfoMapper;
  @Resource
  private OrderItemInfoMapperExtend orderItemInfoMapperExtend;

  /**
   * 插入订单详情信息
   *
   * @param orders
   * @return
   */
  public int batchItemOrderInfo(List<OrderItemInfoDO> orders) {
    return orderItemInfoMapperExtend.batchItemOrderInfo(orders);
  }

  /**
   * 作废数据
   * @param cityId
   * @param warehouseId
   * @param forecastDate
   * @return
   */
  public int deleteByCondition(Integer cityId,Integer warehouseId, String forecastDate){
    Date pickupTime= DateUtil.parseDate(forecastDate);
    pickupTime=DateUtil.addDays(DateUtil.dayStart(pickupTime),1);
    String pickupTimeStr=DateUtil.formatDate(pickupTime);
     return orderItemInfoMapperExtend.deleteByCondition(cityId,warehouseId,pickupTimeStr);
  }

  /**
   * 根据列表删除
   * @param idList
   */
  public void cancelOrderItemInfoListById(List<Long> idList){
    orderItemInfoMapperExtend.deleteByIdList(idList);
  }

  /**
   * 插入单条数据
   * @param orderItemInfoDO
   */
  public void insert(OrderItemInfoDO orderItemInfoDO){
    orderItemInfoMapper.insert(orderItemInfoDO);
  }

  public int countSpecialByExample(OrderItemInfoCriteria orderItemInfoCriteria){
    return orderItemInfoMapper.countByExample(orderItemInfoCriteria);
  }

  /**
   * 根据条件查询
   * @param orderItemInfoCriteria
   * @return
   */
  public List<OrderItemInfoDO> selectOrderItemInfoListByExample(OrderItemInfoCriteria orderItemInfoCriteria){
    return orderItemInfoMapper.selectByExample(orderItemInfoCriteria);
  }

}
