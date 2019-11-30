package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderPackageDetailDO;

import java.util.List;

/**
 * 结算dao
 * @author zhanghao
 * @date 2019-08-13 23:49:23
 */
public interface OrderPackageDetailDAO {

  /**
   * 删除.
   * @param id
   * @return
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * 新增.
   * @param record
   * @return
   */
  int insert(OrderPackageDetailDO record);

  /**
   * 新增.
   * @param record
   * @return
   */
  int insertSelective(OrderPackageDetailDO record);

  /**
   * 根据Id查询.
   * @param id
   * @return
   */
  OrderPackageDetailDO selectByPrimaryKey(Integer id);

  /**
   * 修改记录.
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(OrderPackageDetailDO record);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKey(OrderPackageDetailDO record);

  /**
   * 根据订单查询package.
   * @param orderId
   * @return
   */
  List<OrderPackageDetailDO> selectByOrderId(String orderId);
}