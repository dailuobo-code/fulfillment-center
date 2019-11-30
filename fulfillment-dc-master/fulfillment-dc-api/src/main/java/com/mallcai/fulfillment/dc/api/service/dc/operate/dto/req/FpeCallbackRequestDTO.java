package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import com.mallcai.commons.model.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 生产回调参数
 * @author bh.zhong
 * @date 2019/8/19 10:45 AM
 */
@Data
public class FpeCallbackRequestDTO extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 5998591554769116093L;
    /**
     * 接入来源
     */
    private String appSource;
    /**
     * 批次订单号
     */
    private String batchOrderNo;
    /**
     * 回传状态数据 (例：订单已下发库房，库房已发货，物流运输中)
     */
    private String statusMsg;

}
