package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * TradePackageDetailDTO
 *
 * @author zhanghao
 * @date 2019-08-14 00:20:26
 */
@Data
public class TradePackageDetailDTO implements Serializable {

    private static final long serialVersionUID = 7441797131228971766L;
    private Integer id;

    private String packageNo;

    private Integer productNo;

    private Integer productNum;

    private Integer createUser;

    private Date createTime;

    private String orderId;

    private String productShowName;

}