package com.mallcai.fulfillment.dp.infrastructure.dal.dp.dao;

import com.mallcai.fulfillment.dp.infrastructure.dal.dp.dataobject.DistOrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 配送单DAO
 * @author bh.zhong
 * @date 2019/9/8 8:00 PM
 */
public interface DistOrderDAO {

    /**
     * 批量插入配送单信息
     *
     * @param orders
     * @return
     */
    int batchInsertDistOrder(List<DistOrderDO> orders);
    /**
     * 插入配送单详情信息
     *
     * @param distOrderDO
     * @return
     */
    Integer insertDistOrder(DistOrderDO distOrderDO);

    /**
     * 修改配送单状态
     * @param status       待修改状态
     * @param preStatus    上一状态
     * @param orderNo      订单号
     * @return
     */
    Integer updateDistOrderStatus(@Param("status") Integer status,@Param("preStatus") Integer preStatus, @Param("orderNo") String orderNo);

    /**
     * 修改收货状态
     * @param confirmStatus
     * @param orderNo
     * @return
     */
    Integer updateConfirmStatus(@Param("confirmStatus") Integer confirmStatus, @Param("orderNo") String orderNo);

    /**
     * 修改承运公司
     * @param expressName
     * @param expressNo
     * @return
     */
    Integer updateExpressInfo(@Param("expressName") String expressName, @Param("expressNo") String expressNo,@Param("orderNo") String orderNo);

    /**
     * 查询配送单信息
     * @param orderNo
     * @return
     */
    List<DistOrderDO> selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 查询确认收货订单
     * @param confirmTime
     * @return
     */
    List<DistOrderDO> searchByCreateTime(@Param("confirmTime") String confirmTime);

    /**
     * 设置删除状态
     * @param orderNo
     * @return
     */
    Integer updateIsDeleted(@Param("orderNo") String orderNo);

    /**
     * 获取批量订单发货信息
     * @param orderNos
     * @return
     */
    List<DistOrderDO> selectByOrderNos(@Param("orderNos") List<String> orderNos);
}
