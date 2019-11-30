package com.mallcai.fulfillment.gateway.biz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.backend.common.api.PlainResult;
import com.mallcai.framework.config.plugin.annotation.ConfigValue;
import com.mallcai.fulfillment.common.utils.PlainResultBuilder;
import com.mallcai.fulfillment.gateway.api.service.ExpressCompanyService;
import com.mallcai.fulfillment.dp.api.response.ExpressCompanyListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gaoguoming
 * @date 2019-11-11 11:40:17
 */
@Service("expressCompanyService")
@Slf4j
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

    @ConfigValue(key = "/app_fulfillment/common/express.company.list")
    private JSONObject expressJson;

    @Override
    public PlainResult<ExpressCompanyListDTO> searchExpressCompany() {
        ExpressCompanyListDTO expressCompanyListDTO = expressJson.toJavaObject(ExpressCompanyListDTO.class);
        if (expressCompanyListDTO == null) {
            log.error("物流公司信息未配置");
            expressCompanyListDTO = new ExpressCompanyListDTO();
        }
        return PlainResultBuilder.success(expressCompanyListDTO);
    }
}
