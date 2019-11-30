package com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementWarnLogDO;

/**
 * SettlementWarnLogDAO
 * @author zhanghao
 * @date 2019-08-14 00:07:02
 */
public interface SettlementWarnLogDAO {

  /**
   * 删除.
   * @param id
   * @return
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新增.
   * @param record
   * @return
   */
  int insert(SettlementWarnLogDO record);

  /**
   * 新增.
   * @param record
   * @return
   */
  int insertSelective(SettlementWarnLogDO record);

  /**
   * 根据Id查询.
   * @param id
   * @return
   */
  SettlementWarnLogDO selectByPrimaryKey(Long id);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(SettlementWarnLogDO record);

  /**
   * 修改.
   * @param record
   * @return
   */
  int updateByPrimaryKey(SettlementWarnLogDO record);
}