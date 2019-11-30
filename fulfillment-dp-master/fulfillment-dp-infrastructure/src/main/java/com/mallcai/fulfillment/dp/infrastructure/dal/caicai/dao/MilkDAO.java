package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.SupplierOutstockDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.SupplierOutstockLogDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.MilkQueryParamDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 取奶
 * @author zhanghao
 * @date 2019-08-15 21:06:52
 */
public interface MilkDAO {

    /**
     * 查询取奶列表.
     * @param milkQueryParamDTO
     * @return
     */
    List<SupplierOutstockDO> queryMilkList(MilkQueryParamDTO milkQueryParamDTO);

    /**
     * 查询取奶列表总数.
     * @param milkQueryParamDTO
     * @return
     */
    Integer queryMilkListCount(MilkQueryParamDTO milkQueryParamDTO);

    /**
     * 统计取奶天数
     * @param orderIds
     * @param cityProductIds
     * @param endDate
     * @return
     */
    List<SupplierOutstockDO> queryOrderAndProductCountList(@Param("orderIds") List<String> orderIds, @Param("cityProductIds") List<Integer> cityProductIds,@Param("endDate") Date endDate);

    /**
     * 根据ID查询数据.
     * @param id
     * @return
     */
    SupplierOutstockDO queryMilkById(@Param("id") Integer id);

    /**
     * 还瓶.
     * @param id
     * @param userId
     * @param returnNum
     */
    void pickup(@Param("id") Integer id,@Param("userId") Integer userId,@Param("returnNum") Integer returnNum);

    /**
     * 新增记录表.
     * @param sosl
     */
    void insertLog(SupplierOutstockLogDO sosl);
}
