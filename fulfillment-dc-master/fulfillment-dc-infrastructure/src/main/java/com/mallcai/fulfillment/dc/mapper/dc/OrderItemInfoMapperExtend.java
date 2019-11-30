package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderItemInfoDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 调度中心订单记录，用来流程管控
 *
 * @author bh.zhong
 * @date 2019/8/19 3:10 PM
 */
public interface OrderItemInfoMapperExtend {

  /**
   * 插入订单详情信息
   *
   * @param orders
   * @return
   */
  int batchItemOrderInfo(List<OrderItemInfoDO> orders);

  /**
   * 根据ID列表删除
   *
   * @param idList
   */
  void deleteByIdList(@Param("idList") List<Long> idList);

  /**
   * 根据条件删除
   * @param cityId
   * @param warehouseId
   * @param pickupTime
   */
  int deleteByCondition(@Param("cityId") Integer cityId,@Param("warehouseId") Integer warehouseId, @Param("pickupTime") String pickupTime);

}
