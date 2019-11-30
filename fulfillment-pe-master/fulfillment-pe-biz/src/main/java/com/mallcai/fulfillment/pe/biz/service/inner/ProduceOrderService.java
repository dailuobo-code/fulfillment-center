package com.mallcai.fulfillment.pe.biz.service.inner;

import com.mallcai.fulfillment.pe.biz.service.bo.DataBo;

/**
 * @description: 发货单业务接口
 * @author: chentao
 * @create: 2019-08-24 10:42:02
 */
public interface ProduceOrderService {

//    /**
//     * 创建发货单，根据推单起始、截止时间创建发货单，
//     *      * 时间参数左闭右开
//     * @param startTime
//     * @param endTime
//     * @param orderHoldMins（可选） 订单暂存时间，订单在生产保留多久后再积单、推单。
//     *       根据order表的createTime计算，单位分钟
//     */
//    void createProduceOrder(Date startTime, Date endTime, Integer orderHoldMins);

    /**
     * 创建发货单，根据推单起始、截止时间创建发货单。给测试用的
     * @param startTime
     * @param endTime
     * @param orderHoldMins（可选） 订单暂存时间，订单在生产保留多久后再积单、推单。
     *    根据order表的createTime计算，单位分钟
     * @param storeId 门店ID
     */
    void createProduceOrder(DataBo dataBo);
}
