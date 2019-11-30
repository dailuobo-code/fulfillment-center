package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/9/9 1:51 AM
 */
@Data
public class BathDeliveryOrderDTO implements Serializable {
    /**
     * 是否全部成功
     */
    private Boolean allSuccess;
    /**
     * 失败单号
     */
    private List<String> errorOrderNo;

}
