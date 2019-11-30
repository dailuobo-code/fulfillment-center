package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import com.mallcai.commons.model.request.BaseRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 统一接收信息
 * @author bh.zhong
 * @date 2019/8/14 11:41 AM
 */
@Data
public class ReceiveOrderRequestDTO extends BaseRequest implements Serializable {
    private static final long serialVersionUID = 5699809699998622609L;
    /**
     * 接入来源
     */
    private String appSource;
    /**
     * 批次订单号
     */
    private String batchOrderNo;
    /**
     * 门店信息
     */
    private List<StoreInfoDTO> storeInfoList;
    /**
     * 回传状态数据 (例：订单已下发库房，库房已发货，物流运输中)
     */
    private String statusMsg;
    /**
     * 数据内容
     */
    private String content;
    /**
     * 总数量
     */
    private Integer count;
}
