package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDetailSalesDO;
import com.mallcai.fulfillment.dp.infrastructure.dal.tradehis.dataobject.OrderDetailHisDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @description OrderDetailCityDAO
 * @date 2019-06-19
 */
public interface OrderDetailDAO {

  /**
   * 根据orderId查询.
   * @param orderId
   * @return
   */
  List<OrderDetailDO> selectByOrderId(@Param("orderId") String orderId);

  /**
   * 批量插入.
   * @param list
   * @return
   */
  int insertList(List<OrderDetailDO> list);

  /**
   * 批量修改.
   * @param orderDetailDoList
   * @return
   */
  int updateList(List<OrderDetailDO> orderDetailDoList);

  /**
   * 删除.
   * @param orderId
   * @return
   */
  int deleteByOrderId(@Param("orderId") String orderId);

  /**
   * 写DB.
   * @param orderDetailHisDO
   * @return
   */
  int insertByHisDb(OrderDetailHisDO orderDetailHisDO);

  /**
   * 查询.
   * @param merchantId
   * @param startDate
   * @param endDate
   * @return
   */
  List<OrderDetailDO> selectTotalByMerchantId(@Param("merchantId") Long merchantId,
                                              @Param("startDate") Date startDate, @Param("endDate") Date endDate);

  /**
   * 查询.
   * @param cityProductIds
   * @param startDate
   * @param endDate
   * @return
   */
  List<OrderDetailSalesDO> selectSalesByProductId(
          @Param("cityProductIds") List<Long> cityProductIds,
          @Param("startDate") Date startDate, @Param("endDate") Date endDate);

  /**
   * 查询.
   * @param cityProductIds
   * @param startDate
   * @param endDate
   * @return
   */
  List<OrderDetailSalesDO> selectSalesAmount(@Param("cityProductIds") List<Long> cityProductIds,
                                             @Param("startDate") Date startDate, @Param("endDate") Date endDate);

  /**
   * 查询.
   * @param orderId
   * @param cityProductId
   * @return
   */
  OrderDetailDO selectOrderDetail(@Param("orderId") String orderId,
                                  @Param("cityProductId") Integer cityProductId);


  /**
   * 根据订单ID查询商品详情.
   * @param orderIdList
   * @return
   */
  List<OrderDetailDO> selectDetailByOrderIdList(@Param("orderIdList")List<String> orderIdList);
}
