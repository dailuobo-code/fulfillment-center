package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;


import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.PickupLogDO;

/**
 * PickupLogDAO
 * @author zhanghao
 * @date 2019-08-13 23:53:46
 */
public interface PickupLogDAO {

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
  int insert(PickupLogDO record);

  /**
   * 新增
   * @param record
   * @return
   */
  int insertSelective(PickupLogDO record);

  /**
   * 查询.
   * @param id
   * @return
   */
  PickupLogDO selectByPrimaryKey(Integer id);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(PickupLogDO record);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKey(PickupLogDO record);


}