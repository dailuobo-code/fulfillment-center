package com.mallcai.fulfillment.infrastructure.mapper.pe;

import com.mallcai.fulfillment.infrastructure.object.pe.ProduceOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProduceOrderMapperExtend {
    /**
     * 根据条件查询符合的生产单数
     * @param beginTime
     * @param endTime
     * @param groupValue
     * @param cityIdList
     * @param status
     * @return
     */
    int countByCondition(@Param("beginTime") Date beginTime, @Param("endTime")Date endTime,
                         @Param("groupValue")String groupValue, @Param("cityIdList")List<Integer> cityIdList, @Param("status")Byte status);

    /**
     * 根据条件查询符合的部分生产单
     * @param beginTime
     * @param endTime
     * @param groupValue
     * @param cityIdList
     * @param status
     * @param start
     * @param pageSize
     * @return
     */
    List<ProduceOrder> getByCondition(@Param("beginTime") Date beginTime, @Param("endTime")Date endTime,
                                        @Param("groupValue")String groupValue, @Param("cityIdList")List<Integer> cityIdList, @Param("status")Byte status,
                                        @Param("start")int start,@Param("pageSize")int pageSize);
}