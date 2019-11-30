package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailJsDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailSalesDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.tradehis.dataobject.OrderDetailJsHisDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @description OrderDetailJsCityDAO
 * @date 2019-06-19
 */
public interface OrderDetailJsDAO {

    /**
     * 根据OrderId查询.
     *
     * @param orderId
     * @return
     */
    List<OrderDetailJsDO> selectByOrderId(@Param("orderId") String orderId);

    /**
     * 根据ORderId查询数量.
     *
     * @param orderId
     * @return
     */
    int countByOrderId(@Param("orderId") String orderId);

    /**
     * 批量插入.
     *
     * @param list
     * @return
     */
    int insertList(List<OrderDetailJsDO> list);

    /**
     * 批量修改.
     *
     * @param orderDetailJsDoList
     * @return
     */
    int updateList(List<OrderDetailJsDO> orderDetailJsDoList);

    /**
     * 删除.
     *
     * @param orderId
     * @return
     */
    int deleteByOrderId(@Param("orderId") String orderId);

    /**
     * 插入.
     *
     * @param orderDetailJsHisDO
     * @return
     */
    int insertByHisDb(OrderDetailJsHisDO orderDetailJsHisDO);

    /**
     * 查询.
     *
     * @param cityProductIds
     * @param startDate
     * @param endDate
     * @return
     */
    List<OrderDetailSalesDO> selectSalesAmount(@Param("cityProductIds") List<Long> cityProductIds,
                                               @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 保存.
     *
     * @param list
     * @return
     */
    int saveOrderDetailjs(@Param("list") List<OrderDetailJsDO> list);

    /**
     * 保存.
     *
     * @param pointList
     * @return
     */
    int saveOrderDetailjs4Point(@Param("list") List<OrderDetailJsDO> pointList);

    /**
     * 查询多个订单.
     *
     * @param orderIds
     * @return
     */
    List<String> selectByOrderIdList(@Param("orderIds") List<String> orderIds);

    /**
     * 查询数量.
     *
     * @param orderId
     * @param cityProductId
     * @return
     */
    Integer countByOrderIdAndProductId(@Param("orderId") String orderId,
                                       @Param("cityProductId") Integer cityProductId);

    /**
     * 根据orderIdList查询强结订单.
     *
     * @param orderIdList
     * @return
     */
    List<String> queryForcedSettlemenOrderList(@Param("orderIdList") List<String> orderIdList);
}
