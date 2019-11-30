package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-11-08 15:27:55
 */
@Data
public class BatchDeliveryResultDTO implements Serializable {

    private static final long serialVersionUID = 4694670763632997838L;

    /**
     * 是否全部成功
     */
    private Boolean allSuccess;
    /**
     * 失败单号
     */
    private List<ErrorOrderInfo> errorOrderInfos;
}
