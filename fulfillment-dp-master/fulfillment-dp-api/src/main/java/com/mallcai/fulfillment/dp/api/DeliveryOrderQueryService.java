package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.BatchDeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.request.DeliveryOrderQueryDTO;
import com.mallcai.fulfillment.dp.api.response.BatchDistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderDTO;
import com.mallcai.fulfillment.dp.api.response.DistOrderMultiPkgDTO;
import com.mallcai.fulfillment.dp.api.response.ExpressCompanyListDTO;

/**
 * 查下接口
 * @author bh.zhong
 * @date 2019/9/18 3:10 PM
 */
public interface DeliveryOrderQueryService {
    /**
     * 根据订单查下快递信息
     * @param deliveryOrderQueryDTO
     * @return
     */
    PlainResult<DistOrderDTO> queryByOrderNo(DeliveryOrderQueryDTO deliveryOrderQueryDTO);

    /**
     * 支持多包裹查询快递信息接口
     * @param deliveryOrderQueryDTO
     * @return
     */
    PlainResult<DistOrderMultiPkgDTO> queryMultiPkgByOrderNo(DeliveryOrderQueryDTO deliveryOrderQueryDTO);

    /**
     * 支持多包裹查询快递信息接口
     * @param batchDeliveryOrderQueryDTO
     * @return
     */
    PlainResult<BatchDistOrderMultiPkgDTO> batchQueryMultiPkgByOrderNos(BatchDeliveryOrderQueryDTO batchDeliveryOrderQueryDTO);

    /**
     * 查询快递公司列表
     * @return
     */
    PlainResult<ExpressCompanyListDTO> searchExpressCompany();
}
