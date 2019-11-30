package com.mallcai.fulfillment.infrastructure.mapper.erp;

import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrder;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderCriteria;
import com.mallcai.fulfillment.infrastructure.object.erp.OmsStandardOrderExtend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OmsStandardOrderMapperExtend {
    /**
     * 根据条件查询各城市的冻品总量
     * @param beginTime
     * @param endTime
     * @param cities
     * @param orderType
     * @return
     */
    List<OmsStandardOrderExtend> calFrozenTotalNumByCondition(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
                                                @Param("cities") List<Integer> cities, @Param("orderType") Integer orderType);

    /**
     * 根据条件查询各城市的生鲜分拣总量
     * @param beginTime
     * @param endTime
     * @param cities
     * @param orderType
     * @return
     */
    List<OmsStandardOrderExtend> calFreshSortingTotalNumByCondition(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
                                                              @Param("cities") List<Integer> cities, @Param("orderType") Integer orderType);

    /**
     *
     * @param omsStandardOrderCriteria 查询条件
     * @return
     */
    List<OmsStandardOrder> selectPageByExample(@Param("omsStandardOrderCriteria") OmsStandardOrderCriteria omsStandardOrderCriteria,
                                               @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}