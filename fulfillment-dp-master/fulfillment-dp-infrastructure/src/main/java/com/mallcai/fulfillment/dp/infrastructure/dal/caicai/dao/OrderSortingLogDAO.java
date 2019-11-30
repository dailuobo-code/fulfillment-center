package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderSortingLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderSortingLogDAO
 * @author zhanghao
 * @date 2019年08月13日23:51:04
 */
public interface OrderSortingLogDAO {

  /**
   * 删除.
   * @param id
   * @return
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * 插入.
   * @param record
   * @return
   */
  int insert(OrderSortingLogDO record);

  /**
   * 根据Id查询.
   * @param id
   * @return
   */
  OrderSortingLogDO selectByPrimaryKey(Integer id);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(OrderSortingLogDO record);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKey(OrderSortingLogDO record);

  /**
   * 查询.
   * @param sortingList
   */
  void insertSelective4List(@Param("list") List sortingList);
}