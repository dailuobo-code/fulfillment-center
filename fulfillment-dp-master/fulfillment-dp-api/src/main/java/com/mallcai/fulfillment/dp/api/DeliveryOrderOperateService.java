package com.mallcai.fulfillment.dp.api;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.request.MultiPkgModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.ModifyExpressDTO;
import com.mallcai.fulfillment.dp.api.request.OrderConfirmReceiveDTO;

/**
 * 发货操作类接口，将一些操作类的接口定义到此
 * @author bh.zhong
 * @date 2019/9/6 3:11 PM
 */
public interface DeliveryOrderOperateService {

    /**
     * 确认收货
     * @param orderConfirmReceiveDTO
     * @return
     */
    PlainResult<?> orderConfirmReceive(OrderConfirmReceiveDTO orderConfirmReceiveDTO);

    /**
     * 修改配送信息
     * @param modifyExpressDTO
     * @return
     */
    PlainResult<Boolean> modifyExpress(ModifyExpressDTO modifyExpressDTO);

    /**
     * 修改多包裹配送信息，全量替换原有的配送单
     * @param multiPkgModifyExpressDTO
     * @return
     */
    PlainResult<Boolean> modifyMultiPkgExpress(MultiPkgModifyExpressDTO multiPkgModifyExpressDTO);
}
