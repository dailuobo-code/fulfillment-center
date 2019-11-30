package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.UserPickupChangeErrorLogDO;

/**
 * UserPickupChangeErrorLogDAO
 * @author zhanghao
 * @date 2019-08-14 00:20:47
 */
public interface UserPickupChangeErrorLogDAO {
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
    int insert(UserPickupChangeErrorLogDO record);

    /**
     * 新增.
     * @param record
     * @return
     */
    int insertSelective(UserPickupChangeErrorLogDO record);

    /**
     * 查询.
     * @param id
     * @return
     */
    UserPickupChangeErrorLogDO selectByPrimaryKey(Integer id);

    /**
     * 修改.
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserPickupChangeErrorLogDO record);

    /**
     * 修改.
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserPickupChangeErrorLogDO record);
}