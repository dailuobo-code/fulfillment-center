package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * SupplierOutstockLog
 *
 * @author zhanghao
 * @date 2019-08-16 15:30:16
 */
@Data
public class SupplierOutstockLogDO implements Serializable {

    private static final long serialVersionUID = -632487280902329673L;
    private Integer id;
    private Integer userId;
    private String orderId;
    private String productNo;
    private Integer module;
    private String modifyBefore;
    private String modifyAfter;
    private String remarks;
    private String createTime;

}
