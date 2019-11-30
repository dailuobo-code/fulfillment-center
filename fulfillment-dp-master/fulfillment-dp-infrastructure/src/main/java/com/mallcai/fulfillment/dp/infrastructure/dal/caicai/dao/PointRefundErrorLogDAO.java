package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.PointRefundErrorLogDO;

/**
 * PointRefundErrorLogDAO
 * @author zhanghao
 * @date 2019-08-13 23:55:12
 */
public interface PointRefundErrorLogDAO {
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
    int insert(PointRefundErrorLogDO record);

    /**
     * 新增.
     * @param record
     * @return
     */
    int insertSelective(PointRefundErrorLogDO record);

    /**
     * 查询.
     * @param id
     * @return
     */
    PointRefundErrorLogDO selectByPrimaryKey(Integer id);

    /**
     * 修改.
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PointRefundErrorLogDO record);

    /**
     * 修改.
     * @param record
     * @return
     */
    int updateByPrimaryKey(PointRefundErrorLogDO record);
}