package com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderItemDO;

import java.util.List;

/**
 * 配送明细DAO
 * @author bh.zhong
 * @date 2019/9/8 8:00 PM
 */
public interface DistOrderItemDAO {

    /**
     * 批量插入配送单明细
     *
     * @param orders
     * @return
     */
    int batchInsertDistOrderItem(List<DistOrderItemDO> orders);

}
