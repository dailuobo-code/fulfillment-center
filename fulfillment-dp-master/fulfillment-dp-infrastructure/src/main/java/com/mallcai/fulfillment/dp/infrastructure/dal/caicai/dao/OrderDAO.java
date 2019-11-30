package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;

import java.util.Date;
import java.util.List;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dto.OrderQueryParamDTO;
import com.mallcai.fulfillment.dp.infrastructure.dal.tradehis.dataobject.OrderHisDO;
import com.mallcai.fulfillment.dp.infrastructure.dto.TradeOrderQueryParamDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author yl
 * @description OrderCityDAO
 * @date 2019-06-19
 */
public interface OrderDAO {

    /**
     * 根据订单id查询订单信息.
     *
     * @param orderId
     * @return
     */
    OrderDO selectByOrderId(@Param("orderId") String orderId);

    /**
     * 新增订单
     *
     * @param orderDO
     * @return
     */
    int insert(OrderDO orderDO);

    /**
     * 修改订单
     *
     * @param orderDO
     * @return
     */
    int update(OrderDO orderDO);

    /**
     * 根据时间范围查询.
     *
     * @param timeStart
     * @param timeEnd
     * @param lastId
     * @param limitNum
     * @return
     */
    List<OrderDO> selectByCreateTimeRange(@Param("timeStart") Date timeStart,
                                          @Param("timeEnd") Date timeEnd, @Param("lastId") Integer lastId,
                                          @Param("limitNum") Integer limitNum);

    /**
     * 根据订单号删除订单.
     *
     * @param orderId
     * @return
     */
    int deleteByOrderId(@Param("orderId") String orderId);

    /**
     * setCharacter
     */
    void setCharacter();

    /**
     * 根据订单号及修改时间删除订单
     *
     * @param orderId
     * @param updateTime
     * @return
     */
    int delOrder(@Param("orderId") String orderId, @Param("updateTime") String updateTime);

    /**
     * 插入历史表.
     *
     * @param orderHisDO
     * @return
     */
    int insertByHisDb(OrderHisDO orderHisDO);

    /**
     * 获取用户订单.
     *
     * @param orderQueryParamDTO
     * @return
     */
    List<OrderDO> selectUserOrders(TradeOrderQueryParamDTO orderQueryParamDTO);

    /**
     * 查询多个订单.
     *
     * @param orderIdsStr
     * @return
     */
    List<OrderDO> selectByOrderIds(@Param("orderIdsStr") String orderIdsStr);

    /**
     * 根据条件查询订单.
     *
     * @param orderQueryParamDTO
     * @return
     */
    List<OrderDO> selectByOrderQueryParam(OrderQueryParamDTO orderQueryParamDTO);


    /**
     * 查询多个订单.
     *
     * @param orderIdList
     * @return
     */
    List<OrderDO> selectByOrderIdList(List<String> orderIdList);

    /**
     * 批量修改订单.
     *
     * @param list
     * @return
     */
    int updateOrder(@Param("list") List<OrderDO> list);

    /**
     * 修改订单.
     *
     * @param orderDoList
     */
    void updatePointOrderList(@Param("list") List<OrderDO> orderDoList);

    /**
     * 批量完成订单.
     *
     * @param orders
     */
    void completeOrderList(@Param("list") List<String> orders);

    /**
     * 订单状态锁.
     *
     * @param orderId
     */
    void lockOrderStatus(String orderId);

    /**
     * 修改订单.
     *
     * @param orderUpdate
     * @return
     */
    int updateByOrder(OrderDO orderUpdate);

    /**
     * 修改订单.
     *
     * @param orderDO
     * @return
     */
    int updateByOrderId(OrderDO orderDO);

    /**
     * 查询订单数量.
     *
     * @param orderQueryParamDTO
     * @return
     */
    Integer countByOrderQueryParam(OrderQueryParamDTO orderQueryParamDTO);

    /**
     * 修改订单.
     *
     * @param orderDO
     * @return
     */
    int updateByOrderIdSelective(OrderDO orderDO);

    /**
     * 查询订单数量.
     *
     * @param userId
     * @param timeStart
     * @param list
     * @return
     */
    int countByStatusAndCreateTime(@Param("userId") Integer userId,
                                   @Param("timeStart") Date timeStart,
                                   @Param("list") List<Integer> list);

    /**
     * 查询订单数量.
     *
     * @param userId
     * @param completeTime
     * @return
     */
    int countFinishOrEvaluateByCompleteTime(@Param("userId") Integer userId,
                                            @Param("completeTime") Date completeTime);

    /**
     * 查询订单.
     *
     * @return
     */
    @Deprecated
    OrderDO selectSingle4Test();

    /**
     * 查询订单.
     *
     * @param queryParamDTO
     * @return
     */
    List<OrderDO> selectOrder4Invoice(OrderQueryParamDTO queryParamDTO);
}
