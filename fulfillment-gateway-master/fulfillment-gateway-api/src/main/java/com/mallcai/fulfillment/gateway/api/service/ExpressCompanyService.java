package com.mallcai.fulfillment.gateway.api.service;

import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.fulfillment.dp.api.response.ExpressCompanyListDTO;

/**
 * @author gaoguoming
 * @date 2019-11-11 11:39:55
 */
public interface ExpressCompanyService {
    PlainResult<ExpressCompanyListDTO> searchExpressCompany();
}
