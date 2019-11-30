package com.mallcai.fulfillment.dc.mapper.dc;

import com.mallcai.fulfillment.dc.valueobject.dc.OrderInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * 调度中心订单记录，用来流程管控
 * @author bh.zhong
 * @date 2019/8/19 3:10 PM
 */
public interface OrderInfoMapper {

    /**
     * 插入订单详情信息
     *
     * @param orderInfoDO
     * @return
     */
    Integer insertOrderInfo(OrderInfoDO orderInfoDO);

    /**
     * 修改订单状态为取消
     * @param status
     * @param orderNo
     * @return
     */
    Integer updateOrderStatusCancle(@Param("status") Integer status,@Param("orderNo") String orderNo);

    /**
     * 查询订单信息
     * @param orderNo
     * @return
     */
    OrderInfoDO selectByOrderNo(@Param("orderNo") String orderNo);

}
