package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SettlementLogDTO
 *
 * @author zhanghao
 * @date 2019-08-14 00:06:10
 */
@Data
public class SettlementLogDTO implements Serializable {

    private static final long serialVersionUID = 9155084723151980135L;
    private Long id;

    private String orderId;

    private Long userId;

    private String extra;

    private Integer status;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private Long version;

    private Integer source;

    private String requestData;

    private String deviceId;

}