package com.mallcai.fulfillment.dp.infrastructure.dal.trade.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dataobject.SettlementLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.trade.dto.SettlementQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * SettlementLogDAO
 *
 * @author zhanghao
 * @date 2019-08-14 00:03:47
 */
public interface SettlementLogDAO {

    /**
     * 删除.
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增.
     *
     * @param record
     * @return
     */
    int insert(SettlementLogDO record);

    /**
     * 新增.
     *
     * @param record
     * @return
     */
    int insertSelective(SettlementLogDO record);

    /**
     * 根据ID查询.
     *
     * @param id
     * @return
     */
    SettlementLogDO selectByPrimaryKey(Long id);

    /**
     * 修改.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SettlementLogDO record);

    /**
     * 修改.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(SettlementLogDO record);

    /**
     * 根据OrderId查询log.
     *
     * @param orderId
     * @return
     */
    SettlementLogDO selectByOrderId(String orderId);

    /**
     * 修改.
     *
     * @param settlementLogDO
     * @return
     */
    int updateBySettlement(SettlementLogDO settlementLogDO);

    /**
     * 根据typeList查询logList.
     *
     * @param typeList
     * @param startDate
     * @return
     */
    List<SettlementLogDO> selectByType(@Param("list") List<Integer> typeList,
                                       @Param("startDate") Date startDate);

    /**
     * 查询记录表.
     * @param settlementQueryDTO
     * @return
     */
    List<SettlementLogDO> querySettlementList(SettlementQueryDTO settlementQueryDTO);
}