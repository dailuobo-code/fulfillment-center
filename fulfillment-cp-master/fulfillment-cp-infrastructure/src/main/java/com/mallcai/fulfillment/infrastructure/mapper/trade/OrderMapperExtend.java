package com.mallcai.fulfillment.infrastructure.mapper.trade;

import com.mallcai.fulfillment.infrastructure.object.trade.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapperExtend {
    /**
     * 查询指定时间段，指定城市内的标品、预售和冻品
     * @param beginTime
     * @param endTime
     * @param deliveryMode
     * @param orderTypeList
     * @param frozenLikeStr
     * @param cityIdList
     * @return
     */
    int countByCondition(@Param("beginTime")Date beginTime, @Param("endTime")Date endTime, @Param("deliveryMode")Byte deliveryMode, @Param("orderTypeList")List<Byte> orderTypeList,
                         @Param("frozenLikeStr")String frozenLikeStr, @Param("cityIdList")List<Integer> cityIdList,@Param("status")int status,@Param("asyncPayStatus")String asyncPayStatus);
    /**
     * 查询指定时间段，指定城市内的标品、预售和冻品
     * @param beginTime
     * @param endTime
     * @param deliveryMode
     * @param orderTypeList
     * @param frozenLikeStr
     * @param cityIdList
     * @param status
     * @param start
     * @param pageSize
     * @return
     */
    List<Order> getByCondition(@Param("beginTime")Date beginTime, @Param("endTime")Date endTime, @Param("deliveryMode")Byte deliveryMode, @Param("orderTypeList")List<Byte> orderTypeList,
                               @Param("frozenLikeStr")String frozenLikeStr, @Param("cityIdList")List<Integer> cityIdList,@Param("status")int status,@Param("asyncPayStatus")String asyncPayStatus,@Param("start")int start,@Param("pageSize")int pageSize);
}