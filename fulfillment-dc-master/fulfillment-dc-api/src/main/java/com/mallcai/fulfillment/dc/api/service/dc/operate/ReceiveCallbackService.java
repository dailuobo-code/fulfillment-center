package com.mallcai.fulfillment.dc.api.service.dc.operate;

import com.mallcai.framework.common.util.plugin.api.response.PlainResult;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.FpeCallbackRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req.ReceiveOrderRequestDTO;
import com.mallcai.fulfillment.dc.api.service.dc.operate.dto.rsp.UnityReceiveResponseDTO;

/**
 * 类 {@code ReceiveCallbackService} 调度接收，门面层.
 *
 * <p> 主要包括 生产引擎,WMS,TMS,门店等系统回调
 * @author bh.zhong
 * @date 2019/8/14 11:28 AM
 */
public interface ReceiveCallbackService {

    /**
     * 生产回调
     * @param req 请求参数
     * @return 数据
     */
    PlainResult<UnityReceiveResponseDTO> fpeCallbackTest(ReceiveOrderRequestDTO req);

    /**
     * 生产平台回调
     * @param req 请求参数
     * @return 数据
     */
    PlainResult<?> fpeCallback(FpeCallbackRequestDTO req);

}
