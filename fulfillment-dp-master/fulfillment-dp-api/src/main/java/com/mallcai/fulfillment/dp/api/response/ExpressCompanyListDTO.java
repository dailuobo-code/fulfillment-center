package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaoguoming
 * @date 2019-11-04 17:45:00
 */
@Data
public class ExpressCompanyListDTO implements Serializable {
    private static final long serialVersionUID = 2583141408399952469L;
    /**
     * 快递公司列表
     */
    private List<ExpressCompanyInfo> expressCompanyList;
}