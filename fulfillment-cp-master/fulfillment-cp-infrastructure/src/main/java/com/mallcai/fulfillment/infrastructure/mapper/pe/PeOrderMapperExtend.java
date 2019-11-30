package com.mallcai.fulfillment.infrastructure.mapper.pe;

import com.mallcai.fulfillment.infrastructure.object.pe.PeOrderExtend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PeOrderMapperExtend {
    /**
     * 根据条件查询各城市的冻品总量
     * @param expectPushTime
     * @param groupValue
     * @param cities
     * @param status
     * @return
     */
    List<PeOrderExtend> calFrozenTotalNumByCondition(@Param("expectPushTime") Date expectPushTime, @Param("groupValue") String groupValue,
                                               @Param("cities") List<Integer> cities, @Param("status") Byte status);

    /**
     * 根据条件查询各城市的生鲜分拣总量
     * @param expectPushTime
     * @param groupValue
     * @param cities
     * @param status
     * @return
     */
    List<PeOrderExtend> calFreshTotalNumByCondition(@Param("expectPushTime") Date expectPushTime, @Param("groupValue") String groupValue,
                                                     @Param("cities") List<Integer> cities, @Param("status") Byte status);
}