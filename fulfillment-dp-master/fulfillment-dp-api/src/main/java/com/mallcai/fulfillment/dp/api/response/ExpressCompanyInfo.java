package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaoguoming
 * @date 2019-11-05 10:37:14
 */
@Data
public class ExpressCompanyInfo implements Serializable {

    private static final long serialVersionUID = 2945762351574449868L;

    /**
     * 物流公司编号
     */
    private String expressCompanyNo;

    /**
     * 物流公司名称
     */
    private String expressCompanyName;
}